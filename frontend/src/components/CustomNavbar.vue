<template>
<nav class="fixed w-full z-30 bg-[#2c5172] text-white px-4 py-2.5">
  <div class="container flex flex-wrap justify-between items-center mx-auto">
    <div class="flex items-center">
      <!-- 메인페이지, 로고 및 사이트명 -->
      <router-link to="/main" class="flex items-center">
        <img src="../assets/logo.png" class="h-6" alt="Flowbite Logo">
      </router-link>
      <!-- 메뉴 -->
      <div class="hidden ml-8 items-center w-full lg:flex lg:w-auto lg:order-1">
        <ul class="flex flex-row space-x-8 mt-0 font-medium">
          <li>
            <router-link to="/classlist" :class="{'text-neutral-50 font-bold hover:text-neutral-200':props.curpage === 'classList', 'text-neutral-300 hover:text-neutral-100':props.curpage !== 'classList'}" class="block text-base">수강신청</router-link>
          </li>
          <li>
            <router-link to="/pastclass" :class="{'text-neutral-50 font-bold hover:text-neutral-200':props.curpage === 'pastClass', 'text-neutral-300 hover:text-neutral-100':props.curpage !== 'pastClass'}" class="block text-base">이전강의</router-link>
          </li>
        </ul>
      </div>
    </div>
    <div class="flex items-center lg:order-2">
      <!-- 로그아웃 버튼 -->
      <button type="button" class="text-white bg-[#4076a5] hover:bg-[#fe5358] focus:outline-none font-medium rounded-lg text-sm px-5 text-center py-2.5 mr-3" data-bs-toggle="modal" data-bs-target="#logoutModal">로그아웃</button>
      <!-- 햄버거 버튼 -->
      <button @click="hamButtonClick" data-collapse-toggle="navbar-cta" type="button" class="inline-flex items-center p-2 text-sm text-white rounded-lg lg:hidden hover:bg-[#4076a5] focus:outline-none" aria-controls="navbar-cta" aria-expanded="false">
        <svg class="w-8 h-8" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
      </button>
      <!-- 마이페이지 버튼 -->
      <router-link to="/profile" class="sr-only lg:not-sr-only">
        <svg xmlns="http://www.w3.org/2000/svg" class="ml-1 h-9 w-9 hover:text-neutral-200" viewBox="2 2 16 16" fill="currentColor">
          <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-6-3a2 2 0 11-4 0 2 2 0 014 0zm-2 4a5 5 0 00-4.546 2.916A5.986 5.986 0 0010 16a5.986 5.986 0 004.546-2.084A5 5 0 0010 11z" clip-rule="evenodd" />
        </svg>
      </router-link>
    </div>
    
    
    
    <!-- 햄버거 버튼 클릭 시 메뉴 -->
    <div v-if="state.isHam" class="lg:sr-only justify-between items-center w-full lg:flex lg:w-auto lg:order-1" id="navbar-cta">
        <ul class="border-t-2 boder-white flex flex-col mt-2.5">
          <li>
            <router-link to="/profile" :class="{'text-neutral-50 font-bold':props.curpage === 'profile', 'text-neutral-300':props.curpage !== 'profile'}" class="block py-2 pl-3">마이 페이지</router-link>
          </li>
          <li>
            <router-link to="/classlist" :class="{'text-neutral-50 font-bold':props.curpage === 'classList', 'text-neutral-300':props.curpage !== 'classList'}" class="block py-2 pl-3">수강신청</router-link>
          </li>
          <li>
            <router-link to="/pastclass" :class="{'text-neutral-50 font-bold':props.curpage === 'pastClass', 'text-neutral-300':props.curpage !== 'pastClass'}" class="block py-2 pl-3">이전강의</router-link>
          </li>
        </ul>
      </div>
  </div>
</nav>

<!-- 로그아웃 Modal -->
<div class="modal fade fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" id="logoutModal" tabindex="-1" aria-labelledby="logoutModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
    <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
      <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
        <button type="button" data-bs-dismiss="modal" aria-label="Close">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>
      <div class="modal-body pt-2 pb-4 text-center">
        <p class="font-medium">로그아웃 하시겠습니까?</p>
      </div>
      <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
        <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" data-bs-dismiss="modal" @click="logout">예</button>
        <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" data-bs-dismiss="modal">아니오</button>
      </div>
    </div>
  </div>
</div>
</template>

<script setup>
import { reactive } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()
const route = useRouter()

const props = defineProps({
  curpage : {
    type: String,
  }
})

const state = reactive({
  isHam : false,
})

const logout = async () => {
  await axios({
    url: rct.login.logout(),
    method: 'post',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    store.dispatch('saveAccessToken', '')
    store.dispatch('saveRefreshToken', '')
    store.dispatch("updateRefreshTime", null)
    store.dispatch('updateIsLogin', 0)
    route.replace({path:"/"})
  })
  .catch(err => {
    console.log(err)
  })
}

const hamButtonClick = () => {
  state.isHam = !state.isHam
}

</script>

<style>

</style>
