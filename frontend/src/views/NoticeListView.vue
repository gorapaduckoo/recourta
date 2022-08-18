<template>
  <DarkmodeButton />
  <CustomNavbar :curpage="state.curpage"/>
  <div class="pt-[80px]">
    <NoticeList />
  </div>
</template>

<script setup>
import DarkmodeButton from '../components/DarkmodeButton.vue'
import CustomNavbar from '../components/CustomNavbar.vue'
import NoticeList from '../components/NoticeList.vue'
import { reactive } from 'vue'
import axios from 'axios'
import rct from '../api/rct'
import { useStore } from 'vuex'

const store = useStore()

// const getNoticeList = async () => {
//   await axios({
//     url: rct.lecture.availablelist(store.state.user.userId),
//     method: 'get',
//     headers: {
//       Authorization: store.state.user.accessToken,
//     }
//   })
//   .then(res => {
//     state.noticeList = res.data
//   })
//   .catch(err => {
//     console.log(err)
//   })
// }

// getNoticeList()

const state = reactive({
  curpage: "notice",
  noticeList: [],
})
</script>

<style>

</style>