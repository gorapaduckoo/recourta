<template>
  <DarkmodeButton />
  <CustomNavbar />
  <div class="pt-[60px] container flex justify-around mx-auto">
    <div class="font-bold text-4xl">강의 목록</div>
    <StudentCard />
    <StudentCard />
    <StudentCard />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import StudentCard from '../components/StudentCard.vue'
</script>

<style>

</style>