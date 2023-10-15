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
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
export default {
  name: 'HomeView',
  components: {
    MyNavBar,
    MyButtonBar
  }
}
</script>
