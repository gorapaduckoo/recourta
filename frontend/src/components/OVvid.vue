<template>
  <video class="w-full h-full" ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import * as faceapi from 'face-api.js'


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

  const video = document.querySelector('video')

  setInterval(async() => {
    const detections = await faceapi.detectSingleFace(video, new faceapi.TinyFaceDetectorOptions()).withAgeAndGender()
  console.log(detections)
  })
})


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