<template>
  <DarkmodeButton />
  <CustomNavbar />
  <div class="pt-[60px]">
    <div class="mt-10 ml-10 font-bold text-2xl">수강중인 강의 목록</div> 
    <CardList />
  </div>

</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import CardList from '../components/CardList.vue'
</script>

<style>

</style>