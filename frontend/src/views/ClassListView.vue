<template>
  <DarkmodeButton/>
  <CustomFooter/>
  <CustomNavbar :curpage="state.curpage"/>
  
  <div class="pt-[80px]">
    <div class="flex items-center my-8 lg:mt-3 py-4">
      <!-- The left line -->
      <div class="flex-grow h-px bg-neutral-400"></div> 

      <!-- Your text here -->
      <div class="flex-shrink text-2xl font-bold px-4">신청 가능한 강의</div>

      <!-- The right line -->
      <div class="flex-grow h-px bg-neutral-400 lg:w-3/4"></div>
    </div>
  </div>

  <SearchForm :allClassList="state.allClassList"/>

  <AllClassList :allClassList="state.allClassList"/>
  <div v-if="(state.allClassList)?!state.allClassList.length:true" class="w-3/4 ml-auto mr-auto mt-20 mb-16 text-lg lg:text-xl text-center font-semibold">신청 가능한 강의가 없습니다.</div>
</template>

<script setup>
import CustomFooter from '../components/CustomFooter.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import AllClassList from '../components/AllClassList.vue'
import SearchForm from '../components/SearchForm.vue'
import { reactive } from 'vue'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from 'vuex'

const store = useStore()

const getClassList = async () => {
  await axios({
    url: rct.lecture.availablelist(store.state.user.userId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    state.allClassList = res.data
  })
  .catch(err => {
    console.log(err)
  })
}

const resetSearchInfo = () => {
  store.commit("UpdateClassification", 1)
  store.commit("UpdateSearchText", "")
  store.commit('UpdateSearchList', [])
}

getClassList()
resetSearchInfo()

const state = reactive({
  curpage : "classList",
  allClassList : [],
})

</script>

<style>

</style>