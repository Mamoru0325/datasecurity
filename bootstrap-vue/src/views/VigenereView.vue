<template>
    <div>
      <MyNavBar />
      <br>
      <h1>This is an Vigenere page</h1>
      <br>
      <MyButtonbarBar />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import MyButtonbarBar from '@/components/MyButtonBar.vue'
  import MyNavBar from '@/components/MyNavBar.vue'
  export default {
    name: 'VigenereView',
    components: {
      MyButtonbarBar,
      MyNavBar
    }
  }
  </script>