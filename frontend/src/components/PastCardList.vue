<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
    <!-- The left line -->
    <div class="flex-grow h-px bg-neutral-400"></div> 

    <!-- Your text here -->
    <div class="flex-shrink text-2xl font-bold dark:font-semibold px-4">수강 내역</div>

    <!-- The right line -->
    <div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
  </div>


  <!-- 반응형 큰 화면 -->
  <div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
  <PastCard 
    v-for="(num) in [0,1,2,3,4,5]"
    :key="num"
  />
  </div>

  <!-- 반응형 모바일 -->
  <div class="lg:hidden flex flex-col my-8">
    <mPastCard
      v-for="num in [0,1,2,3,4,5]"
      :key="num"
    />
  </div>
</template>

<script setup>
import PastCard from './PastCard.vue'
import mPastCard from './mPastCard.vue'


</script>

<style scoped>

</style>