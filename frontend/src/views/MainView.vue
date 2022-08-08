<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px]">
    <CardList />
  </div>

</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import CardList from '../components/CardList.vue'
import { reactive } from 'vue'

const state = reactive({
  curpage : "main",
})
</script>

<style>

</style>