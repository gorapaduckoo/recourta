<template>
  <div v-if="(studentList)?!studentList.length:true" class="w-3/4 ml-auto mr-auto lg:mt-20 mb-16 pt-60 lg:pt-[25vh] text-lg lg:text-xl text-center font-semibold">현재 수강중인 학생이 없습니다.</div>
  <table v-else class="lg:mt-4 w-3/4 lg:w-2/3 min-w-[450px] ml-auto mr-auto">
    <tr>
      <th class="px-4 py-3 border">이름</th>
      <th class="px-4 py-3 border">이메일</th>
    </tr>
    <ClassSetAttUserTable 
      v-for="student in studentList"
      :key="student"
      :student="student"
    />
  </table>
</template>

<script setup>
import ClassSetAttUserTable from "./ClassSetAttUserTable.vue"

const props = defineProps({
  studentList : {
    type: Array,
  }
})

</script>

<style scoped>

</style>