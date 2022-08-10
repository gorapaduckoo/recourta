<template>
  <div v-if="props.streamManager">
    <OVvid :streamManager="props.streamManager"/>
    <p>{{ state.clientData }}</p>
  </div>
</template>

<script setup>
import { reactive, ref, computed, onMounted } from 'vue'
import OVvid from './OVvid.vue'

const props = defineProps({
  streamManager: Object,
})

const state = reactive({
  clientData : computed(()=>{
    const { cD } = getConnectoinData()
    return cD
  })
})

const getConnectoinData = () => {
  const { connection } = props.streamManager.stream
  return JSON.parse(connection.data)
}


</script>

<style>

</style>