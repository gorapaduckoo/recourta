<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[80px]">
    <LecturerList v-if="!store.state.user.isStudent" :currentTeaching="state.currentTeaching"/>
    <CardList />
    <div>{{state.currentLearning}}</div>
  </div>

</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import CardList from '../components/CardList.vue'
import LecturerList from '../components/LecturerList.vue'
import { reactive } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()

const state = reactive({
  curpage : "main",
  currentTeaching : [],
  currentLearning : {},
})

const getCurrentTeaching = async () => {
  await axios({
    url: rct.lecture.currentteaching(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.currentTeaching = res.data
  })
  .catch(err => {
    console.log(err)
  })
}

const getCurrentLearning = async () => {
  await axios({
    url: rct.regist.registlearning(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.currentLearning = res.data
  })
  .catch(err => {
    console.log(err)
  })
}

getCurrentTeaching()
getCurrentLearning()

</script>

<style>

</style>