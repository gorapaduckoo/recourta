<template>
  <div v-if="props.publisher" class="flex items-center space-x-3">
      <UserCam class="h-full w-[480px] relative" :isonMic="false" :screenName="null" :isList="true" :mainStreamManager="props.publisher"/>    
  </div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, reactive, defineEmits } from 'vue'
import * as faceapi from '@vladmandic/face-api'
import { useStore } from 'vuex'
import rct from '../api/rct'
import axios from 'axios'

const emit = defineEmits(["finishLoading","changeFacecount"])

const state = reactive({
  userId: null,
  takenImg: "",
})

const props = defineProps({
  publisher: Object,
  isLecturer: Boolean,
  facecount: Number,
})

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
    state.takenImg = store.state.user.userImgFirstUrl+res.data.userImg
  })
  .catch(err => {
    console.log(err)
  })
}

function loadLabeledImage() {
  const labels = [store.state.user.userId]
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
      
      // console.log(label+' Faces Loaded | ')
      return new faceapi.LabeledFaceDescriptors(label, descriptions)
    })
  )
}

async function recognizeFaces(){
  // console.log("here!!")
  const labeledDescriptor = await loadLabeledImage()
  // console.log(labeledDescriptor)
  // 두번째 인자는 역치값: 높을수록 본인 인정 기준이 널널해짐
  const faceMatcher = new faceapi.FaceMatcher(labeledDescriptor, 0.4)
  setTimeout(()=>{ 
  },4000)
  props.publisher.on("streamPlaying", event => {
    // console.log("stream")
    
    const video = props.publisher.videos[0]
    const videoWidth = props.publisher.videos[0].video.clientWidth
    const videoHeight = props.publisher.videos[0].video.clientHeight

    const canvas = faceapi.createCanvasFromMedia(video.video)
    canvas.setAttribute('style', 'position: absolute;')
    document.getElementById('local-video-undefined').before(canvas)

    const displaySize = {width: videoWidth, height: videoHeight}
    faceapi.matchDimensions(canvas, displaySize)

    setInterval(async ()=> {
      if(props.publisher.stream.typeOFVideo==="SCREEN") emit("changeFacecount",0)
      else{
        const detections = await faceapi.detectAllFaces(video.video).withFaceLandmarks().withFaceDescriptors()
        const resizedDetections = faceapi.resizeResults(detections, displaySize)

        canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
        // result는 distance(거리)와 label(인식한 사람 이름) 두개의 요소로 구성.
        // distance가 특정 값 이하면 타이머를 작동시키는 방향으로 활용 가능할 것 같아요~
        // distance가 낮을수록 본인이 올린 사진과 일치한다는 의미
        const results = resizedDetections.map((d) => {
          return faceMatcher.findBestMatch(d.descriptor)
        })
        let dist = 1
        for(let res of results){
          // 이것을 점수 중 최소 값을로 이용하여 나타내자
          dist = (dist<res.distance)?dist:res.distance
        }
        if(dist>0.45) emit("changeFacecount",1)
        else emit("changeFacecount",0)
      }
      // results.forEach((result, i) => {
      //   const box = resizedDetections[i].detection.box
      //   const drawBox = new faceapi.draw.DrawBox(box, { label: result.toString() })
      //   console.log(result.toString())
      //   drawBox.draw(canvas)
      // })
      // faceapi.draw.drawDetections(canvas, resizedDetections)
    },1000)
  })
  emit("finishLoading",null)
}

if(!props.isLecturer){
  getUserImage()
  Promise.all([
    faceapi.nets.ssdMobilenetv1.loadFromUri("/model"),
    faceapi.nets.faceLandmark68Net.loadFromUri("/model"),
    faceapi.nets.faceRecognitionNet.loadFromUri("/model"),
  ]).then(() => {
    // console.log("success!")
    recognizeFaces()
  })
}
  
</script>

<style>

</style>