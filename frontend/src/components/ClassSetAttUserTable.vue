<template>
  <tr>
    <td class="px-6 py-3 border text-sm flex justify-between">
      <div>{{student.name}}</div>
      <button type="button" @click="modalOpen">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-[#fe343b] dark:text-[#fe4d53]" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
          <path stroke-linecap="round" stroke-linejoin="round" d="M15 12H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </td>
    <td class="px-6 py-3 border text-sm">{{student.email}}</td>
  </tr>

  <!-- 내보내기 Modal -->
  <div class="modal fade bg-black bg-opacity-50 fixed top-0 left-0 hidden w-full h-full outline-none overflow-x-hidden overflow-y-auto" :id="useroutModal" tabindex="-1" aria-labelledby="useroutModalLabel" aria-hidden="true" @click.self="modalClose">
    <div class="modal-dialog modal-dialog-centered relative w-1/4 min-w-[360px] ml-auto mr-auto pointer-events-none">
      <div class="modal-content border-none shadow-lg relative flex flex-col w-full pointer-events-auto bg-white dark:bg-[#4c4c4c] bg-clip-padding rounded-md outline-none text-current">
        <div class="modal-header flex flex-row-reverse flex-shrink-0 items-center justify-between px-4 pt-4 rounded-t-md">
          <button type="button" data-bs-dismiss="modal" aria-label="Close" @click="modalClose">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-neutral-600 dark:text-neutral-300 hover:text-black dark:hover:text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
        <div class="modal-body pt-2 pb-4 text-center">
          <p class="font-medium">{{student.name}}({{student.email}}) 님을<br/>강의에서 내보내시겠습니까?</p>
        </div>
        <div class="modal-footer flex flex-shrink-0 flex-wrap items-center justify-end px-4 pb-4 rounded-b-md space-x-3">
          <button type="button" class="text-white bg-[#fe5358] w-[62.3px] border border-[#fe5358] font-medium rounded-lg text-sm px-3 py-1.5 text-center hover:bg-[#fe343b]" @click="outRegistration">예</button>
          <button type="button" class="text-gray-500 w-[62.3px] rounded-lg border border-gray-200 text-sm font-medium px-3 py-1.5 dark:text-gray-300 dark:border-gray-500 hover:bg-gray-100 dark:hover:bg-[#555555]" @click="modalClose">아니오</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import axios from 'axios'
import rct from '../api/rct'

const store = useStore()
const rout = useRoute()

// { "userId": 2, "name": "김그작", "email": "2753dudwns@gmail.com", "userImg": "cbde9471-50c5-449f-bff9-6d400d92a0df.png" }
const props = defineProps({
  student : {
    type: Object,
  }
})

const useroutModal = 'userOut'+props.student.userId

const modalOpen = () => {
  document.getElementById(useroutModal).classList.replace('hidden', 'show')
}

const modalClose = () => {
  document.getElementById(useroutModal).classList.replace('show', 'hidden')
}

const outRegistration = async () => {
  await axios({
    url: rct.regist.regist(),
    method: 'delete',
    headers: {
      Authorization: store.state.user.accessToken,
    },
    data: {
      userId : props.student.userId,
      lectureId : Number(rout.params.lecId),
    }
  })
  .then(res => {
    location.reload()
  })
  .catch(err => {
    console.log(err)
  })
}
</script>

<style>

</style>