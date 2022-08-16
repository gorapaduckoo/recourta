<template>
  <UserCam id="video" class="w-[1000px] h-[750px]" :mainStreamManager="props.mainStreamManager"/>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, onMounted } from 'vue'


const props = defineProps({
  mainStreamManager:Object,
})


</script>

<style>

</style>