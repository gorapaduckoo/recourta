<template>
  <UserCam class="w-[800px] h-[600px]" :mainStreamManager="props.mainStreamManager"/>
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