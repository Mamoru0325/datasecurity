<template>
  <div style="background-color:#fff9f5;">
    <MyNavBar />
    <br>
    <div class="row">
      <div>
        <h1 style="text-align: center;">ข้อความเสนอแนะจากผู้ใช้งาน</h1>
      </div>
    </div>
    <br>
    <div class="container" style="height: 50dvb;">
      <div class="dropdown d-flex flex-row-reverse" style="margin-bottom: 1%;">
        <select name="year" id="year" v-model="year" class="form-select form-control" style="width: 13%;" 
          @click="getData">
          <option value=""></option>
          <option v-for="item in years" :key="item" :value=item>{{item}}</option>
        </select>
        <h4 style="margin-left: 1%;">ปี :</h4>
        <select name="month" id="month" v-model="month" class="form-select form-control" style="width: 13%;"
          @click="getData">
          <option value=""></option>
          <option value="1">มกราคม</option>
          <option value="2">กุมภาพันธ์</option>
          <option value="3">มีนาคม</option>
          <option value="4">เมษายน</option>
          <option value="5">พฤษภาคม</option>
          <option value="6">มิถุนายน</option>
          <option value="7">กรกฦาคม</option>
          <option value="8">สิงหาคม</option>
          <option value="9">กันยายน</option>
          <option value="10">ตุลาคม</option>
          <option value="11">พฤศจิกายน</option>
          <option value="12">ธันวาคม</option>
        </select>
        <h4>เดือน :</h4>
      </div>
      <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0">
        <thead style="border: 100cm;">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">text</th>
            <th scope="col">date</th>
          </tr>
        </thead>
        <tbody v-for="(item, index) in paginatedData" :key="index">
          <tr>
            <th scope="row">{{ this.startInt + index + 1 }}</th>
            <td>{{ item.username }}</td>
            <td>{{ item.text }}</td>
            <td>{{ date(item.date) }}</td>
          </tr>
        </tbody>
      </table>

    </div>
    <div>
      <pagination :currentPage="currentPage" :totalPages="totalPages" @page-changed="changePage" />
    </div>

    <br>
    <MyButtonbarBar />
  </div>
</template>

<script>
import MyNavBar from '@/components/MyNavBar.vue'
import MyButtonbarBar from '@/components/MyButtonBar.vue'
import axios from 'axios';
import Pagination from './Pagination.vue';
export default {
  name: 'FeedbackView',
  components: {
    MyNavBar,
    MyButtonbarBar,
    Pagination,
  }, data() {
    return {
      year: null,
      month: null,
      years: [],
      data: [],
      id: "easy",
      startInt: 0,
      currentPage: 1,
      itemsPerPage: 8,
      totalItems: 100,
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.data.length / this.itemsPerPage);
    },
    paginatedData() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.data.slice(startIndex, endIndex);
    }
  }, methods: {
    changePage(newPage) {
      this.currentPage = newPage;
      this.startInt = (this.currentPage - 1) * this.itemsPerPage;
    },date(value){
      console.log(value)
      const today = new Date(value);
      const yyyy = today.getFullYear();
      let mm = today.getMonth() + 1; // Months start at 0!
      let dd = today.getDate();

      if (dd < 10) dd = '0' + dd;
      if (mm < 10) mm = '0' + mm;

      const formattedToday = dd + '/' + mm + '/' + yyyy;
      return formattedToday;
    },
    async getData() {
      const token = this.$store.state.auth.user.accessToken;
      if(this.month && this.year){
        axios.get("http://localhost:8080/api/feedback/month/"+this.month+" /year/"+this.year , { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then((response) => {
            console.log(response.data.body);
            this.data = response.data.body
          },
            (error) => {
              alert("username or password is already used");
              console.log(error)
            })
      }else{
        axios.get("http://localhost:8080/api/feedback/" , { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
          .then((response) => {
            console.log(response.data.body);
            this.data = response.data.body
          },
            (error) => {
              alert("username or password is already used");
              console.log(error)
            })
      }
    },async getYear() {
      const token = this.$store.state.auth.user.accessToken;

      await axios.get("http://localhost:8080/api/feedback/year/" , { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.years = response.data.body
        },
          (error) => {
            alert("username or password is already used");
            console.log(error)
          })
    }
  }, mounted() {
    this.getYear()
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