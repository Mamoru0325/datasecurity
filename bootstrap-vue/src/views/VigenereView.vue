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
<template>
  <div style="background-color:#fff9f5;">
    <MyNavBar />
    <div class="container">
      <br>
      <h1 style="text-align: center;">สอนการเข้ารหัสและถอดรหัส Vigenere</h1>
      <div style="text-align: left; ">
        <h4>การเข้ารหัสข้อมูล Vigenere และ ความเป็นมา<br></h4>
        <p>• Vigenere cipher เป็นการเข้ารหัสแบบซีเคร็ทคีย์ (Secret Key) หรือ Symmetric Key Cryptography
          ที่อาศัยพื้นฐานเดียวกันกับ Caesar<br>

          • หลักการของ Vigenere cipher คือ จะใช้ Key ที่เป็นคำมาเรียงต่อๆ กัน แล้วเข้ารหัสโดยสร้าง Caesar Cipher
          จากตัวอักษรที่ปรากฏอยู่ใน Key
          <br>
          • ตัวอย่าง มี Plaintext : ATTACK AT DAWN และเลือกใช้ Keyword : LEMON
          นำ Plaintext มาเรียงคู่กับ Keyword ให้ได้ความยาวเท่ากันดังนี้<br>
        </p>
        <h5 style="text-align: center;">Plaintext : ATTACK AT DAWN <br></h5>
        <h5 style="text-align: center;">Key : LEMONL EM ONLE <br></h5>
        <h5 style="text-align: center;">Ciphertext : LXFOPV EF RNHR <br></h5>

        <p>- ตัวอักษรตัวแรก - A จะถูกเข้ารหัสด้วย Caesar Cipher Key L<br>
          - ตัวอักษรตัวที่ 2 - T จะถูกเข้ารหัสด้วย Caesar Cipher Key E<br>
          - ตัวอักษรตัวที่ 3 - T จะถูกเข้ารหัสด้วย Caesar Cipher Key M<br>
          - ตัวอักษรตัวที่ 4 - A จะถูกเข้ารหัสด้วย Caesar Cipher Key O<br>
          - ตัวอักษรตัวที่ 5 - C จะถูกเข้ารหัสด้วย Caesar Cipher Key N<br>
          - และเรียงต่อไปเรื่อยๆ จนกว่าจะครบประโยค
          <br>
        </p>
        <hr>
        <h4>การถอดรหัสข้อมูล Vigenere <br></h4>
        <p>การถอดรหัสก็ให้ทํากระบวนการย้อนกลับเหมือนกับของ Caesar แต่ ต้องรู้Keyword<br>
          • Cipher Text : LXFOPV EF RNHR และ Keyword : LEMON
          นํา Cipher Text มาเรียงคู่กับ Keyword ให้ได้ความยาวเท่ากันดังน<br>
        </p>
        <h5 style="text-align: center;">Ciphertext : LXFOPV EF RNHR <br></h5>
        <h5 style="text-align: center;">Key : LEMONL EM ONLE <br></h5>
        <h5 style="text-align: center;">Plaintext : ATTACK AT DAWN <br></h5>
        <p>◦ ตัวอักษรตัวแรก-L จะถูกถอดรหัสด้วย Caesar Cipher Key L <br>
          ◦ ตัวอักษรตัวที่ 2-X จะถูกถอดรหัสด้วย Caesar Cipher Key E <br>
          ◦ ตัวอักษรตัวที่ 3-F จะถูกถอดรหัสด้วย Caesar Cipher Key M <br>
          ◦ ตัวอักษรตัวที่ 4-O จะถูกถอดรหัสดั วย Caesar Cipher Key O <br>
          ◦ ตัวอักษรตัวที่ 5-P จะถูกถอดรหัสด้วย Caesar Cipher Key N <br>
            และเรียงต่อไปเรื่อยๆ จนกว่าจะครบประโยค</p>
      </div>

      <div>
        <h4>แบบฝึกหัดเพื่อทดสอบความเข้าใจ <span style="color: red;"> (ลำดับตัวอักษร a b c d e f g h I j k l m n o p q r s
            t
            u v w x y z) </span> <br></h4>
        <p>1.ให้ทำการเข้ารหัสข้อความนี้ " morning " <span style="color: red;"> Key = "love" </span></p>
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
        <p>2.ให้ทำการถอดรหัสข้อความนี้ " hvzvp lfz jcp " <span style="color: red;"> Key = "love" </span></p>
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
  name: 'vigenereView',
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
        type: 2,
        plainText: "morning",
        cipherKey: "love",
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
          type: 2,
          plainText: "morning",
          cipherKey: "love",
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
  