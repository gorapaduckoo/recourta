<template>
  <div v-if="props.publisher" class="flex items-center space-x-3">
    <button @click="shiftleft" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
      </svg>
    </button>
    <UserCam class="w-[180px] h-[135px]" :mainStreamManager="props.publisher"/>
    <UserCam class="w-[180px] h-[135px]" v-for="sub in props.subscribers" :key="sub.stream.connection.connectionId" :mainStreamManager="sub"/>    
    <button @click="shiftright" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
      </svg>
    </button>
  </div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps } from 'vue'

const props = defineProps({
  publisher:Object,
  subscribers:Object,
})

const shiftleft = () => {
  return
}

const shiftright = () => {
  return
}
  
</script>

<style>

</style>