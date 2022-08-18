<template>
  <UserCam class="max-w-[1050px] w-full lg:w-auto" :isList="state.isList" :isonMic="false" :isunsit="false" :screenName="null" :mainStreamManager="props.mainStreamManager"/>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, reactive } from 'vue'

const props = defineProps({
  mainStreamManager:Object,
})

const state = reactive({
  isList : false,
})

</script>

<style>

</style>