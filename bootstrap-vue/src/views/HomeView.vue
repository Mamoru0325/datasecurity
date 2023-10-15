<template>
  <div class="home">
    <MyNavBar />
    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
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
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    MyNavBar,
    MyButtonBar
  }
}
</script>
