<template>
  <div id="user-video" v-if="props.mainStreamManager">
    <OVvid :mainStreamManager="props.mainStreamManager"/>
  </div>
</template>

<script setup>
import { reactive, ref,  computed, onMounted, defineProps } from 'vue'
import OVvid from './OVvid.vue'
import * as faceapi from 'face-api.js'
import { FaceMatch, FaceMatcher } from 'face-api.js'

const props = defineProps({
  mainStreamManager: Object,
})

let video = "";
computed( () => {
  video = document.querySelector('video');
  const canvas = faceapi.createCanvasFromMedia(video)
canvas.setAttribute('class', 'position-absolute')
const canvasArea = document.querySelector('#user-video')
canvasArea.append(canvas)

const videoWidth = 1000;
const videoHeight= 750;
const displaySize = {width: videoWidth, height: videoHeight}
faceapi.matchDimensions(canvas, displaySize)

setInterval(async() => {
  const detections = await faceapi.detectSingleFace(video, new faceapi.DetectSingleFaceLandmarksTask()).withFaceLandmarks()

  canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height)
  faceapi.draw.drawDetections(canvas, resizedDetections)
  faceapi.draw.drawFaceLandmarks(canvas, resizedDetections)
  faceapi.draw.drawFaceExpressions(canvas, resizedDetections)
  
})
})

const getConnectoinData = () => {
  const { connection } = props.mainStreamManager.stream
  return JSON.parse(connection.data)
}



</script>

<style>

</style>