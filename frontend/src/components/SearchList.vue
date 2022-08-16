<template>
  <!-- Reactive Desktop -->
  <div class="hidden lg:grid lg:pt-10 lg:grid-cols-2 xl:grid-cols-3">
	<SearchListCard 
		v-for="searchResult in state.searchList"
		:key="searchResult"
    :searchResult="searchResult"
	/>
	</div>

  <!-- Reactive Mobile -->
  <div class="lg:hidden flex flex-col my-10">
		<mSearchListCard
			v-for="searchResult in state.searchList"
			:key="searchResult"
			:searchResult="searchResult"
		/>
	</div>
	<div v-if="(state.searchList)?!state.searchList.length:true" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">검색 결과가 없습니다.</div>
</template>

<script setup>
import SearchListCard from './SearchListCard.vue'
import mSearchListCard from './mSearchListCard.vue'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const state = reactive({
	searchList: computed(() => store.state.lecture.searchList),
})

</script>

<style scoped>

</style>