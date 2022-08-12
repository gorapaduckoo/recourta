<template>
  <div v-if="props.mainStreamManager">
    <OVvid :mainStreamManager="props.mainStreamManager"/>
  </div>
</template>

<script setup>
import { reactive, ref, computed, onMounted } from 'vue'
import OVvid from './OVvid.vue'

const props = defineProps({
  mainStreamManager: Object,
})

const getConnectoinData = () => {
  const { connection } = props.mainStreamManager.stream
  return JSON.parse(connection.data)
}


</script>

<style>

</style>