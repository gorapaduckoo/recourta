<template>

	<div class="flex items-center mt-8 lg:mt-3 py-4">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">수강중인 강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

	
	<!-- Reactive Desktop -->
	<div class="hidden lg:grid lg:pt-8 lg:grid-cols-2 xl:grid-cols-3">
	<StudentCard 
		v-for="(num) in [0,1,2,3,4,5]"
		:key="num"
	/>
	</div>

	<!-- Reactive Mobile -->
	<div class="lg:hidden flex flex-col my-8">
		<mStudentCard
			v-for="num in [0,1,2,3,4,5]"
			:key="num"
		/>
	</div>  
</template>

<script setup>
import StudentCard from './StudentCard.vue'
import mStudentCard from './mStudentCard.vue'


</script>

<style scoped>

</style>