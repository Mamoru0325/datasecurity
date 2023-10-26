<template>
  <div class="about">
    <MyNavBar />
    <br>
    <h1>This is an about page</h1>
    <br>
    <MyButtonBar />
  </div>
</template>

<script>
// @ is an alias to /src
import MyButtonBar from '@/components/MyButtonBar.vue'
import MyNavBar from '@/components/MyNavBar.vue'
export default {
  name: 'HomeView',
  components: {
    MyButtonBar,
    MyNavBar
  }
}
</script>
