<template lang="">
    <div>
        <MyNavBar />
        <br>
        <h1>จัดอันดับ</h1>
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
        <MyButtonbarBar class="bottom"/>
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
  position:absolute; 
  bottom:0; 

  width:100%;
}
</style>