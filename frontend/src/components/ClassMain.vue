<template>
  <UserCam class="w-full lg:w-auto" :mainStreamManager="props.mainStreamManager"/>
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