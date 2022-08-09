<template>
  <video ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted } from 'vue'
const props = defineProps({
  streamManager: Object,
})

const el = ref("")

onMounted(() => {
  console.log('test')
  console.log(props.streamManager)
  props.streamManager.addVideoElement(el.value)
})

</script>

<style>

</style>