<template>
  <div id="myVideo" v-if="props.publisher" class="flex items-center space-x-3">
    <button @click="shiftleft" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
      </svg>
    </button>
      <UserCam class="h-full max-h-[135px]" v-for="usr in props.userAll.slice(state.cnt*4, state.endIndex)" :key="usr.stream.connection.connectionId" :mainStreamManager="usr"/>    
    <button @click="shiftright" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
      </svg>
    </button>
  </div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, reactive, computed, watch } from 'vue'
import * as faceapi from '@vladmandic/face-api'
import { useStore } from 'vuex'
import rct from '../api/rct'

const state = reactive({
  cnt: 0,
  endIndex : computed(() => calEndIndex()),
  userId: null,
  userName: "",
  takenImg: "",

})

const props = defineProps({
  publisher: Object,
  userAll: Array,
})

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
let FaceMatcher = null

let input = document.createElement("img")

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
    input.src = state.takenImg
  })
  .catch(err => {
    console.log(err)
  })
}


function loadLabeledImage() {
  const label = state.userName
  return Promise.all(
    async () => {
      const img = faceapi.fetchImage(state.takenImg)
      const detection = await faceapi.detectSingleFace(img).withFaceLandmarks().withFaceDescriptor()
      console.log(JSON.stringify(detection))
      const description = detection.descriptor
      
      return new faceapi.LabeledFaceDescriptors(label, description)
    }
  )
}


Promise.all([
    faceapi.nets.ssdMobilenetv1.loadFromUri("/model"),
    faceapi.nets.tinyFaceDetector.loadFromUri("/model"),
    faceapi.nets.faceRecognitionNet.loadFromUri("/model"),
    getUserImage()
]).then(() => {
  console.log("success!")
}).catch(e => {
  console.log(e)
})

const labeledDescriptor = await loadLabeledImage()
console.log(faceapi.labeledDescriptor)
const faceMatcher = new faceapi.FaceMatcher(labeledDescriptor, 0.7)

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
    const detections = await faceapi.detectSingleFace(video.video, getFaceDetectorOptions())
    const resizedDetections = faceapi.resizeResults(detections, displaySize)

    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
    const result = faceMatcher.findBestMatch(detections.descriptor)
    const box = resizedDetections.detection.box
    const drawBox = new faceapi.draw.DrawBox(box, { label: result.toString() })
    drawBox.draw(canvas)
    // faceapi.draw.drawDetections(canvas, resizedDetections)
  })
})
  
</script>

<style>

</style>