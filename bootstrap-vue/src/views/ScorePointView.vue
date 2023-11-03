<template>
  <MyNavBar />
  <div style="background-color:#fff9f5;">
  <div class="container" style="height: 80vb;">
    <div>
      <br>
      
      <br>
      
      <br>
      <!-- <h1 style="text-align: center;">เกมถอดรหัส</h1> -->
      
        <p v-if="this.score2<200">
          <img src="https://i.imgflip.com/1ff1dx.jpg" style="wight:200px;height:200px;margin-left: 36%;">
         </p>
         <p v-else-if="this.score2>1000">
          <img style="wight:200px;height:200px;margin-left: 36%;" src="data:image/jpeg;base64,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">
         </p>
        <p v-else>
          <img  style="wight:200px;height:200px;margin-left: 36%; " src="https://media.makeameme.org/created/good-job-pk6cjo.jpg">
          
        </p>
      
      <h1 style="text-align: center;color:blue;">คุณได้คะเเนน {{ this.score2 }}</h1>
      <br><br>
      <h2 style="text-align: center;color:rgb(0, 0, 0);"><RouterLink class="dropdown-item" to="/user/game"><a class="dropdown-item" >ย้อนกลับหน้ารวม</a></RouterLink></h2>
    </div>

  </div>

  <MyButtonBar />
  </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
//import axios from 'axios';
//import Pagination from './../views/admin/Pagination.vue';
// import axios from 'axios'

//import Countdown from 'vue3-countdown'

export default {
  name: 'scorePoint',
  props: ['aboutNo'],
  components: {
    MyNavBar,
    MyButtonBar,
    //    Pagination
    //Countdown
  },
  data() {

    return {

      timer: localStorage.getItem("timer"),
      score: "ระดับง่าย",
      datas: [],
      data: [],
      no: localStorage.getItem("no"),
      score2: localStorage.getItem("point"),
    }
    //console.log(this.$route.params.time)
  },
  computed: {

  }, methods: {
    async getData() {
      localStorage.removeItem("no");
      localStorage.removeItem("point");
      localStorage.removeItem("timer");
      localStorage.removeItem("datas");
      localStorage.removeItem("score2");

    }


  }, mounted() {
    this.getData();
  }

}
</script>
<style scoped>
table {
  width: 60%;
}

.bottom {
  position: absolute;
  bottom: 0;

  text-align: center;
  width: 100%;

  border: 1px solid green;
}

#outer {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  height: 100%;
  width: 100%;
}

h1 {
  margin: 0 0 20px;
  font-weight: 400;
  color: #1c87c9;
}

form {
  padding: 25px;
  margin: 25px;
  box-shadow: 0 2px 5px #f5f5f5;
  background: #f5f5f5;
}

input,
textarea {
  width: calc(100%);
  padding: 8px;
  margin-bottom: 20px;
  border: 1px solid #1c87c9;
  outline: none;
}

input::placeholder {
  color: #666;
}

button {
  width: 100%;
  padding: 10px;
  border: none;
  background: #1c87c9;
  font-size: 16px;
  font-weight: 400;
  color: #fff;
}

button:hover {
  background: #2371a0;
}

p {
  margin-left: 5%;
}
</style>