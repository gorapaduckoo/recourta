<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
    <!-- The left line -->
    <div class="flex-grow h-px bg-neutral-400"></div> 

    <!-- Your text here -->
    <div class="flex-shrink text-2xl font-bold dark:font-semibold px-4">수강 내역</div>

    <!-- The right line -->
    <div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
  </div>
  
  <div v-if="(pastLearned)?!pastLearned.length:true" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">수강했던 강의가 없습니다.</div>

  <!-- 반응형 큰 화면 -->
  <div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
  <PastCard 
    v-for="pastClass in pastLearned"
    :key="pastClass"
    :pastClass="pastClass"
  />
  </div>

  <!-- 반응형 모바일 -->
  <div class="lg:hidden flex flex-col my-8">
    <mPastCard
      v-for="pastClass in pastLearned"
      :key="pastClass"
      :pastClass="pastClass"
    />
  </div>
</template>

<script setup>
import PastCard from './PastCard.vue'
import mPastCard from './mPastCard.vue'

const props = defineProps({
  pastLearned : {
    type: Array,
  }
})

</script>

<style scoped>

</style>