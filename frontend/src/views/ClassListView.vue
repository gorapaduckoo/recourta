<template>
  <DarkmodeButton/>
  <CustomNavbar :curpage="state.curpage"/>
  <div class="flex items-center pt-[80px] mb-8">
		<!-- The left line -->
		<div class="flex-grow h-px bg-neutral-400"></div> 

		<!-- Your text here -->
		<div class="flex-shrink text-2xl font-bold px-4">강의 목록</div>

		<!-- The right line -->
		<div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
	</div>

  <SearchForm />

  <SearchList />


</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import SearchList from '../components/SearchList.vue'
import SearchForm from '../components/SearchForm.vue'

import { reactive } from 'vue'
 

const state = reactive({
  curpage : "classList",
})

</script>

<style>

</style>