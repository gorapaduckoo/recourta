<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">수강중인 강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

	<!-- 수강중인 강의가 없을 때 -->
	<div v-if="(props.currentLearning)?!props.currentLearning.length:true" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">현재 수강중인 강의가 없습니다.</div>
	
	<!-- Reactive Desktop -->
	<div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
	<StudentCard 
		v-for="lecture in currentLearning"
		:key="lecture.lectureId"
		:lecture="lecture"
	/>
	</div>

	<!-- Reactive Mobile -->
	<div class="lg:hidden flex flex-col my-8">
		<mStudentCard
			v-for="lecture in currentLearning"
			:key="lecture.lectureId"
			:lecture="lecture"
		/>
	</div>  
</template>

<script setup>
import StudentCard from './StudentCard.vue'
import mStudentCard from './mStudentCard.vue'

const props = defineProps({
  currentLearning : {
    type: Array,
  }
})

</script>

<style scoped>

</style>