<template>
  <div class="w-[360px] flex flex-col">
    <div class="flex-none flex justify-between items-center space-x-3 mt-2 pb-2 pl-3 pr-4 border-b-[1px] border-neutral-400">
      <button  @click="closeListEmit" class="hover:text-neutral-200 text-neutral-400">
        <svg class="h-10 w-10" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
          <path stroke="none" d="M0 0h24v24H0z"/>
          <line x1="5" y1="12" x2="19" y2="12" />
          <line x1="15" y1="16" x2="19" y2="12" />
          <line x1="15" y1="8" x2="19" y2="12" />
        </svg>
      </button>
      <div class="text-xl text-justify">데이터로 표현한 세상과 아름다운 바다</div>
      <button @click="togglelist" class="hover:text-neutral-200 text-neutral-400">
        <svg class="h-10 w-10"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
        </svg>
      </button>
    </div>
    <div id="attabslist" class="flex-1 overflow-y-auto flex flex-col items-start p-2 border-b-[1px] border-neutral-400" v-if="state.isAtt">
      <div class="mb-3">
        참가자({{ state.attendList.length+state.absenList.length }})
      </div>
      <button @click="toggleattendlist" class="w-3/4 text-left border-b-[1px] border-neutral-400">
        온라인({{ state.attendList.length }})
      </button>
      <div v-if="state.isattendlist" class="flex flex-col">
        <div v-for="name in state.attendList" class="px-5">{{ name }}</div>
      </div>
      <button @click="toggleabsenlist" class="w-3/4 mt-3 text-left border-b-[1px] border-neutral-400">
        오프라인({{ state.absenList.length }})
      </button>
      <div v-if="state.isabsenlist">
        <div v-for="name in state.absenList" class="px-5">{{ name }}</div>
      </div>
    </div>
    <div id="msg" class="flex flex-col overflow-y-auto border-b-[1px] border-neutral-400">
      <div v-for="(msg,index) in props.msglist" :key="index">
        <div v-if="props.myID===props.fromID">내가{{props.myID}}:{{msg}}</div>
        <div v-else>타인{{props.fromID}}:{{msg}}</div>
      </div>
    </div>
    <div id="msginput">
        <div id="selectreceiver" class="flex items-center px-3 pt-2 space-x-3">
          <div class="class-none">받는 사람</div>
          <select class="flex-1 rounded-lg px-2 py-1 bg-[#444444] border border-neutral-400" v-model="state.receiver" name="receiver" id="receiver">
            <option value="모두에게">모두에게</option>
            <option v-for="rec in state.attendList" :key="rec" value="rec">{{ rec }}</option>
          </select>
        </div>
        <div class="relative px-3 pt-2 mb-2">
          <textarea id="msgbox" rows="2" name="sendmsg" v-model.trim="state.sendmsg" class="block py-2 pl-2 pr-[36px] w-full text-sm resize-none rounded-lg bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-neutral-200 border-neutral-400" placeholder=" "/>
          <button @click="submitmsg" class="absolute top-[28px] right-[20px] hover:text-neutral-200 text-neutral-400">
            <svg class="h-6 w-6"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
              <polyline points="9 10 4 15 9 20" />
              <path d="M20 4v7a4 4 0 0 1-4 4H4" />
            </svg>
          </button>
        </div>
      </div>
  </div>
</template>

<script setup>
import { reactive, defineEmits, defineProps, computed } from 'vue'

const emit = defineEmits(["closeList","submitMsg"])
const props = defineProps({
  msglist:Array,
  myID:String,
  fromID:String,
  publisher:Object,
  subscribers:Array,
})

const state = reactive({
  isAtt:false,
  isattendlist:false,
  isabsenlist:false,
  attendList:computed(()=>getConnectionData()),
  absenList:["유지슬","남궁준","이지영"],
  receiver:"모두에게",
  sendmsg:"",
})

const getConnectionData = () => {
  const conneclist = []
  conneclist.push(JSON.parse(props.publisher.stream.connection.data).clientData);
  for (let sub of props.subscribers) {
    conneclist.push(JSON.parse(sub.stream.connection.data).clientData)
  }
  return conneclist
}

const togglelist = () => {
  state.isAtt=!state.isAtt
}

const closeListEmit = () => {
  emit('closeList',null)
}

const submitmsg = () => {
  emit('submitMsg',state.sendmsg,state.receiver)
}

const toggleabsenlist = () => {
  state.isabsenlist=!state.isabsenlist
}

const toggleattendlist = () => {
  state.isattendlist=!state.isattendlist
}

</script>

<style scoped>
#attabslist,#msg,#msgbox{
  -ms-overflow-style: none;
}
#attabslist::-webkit-scrollbar,#msg::-webkit-scrollbar,#msgbox::-webkit-scrollbar{
  display: none;
}
#msg{
  flex:2 1 0%;
}
</style>