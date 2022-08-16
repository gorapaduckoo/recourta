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




const getConnectoinData = () => {
  const { connection } = props.mainStreamManager.stream
  return JSON.parse(connection.data)
}



</script>

<style>

</style>