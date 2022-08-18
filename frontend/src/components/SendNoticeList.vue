<template>
  <SendNoticeCard 
    v-for="num in [0,1,2,3,4,5]"
    :key="num"
    :num="num"
  />
</template>

<script setup>
import SendNoticeCard from './SendNoticeCard.vue'

</script>

<style>

</style>