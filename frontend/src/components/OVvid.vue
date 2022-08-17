<template>
  <video class="w-full h-auto lg:h-full" ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted, onUpdated } from 'vue'



const props = defineProps({
  mainStreamManager: Object,
})

const el = ref("");



onMounted(async () => {
  props.mainStreamManager.addVideoElement(el.value)

})



// console.log(props.mainStreamManager)

// onUpdated(() => {

//   const canvas1 = faceapi.createCanvas(el.value)
//   canvas1.setAttribute('class', 'position-absolute')
//   const canvasArea = document.querySelector('#myVideo')
//   canvasArea.append(cavas1)

//   const videoWidth = el.value.videoWidth
//   const videoHeight = el.value.videoHeight
//   console.log("1: " + el.value.videoWidth + " " + el.value.videoHeight)

// })

// const displaySize = {width: videoWidth, height: videoHeight}
// faceapi.matchDimensions(cavas, displaySize)

// setInterval(async() => {
//   console.log("2: " + el.value.videoWidth + " " + el.value.videoHeight)
//   const detections = await faceapi.detectSingleFace(el.value).withFaceLandmarks()
//   console.log(detections)
//   console.log("3: " + el.value.videoWidth + " " + el.value.videoHeight)
  // const resizedDetections = faceapi.resizeResults(detections, displaySize)
  // canvas1.getContext('2d').clearRect(0, 0, canvas1.width, canvas1.height)
  // faceapi.draw.drawDetections(canvas1, resizedDetections)
  // faceapi.draw.drawFaceLandmarks(canvas1, resizedDetections)
  // faceapi.draw.drawFaceExpressions(canvas1, resizedDetections)
// })





</script>

<style>

</style>