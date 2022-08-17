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
import cv from 'canvas'

const state = reactive({
  cnt: 0,
  endIndex : computed(() => calEndIndex())
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


Promise.all([
    faceapi.nets.ssdMobilenetv1.loadFromUri("/model"),
    faceapi.nets.tinyFaceDetector.loadFromUri("/model"),
    faceapi.nets.tinyYolov2.loadFromUri("/model"),
    faceapi.nets.faceLandmark68TinyNet.loadFromUri("/model"),
    faceapi.nets.faceLandmark68Net.loadFromUri("/model"),
    faceapi.nets.faceRecognitionNet.loadFromUri("/model"),
]).then(() => {
  console.log("success!")
}).catch(e => {
  console.log(e)
})


props.publisher.on("streamPlaying", event => {
  const video = props.publisher.videos[0]
  const videoWidth = props.publisher.videos[0].video.clientWidth
  const videoHeight = props.publisher.videos[0].video.clientHeight
  console.log(video)
  const canvas = faceapi.createCanvasFromMedia(video.video)
  canvas.setAttribute('style', 'position: absolute;')
  document.getElementById('local-video-undefined').before(canvas)
  const displaySize = {width: videoWidth, height: videoHeight}
  console.log(displaySize)
  faceapi.matchDimensions(canvas, displaySize)

  setInterval(async ()=> {
    const detection = await faceapi.detectAllFaces(video.video).withFaceLandmarks()
    const resizedDetections = faceapi.resizeResults(detection, displaySize)

    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
    faceapi.draw.drawDetections(canvas, resizedDetections)
    faceapi.draw.drawFaceLandmarks(canvas, resizedDetections)
  })
})
  
</script>

<style>

</style>