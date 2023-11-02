<template lang="">
    <div>
        <MyNavBar />
        <br>
        <h1 style="text-align: center;">เกมถอดรหัส</h1>
        <br>
        <div style="text-align: left; margin-left: 25%">
            <h2 >กฎการเล่น</h2>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
            <p>- sadasdasdsadsadsadsadsadasdasdadadsa</p>
        </div>
        <br>
        <div >
            <div class="card mb-3" style="max-width: 800px;margin: auto" >
                <div class="row g-0">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Easy</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                    <a class href="/user/timeslow/1/1800/0"  @click="timerstart" >
                        3 นาที
                    </a>
                    </div>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 800px;margin: auto">
                <div class="row g-0">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Normal</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                    </div>
                </div>
            </div>
            <div class="card mb-3" style="max-width: 800px;margin: auto">
                <div class="row g-0">
                    <div class="col-md-4">
                    <img src="https://html.com/wp-content/uploads/flamingo.webp" class="img-fluid rounded-start" >
                    </div>
                    <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Hard</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
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
        async timerstart() {
            localStorage.setItem('no',1);
            localStorage.setItem('timer',1800);
            if (this.score === 'ระดับง่าย') {
                this.id = "easy"
            }
            if (this.score === "ระดับปานกลาง") {
                this.id = "moderate"
            }
            if (this.score === "ระดับยาก") {
                this.id = "hard"
            }
            this.id = "easy"
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
        margin-left: 5%;
    }
</style>