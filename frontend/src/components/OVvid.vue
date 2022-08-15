<template>
  <video class="w-full h-full" ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as faceapi from 'face-api.js'
import { FaceMatch, FaceMatcher } from 'face-api.js'


const initModel = async () => {
    await faceapi.nets.tinyFaceDetector.loadFromUri('/public/models')
    await faceapi.nets.faceLandmark68Net.loadFromUri('/public/models')
  }

const props = defineProps({
  mainStreamManager: Object,
})

const el = ref("")

onMounted(() => {
  props.mainStreamManager.addVideoElement(el.value)
})

const video = document.querySelector('video')

  console.log(">>>>>>>>>>>.here!<<<<<<<<<<<<<")

  const canvas = faceapi.createCanvasFromMedia(video)
  document.querySelector('template').append(canvas)
  const displaySize = {width: video.width, height: video.height}
  faceapi.matchDimensions(canvas, displaySize)
  setInterval(async() => {
    const detections = await faceapi.detectSingleFace(video, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks()
    console.log(detections)
    const resizedDetections = faceapi.resizeResults(detections, displaySize)

    canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height)
    faceapi.draw.drawDetections(canvas, resizedDetections)
    faceapi.draw.DrawFaceLandmarks(canvas, resizedDetections)
    }, 100);


// async function loadModels() {
//   console.log("loadModels");
//   console.log(faceapi.nets)
//   Promise.all([
//     tinyFaceDetector = faceapi.nets.tinyFaceDetector.loadFromUri('/public/models'),
//     faceLandmark68Net = faceapi.nets.faceLandmark68Net.loadFromUri('/public/models'),
//   ])
//   .then(
//     console.log(">>>>>>>>>>>>>ready!<<<<<<<<<<<<<")
//   ).catch(errors=> {
//     console.log(errors)
//   })

//   const video = document.querySelector('video')
//   console.log('video!');
//   const detections = await faceapi.detectSingleFace(video, new faceapi.TinyFaceDetectorOptions());
//   console.log(detections);

// }





</script>

<style>

</style>