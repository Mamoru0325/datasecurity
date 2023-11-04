<template lang="">
    <div style="background-color:#fff9f5;">
        <MyNavBar />
        <br>
        <h1 style="text-align: center;">เกมถอดรหัส</h1>
        <br>
        <div style="text-align: left; margin-left: 22%;">
            <h2 style="font-size:30;margin-left: 5%">กฎการเล่น</h2>
            <p style="font-size:20px">- ศึกษาวิธีการเข้ารหัส - ถอดรหัส ก่อนเริ่มเล่นน</p>
            <p style="font-size:20px">- เรื่องระดับความยาก โดยมี - ระดับ Easy Normal Hard</p>
            <p style="font-size:20px">- เลือกเวลาที่ต้องการคือ ข้อละ 3 นาที เเละ 5 นาที <span style="color: red"><br>(โดยเวลาที่มีให้เลือก 3 และ 5 นาทีเป็นความท้าทายที่ให้เลือก นั้น เมื่อเลือก 5 นาที คะแนนจะ * 1 และ เมื่อเลือก 3 นาที คะแนนจะ * 2 )</span></p>
            <p style="font-size:20px;color:red;">*เมื่อเริ่มแล้วกรุณา เช็คคำตอบทุกครั้งก่อนที่จะกดตกลงเพราะไม่สามารถย้อนกลับได้ </p>
        </div>
        <br>
        <div >
            <div class="card mb-3" style="max-width: 800px;margin: auto" >
                <div class="row g-0" style="background-color:#fff9f5;box-shadow: rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px, rgba(255, 255, 255, 0.08) 0px 1px 0px inset;">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8">
                    <div class="card-body" >
                        <h5 class="card-title">Easy</h5>
                        <p class="card-text">เหมาะสำหรับผู้เริ่มต้น</p>
                       
                    </div>
                    <br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart(3)" >
                        3 นาที
                    </a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart(5)" >
                        5 นาที
                    </a>
                    <br> <br>
                    </div>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 800px;margin: auto">
                <div class="row g-0">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8" style="background-color:#fff9f5;box-shadow: rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px, rgba(255, 255, 255, 0.08) 0px 1px 0px inset;">
                    <div class="card-body">
                        <h5 class="card-title">Normal</h5>
                        <p class="card-text">เหมาะสำหรับผู้คนที่เริ่มมีคล่องในการถอดรหัส</p>
                       
                    </div>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart2(3)" >
                        3 นาที
                    </a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart2(5)" >
                        5 นาที
                    </a>
                    <br> <br>
                    </div>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 800px;margin: auto">
                <div class="row g-0" style="background-color:#fff9f5;box-shadow: rgba(6, 24, 44, 0.4) 0px 0px 0px 2px, rgba(6, 24, 44, 0.65) 0px 4px 6px -1px, rgba(255, 255, 255, 0.08) 0px 1px 0px inset;">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Hard</h5>
                        <p class="card-text">เหมาะสำหรับผู้คนที่ชำนาญในการถอดรหัส</p>
                       
                    </div>
                    <br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart3(3)" >
                        3 นาที
                    </a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a class="button-54"   @click="timerstart3(5)" >
                        5 นาที
                    </a>
                    <br> <br>
                    </div>
                </div>
            </div>
        </div>
        
        
        <br>
        <MyButtonBar />
    </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
//import Send from '@/views/TimeslowView.vue'
import axios from 'axios'
export default {
    name: 'GameView',
    components: {
        MyNavBar,
        MyButtonBar,
        //Send
    },data(){
        localStorage.setItem('timer',180);
        return{
        }
    },
    props:{
        getNo:Function
    }, methods: {
        async timerstart(value) {
            console.log("1")
            localStorage.setItem('no',1);
            if(value==3){
            localStorage.setItem('timer',180);
        }else{
            localStorage.setItem('timer',300);
        }
            console.log("2")
            this.id = "easy";
            const token = this.$store.state.auth.user.accessToken;
            axios.get("http://localhost:8080/api/cipher/question/easy", { headers: { "Content-Type": "application/json" , "Authorization": 'Bearer ' + token } })
                .then((response) => {
                    this.datas = response.data.body;
                    console.log("obj"+this.datas);
                    alert("---");
                    for (let i = 0; i < 3; i++) {
                       // this.datas[i].plaintext="";
                    }
                    console.log("obj"+this.datas);
                    localStorage.setItem('datas', JSON.stringify(this.datas));
                    
                    // Retrieve the object from storage
                    var retrievedObject = localStorage.getItem('datas');

                    console.log('retrievedObject: ', JSON.parse(retrievedObject));
                    this.$router.push("/user/timeslow/"+value);
                    // href="/user/user/timeslow/"+value
                    //console.log(this.datas[0].type)
                },
                    (error) => {
                        alert("username or password is already used");
                        console.log(error)
                    })
        },async timerstart2(value) {
            localStorage.setItem('no',1);
            if(value==3){
            localStorage.setItem('timer',180);
        }else{
            localStorage.setItem('timer',300);
        }
           
            this.id = "moderate"
            const token = this.$store.state.auth.user.accessToken;
            //console.log(token)
            axios.get(`http://localhost:8080/api/cipher/question/`+ this.id, { headers: { "Content-Type": "application/json" , "Authorization": 'Bearer ' + token } })
                .then((response) => {
                    this.datas = response.data.body;
                    console.log("obj"+this.datas);
                    alert("---");
                    for (let i = 0; i < 3; i++) {
                       // this.datas[i].plaintext="";
                    }
                    console.log("obj"+this.datas);
                    localStorage.setItem('datas', JSON.stringify(this.datas));
                    
                    // Retrieve the object from storage
                    var retrievedObject = localStorage.getItem('datas');

                    console.log('retrievedObject: ', JSON.parse(retrievedObject));
                    this.$router.push("/user/timeslow/"+value);
                    //console.log(this.datas[0].type)
                },
                    (error) => {
                        alert("username or password is already used");
                        console.log(error)
                    })
        },async timerstart3(value) {
            localStorage.setItem('no',1);
            if(value==3){
            localStorage.setItem('timer',180);
        }else{
            localStorage.setItem('timer',300);
        }
           
            this.id = "hard"
            const token = this.$store.state.auth.user.accessToken;
            //console.log(token)
            axios.get(`http://localhost:8080/api/cipher/question/`+ this.id, { headers: { "Content-Type": "application/json" , "Authorization": 'Bearer ' + token } })
                .then((response) => {
                    this.datas = response.data.body;
                    console.log("obj"+this.datas);
                    alert("---");
                    for (let i = 0; i < 10; i++) {
                       // this.datas[i].plaintext="";
                    }
                    console.log("obj"+this.datas);
                    localStorage.setItem('datas', JSON.stringify(this.datas));
                    alert("new data");
                    // Retrieve the object from storage
                    var retrievedObject = localStorage.getItem('datas');

                    console.log('retrievedObject: ', JSON.parse(retrievedObject));
                    this.$router.push("/user/timeslow/"+value);
                    //console.log(this.datas[0].type)
                },
                    (error) => {
                        alert("username or password is already used");
                        console.log(error)
                    })
        }
    }
}
</script>
<style scoped>
p {
    margin-left: 8%;
}
/* CSS */
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