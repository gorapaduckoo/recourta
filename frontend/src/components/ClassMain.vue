<template>
  <div id="main-container" class="container">
		<div id="session">
			<div id="session-header">
				<h1 id="session-title">{{ state.mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveClass" value="Leave session">
			</div>
			<div id="main-video" class="col-md-6">
				<UserCam :streamManager="state.mainStreamManager"/>
			</div>
			<div id="video-container" class="col-md-6">
				<UserCam :streamManager="state.publisher" @click.native="updateMainVideoStreamManager(state.publisher)"/>
				<UserCam v-for="sub in state.subscribers" :key="sub.stream.connection.connectionId" :streamManager="sub" @click.native="updateMainVideoStreamManager(sub)"/>
			</div>
		</div>
	</div>
</template>

<script setup>
import UserCam from "./UserCam.vue"
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'

const store = useStore()

const state = reactive({
  // OV: computed(()=>store.getters.currentOV),
  // session: computed(()=>store.getters.currentSession),
  mainStreamManager: computed(()=>store.getters.currentMainStreamManager),
  publisher: computed(()=>store.getters.currentPublisher),
  subscribers: computed(()=>store.getters.currentSubscribers),

  // mySessionId: 'SessionA',
  // myUserName: 'Participant' + Math.floor(Math.random() * 100),
})

const leaveClass = () => {
  // --- Leave the session by calling 'disconnect' method over the Session object ---
  // if (state.session) state.session.disconnect();

  // state.session = undefined;
  // state.mainStreamManager = undefined;
  // state.publisher = undefined;
  // state.subscribers = [];
  // state.OV = undefined;

  // window.removeEventListener('beforeunload', leaveClass);
}
</script>

<style>

</style>