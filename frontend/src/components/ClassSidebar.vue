<template>
  <div class="w-full lg:w-[360px] flex flex-col text-sm lg:text-base bg-black bg-opacity-50">
    <div class="flex-none flex justify-between items-center space-x-3 mt-2 pb-2 pl-3 pr-4 border-b-[1px] border-neutral-400">
      <button  @click="closeListEmit" class="hidden lg:flex hover:text-neutral-200 text-neutral-400">
        <svg class="h-10 w-10" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
          <path stroke="none" d="M0 0h24v24H0z"/>
          <line x1="5" y1="12" x2="19" y2="12" />
          <line x1="15" y1="16" x2="19" y2="12" />
          <line x1="15" y1="8" x2="19" y2="12" />
        </svg>
      </button>
      <div class="text-xl text-center lg:text-justify">{{ props.sidebarTitle }}</div>
      <button @click="togglelist" class="hover:text-neutral-200 text-neutral-400">
        <svg class="h-10 w-10"  fill="none" viewBox="0 0 24 24" stroke="currentColor">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
        </svg>
      </button>
    </div>
    <div id="attabslist" class="flex-1 overflow-y-auto flex flex-col items-start p-2 border-b-[1px] border-neutral-400" v-if="state.isAtt">
      <div class="mb-3">
        참가자({{ props.classAttList.length + props.classAbsList.length }})
      </div>
      <button @click="toggleattendlist" class="w-full text-left border-b-[1px] mb-1 border-neutral-400">
        온라인({{ props.classAttList.length }})
      </button>
      <div v-if="state.isattendlist" class="flex w-full flex-col">
        <div v-for="(name, index) in props.classAttList" :key="index" class="pl-5 mb-1 flex items-center justify-between">
          <div class="flex">
            <div class="mr-2" :class="{'text-[#fe5358]':props.unsitList.indexOf(name[2])>=0}">{{ name[1] }}</div>
            <div :class="{'text-[#fe5358]':props.facecount>=props.outtime,'text-[#faa405]':props.facecount>=(props.outtime*2/3)&&props.facecount<props.outtime}" class="text-neutral-400" v-if="index===0&&props.facecount">
              {{Math.floor(props.facecount / 60) + ":" + (props.facecount % 60).toString().padStart(2,"0")}}
            </div>
          </div>
          <div v-if="props.isLecturer&&index==0" class="flex items-center text-xs lg:text-sm space-x-1">
            <div class="text-neutral-400">자리비움시간 : </div>
            <select class="flex-1 rounded-lg px-2 py-1 bg-[#444444] border border-neutral-400" v-model="state.thistimemult" @change="updateouttime">
              <option v-for="timemul in timeemuls" :key="timemul" :value="timemul">{{ timemul }}</option>
            </select>
            <select class="flex-1 rounded-lg px-2 py-1 bg-[#444444] border border-neutral-400" v-model="state.thistimesec" @change="updateouttime">
              <option :value=60 selected>분</option>
              <option :value=1>초</option>
            </select>
          </div>
          <div v-if="index" class="flex items-center space-x-2">
            <button v-if="props.isLecturer" @click="onClickAuth(name[3])" class="hover:text-neutral-200 text-neutral-400">
              <svg class="h-5 w-5 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
                <rect x="2" y="3" width="20" height="14" rx="2" ry="2" />
                <line x1="8" y1="21" x2="16" y2="21" />
                <line x1="12" y1="17" x2="12" y2="21" />
              </svg>
            </button>
            <button @click="onClickCam(name[4])" class="hover:text-neutral-200 text-neutral-400">
              <svg v-if="(props.isLecturer&&name[4].stream.videoActive)||(!props.isLecturer&&!state.camBanList.find(connectionid => connectionid === name[2]))" class="h-5 w-5 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
                <polygon points="23 7 16 12 23 17 23 7" />
                <rect x="1" y="5" width="15" height="14" rx="2" ry="2" />
              </svg>
              <svg v-else-if="(props.isLecturer&&!(name[4].stream.videoActive))||(!props.isLecturer&&state.camBanList.find(connectionid => connectionid === name[2]))" class="h-5 w-5 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
                <path d="M16 16v1a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V7a2 2 0 0 1 2-2h2m5.66 0H14a2 2 0 0 1 2 2v3.34l1 1L23 7v10" />
                <line x1="1" y1="1" x2="23" y2="23" />
              </svg>
            </button>
            <button @click="onClickMic(name[4])" class="hover:text-neutral-200 text-neutral-400">
              <svg v-if="(props.isLecturer&&name[4].stream.audioActive)||(!props.isLecturer&&!state.micBanList.find(connectionid => connectionid === name[2]))" :class="{'text-[#4fb054] hover:text-[#66bb6a]':props.onMic.indexOf(name[2])>=0}" class="h-5 w-5 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">
                <path d="M12 1a3 3 0 0 0-3 3v8a3 3 0 0 0 6 0V4a3 3 0 0 0-3-3z" />
                <path d="M19 10v2a7 7 0 0 1-14 0v-2" />
                <line x1="12" y1="19" x2="12" y2="23" />
                <line x1="8" y1="23" x2="16" y2="23" />
              </svg>
              <svg v-else-if="(props.isLecturer&&!(name[4].stream.audioActive))||(!props.isLecturer&&state.micBanList.find(connectionid => connectionid === name[2]))" class="h-5 w-5 mr-auto ml-auto"  viewBox="0 0 24 24"  fill="none"  stroke="currentColor"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round">  <line x1="1" y1="1" x2="23" y2="23" />  <path d="M9 9v3a3 3 0 0 0 5.12 2.12M15 9.34V4a3 3 0 0 0-5.94-.6" />
                <path d="M17 16.95A7 7 0 0 1 5 12v-2m14 0v2a7 7 0 0 1-.11 1.23" />
                <line x1="12" y1="19" x2="12" y2="23" />
                <line x1="8" y1="23" x2="16" y2="23" />
              </svg>
            </button>
            <button v-if="props.isLecturer"  @click="onClickBan(name[3])">
              <svg class="h-5 w-5 mr-auto ml-auto text-[#fe5358]"  width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">  <path stroke="none" d="M0 0h24v24H0z"/>
                <path d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
                <path d="M7 12h14l-3 -3m0 6l3 -3" />
              </svg>
            </button>
          </div>
        </div>
      </div>
      <button @click="toggleabsenlist" class="w-full mt-3 mb-1 text-left border-b-[1px] border-neutral-400">
        오프라인({{ props.classAbsList.length }})
      </button>
      <div v-if="state.isabsenlist">
        <div v-for="name in props.classAbsList" :key="name" class="px-5 mb-1">{{ name[1] }}</div>
      </div>
    </div>
    <div id="msg" ref="msg" class="flex flex-col text-base overflow-y-auto border-b-[1px] px-3 pt-2 border-neutral-400">
      <div class="mb-2" v-for="(msg,index) in props.msglist" :key="index">
        <div v-if="props.myID===msg[1]">
          <div class="flex items-end justify-end">
            <div class="text-[12px] lg:text-sm text-neutral-400 mr-2">{{msg[2]}}</div>
            <div class="max-w-[240px] px-3 py-2 border-2 border-neutral-400 rounded-2xl">
              {{msg[0]}}
            </div>
          </div>
        </div>
        <div v-else>
          <div class="flex items-end">
            <div>{{props.classAttList.find(v => v[2]===msg[1])[1]}}</div>
          </div>
          <div class="flex items-end">
            <div :class="{'text-[#faa405]':msg[0].includes('WARNING')}" class="max-w-[240px] px-3 py-2 border-2 border-neutral-400 rounded-2xl mr-2">
              {{msg[0]}}
            </div>
            <div class="text-[12px] lg:text-sm text-neutral-400">{{msg[2]}}</div>
          </div>
        </div>
      </div>
    </div>
    <div id="msginput">
      <div id="selectreceiver" class="flex items-center px-3 pt-2 space-x-3">
        <div class="class-none">받는 사람</div>
        <select class="flex-1 rounded-lg px-2 py-1 bg-[#444444] border border-neutral-400" v-model="state.receiver" name="receiver" id="receiver">
          <option value="모두에게" selected>모두에게</option>
          <option v-for="rec in props.classAttList" :key="rec[0]" :value="rec[3]">{{ rec[1] }}</option>
        </select>
      </div>
      <div class="relative px-3 pt-2 mb-2">
        <textarea @keyup.enter="submitmsg" id="msgbox" rows="2" name="sendmsg" v-model.trim="state.sendmsg" class="block py-2 pl-2 pr-[36px] w-full resize-none rounded-lg bg-transparent border-2 appearance-none focus:outline-none focus:ring-0 focus:border-neutral-200 border-neutral-400" placeholder=" "/>
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
import { reactive, defineEmits, defineProps, ref, onUpdated } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const msg = ref("")

const timeemuls = [1,3,5,10,15,30,45,60,90,120,150,180]

const emit = defineEmits(["closeList","submitMsg","submitAuth","submitCam","submitMic","submitBan","updateouttime"])
const props = defineProps({
  msglist:Array,
  myID:String,
  publisher:Object,
  subscribers:Array,
  sidebarTitle:String,
  classAttList: Array,
  classAbsList: Array,
  isLecturer: Boolean,
  onMic: Array,
  facecount: Number,
  unsitList: Array,
  outtime: Number,
  timemul: Number,
  timesec: Number,
})

const state = reactive({
  isAtt:false,
  isattendlist:true,
  isabsenlist:false,
  receiver:"모두에게",
  sendmsg:"",
  camBanList:[],
  micBanList:[],
  thistimemult:15,
  thistimesec:60,
})

const updateouttime = () => {
  emit("updateouttime",state.thistimemult,state.thistimesec)
}

const togglelist = () => {
  state.isAtt=!state.isAtt
}

const closeListEmit = () => {
  emit('closeList',null)
}

const submitmsg = () => {
  const reclist = []
  if(state.receiver!=="모두에게"){
    reclist.push(state.receiver)
    if(state.receiver.connectionId!==props.publisher.stream.connection.connectionId)reclist.push(props.publisher.stream.connection)
  }
  if(state.sendmsg!=="") emit('submitMsg',state.sendmsg,reclist)
  state.sendmsg=""
  // console.log(state.msgheight)
  
  // msg.value.scrollTo(0,msg.value.scrollHeight)
}

const toggleabsenlist = () => {
  state.isabsenlist=!state.isabsenlist
}

const toggleattendlist = () => {
  state.isattendlist=!state.isattendlist
}


const onClickAuth = (connection) => {
  emit('submitAuth',[connection])
}

const onClickCam = (sub) => {
  if(props.isLecturer){
    emit('submitCam',[sub.stream.connection])
  }
  else{
    if(state.camBanList.find(connectionid => connectionid === sub.stream.connection.connectionId)){
      sub.subscribeToVideo(true)
      const tmpcamBanList = state.camBanList.filter(conid=>conid!==sub.stream.connection.connectionId)
      state.camBanList = tmpcamBanList
    }
    else {
      if(JSON.parse(sub.stream.connection.data).clientData!=="lecturer"){
        sub.subscribeToVideo(false)
        state.camBanList.push(sub.stream.connection.connectionId)
      }
    }
  }
}

const onClickMic = (sub) => {
  if(props.isLecturer){
    emit('submitMic',[sub.stream.connection])
  }
  else{
    if(state.micBanList.find(connectionid => connectionid === sub.stream.connection.connectionId)){
      sub.subscribeToAudio(true)
      const tmpmicBanList = state.micBanList.filter(conid=>conid!==sub.stream.connection.connectionId)
      state.micBanList = tmpmicBanList
    }
    else {
      if(JSON.parse(sub.stream.connection.data).clientData!=="lecturer"){
        sub.subscribeToAudio(false)
        state.micBanList.push(sub.stream.connection.connectionId)
      }
    }
  }
}

const onClickBan = (connection) => {
  emit('submitBan',"BAN",[connection])
}

onUpdated(()=>{
  msg.value.scrollTop = msg.value.scrollHeight
})

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