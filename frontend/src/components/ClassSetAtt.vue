<template>
  <div v-if="(studentList)?!studentList.length:true" class="w-3/4 ml-auto mr-auto lg:mt-20 mb-16 pt-60 lg:pt-[25vh] text-lg lg:text-xl text-center font-semibold">현재 수강중인 학생이 없습니다.</div>
  <div v-else class="w-4/5 px-6 lg:mt-4 overflow-x">
    <table class="table-fixed overflow-scroll w-full mb-12">
      <tr>
        <th class="px-4 py-3 border w-[160px]">이름</th>
        <th class="px-4 py-3 border w-[250px]">이메일</th>
        <th class="px-4 py-3 border w-[100px]">출석 현황</th>
        <th class="px-3 py-3 border w-[75px]"
          v-for="startDate of state.sessionStartTimeList.sort()"
          :key="startDate"
        >{{startDate}}</th>
      </tr>
      <ClassSetAttUserTable 
        v-for="userAttendance in state.userAttendance"
        :key="userAttendance"
        :userAttendance="userAttendance"
        :sessionList="state.sessionList"
      />
    </table>
  </div>
</template>

<script setup>
import ClassSetAttUserTable from "./ClassSetAttUserTable.vue"
import { reactive } from 'vue'
import { useStore } from "vuex"
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()

const props = defineProps({
  studentList : {
    type: Array,
  },
  lectureId : {
    type: Number,
  }
})

const state = reactive({
  lectureAttendance : [],
  sessionList: [],
  sessionStartTimeList: [],
  userAttendance: [],
})

const getLectureAttendance = async () => {
  await axios({
    url: rct.attendance.lectureAllAttendance(props.lectureId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    state.lectureAttendance = res.data.lectureAttendance
    state.sessionList = res.data.lectureAttendance.map(a => a.sessionId)
    for (let ssId of state.sessionList) {
      getSessionStartTime(ssId)
    }
    if(props.studentList){
      for (let i = 0; i < props.studentList.length; i++) {
      const tempAttList = Array(props.studentList[i])
      state.lectureAttendance.map(sessionAtt => {
        const tmp = sessionAtt.sessionAttendance.find(sessionUserAtt => {return sessionUserAtt.userId === props.studentList[i].userId})
        console.log("what is tmp?",tmp)
        if(tmp) tempAttList.push(tmp.attType)
      })
      state.userAttendance.push(tempAttList)
    }
    }
  })
  .catch(err => {
    console.log(err)
  })
}

getLectureAttendance()

const getSessionStartTime = async (ssId) => {
  await axios({
    url: rct.attendance.sessionstarttime(ssId),
    method: 'get',
    headers: {
      Authorization: store.state.user.accessToken,
    }
  })
  .then(res => {
    state.sessionStartTimeList.push(res.data.startTime.slice(5,10).replace('-','/'))
  })
  .catch(err => {
    console.log(err)
  })
}



</script>

<style scoped>

</style>
