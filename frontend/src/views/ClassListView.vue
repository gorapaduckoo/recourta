<template>
  <DarkmodeButton/>
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px] container flex justify-around mx-auto">
    <div class="font-bold text-4xl">강의 목록</div>
    <CardList />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import CardList from '../components/CardList.vue'
import { reactive } from 'vue'

const state = reactive({
  curpage : "classList",
})

</script>

<style>

</style>