<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
    <!-- The left line -->
    <div class="flex-grow h-px bg-neutral-400"></div> 

    <!-- Your text here -->
    <div class="flex-shrink text-2xl font-bold dark:font-semibold px-4">알림 내역</div>

    <!-- The right line -->
    <div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
  </div>

  <div class="pt-8">
    <NoticeCard
      :isread="state.t_isread"
    />
    <NoticeCard 
      v-for="(num) in [0,1,2,3,4,5]"
      :key="num"
      :isread="state.f_isread"
    />
  </div>
</template>

<script setup>
import NoticeCard from './Notice.vue'
import { reactive } from 'vue'

const state = reactive({
  t_isread : true,
  f_isread : false
})
</script>

<style scoped>

</style>