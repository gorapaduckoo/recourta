<template>
  <DarkmodeButton />
  <CustomFooter/>
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[80px]">
    <PastCardList :pastLearned="state.pastLearned"/>
  </div>
</template>

<script setup>
import CustomFooter from './components/CustomFooter.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import PastCardList from '../components/PastCardList.vue'
import { reactive } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()

const getPastLearned = async () => {
  await axios({
    url: rct.regist.registlearned(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
      'Context-Type' : 'multipart/form-data',
    }
  })
  .then(res => {
    state.pastLearned = res.data.lectureDetailList
  })
  .catch(err => {
    console.log(err)
  })
}

getPastLearned()

const state = reactive({
  curpage: "pastClass",
  pastLearned: [],
})
</script>

<style>

</style>