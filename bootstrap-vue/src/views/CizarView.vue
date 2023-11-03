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
      <h1 style="text-align: center;">สอนการเข้ารหัสและถอดรหัส Caesar</h1>
      <div style="text-align: left; ">
        <h4>การเข้ารหัสข้อมูล Caesar และ ความเป็นมา<br></h4>
        <p>• เป็นการแทนค่าแต่ละตัวอักษรด้วยสัญลักษณ์เพียงตัวเดียว เป็นวิธีทีง่ายที่สุด ใช้มาตั้งแต่สมัยจูเลียส ซี<br>
          &ensp;ซาร์ ในการเข้ารหัสเนื้อความจดหมายส่งไปให้ทัพทหารระหว่างการรบ<br>
          • ตัวอย่าง ใช้ความสัมพันธ์ของอักษรในภาษาอังกฤษ 26 ตัว โดยที่ ตัวอักษรใน Cipher Text จะได้<br>
          &ensp;จาก Plain Text + ไปยัง 3 ลำดับของตัวอักษรในภาษาอังกฤษ ในทางกลับกัน Plain Text จะเท่ากับ<br>
          &ensp;Cipher Text - ไปยัง 3 ลำดับของตัวอักษรในภาษาอังกฤษ<br><br></p>
        <h5 style="text-align: center;">Plain Text : a b c d e f g h i j k l m n o p q r s t u v w x y z</h5><br>
        <h5 style="text-align: center;">Cipher Text : d e f g h i j k l m n o p q r s t u v w x y z a b c (กรณี key = 3)
        </h5><br>
        <p>&ensp;เช่น Love You --- oryh brx<br></p>
        <hr>
        <h4>การถอดรหัสข้อมูล Caesar <br></h4>
        <p>ซึ่งวิธีนี้เมื่อมีการขโมย Cipher Text สามารถถอดได้ไม่ยากนัก เนื่องจากมีคำตอบที่เป็นไปทั้งหมด 25 คำตอบ <br>
          • ซึ่งสามารถทดสอบการแทนที่ตัวอักษรไปเรื่อยๆ ก็จะเจอข้อความที่สามารถอ่านได้ <br>
          ตัวอย่าง เช่น การถอดรหัสข้อมูล 0ryh brx จากด้านบน<br>
          &ensp;ลำดับตัวอักษร a b c d e f g h I j k l m n o p q r s t u v w x y z <br>
          &ensp;เลื่อนกลับ 1 ตำแหน่ง : nqxq aqw <br>
          &ensp;เลื่อนกลับ 2 ตำแหน่ง : mpwf zpv <br>
          &ensp;เลื่อนกลับ 3 ตำแหน่ง : love you ก็จะเจอคำที่สามารถอ่านได้</p>
      </div>

      <div>
        <h4>แบบฝึกหัดเพื่อทดสอบความเข้าใจ <span style="color: red;"> (ลำดับตัวอักษร a b c d e f g h I j k l m n o p q r s
            t
            u v w x y z) </span> <br></h4>
        <p>1.ให้ทำการเข้ารหัสข้อความนี้ " morning " <span style="color: red;"> Key = 5 </span></p>
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
        <p>2.ให้ทำการถอดรหัสข้อความนี้ " bmjwj fwj dtz " </p>
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
  name: 'CizarView',
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
        type: 1,
        plainText: "morning",
        cipherKey: "5",
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
          type: 1,
          plainText: "morning",
          cipherKey: "5",
          level: this.score
        };
        const token = this.$store.state.auth.user.accessToken;
        axios.post("http://localhost:8080/api/cipher/precreate", data, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then((response) => {
            if (response.data.body.cipherText == (this.plainText + " ")) {
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
  