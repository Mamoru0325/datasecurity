<template>
  <div class="hello">
    <h1 style="text-align: center;">ยินดีต้อนรับสู่ Cryptofer</h1>
    <br>
    <div class="row">
      <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
        <img src="https://m.media-amazon.com/images/I/71SgSx51bbL.__AC_SY300_SX300_QL70_FMwebp_.jpg" class="card-img-top" alt="..." height="300"
          style="margin-top: 3%">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบซีซาร์</h5>
          <p class="card-text"> เป็นการแทนค่าแต่ละตัวอักษรด้วยสัญลักษณ์เพียงตัวเดียว เป็นวิธีทีง่ายที่สุด
            ใช้มาตั้งแต่สมัยจูเลียส ซีซาร์</p>
          <RouterLink to="/admin/cizar" v-if="showAdmin"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
          <RouterLink to="/user/cizar" v-if="showUser"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
      <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
        <img src="../assets/Screenshot 2023-11-03 233535.png" class="card-img-top" alt="..." height="300"
          style="margin-top: 3%">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบ Vigenere</h5>
          <p class="card-text">Vigenere cipher เป็นการเข้ารหัสแบบซีเคร็ทคีย์ (Secret Key) 
          ที่อาศัยพื้นฐานเดียวกันกับ Caesar</p>
          <RouterLink to="/admin/vigenere" v-if="showAdmin"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
          <RouterLink to="/user/vigenere" v-if="showUser"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
      <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
        <img src="../assets/Screenshot 2023-11-03 233250.png" class="card-img-top" alt="..." height="300"
          style="margin-top: 3%">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบ Permutation</h5>
          <p class="card-text">การเข้ารหัสข้อมูล Permutation หรือ Columnar Transposition Cipher(สำหรับเล่นบนเว็บนี้)</p>
          <RouterLink to="/admin/permutation" v-if="showAdmin"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
          <RouterLink to="/user/permutation" v-if="showUser"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { RouterLink } from 'vue-router';

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  components: { RouterLink },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdmin() {
      if (this.currentUser && this.currentUser.roles[0] === 'ROLE_Admin') {
        return true;
      }

      return false;
    },
    showUser() {
      if (this.currentUser && this.currentUser.roles[0] === 'ROLE_User') {
        return true;
      }

      return false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}</style>
