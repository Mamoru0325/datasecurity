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
                    <a class href="/user/timeslow/1/1800/0"  @click="createFeedback" >
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
        <Send aboutNo="1"> </Send>
        
        <br>
        <MyButtonBar />
    </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonBar from '@/components/MyButtonBar.vue'
import Send from '@/views/TimeslowView.vue'

export default {
    name: 'GameView',
    components: {
        MyNavBar,
        MyButtonBar,
        Send
    },data(){
        localStorage.setItem('timer',1800);
        return{
        }
    },
    props:{
        getNo:Function
    }, methods: {
        async timerstart() {
            
            localStorage.setItem('timer',1800);
        }
    }
}
</script>
<style scoped>
    p {
        margin-left: 5%;
    }
</style>