<template>
  <div style="background-color:#fff9f5;">
    <MyNavBar />
    <div class="container">
      <br>
      <h1 style="text-align: center;">สอนการเข้ารหัสและถอดรหัส Permutation <br>หรือ Columnar Transposition Cipher(สำหรับเล่นบนเว็บนี้)</h1>
      <div style="text-align: left; ">
        <h4>การเข้ารหัสข้อมูล Permutation<br></h4>
        <p>  • ตัวอย่าง มี Plaintext :  Once upon a time there was a little girl called snow whit และ มี่ key เป็น 2,4,1,3,5 หมายถึง<br>
            ตำแหน่ง 1 ไป ช่อง 3 ตำแหน่ง 2 ไป ช่อง 1 ตำแหน่ง 3 ไป ช่อง 4 ตำแหน่ง 4 ไป ช่อง 2 ตำแหน่ง 5 ไป ช่อง 5 ดังนั้น มี lenght key 5
        </p>
        <h5 style="text-align: center;">Plaintext :  What you want <br></h5><br>
        <p style="font-size: 20px;">1. แบ่งช่องช่องหนึ่งมี 5 ตัว ตาม lenght key และ เปลี่ยนเป็นตัวเล็กทั้งหมด</p>
        <p style="font-size: 20px;">whaty ouwan t</p>
        <p style="font-size: 20px;">2. เพิ่มข้อความให้เต็มช่อง</p>
        <p style="font-size: 20px;">whaty ouwan t<span style="font-size: 20px;color: red">aaaa</span></p>
        <p style="font-size: 20px;">3. สลับตำแหน่งตาม key ที่มีบอก  </p>
        <p style="font-size: 20px;">htway uaown aataa</p>
        <p style="font-size: 20px;">4. ลบช่องว่าง </p>
        <h5 style="text-align: center;">Ciphertex :   htwayuaownaataa <br></h5><br>
        <hr>
        <h4>การถอดรหัสข้อมูล Permutation <br></h4>
        <h5 style="text-align: center;">Ciphertex :   htwayuaownaataa และ lenght key เป็น 5<br></h5><br>
        <p style="font-size: 20px;">1.การถอดรหัสก็ให้ทํากระบวนการย้อนกลับ โดยขั้นแรกให้ทำการแบ่งช่อง ช่องละ 5 ตัว ตาม lenght key <br></p>
        <p style="font-size: 20px;">htway uaown aataa<br></p>
        <p style="font-size: 20px;">2.หลังจากนั้นทำการสุ่มเดา เรียงคำให้มีความหมายให้ได้ เช่น htway มีคำน่าสนคือ whaty จะเห็นว่า ช่อง 1 ย้ายไป 2  ช่อง 2 ย้ายไป 4   ช่อง 3 ย้ายไป 1   ช่อง 4 ย้ายไป 2   ช่อง 5 ย้ายไป 5  <br></p>
        <p style="font-size: 20px;">3.เดาว่า key คือ 2,4,1,3,5 (key ที่ใช่เข้ารหัส) จะได้<br></p>
        <p style="font-size: 20px;">whaty ouwan t<span style="font-size: 20px;color: red">aaaa</span><br></p>

        <h5 style="text-align: center;">Plaintext :  What you want และ key เป็น 2,4,1,3,5 <br></h5><br>
      </div>

      <div>
        <h4>แบบฝึกหัดเพื่อทดสอบความเข้าใจ <span style="color: red;"> (ลำดับตัวอักษร a b c d e f g h I j k l m n o p q r s
            t
            u v w x y z) </span> <br></h4>
        <p>1.ให้ทำการเข้ารหัสข้อความนี้ " morning " <span style="color: red;"> Key = "3,1,2" คือ ตำแหน่ง 1 ไป ช่อง 2 ตำแหน่ง 2 ไป ช่อง 3 ตำแหน่ง 3 ไป ช่อง 1 </span></p>
        <div class="row" style="margin-left: 18%;margin-top: 2%;">
          <div class="col-md-10">
            <input type="text" class="form-control" aria-label="plainText" id="plainText" v-model="plainText"
              :class="v$.plainText.$error === true ? 'text-fields-error' : 'text-fields'">
            <h6 class="text-red-500 text-xs font-thin" style="color: red;" v-for="error of v$.plainText.$errors"
              :key="error.$uid">
              {{ error.$message }}
            </h6>
          </div>
          <div class="col-md-2">
            <button class="button-33" style="" @click="check()">ส่งคำตอบ</button>
          </div>
        </div>
        <div class="row" style="margin-left: 18%;margin-top: 2%;margin-bottom: 2%;">
          <div class="col-md-10">
            <label for="CipherText" class="form-label">เฉลย</label>
            <input type="text" class="form-control" :value="this.CipherText" disabled>
          </div>
          <div class="col-md-2" style="margin-top: 3%;">
            <button class="button-33" style="" @click="tranfrom()">เฉลย</button>
          </div>
        </div>
        <p>2.ให้ทำการถอดรหัสข้อความนี้ " hvzvp lfz jcp " <span style="color: red;"> lenght = 3 </span></p>
        <div class="row" style="margin-left: 18%;margin-top: 2%;">
          <div class="col-md-10">
            <input type="text" class="form-control" aria-label="plainText2" id="plainText2" v-model="plainText2">
          </div>
          <div class="col-md-2">
            <button class="button-33" style="" @click="check2()">ส่งคำตอบ</button>
          </div>
        </div>
        <div class="row" style="margin-left: 18%;margin-top: 2%;margin-bottom: 4%;">
          <div class="col-md-10">
            <label for="CipherText2" class="form-label">เฉลย</label>
            <input type="text" class="form-control" :value="this.CipherText2" disabled>
          </div>
          <div class="col-md-2" style="margin-top: 3%;">
            <button class="button-33" style="" @click="tranfrom2()">เฉลย</button>
          </div>
        </div>
      </div>
    </div>
    <br>
    <MyButtonbar />
  </div>
</template>
<script>
// @ is an alias to /src
import MyButtonbar from '@/components/MyButtonBar.vue'
import MyNavBar from '@/components/MyNavBar.vue'
import useVuelidate from "@vuelidate/core";
import axios from 'axios';
import { required, minLength, maxLength } from "@vuelidate/validators";
export default {
  name: 'PermutationView',
  components: {
    MyButtonbar,
    MyNavBar
  }, data() {
    return {
      v$: useVuelidate(),
      score: "easy",
      type: "",
      plainText2: "",
      plainText: "",
      CipherText: "",
      CipherText2: "",
    }
  }, validations() {
    return {
      plainText: { required, min: minLength(2), max: maxLength(50), $autoDirty: true },
    };
  }, methods: {
    async tranfrom() {
      let data = {
        type: 3,
        plainText: "morning",
        cipherKey: "3,1,2",
        level: this.score
      };
      const token = this.$store.state.auth.user.accessToken;
      axios.post("http://localhost:8080/api/cipher/precreate", data, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.CipherText = response.data.body.cipherText
        },
          (error) => {
            alert("Error");
            console.log(error)
          })
    }, async check() {
      this.v$.$touch();
      if (!this.v$.$error) {
        let data = {
          type: 3,
          plainText: "morning",
          cipherKey: "3,2,1",
          level: this.score
        };
        const token = this.$store.state.auth.user.accessToken;
        axios.post("http://localhost:8080/api/cipher/precreate", data, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then((response) => {
            if (response.data.body.cipherText == this.plainText) {
              alert("คำตอบถูกต้อง")
            }
            else {
              alert("คำตอบผิด")
            }
          },
            (error) => {
              alert("Error");
              console.log(error)
            })
      }
    }, check2() {
      if ("where are you" == this.plainText2) {
        alert("คำตอบถูกต้อง")
      }
      else {
        alert("คำตอบผิด")
      }
    }, tranfrom2() {
      this.CipherText2 = "where are you"
    }
  }
}
</script>
<style scoped>
h4 {
  align-items: first baselines;
  margin-left: 15%;
  color: rgb(42, 165, 54);
}

h5 {
  font-size: 25px;
  color: rgb(255, 0, 0);
}

p {
  font-size: 18px;
  margin-left: 18%;
}

.button-33 {
  background-color: #00fff2;
  border-radius: 100px;
  box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset, rgba(44, 187, 99, .15) 0 1px 2px, rgba(44, 187, 99, .15) 0 2px 4px, rgba(44, 187, 99, .15) 0 4px 8px, rgba(44, 187, 99, .15) 0 8px 16px, rgba(44, 187, 99, .15) 0 16px 32px;
  color: green;
  cursor: pointer;
  display: inline-block;
  font-family: CerebriSans-Regular, -apple-system, system-ui, Roboto, sans-serif;
  padding: 7px 20px;
  text-align: center;
  text-decoration: none;
  transition: all 250ms;
  border: 0;
  font-size: 16px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}
</style>