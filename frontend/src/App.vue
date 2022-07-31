<template>
  <router-view/>
  <CustomFooter />
</template>

<style scoped>
</style>

<script>
import CustomFooter from './components/CustomFooter.vue'
export default {
  
  name: 'App',
  components: {
    CustomFooter,
  }
}
</script>
