<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px]">
    <LecturerList v-if="!store.state.user.isStudent"/>
    <CardList />
  </div>

</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import CardList from '../components/CardList.vue'
import LecturerList from '../components/LecturerList.vue'
import { reactive } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const state = reactive({
  curpage : "main",
})
</script>

<style>

</style>