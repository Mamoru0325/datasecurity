<template>
  <div>
    <MyNavBar />
    <br>
    <div class="row">
      <h1 style="text-align: center;">จัดอันดับ</h1>
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
          aria-expanded="false">
          Dropdown button
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#">Action</a></li>
          <li><a class="dropdown-item" href="#">Another action</a></li>
          <li><a class="dropdown-item" href="#">Something else here</a></li>
        </ul>
      </div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">First</th>
          <th scope="col">Last</th>
          <th scope="col">Handle</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>Jacob</td>
          <td>Thornton</td>
          <td>@fat</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td colspan="2">Larry the Bird</td>
          <td>@twitter</td>
        </tr>
      </tbody>
    </table>
    <br>
    <MyButtonbarBar class="bottom" />
  </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonbarBar from '@/components/MyButtonBar.vue'
export default {
  name: 'ScoreView',
  components: {
    MyNavBar,
    MyButtonbarBar
  }
}
</script>
<style scoped>
.bottom {
  position: absolute;
  bottom: 0;

  width: 100%;
}
</style>