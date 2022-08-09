<template>
<div class="bg-[#444444] text-white">
  <div :class="{'pr-[360px]':state.isside}" class="flex flex-col h-screen pb-3 justify-between items-center font-bold text-4xl">
    <ClassList />
    <ClassMain />
    <ClassToolbar />
  </div>
  <ClassSidebar v-if="state.isside" class="absolute top-0 right-0 h-screen width-[360px] border-l-[1px] border-neutral-400"/>
</div>
<button @click="toggleside" class="text-white absolute top-0 right-0">X</button>
</template>

<script setup>
import ClassList from '../components/ClassList.vue'
import ClassMain from '../components/ClassMain.vue'
import ClassToolbar from '../components/ClassToolbar.vue'
import ClassSidebar from '../components/ClassSidebar.vue'
import { reactive } from 'vue'

const state = reactive({
  isside:false,
})

const toggleside = () => {
  state.isside=!state.isside
}

</script>

<style>

</style>