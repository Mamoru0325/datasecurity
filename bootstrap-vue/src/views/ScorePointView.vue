<template lang="">
    <MyNavBar />
    <div id="outer">
    <div>
        <br>
        <h1 style="text-align: center;">เกมถอดรหัส</h1>
        <h1 style="text-align: center;color:blue;">คุณได้คะเเนน- {{this.score2}}</h1>
        <br>
       
    <!-- <pagination :currentPage="currentPage" :totalPages="totalPages" @page-changed="changePage" /> -->
       
        
        <br>
        
        <br>
        
    </div>
    
    </div>
    
    <MyButtonBar/>

</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
//import axios from 'axios';
//import Pagination from './../views/admin/Pagination.vue';
// import axios from 'axios'

//import Countdown from 'vue3-countdown'

export default {
  name: 'GameView',
  props: ['aboutNo'],
  components: {
    MyNavBar,
    MyButtonBar,
    //    Pagination
    //Countdown
  },
  data() {
    

    return {
      timerset: localStorage.setItem('timer', 180),
      timer: localStorage.getItem("timer"),
      score: "ระดับง่าย",
      datas: [],
      data: [],
      no: localStorage.getItem("no"),
      score2:localStorage.getItem("point"),

      
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
  height: 700px;
  width: 2400px;
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