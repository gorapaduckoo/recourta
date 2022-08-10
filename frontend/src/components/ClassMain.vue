<template>
  <UserCam :streamManager="props.mainStreamManager"/>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps } from 'vue'

const props = defineProps({
  mainStreamManager:Object,
})

</script>

<style>

</style>