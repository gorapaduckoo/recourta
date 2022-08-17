<template>
  <div v-if="props.mainStreamManager" class="relative">
    <OVvid :class="{'rounded-lg':props.isList&&!props.isonMic,'rounded-lg border-2 border-[#4fb054]':props.isList&&props.isonMic}" :mainStreamManager="props.mainStreamManager"/>
    <div v-if="props.screenName" class="text-sm bg-black rounded-lg opacity-50 p-1 absolute bottom-1 right-1">{{ props.screenName }}</div>
  </div>
</template>

<script setup>
import { reactive, ref,  computed, onMounted, defineProps } from 'vue'
import OVvid from './OVvid.vue'

const props = defineProps({
  mainStreamManager: Object,
  isList:Boolean,
  isonMic:Boolean,
  screenName:String,
})




const getConnectoinData = () => {
  const { connection } = props.mainStreamManager.stream
  return JSON.parse(connection.data)
}



</script>

<style>

</style>