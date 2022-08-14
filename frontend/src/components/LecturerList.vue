<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">강의중인 강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

	<!-- 강의중인 강의가 없을 때 -->
	<div v-if="!props.currentTeaching.length" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">현재 강의중인 강의가 없습니다.</div>
	
	<!-- Reactive Desktop -->
	<div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
    <LecturerCard
      v-for="lecture in props.currentTeaching"
      :key="lecture.lectureId"
      :lecture="lecture"
    />
	</div>

	<!-- Reactive Mobile -->
	<div class="lg:hidden flex flex-col my-8">
		<mLecturerCard
		v-for="lecture in props.currentTeaching"
		:key="lecture.lectureId"
    :lecture="lecture"
		/>
	</div>  
</template>

<script setup>
import LecturerCard from './LecturerCard.vue'
import mLecturerCard from './mLecturerCard.vue'

const props = defineProps({
  currentTeaching : {
    type: Array,
  }
})

</script>

<style scoped>

</style>