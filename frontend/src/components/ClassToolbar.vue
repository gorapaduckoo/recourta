<template>
  <div class="flex space-x-4">
    <button @click="toggleshare" :class="{'hover:text-neutral-100 text-neutral-200':state.isshare,'hover:text-[#b8b8b8] text-neutral-400':!state.isshare}" class="rounded-2xl h-15 w-24 p-1 border-2 hover:bg-[#4e4e4e] hover:border-[#b8b8b8] border-neutral-400">
      <svg class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <rect x="2" y="3" width="20" height="14" rx="2" ry="2" />
        <line x1="8" y1="21" x2="16" y2="21" />
        <line x1="12" y1="17" x2="12" y2="21" />
      </svg>
    </button>
    <button @click="togglecam" :class="{'hover:text-[#66bb6a] text-[#4fb054]':state.iscam,'hover:text-[#fe343b] text-[#fe5358]':!state.iscam}" class="rounded-2xl h-15 w-24 p-1 hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400">
      <svg v-if="state.iscam" class="h-6 w-6 mr-auto ml-auto text-green-500"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <polygon points="23 7 16 12 23 17 23 7" />
        <rect x="1" y="5" width="15" height="14" rx="2" ry="2" />
      </svg>
      <svg v-else class="h-6 w-6 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
        <path d="M16 16v1a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V7a2 2 0 0 1 2-2h2m5.66 0H14a2 2 0 0 1 2 2v3.34l1 1L23 7v10" />
        <line x1="1" y1="1" x2="23" y2="23" />
      </svg>
    </button>
    <button @click="togglemic" :class="{'hover:text-[#66bb6a] text-[#4fb054]':state.ismic,'hover:text-[#fe343b] text-[#fe5358]':!state.ismic}" class="rounded-2xl h-15 w-24 p-1 hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400">
      <svg v-if="state.ismic" class="h-6 w-6 mr-auto ml-auto text-green-500"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">  <path d="M12 1a3 3 0 0 0-3 3v8a3 3 0 0 0 6 0V4a3 3 0 0 0-3-3z" />
        <path d="M19 10v2a7 7 0 0 1-14 0v-2" />
        <line x1="12" y1="19" x2="12" y2="23" />
        <line x1="8" y1="23" x2="16" y2="23" />
      </svg>
      <svg v-else class="h-6 w-6 mr-auto ml-auto text-[#fe5358]"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">  <line x1="1" y1="1" x2="23" y2="23" />  <path d="M9 9v3a3 3 0 0 0 5.12 2.12M15 9.34V4a3 3 0 0 0-5.94-.6" />
        <path d="M17 16.95A7 7 0 0 1 5 12v-2m14 0v2a7 7 0 0 1-.11 1.23" />
        <line x1="12" y1="19" x2="12" y2="23" />
        <line x1="8" y1="23" x2="16" y2="23" />
      </svg>
    </button>
    <button @click="leaveclass" class="rounded-2xl h-15 w-24 p-1 hover:text-[#fe343b] text-[#fe5358] hover:bg-[#4e4e4e] border-2 hover:border-[#b8b8b8] border-neutral-400">
      <svg class="h-6 w-6 mr-auto ml-auto"  width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">  <path stroke="none" d="M0 0h24v24H0z"/>
        <path d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
        <path d="M7 12h14l-3 -3m0 6l3 -3" />
      </svg>
    </button>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

const state = reactive({
  isshare:false,
  iscam:true,
  ismic:true,
})

const toggleshare = () => {
  state.isshare=!state.isshare
}

const togglecam = () => {
  state.iscam = !state.iscam
}

const togglemic = () => {
  state.ismic = !state.ismic
}

const leaveclass = () => {
  return null
}

</script>

<style>

</style>