<template>
  <video class="w-full h-auto lg:h-full" ref="el" autoplay/>
</template>

<script setup>
import { ref, onMounted } from 'vue'
const props = defineProps({
  mainStreamManager: Object,
})

const el = ref("")

onMounted(() => {
  props.mainStreamManager.addVideoElement(el.value)
})

</script>

<style>

</style>