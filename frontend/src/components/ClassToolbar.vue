<template>
  <div class="flex">
    <button v-if="props.isLecturer||props.isAuth" @click="toggleshare" :class="{'hover:text-neutral-100 text-neutral-200':props.isshare,'hover:text-[#b8b8b8] text-neutral-400':!props.isshare}" class="rounded-2xl h-15 w-24 p-1 border-2 hover:bg-[#4e4e4e] hover:border-[#b8b8b8] border-neutral-400 mx-4">
      <svg class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <rect x="2" y="3" width="20" height="14" rx="2" ry="2" />
        <line x1="8" y1="21" x2="16" y2="21" />
        <line x1="12" y1="17" x2="12" y2="21" />
      </svg>
    </button>
    <button @click="togglecam" :class="{'hover:text-[#66bb6a] text-[#4fb054]':props.iscam,'hover:text-[#fe343b] text-[#fe5358]':!props.iscam}" class="rounded-2xl h-15 w-24 p-1 hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400 mx-4">
      <svg v-if="props.iscam" class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <polygon points="23 7 16 12 23 17 23 7" />
        <rect x="1" y="5" width="15" height="14" rx="2" ry="2" />
      </svg>
      <svg v-else class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <path d="M16 16v1a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V7a2 2 0 0 1 2-2h2m5.66 0H14a2 2 0 0 1 2 2v3.34l1 1L23 7v10" />
        <line x1="1" y1="1" x2="23" y2="23" />
      </svg>
    </button>
    <button @click="togglemic" :class="{'hover:text-[#66bb6a] text-[#4fb054]':props.ismic,'hover:text-[#fe343b] text-[#fe5358]':!props.ismic}" class="rounded-2xl h-15 w-24 p-1 hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400 mx-4">
      <svg v-if="props.ismic" class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">  <path d="M12 1a3 3 0 0 0-3 3v8a3 3 0 0 0 6 0V4a3 3 0 0 0-3-3z" />
        <path d="M19 10v2a7 7 0 0 1-14 0v-2" />
        <line x1="12" y1="19" x2="12" y2="23" />
        <line x1="8" y1="23" x2="16" y2="23" />
      </svg>
      <svg v-else class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">  <line x1="1" y1="1" x2="23" y2="23" />  <path d="M9 9v3a3 3 0 0 0 5.12 2.12M15 9.34V4a3 3 0 0 0-5.94-.6" />
        <path d="M17 16.95A7 7 0 0 1 5 12v-2m14 0v2a7 7 0 0 1-.11 1.23" />
        <line x1="12" y1="19" x2="12" y2="23" />
        <line x1="8" y1="23" x2="16" y2="23" />
      </svg>
    </button>
    <button @click="leaveclass" class="rounded-2xl h-15 w-24 p-1 hover:text-[#fe343b] text-[#fe5358] hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400 mx-4">
      <svg class="h-6 w-6 mr-auto ml-auto"  width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">  <path stroke="none" d="M0 0h24v24H0z"/>
        <path d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
        <path d="M7 12h14l-3 -3m0 6l3 -3" />
      </svg>
    </button>
    <button @click="toggleSubtitle" class="rounded-2xl h-15 w-24 p-1 border-2 hover:bg-[#4e4e4e] hover:border-[#b8b8b8] border-neutral-400 mx-4">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-auto ml-auto" viewBox="0 0 20 20" fill="currentColor">
        <path fill-rule="evenodd" d="M18 13V5a2 2 0 00-2-2H4a2 2 0 00-2 2v8a2 2 0 002 2h3l3 3 3-3h3a2 2 0 002-2zM5 7a1 1 0 011-1h8a1 1 0 110 2H6a1 1 0 01-1-1zm1 3a1 1 0 100 2h3a1 1 0 100-2H6z" clip-rule="evenodd" />
      </svg>
    </button>
  </div>
</template>

<script setup>
import { reactive, defineEmits, defineProps } from 'vue'

const props = defineProps({
  isshare:Boolean,
  iscam:Boolean,
  ismic:Boolean,
  isLecturer:Boolean,
  isAuth:Boolean,
})
const emit = defineEmits(["tryleave","toggleshare","togglecam","togglemic", "toggleSubtitle"])

const state = reactive({
})

const toggleshare = () => {
  emit("toggleshare",null)
}

const togglecam = () => {
  emit("togglecam",null)
}

const togglemic = () => {
  emit("togglemic",null)
}

const leaveclass = () => {
  emit("tryleave",null)
}

const toggleSubtitle = () => {
  emit("toggleSubtitle",null)
}

</script>

<style>

</style>