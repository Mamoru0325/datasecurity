<template>
    <div>
      <MyNavBar />
      <br>
      <h1>This is an Permutation page</h1>
      <br>
      <MyButtonbar />
    </div>
  </template>
  
  <script>
  // @ is an alias to /src
  import MyButtonbar from '@/components/MyButtonBar.vue'
  import MyNavBar from '@/components/MyNavBar.vue'
  export default {
    name: 'PermutationView',
    components: {
      MyButtonbar,
      MyNavBar
    }
  }
  </script>