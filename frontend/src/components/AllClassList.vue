<template>
  <!-- Reactive Desktop -->
  <div class="hidden lg:grid lg:pt-10 lg:grid-cols-2 xl:grid-cols-3">
	<AllClassListCard 
		v-for="lecture in allClassList"
		:key="lecture"
    :lecpreview="lecture"
	/>
	</div>

  <!-- Reactive Mobile -->
  <div class="lg:hidden flex flex-col my-10">
		<mAllClassListCard
			v-for="lecture in allClassList"
      :key="lecture"
      :lecpreview="lecture"
		/>
	</div>
</template>

<script setup>
import AllClassListCard from './AllClassListCard.vue'
import mAllClassListCard from './mAllClassListCard.vue'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const props = defineProps({
  allClassList : {
    type: Array,
  }
})

const state = reactive({
	searchList: computed(() => store.state.lecture.searchList),
})

</script>

<style scoped>

</style>