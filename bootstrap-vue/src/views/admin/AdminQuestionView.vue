<template>
    <MyNavBar />
    <div class="hello" style="height: 80dvb;">
        <h1 style="text-align: center;margin-top: 2%;">โจทย์คำถามที่ใช้งาน</h1>
        <br>
        <div class="row" >
            <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
                <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="..." style="margin-top: 5%">
                <div class="card-body">
                    <h5 class="card-title">การถอดรหัสแบบซีซาร์</h5>
                   <a class="btn btn-primary" style="color:#000000" @click="getLink('caesar')">ดูข้อมูล</a>
                </div>
            </div>
            <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
                <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="..." style="margin-top: 5%">
                <div class="card-body">
                    <h5 class="card-title">การถอดรหัสแบบ Vigenere</h5>
                    <a class="btn btn-primary" style="color:#000000" @click="getLink('vigenere')">ดูข้อมูล</a>
                </div>
            </div>
            <div class="card" style="width: 25rem; margin: auto; border: 1px solid #000;">
                <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="..." style="margin-top: 5%">
                <div class="card-body">
                    <h5 class="card-title">การถอดรหัสแบบ Permutation</h5>
                    <a class="btn btn-primary" style="color:#000000" @click="getLink('permutation')">ดูข้อมูล</a>
                </div>
            </div>
        </div>
    </div>
    <MyButtonBar />
</template>
  
<script>
import MyNavBar from '@/components/MyNavBar.vue';
import MyButtonBar from '@/components/MyButtonBar.vue';

export default {
    name: 'AdminQuestionView',
    props: {
        msg: String,
    },
    components: {
        MyNavBar,
        MyButtonBar
    },methods:{
        getLink(values){
            console.log(values)
            this.$router.push("/admin/question/type/"+values);
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
}
</style>
  