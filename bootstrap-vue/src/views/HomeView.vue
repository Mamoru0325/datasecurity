<template>
  <div class="home" style="background-color:#fff9f5;">
    <MyNavBar />
    <br>
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel" style="width: 90%;margin: auto;">
      <div class="carousel-inner" >
        <div class="carousel-item active" >
          <img src="https://html.com/wp-content/uploads/flamingo.webp" class="d-block w-100"  height="500" alt="...">
        </div>
        <div class="carousel-item">
          <img src="https://html.com/wp-content/uploads/flamingo.webp" class="d-block w-100" height="500" alt="...">
        </div>
        <div class="carousel-item">
          <img src="https://html.com/wp-content/uploads/flamingo.webp" class="d-block w-100" height="500" alt="...">
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <br>
    <HelloWorld msg="Welcome to Your Vue.js App" />
    <br>
    <MyButtonBar />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
import MyNavBar from '@/components/MyNavBar.vue'
export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    MyButtonBar,
    MyNavBar
  }
}
</script>
<style scoped>
.bottom {
  position:absolute; 
  bottom:0; 
  
  text-align:center; 
  width:100%;

  border:1px solid green; 
}
</style>
