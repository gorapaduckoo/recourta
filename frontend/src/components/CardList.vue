<template>
	<div class="hidden lg:grid lg:pt-10 lg:grid-cols-2 xl:grid-cols-3">
	<StudentCard 
		v-for="num in [0,1,2,3,4,5]"
		:key="num"
	/>
	</div>

	<div class="lg:hidden flex flex-col my-10">
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