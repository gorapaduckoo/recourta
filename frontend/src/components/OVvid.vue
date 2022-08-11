<template>
  <video class="w-full h-full" ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted } from 'vue'
const props = defineProps({
  streamManager: Object,
})

const el = ref("")

onMounted(() => {
  props.streamManager.addVideoElement(el.value)
})

</script>

<style>

</style>