<template>
  <div>
    <MyNavBar />
    <br>
    <div class="row">
      <div>
        <h1 style="text-align: center;">จัดอันดับ {{ score }}</h1>
      </div>
    </div>
    <br>
    <div class="container">
      <div class="dropdown d-flex flex-row-reverse">
        <select name="score" id="score" v-model="score" class="form-select form-control" style="width: 20%;" @click="getData">
          <option value="ระดับง่าย" required>ระดับง่าย</option>
          <option value="ระดับปานกลาง">ระดับปานกลาง</option>
          <option value="ระดับยาก">ระดับยาก</option>
        </select>
      </div>
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
            <td>{{ da.name }}</td>
            <td>{{ da.score }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <br>
    <MyButtonbarBar class="bottom" />
  </div>
</template>
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonbarBar from '@/components/MyButtonBar.vue'
import axios from 'axios';
export default {
  name: 'ScoreView',
  components: {
    MyNavBar,
    MyButtonbarBar
  }, data() {
    return {
      score: "ระดับง่าย",
      data: [],
      id: "easy"
    }
  }, methods: {
    async getData() {
      if (this.score === 'ระดับง่าย') {
        this.id = "easy"
      }
      if (this.score === "ระดับปานกลาง") {
        this.id = "moderate"
      }
      if (this.score === "ระดับยาก") {
        this.id = "hard"
      }
      const token = this.$store.state.auth.user.accessToken;
      console.log(token)
      console.log(this.id)
      
      axios.get(`http://localhost:8080/api/scoreboard/scores/`+this.id+'/value/20', { headers: { "Content-Type": "application/json" ,"Authorization": 'Bearer '+token } })
            .then((response) => {
              this.data = response.data.body
              
              console.log(this.data[0])
            },
              (error) => {
                alert("username or password is already used");
                console.log(error)
              })
    }
  }, mounted() {
    this.getData()
  }
}
</script>
<style scoped>
.bottom {
  position: absolute;
  bottom: 0;

  width: 100%;
}

.last {
  padding-right: 0;
}
</style>