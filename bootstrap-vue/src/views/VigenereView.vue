<template>
    <div>
      <MyNavBar />
      <br>
      <h1>This is an Vigenere page</h1>
      <br>
      <MyButtonBar />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import MyNavBar from '@/components/MyNavBar.vue'
  import MyButtonBar from '@/components/MyButtonBar.vue'
  export default {
    name: 'VigenereView',
    components: {
      MyNavBar,
      MyButtonBar
    }
  }
  </script>