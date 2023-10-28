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
            <h2>โจทย์</h2>

            <h2>จงเติมคำตอบ</h2>
        </div>
      <div style="margin-left: 20%;margin-right: 20%;">
        <textarea rows="3"></textarea>
        
        <button type="submit"  @click="createFeedback">Submit</button>
      </div>
        
        <br>
        <p> {{this.timer}}</p>
        <br>
        <MyButtonBar />
    </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
import axios from 'axios'

//import Countdown from 'vue3-countdown'

export default {
    name: 'GameView',
    props: ['aboutNo'],
    components: {
        MyNavBar,
        MyButtonBar,
        //Countdown
    },
    data() {
        let interval = setInterval(() => {
                if (this.timer === 0) {
                    clearInterval(interval)
                } else {
                    this.timer--
                    console.log(this.timer)
                    localStorage.setItem('timer',this.timer);
                }
            }, 1000)
        
        return { timer:localStorage.getItem("timer") }
        

        //console.log(this.$route.params.time)
    }, methods: {
        async createFeedback() {
            
            console.log("p-"+this.timer)
            localStorage.setItem('timer',this.timer);
            let x = localStorage.getItem("timer");
            console.log("p-"+x)
        }
        
    }

}
</script>
<style scoped>
.bottom {
    position: absolute;
    bottom: 0;

    text-align: center;
    width: 100%;

    border: 1px solid green;
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