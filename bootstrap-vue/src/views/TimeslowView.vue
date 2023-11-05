<template lang="">
  <MyNavBar />
  <div class="center" style="background-color:#fff9f5;" > 
  <!-- <h1 style="text-align: center;">เกมถอดรหัส</h1> -->
  <br>
  <br>
  
  <font-awesome-icon icon="alarm-clock" size="2xs" />
  
        <h1 style="text-align: center;color:red;font-family: Lucida Console, Courier New, monospace;"><font-awesome-icon icon="clock" />  Timer  {{this.timer}}</h1>
    <div class="row">
      <div class="col-8">
        
        <div style="text-align: left; margin-left: 20%;background-color:#fff9f5;box-shadow: rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px, rgba(255, 255, 255, 0.08) 0px 1px 0px inset;" >
          
           <!--<h2 >กฎการเล่น</h2>
            
            <p>- พิมพ์คำตอบลงในช่องว่าง</p>
            <p>- กดส่งข้อมูลได้เลย</p>
            <p style="color:red;">*เมื่อเริ่มเเล้ว ต์เมื่อเริ่มแล้วกรุณา เช็คคำตอบทุกครั้งก่อนที่จะกดตกลงเพราะไม่สามารถย้อนกลับได้ </p>--> 
            <!-- <h1 style="text-align:center;color:#212529">โจทย์ </h1> -->
          <h2 v-for="(da, index) in this.datas" :key="da">
                <div v-if="index+1 == this.no">
                <br>&nbsp;&nbsp; ข้อที่ {{ index+1 }} ชนิดการเข้ารหัส {{da.type}} <br>&nbsp;&nbsp; ข้อความที่ต้องถอด"{{da.cipherText}}"
                <div v-if="da.type=='vigenere'">
                  <br>&nbsp;&nbsp; key : {{da.key}}
                </div>
                <div v-if="da.type=='permutation'">
                  <br>&nbsp;&nbsp; lenght : {{da.length}}
                </div>
                </div>  
          </h2>
        <br>
        <br>
        
        
          <!-- <h2>จงเติมคำตอบ</h2> -->
        </div>
        <br>
        <br>
          <div style="text-align:center;margin-left: 20%;">
            <textarea rows="3" id="ans" value="" style="background-color:#FFF ;"></textarea>
            <br>
        <br>
            <a @click="createFeedback()" class="button-54">Submit</a>
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
      const token = this.$store.state.auth.user.accessToken;
      
      let point = {
        scoreboardId: this.datas[this.no - 1].scoreboardId,
        cipherId: this.datas[this.no - 1].cipherId,
        plainText: document.getElementById("ans").value,
        time: localStorage.getItem("timer"),
      };
      //console.log("Number(localStorage.getItem(no)) + 1 == 10 ----" + localStorage.getItem("no"))
      //alert(this.saveno);
      if (this.saveno >= 11) {
        ////console.log(point)
        //alert(this.saveno);
        axios.post(`http://localhost:8080/api/scoreboard/`, point, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then(() => {
            //this.setScore2();
            //alert("checksuss"+this.saveno);
            this.$router.push("/user/scorepoint");
          },
          (error) => {
            alert("2");
            console.log(error)
          })
      }else{
        //console.log("error");
      }
        this.saveno = Number(localStorage.getItem("no")) + 1;
        //console.log("saveno" + this.saveno)
        localStorage.setItem('no', this.saveno);
        //console.log("p-" + this.timer)
        localStorage.setItem('timer', this.timer);
        localStorage.getItem("timer");
        //console.log("p-" + x + "no " + localStorage.getItem("no"))
        document.getElementById("ans").value;
        //console.log("vale" + y);
        localStorage.setItem('datas', JSON.stringify(this.datas));
        //console.log(this.datas[this.no - 1].cipherText);
        if (this.saveno <11) {
        this.$router.go(0);
        }
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

      //console.log("createFeedback" + document.getElementById("ans").value);
      let point = {
        scoreboardId: this.datas[this.no - 1].scoreboardId,
        cipherId: this.datas[this.no - 1].cipherId,
        plainText: document.getElementById("ans").value,
        time: x,
      };

      //console.log("point" + JSON.stringify(point));
      this.saveno = Number(localStorage.getItem("no")) + 1;
      const token = this.$store.state.auth.user.accessToken;
      //console.log("Number(localStorage.getItem(no)) + 1 == 10 ----" + localStorage.getItem("no"))
      
      if (this.saveno > 10) {
        //console.log(point)
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
        //console.log("saveno" + this.saveno)
        localStorage.setItem('no', this.saveno);
        //console.log("p-" + this.timer)
        localStorage.setItem('timer', this.timer);
        //console.log("p-" + x + "no " + localStorage.getItem("no"))
        document.getElementById("ans").value;
        //console.log("vale" + y);
        this.datas[this.no - 1].plaintext = document.getElementById("ans").value;
        localStorage.setItem('datas', JSON.stringify(this.datas));
        //console.log(this.datas[this.no - 1].cipherText);

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
    
      axios.get(`http://localhost:8080/api/scoreboard/scores/` + this.datas[0].level + "/value/10", { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.data = response.data.body

          //console.log(this.data)
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

          //console.log(error);
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
.button-54 {
  font-family: "Open Sans", sans-serif;
  font-size: 16px;
  letter-spacing: 2px;
  text-decoration: none;
  text-transform: uppercase;
  color: #000;
  cursor: pointer;
  border: 3px solid;
  padding: 0.25em 0.5em;
  box-shadow: 1px 1px 0px 0px, 2px 2px 0px 0px, 3px 3px 0px 0px, 4px 4px 0px 0px, 5px 5px 0px 0px;
  position: relative;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}

.button-54:active {
  box-shadow: 0px 0px 0px 0px;
  top: 5px;
  left: 5px;
}

@media (min-width: 768px) {
  .button-54 {
    padding: 0.25em 0.75em;
  }
}

</style>