export const lecture = {
    state: {
      lectureTimeList: [],
      lectureImgFirstUrl: 'file:///media/uploads/img/lecture/',
      lecInfo: {},
      searchList: [],
      classification: 1,
      searchText: "",
    },
    getters: {
    },
    mutations: {
      UpdateSearchList:(state,searchList) => state.searchList = searchList,
      UpdateClassification:(state,classification) => state.classification = classification,
      UpdateSearchText:(state,searchText) => state.searchText = searchText,
    },
    actions: {
    },
    modules: {
    },
  }