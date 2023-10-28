<template>
  <div>
    <MyNavBar />
    <h1 style="text-align: center;margin-top: 1%;">ข้อเสนอแนะแก่ผู้พัฒนา</h1>
    <div style="margin-left: 20%;margin-right: 20%;">
      <textarea rows="5" v-model="text" name="text"
        :class="v$.text.$error === true ? 'text-fields-error' : 'text-fields'"></textarea>
      <p1 class="text-red-500 font-thin" v-for="error of v$.text.$errors" :key="error.$uid" style="color: red;">
        {{ error.$message }}
      </p1>
      <button type="submit" @click="createFeedback">Submit</button>
    </div>
    <MyButtonBar class="bottom" />
  </div>
</template>
  
<script>
import MyButtonBar from '@/components/MyButtonBar.vue'
import MyNavBar from '@/components/MyNavBar.vue'
import useVuelidate from "@vuelidate/core";
import axios from 'axios';
import { required, maxLength } from "@vuelidate/validators";
export default {
  name: 'FeedbackView',
  components: {
    MyButtonBar,
    MyNavBar
  }, data() {
    return {
      v$: useVuelidate(),
      text: ""
    }
  },
  validations() {
    return {
      text: { required, max: maxLength(255), $autoDirty: true, }
    };
  }, methods: {
    async createFeedback() {
      this.v$.$touch();
      if (!this.v$.$error) {
        let feedback = {
          text: this.text
        };
        console.log(feedback)
        const token = this.$store.state.auth.user.accessToken;
        axios.post(`http://localhost:8080/api/feedback/`,feedback, { headers: { "Content-Type": "application/json","Authorization": 'Bearer '+token } })
          .then(() => {
            alert("send Feedback success")
            this.$router.push("/user");
          },
            (error) => {
              alert("send Feedback unsuccess");
              console.log(error)
            })
      }
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

p {
  margin: 0 0 5px;
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
</style>
  