export const lecture = {
    state: {
      lectureId: null,
      lectureTimeList: [],
      lectureImgFirstUrl: 'assets/img/lecture/',
      lecInfo: {},
      searchList: [],
      classification: 1,
      searchText: "",
    },
    getters: {
    },
    mutations: {
      updateLectureId:(state,newId) => state.lectureId = newId,
      UpdateSearchList:(state,searchList) => state.searchList = searchList,
      UpdateClassification:(state,classification) => state.classification = classification,
      UpdateSearchText:(state,searchText) => state.searchText = searchText,
    },
    actions: {
    },
    modules: {
    },
  }
