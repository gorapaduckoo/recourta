<template>
  <router-view/>
  <CustomFooter ref="customfooter" v-if="!state.isclass"/>
</template>

<style scoped>
</style>

<script setup>
import CustomFooter from './components/CustomFooter.vue'
import { reactive, computed } from 'vue'

const state = reactive({
  isclass:computed(() => {
    return document.location.pathname==="/class"
  }),
})

</script>
