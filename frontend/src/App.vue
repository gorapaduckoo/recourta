<template>
  <router-view/>
  <CustomFooter ref="customfooter" v-if="!state.isclass"/>
</template>

<style scoped>
</style>

<script setup>
import CustomFooter from './components/CustomFooter.vue'
import { reactive, computed, onBeforeMount } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
import rct from './api/rct'
import jwt_decode from "jwt-decode"

const store = useStore()
const route = useRouter()

const state = reactive({
  isclass:computed(() => {
    return document.location.pathname==="/class"
  }),
})

const currrentTime = new Date() / 1000 | 0

beforeRouteUpdate(() => {
  if(store.state.user.islogin && store.state.user.refreshtime - currrentTime < 1780) {
    refresh()
  }
})

const refresh = async () => {
  await axios({
    url: rct.user.tokenrefresh(),
    method: 'post',
    data: {
      Authorization : store.state.user.accessToken,
      Refresh : store.state.user.refreshToken,
    }
  })
  .then(res => {
    store.dispatch('saveAccessToken', res.data.accessToken)
    store.dispatch('saveRefreshToken', res.data.refreshToken)
    const jwt = jwt_decode(res.data.accessToken)
    store.dispatch("updateRefreshTime",jwt.exp)
    store.commit("Set_userId",jwt.sub)
    store.commit("Set_isStudent",jwt.isStudent)
    console.log('갱신성공')
  })
  .catch(err => {
    logout()
  })
}

const logout = async () => {
  await axios({
    url: rct.login.logout(),
    method: 'post',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    store.dispatch("updateIsLogin",false)
    route.replace({path:'/'})
  })
  .catch(err => {
    console.log(err)
  })
}
</script>
