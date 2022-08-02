<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px] container flex justify-around mx-auto">
    <div class="font-bold text-4xl">이전 강의</div>
    <PastCard />
    <PastCard />
    <PastCard />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import PastCard from '../components/PastCard.vue'
import { reactive } from 'vue'

const state = reactive({
  curpage : "pastClass",
})
</script>

<style>

</style>