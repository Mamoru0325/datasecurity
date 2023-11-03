<template lang="">
  <MyNavBar />
  <div class="center" style="background-color:#fff9f5;height:75vb;" > 
  <!-- <h1 style="text-align: center;">เกมถอดรหัส</h1> -->
  <br>
  <br>
        <h1 style="text-align: center;color:red;">Timer - {{this.timer}}</h1>
    <div class="row">
      <div class="col-8">
        
        <div style="text-align: left; margin-left: 20%;background-color:#fff9f5;border-radius:20px;" >
          
           <!--<h2 >กฎการเล่น</h2>
            
            <p>- พิมพ์คำตอบลงในช่องว่าง</p>
            <p>- กดส่งข้อมูลได้เลย</p>
            <p style="color:red;">*เมื่อเริ่มเเล้ว ต์เมื่อเริ่มแล้วกรุณา เช็คคำตอบทุกครั้งก่อนที่จะกดตกลงเพราะไม่สามารถย้อนกลับได้ </p>--> 
            <!-- <h1 style="text-align:center;color:#212529">โจทย์ </h1> -->
          <h2 v-for="(da, index) in this.datas" :key="da">
                <div v-if="index+1 == this.no">
                <br> ข้อที่ {{ index+1 }} ชนิดการเข้ารหัส {{da.type}} ข้อความที่ต้องถอด"{{da.cipherText}}"
                </div>  
          </h2>
        <br>
        <br>
        
          <!-- <h2>จงเติมคำตอบ</h2> -->
        </div>
          <div style="text-align:center;margin-left: 20%;">
            <textarea rows="3" id="ans" value="" style="background-color:#FFF ;"></textarea>
            <br>
        <br>
            <button @click="createFeedback()" >Submit</button>
          </div>
        <br>
        <br>
      </div>
      
      <div class="col-4" v-if="check">
        
        <table class="table table-striped" style="margin-left:20%;">
          <thead style="border: 100cm;">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Username</th>
              <th scope="col">Score</th>
            </tr>
          </thead>
          <tbody v-for="(da, index) in this.data" :key="da">
            <tr>
              <th scope="row">{{ index+1 }}</th>
              <td>{{ da.name }}</td>
              <td>{{ da.score }}</td>
            </tr>
          </tbody>
          <tbody >
            <tr>
              <th scope="row">#</th>
              <td>{{this.$store.state.auth.user.name}}</td>
              <td>{{this.score2}}</td>
            </tr>
          </tbody>
        </table>
        <!-- <h1 style="text-align: center;color:black;font-size:25px;">คะเเนน- {{this.score2}}</h1> -->
      </div>
    </div>
  </div>
  <MyButtonBar />
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
import axios from 'axios';
//import Pagination from './../views/admin/Pagination.vue';
// import axios from 'axios'

//import Countdown from 'vue3-countdown'

export default {
  name: 'GameView',
  props: ['aboutNo'],
  components: {
    MyNavBar,
    MyButtonBar
  },
  data() {
    let interval = setInterval(() => {
      if (this.timer === 0 && (this.$route.fullPath == "/user/timeslow/5" || this.$route.fullPath == "/user/timeslow/3")) {
        clearTimeout(interval)
        clearInterval(interval)
        this.saveno = Number(localStorage.getItem("no")) + 1;
        console.log("saveno" + this.saveno)
        localStorage.setItem('no', this.saveno);
        console.log("p-" + this.timer)
        localStorage.setItem('timer', this.timer);
        let x = localStorage.getItem("timer");
        console.log("p-" + x + "no " + localStorage.getItem("no"))
        var y = document.getElementById("ans").value;
        console.log("vale" + y);
        localStorage.setItem('datas', JSON.stringify(this.datas));
        console.log(this.datas[this.no - 1].cipherText);
        this.$router.go(0);
        if (this.level == 3) {
          localStorage.setItem('timer', 180);
        } else {
          localStorage.setItem('timer', 300);
        }
      } else if(this.timer>0 && (this.$route.fullPath == "/user/timeslow/5" || this.$route.fullPath == "/user/timeslow/3")){
        this.timer--
        localStorage.setItem('timer', this.timer);
      }
      else{
        clearTimeout(interval)
        clearInterval(interval)
        localStorage.removeItem("timer");
      }
    }, 1000)

    return {
      //timerset: localStorage.setItem('timer', 180),
      timer: localStorage.getItem("timer"),
      score: "ระดับง่าย",
      datas: [],
      data: [],
      no: localStorage.getItem("no"),
      startInt: 0,
      currentPage: 1,
      itemsPerPage: 1,
      totalItems: 100,
      level: this.$route.params.no,
      score2: 0,
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.datas.length / this.itemsPerPage);
    },
    paginatedData() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.datas.slice(startIndex, endIndex);
    }, check() {
      if (this.saveno > 10) {
        return false;
      } else {
        return true;
      }
    }
  }, methods: {
    async createFeedback() {
      let x = localStorage.getItem("timer");
      if (this.$route.params.no == 3) {
        x = Number(x) * 2;
      }

      console.log("createFeedback" + document.getElementById("ans").value);
      let point = {
        scoreboardId: this.datas[this.no - 1].scoreboardId,
        cipherId: this.datas[this.no - 1].cipherId,
        plainText: document.getElementById("ans").value,
        time: x,
      };

      console.log("point" + JSON.stringify(point));
      this.saveno = Number(localStorage.getItem("no")) + 1;
      const token = this.$store.state.auth.user.accessToken;
      console.log("Number(localStorage.getItem(no)) + 1 == 10 ----" + localStorage.getItem("no"))
      
      if (this.saveno > 10) {
        console.log(point)
        axios.post(`http://localhost:8080/api/scoreboard/`, point, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then(() => {
            this.setScore2();
            this.$router.push("/user/scorepoint");
          },
          (error) => {
            console.log(error)
          })
      } else {
        this.getData();
        console.log("saveno" + this.saveno)
        localStorage.setItem('no', this.saveno);
        console.log("p-" + this.timer)
        localStorage.setItem('timer', this.timer);
        console.log("p-" + x + "no " + localStorage.getItem("no"))
        var y = document.getElementById("ans").value;
        console.log("vale" + y);
        this.datas[this.no - 1].plaintext = document.getElementById("ans").value;
        localStorage.setItem('datas', JSON.stringify(this.datas));
        console.log(this.datas[this.no - 1].cipherText);

        axios.post(`http://localhost:8080/api/scoreboard/`, point, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then(() => {
          },
            (error) => {
              console.log(error)
            })
        
        if (this.level == 3) {
          localStorage.setItem('timer', 180);
        } else {
          localStorage.setItem('timer', 300);
        }
        this.$router.go(0);
      }
    }, changePage(newPage) {
      this.currentPage = newPage;
      this.startInt = (this.currentPage - 1) * this.itemsPerPage;
    }, async getData() {
      var retrievedObject = localStorage.getItem('datas');
      this.datas = JSON.parse(retrievedObject);
      const token = this.$store.state.auth.user.accessToken;
    
      axios.get(`http://localhost:8080/api/scoreboard/scores/` + "easy" + "/value/10", { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.data = response.data.body

          console.log(this.data)
        },
          (error) => {
            console.log(error)
          })
    },setScore2(){
      const token = this.$store.state.auth.user.accessToken;
      axios.get(`http://localhost:8080/api/scoreboard/` + this.datas[0].scoreboardId, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.score2 = response.data.body.score;
          localStorage.setItem('point',  response.data.body.score);
        },
        (error) => {

          console.log(error);
          alert(error);
        })
    }

  }, mounted() {
    this.getData()
    this.setScore2()
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