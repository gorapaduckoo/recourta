<template>
  <DarkmodeButton />
  <CustomNavbar />
  <div class="pt-[60px] container flex justify-around mx-auto">
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