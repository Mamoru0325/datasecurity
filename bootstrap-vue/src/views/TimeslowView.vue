<template lang="">
    <MyNavBar />
    <div id="outer">
    <div>
        <br>
        <h1 style="text-align: center;">เกมถอดรหัส</h1>
        <h1 style="text-align: center;color:red;">เวลาคงเหลือ - {{this.timer}}</h1>
        <br>
        <div style="text-align: left; margin-left: 25%">
            <h2 >กฎการเล่น</h2>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
           <h2 v-for="(da, index) in this.datas" :key="da">
                <div v-if="index+1 == this.no">
                โจทย์{{ index+1 }} {{da.type}} {{da.cipherText}}
                </div>  
            </h2>
        
            <h2>จงเติมคำตอบ</h2>
        
        <!--     <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0">
        <thead style="border: 100cm;">
          <tr>
            <th scope="col">No. </th>
            <th scope="col">cipherText</th>
            <th scope="col">type</th>
            <th scope="col">type</th>
          </tr>
        </thead>
        <tbody v-for="(item, index) in paginatedData" :key="index">
          <tr>
            <th scope="row">{{ this.startInt + index + 1 }}</th>
            <td>{{ item.cipherText}}</td>
            <td>{{ item.type }}</td>
            <textarea rows="3" id="ans" value=""></textarea>
            <td>{{ date(item.date) }}</td>
          </tr>
        </tbody>
        
            </table>
       -->
        
      
        </div>
      <div style="margin-left: 20%;margin-right: 20%;">
    <!-- <pagination :currentPage="currentPage" :totalPages="totalPages" @page-changed="changePage" /> -->
        <textarea rows="3" id="ans" value=""></textarea>
        
        <a   @click="createFeedback" href="/user/timeslow/1/1800/0">Submit</a>
      </div>
        
        <br>
        
        <br>
        
    </div>
    <div>
        <div class="container">
      
      <table class="table table-striped">
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
            <td>{{ da.userName }}</td>
            <td>{{ da.score }}</td>
          </tr>
        </tbody>
      </table>
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
        MyButtonBar,
   //    Pagination
        //Countdown
    },
    data() {
        let interval = setInterval(() => {
            if (this.timer === 0) {
                clearInterval(interval)
            } else {
                this.timer--
                console.log(this.timer)
                localStorage.setItem('timer', this.timer);
            }
        }, 1000)

        return {
            timerset:localStorage.setItem('timer',180),
            timer: localStorage.getItem("timer"),
            score: "ระดับง่าย",
            datas: [],
            data:[],
            no: localStorage.getItem("no"),
            startInt: 0,
            currentPage: 1,
            itemsPerPage: 1,
            totalItems: 100,
        }
        //console.log(this.$route.params.time)
    },
    computed: {
        totalPages() {
            return Math.ceil(this.datas.length / this.itemsPerPage);
        },
        paginatedData() {
            const startIndex = (this.currentPage - 1) * this.itemsPerPage;
            const endIndex = startIndex + this.itemsPerPage;
            //var retrievedObject = localStorage.getItem('datas');
            //this.datas = JSON.parse(retrievedObject);
            console.log(startIndex);
            console.log(endIndex);
            return this.datas.slice(startIndex, endIndex);
        }
    }, methods: {
        async createFeedback() {
            let x = localStorage.getItem("timer");
            console.log("createFeedback"+ document.getElementById("ans").value);
             let point = {
                scoreboardId: this.datas[this.no - 1].scoreboardId,
                cipherId:this.datas[this.no - 1].cipherId,
                plainText:document.getElementById("ans").value,
                time:x,
            };
            console.log("point"+JSON.stringify(point));
            this.saveno = Number(localStorage.getItem("no")) + 1;
            console.log("saveno" + this.saveno)
            localStorage.setItem('no', this.saveno);
            console.log("p-" + this.timer)
            localStorage.setItem('timer', this.timer);
            console.log("p-" + x + "no " + localStorage.getItem("no"))
            var y = document.getElementById("ans").value;
            console.log("vale" + y);
            alert("check" + y);
            this.datas[this.no - 1].plaintext = document.getElementById("ans").value;
            localStorage.setItem('datas', JSON.stringify(this.datas));
            console.log(this.datas[this.no - 1].cipherText);
            //const token = this.$store.state.auth.user.accessToken;
            
            axios.post(`http://localhost:8080/api/cipher/precreate`,point)
          .then(() => {
            alert("send Feedback success")
            //this.$router.push("/user");
          },
            (error) => {
              alert("send Feedback unsuccess");
              console.log(error)
            })

        }, changePage(newPage) {
            this.currentPage = newPage;
            this.startInt = (this.currentPage - 1) * this.itemsPerPage;
        }, async getData() {
            var retrievedObject = localStorage.getItem('datas');
            console.log('retrievedObject: ', JSON.parse(retrievedObject));
            this.datas = JSON.parse(retrievedObject);
            const token = this.$store.state.auth.user.accessToken;
      console.log(token)
      console.log(this.id)
      
      axios.get(`http://localhost:8080/api/scoreboard/scores/` + "easy", { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.data = response.data.body

          console.log(this.data[0])
        },
          (error) => {
            alert("username or password is already used");
            console.log(error)
          })
    

            // if (this.score === 'ระดับง่าย') {
            //     this.id = "easy"
            // }
            // if (this.score === "ระดับปานกลาง") {
            //     this.id = "moderate"
            // }
            // if (this.score === "ระดับยาก") {
            //     this.id = "hard"
            // }
            // const token = this.$store.state.auth.user.accessToken;
            // axios.get(`http://localhost:8080/api/cipher/question/`+ this.id, { headers: { "Content-Type": "application/json" , "Authorization": 'Bearer ' + token } })
            //     .then((response) => {
            //         this.datas = response.data.body

            //         //console.log(this.datas[0].type)
            //     },
            //         (error) => {
            //             alert("username or password is already used");
            //             console.log(error)
            //         })
            const myTimeout = setTimeout(myGreeting, 180000);
            function myGreeting() {
                this.saveno = Number(localStorage.getItem("no")) + 1;
            console.log("saveno" + this.saveno)
            localStorage.setItem('no', this.saveno);
            console.log("p-" + this.timer)
            localStorage.setItem('timer', this.timer);
            let x = localStorage.getItem("timer");
            console.log("p-" + x + "no " + localStorage.getItem("no"))
            var y = document.getElementById("ans").value;
            console.log("vale" + y);
            alert("check" + y);
            //this.datas[this.no - 1].plaintext = document.getElementById("ans").value;
            localStorage.setItem('datas', JSON.stringify(this.datas));
            console.log(this.datas[this.no - 1].cipherText);
                location.href = "/user/timeslow/1/1800/0"
            }
            console.log(myTimeout);
        }
        
    }, mounted() {
        this.getData()
    }

}
</script>
<style scoped>
table{
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