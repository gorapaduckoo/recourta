<template>
  <div id="myVideo" v-if="props.publisher" class="flex items-center space-x-3">
    <button @click="shiftleft" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
      </svg>
    </button>
      <UserCam class="h-full max-h-[135px]" v-for="usr in props.userAll.slice(state.cnt*4, state.endIndex)" :isonMic="(props.onMic.indexOf(usr[4].stream.connection.connectionId)>=0)" :isunsit="(props.unsitList.indexOf(usr[4].stream.connection.connectionId)>=0)" :screenName="usr[1]" :isList="state.isList" @click="onclickuser(usr[4])" :key="usr[2]" :mainStreamManager="usr[4]"/>    
    <button @click="shiftright" class="hover:text-neutral-200 text-neutral-400">
      <svg class="h-8 w-8"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"/>
      </svg>
    </button>
  </div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { defineProps, reactive, computed, defineEmits, onMounted } from 'vue'
import * as faceapi from '@vladmandic/face-api'
import { useStore } from 'vuex'
import rct from '../api/rct'
import axios from 'axios'

const emit = defineEmits(["updateMainVideoStreamManager"])

const state = reactive({
  cnt: 0,
  endIndex : computed(() => calEndIndex()),
  userId: null,
  userName: "",
  takenImg: "",
  isList:true,
})

const props = defineProps({
  publisher: Object,
  userAll: Array,
  onMic: Array,
  unsitList: Array,
})

const onclickuser = (user) => {
  emit("updateMainVideoStreamManager",user)
}

const shiftleft = () => {
  let tmpCnt = state.cnt - 1

  if (tmpCnt < 0) tmpCnt = 0

  state.cnt = tmpCnt
}

const shiftright = () => {
  let tmpCnt = state.cnt + 1
  
  if (tmpCnt * 4 > props.userAll.length) {
    tmpCnt -= 1
  }

  state.cnt = tmpCnt
}

const calEndIndex = () => {
  let tmpEndIndex = state.cnt * 4 + 4

  if (tmpEndIndex > props.userAll.length) tmpEndIndex = props.userAll.length

  return tmpEndIndex
}
  
</script>

<style>

</style>