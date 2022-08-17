<template>
  <div id="myVideo" v-if="props.publisher" class="flex items-center space-x-3">
    <button @click="shiftleft" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
      </svg>
    </button>
      <UserCam class="h-full max-h-[135px]" v-for="usr in props.userAll.slice(state.cnt*4, state.endIndex)" :isonMic="(props.onMic.indexOf(usr[4].stream.connection.connectionId)>=0)" :screenName="usr[1]" :isList="state.isList" @click="onclickuser(usr[4])" :key="usr[2]" :mainStreamManager="usr[4]"/>    
    <button @click="shiftright" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
      </svg>
    </button>
  </div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, reactive, computed, defineEmits, onMounted } from 'vue'
import * as faceapi from '@vladmandic/face-api'
import { useStore } from 'vuex'
import rct from '../api/rct'
import axios from 'axios'

const emit = defineEmits(["updateMainVideoStreamManager"])

const state = reactive({
  cnt: 0,
  endIndex : computed(() => calEndIndex()),
  userId: null,
  userName: "",
  takenImg: "",
  isList:true,
})

const props = defineProps({
  publisher: Object,
  userAll: Array,
  onMic: Array,
})

const onclickuser = (user) => {
  emit("updateMainVideoStreamManager",user)
}

const shiftleft = () => {
  let tmpCnt = state.cnt - 1

  if (tmpCnt < 0) tmpCnt = 0

  state.cnt = tmpCnt
}

const shiftright = () => {
  let tmpCnt = state.cnt + 1
  
  if (tmpCnt * 4 > props.userAll.length) {
    tmpCnt -= 1
  }

  state.cnt = tmpCnt
}

const calEndIndex = () => {
  let tmpEndIndex = state.cnt * 4 + 4

  if (tmpEndIndex > props.userAll.length) tmpEndIndex = props.userAll.length

  return tmpEndIndex
}


/////////////// Face Recognition Part

const store = useStore()

const getUserImage = async () => {
  await axios({
    url: rct.user.userinfo(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.userId = res.data.userId
    state.userName = res.data.name
    state.takenImg = store.state.user.userImgFirstUrl+res.data.userImg
  })
  .catch(err => {
    console.log(err)
  })
}


function loadLabeledImage() {
  const labels = [state.userName]
  return Promise.all(
    labels.map(async (label) => {
      const descriptions = []
      for (let i=1; i<=2; i++) {
        // let img = document.createElement('img')
        // img.src = state.takenImg
        const img = await faceapi.fetchImage(state.takenImg)
        const detections = await faceapi.detectSingleFace(img).withFaceLandmarks().withFaceDescriptor()
        descriptions.push(detections.descriptor)
      }
      
      console.log(label+' Faces Loaded | ')
      return new faceapi.LabeledFaceDescriptors(label, descriptions)
    })
  )
}

async function recognizeFaces(){


  console.log("here!!")
  const labeledDescriptor = await loadLabeledImage()
  console.log(labeledDescriptor)
  // 두번째 인자는 역치값: 높을수록 본인 인정 기준이 널널해짐
  const faceMatcher = new faceapi.FaceMatcher(labeledDescriptor, 0.4)


  props.publisher.on("streamPlaying", event => {
    const video = props.publisher.videos[0]
    const videoWidth = props.publisher.videos[0].video.clientWidth
    const videoHeight = props.publisher.videos[0].video.clientHeight

    const canvas = faceapi.createCanvasFromMedia(video.video)
    canvas.setAttribute('style', 'position: absolute;')
    document.getElementById('local-video-undefined').before(canvas)

    const displaySize = {width: videoWidth, height: videoHeight}
    faceapi.matchDimensions(canvas, displaySize)

    setInterval(async ()=> {
      const detections = await faceapi.detectAllFaces(video.video).withFaceLandmarks().withFaceDescriptors()
      const resizedDetections = faceapi.resizeResults(detections, displaySize)

      canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
      // result는 distance(거리)와 label(인식한 사람 이름) 두개의 요소로 구성.
      // distance가 특정 값 이하면 타이머를 작동시키는 방향으로 활용 가능할 것 같아요~
      // distance가 낮을수록 본인이 올린 사진과 일치한다는 의미
      const results = resizedDetections.map((d) => {
        return faceMatcher.findBestMatch(d.descriptor)
      })
      results.forEach((result, i) => {
        const box = resizedDetections[i].detection.box
      const drawBox = new faceapi.draw.DrawBox(box, { label: result.toString() })
      console.log(result.toString())
      drawBox.draw(canvas)
      })
      // faceapi.draw.drawDetections(canvas, resizedDetections)
    })
},100)

}

getUserImage()

Promise.all([
    faceapi.nets.ssdMobilenetv1.loadFromUri("/model"),
    faceapi.nets.faceLandmark68Net.loadFromUri("/model"),
    faceapi.nets.faceRecognitionNet.loadFromUri("/model"),
]).then(() => {
  console.log("success!")
  recognizeFaces()
})




  
</script>

<style>

</style>