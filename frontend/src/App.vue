<template>
  <router-view/>
</template>

<style scoped>
</style>

<script setup>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios'
import rct from './api/rct'
import jwt_decode from "jwt-decode"

const store = useStore()

const state = reactive({
  loginCheck: computed(() => store.state.user.islogin),
})

// if(state.loginCheck) {
//   setInterval(() => {
//     refresh()
//   }, 1500000)
// }

// const refresh = async () => {
//   await axios({
//     url: rct.user.tokenrefresh(),
//     method: 'post',
//     headers: {
//       Authorization : store.state.user.accessToken,
//       Refresh : store.state.user.refreshToken,
//     }
//   })
//   .then(res => {
//     store.dispatch('saveAccessToken', res.data.accessToken)
//     store.dispatch('saveRefreshToken', res.data.refreshToken)
//     const jwt = jwt_decode(res.data.accessToken)
//     store.dispatch("updateRefreshTime",jwt.exp)
//     store.commit("Set_userId",jwt.sub)
//     store.commit("Set_isStudent",jwt.isStudent)
//   })
//   .catch(err => {
//     console.log(err)
//     logout()
//   })
// }

// const logout = async () => {
//   await axios({
//     url: rct.login.logout(),
//     method: 'post',
//     headers: {
//       Authorization: store.state.user.accessToken,
//     }
//   })
//   .then(res => {
//     store.dispatch('saveAccessToken', '')
//     store.dispatch('saveRefreshToken', '')
//     store.dispatch("updateRefreshTime", null)
//     store.dispatch('updateIsLogin', 0)
//     location.href = '/'
//   })
//   .catch(err => {
//     console.log(err)
//   })
// }
</script>
