<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[60px]">
    <NoticeList />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import NoticeList from '../components/NoticeList.vue'
import { reactive } from 'vue'

const state = reactive({
  curpage : "notice",
})
</script>

<style>

</style>