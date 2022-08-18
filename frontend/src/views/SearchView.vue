<template>
  <DarkmodeButton/>
  <CustomFooter/>
  <CustomNavbar :curpage="state.curpage"/>
  
  <div class="pt-[80px]">
    <div class="flex items-center my-8 lg:mt-3 py-4">
      <!-- The left line -->
      <div class="flex-grow h-px bg-neutral-400"></div> 

      <!-- Your text here -->
      <div class="flex-shrink text-2xl font-bold px-4">'{{state.searchText}}' 에 대한 검색 결과</div>

      <!-- The right line -->
      <div class="flex-grow h-px bg-neutral-400"></div>
    </div>
  </div>

  <SearchForm :allClassList="state.allClassList"/>

  <SearchList />

</template>

<script setup>
import CustomFooter from '../components/CustomFooter.vue'
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import SearchList from '../components/SearchList.vue'
import SearchForm from '../components/SearchForm.vue'
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()
const rout = useRoute()

const getClassList = async () => {
  await axios({
    url: rct.lecture.lecturecreate(),
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

getClassList()

const state = reactive({
  curpage: "classList",
  allClassList: [],
  searchText: computed (() => rout.params.searchText)
})

</script>

<style>

</style>