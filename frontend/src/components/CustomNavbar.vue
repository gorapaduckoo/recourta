<template>
  
<nav class="bg-[#2c5172] text-white px-2 sm:px-4 py-2.5">
  <div class="container flex flex-wrap justify-between items-center mx-auto">
    <div class="flex items-center">
      <!-- 메인페이지, 로고 및 사이트명 -->
      <a href="#" class="flex items-center">
        <img src="../assets/placeholder.png" class="mr-3 h-6 sm:h-9" alt="Flowbite Logo">
        <span class="self-center text-xl font-semibold whitespace-nowrap dark:text-white">ReCourTa</span>
      </a>
      <!-- 메뉴 -->
      <div class="hidden justify-between ml-8 items-center w-full md:flex md:w-auto md:order-1">
        <ul class="flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
          <li>
            <a href="#" class="block py-2 pr-4 pl-3 text-white md:p-0 dark:text-white" aria-current="page">강의목록</a>
          </li>
          <li>
            <a href="#" class="block py-2 pr-4 pl-3 text-gray-300 md:hover:bg-transparent md:hover:text-white md:p-0">이전강의</a>
          </li>
        </ul>
      </div>
    </div>
    <!-- 로그아웃 버튼 -->
    <div class="flex md:order-2 space-x-3">
      <button type="button" class="text-white bg-[#4076a5] hover:bg-[#fe5358] focus:outline-none font-medium rounded-lg text-sm px-5 text-center">로그아웃</button>
      <!-- 햄버거 버튼 -->
      <button data-collapse-toggle="navbar-cta" type="button" class="inline-flex items-center p-2 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="navbar-cta" aria-expanded="false">
        <svg class="w-6 h-6" aria-hidden="true" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z" clip-rule="evenodd"></path></svg>
      </button>
      <!-- 마이페이지 버튼 -->
      
      <button type="button" class="sr-only md:not-sr-only">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-9 w-9" viewBox="2 2 16 16" fill="currentColor">
          <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-6-3a2 2 0 11-4 0 2 2 0 014 0zm-2 4a5 5 0 00-4.546 2.916A5.986 5.986 0 0010 16a5.986 5.986 0 004.546-2.084A5 5 0 0010 11z" clip-rule="evenodd" />
        </svg>
      </button>
    </div>
    
    
    
    <!-- 햄버거 버튼 클릭 시 메뉴 -->
    <div class="hidden md:sr-only justify-between ml-8 items-center w-full md:flex md:w-auto md:order-1" id="navbar-cta">
        <ul class="flex flex-col mt-4 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium">
          <li>
            <a href="#" class="block py-2 pr-4 pl-3 text-white md:p-0 dark:text-white" aria-current="page">강의목록</a>
          </li>
          <li>
            <a href="#" class="block py-2 pr-4 pl-3 text-gray-300 md:hover:bg-transparent md:hover:text-white md:p-0">이전강의</a>
          </li>
          <li>
            <a href="#" class="block py-2 pr-4 pl-3 text-gray-300 md:hover:bg-transparent md:hover:text-white md:p-0">마이 페이지</a>
          </li>
        </ul>
      </div>
  </div>
</nav>

</template>

<script>
export default {
  name: 'CustomNavbar',
  setup(){

  }
}
</script>

<style>

</style>