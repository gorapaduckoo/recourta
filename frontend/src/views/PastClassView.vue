<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px]">
    <PastCardList />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import PastCardList from '../components/PastCardList.vue'
import { reactive } from 'vue'

const state = reactive({
  curpage : "pastClass",
})
</script>

<style>

</style>