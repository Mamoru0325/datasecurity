<template>
  <div>
    <MyNavBar />
    <br>
    <div class="row">
      <div>
        <h1 style="text-align: center;">คำถาม ({{ this.type }})</h1>
      </div>
    </div>
    <br>
    <div class="container" style="height: 50dvb;">
      <div class="dropdown d-flex flex-row-reverse">
        <button class="button-33" @click="getAddLink(this.type)">เพิ่มคำถาม</button>
        <select name="score" id="score" v-model="score" class="form-select form-control"
          style="width: 20%;margin-right: 1%;" @click="getData">
          <option value="easy" required>ระดับง่าย</option>
          <option value="moderate">ระดับปานกลาง</option>
          <option value="hard">ระดับยาก</option>
        </select>
      </div>
      <table cellspacing="0" style="margin-top: 1%;">
        <thead style="border: 100cm;">
          <tr>
            <th scope="col" style="text-align: center;width: 8%;">#</th>
            <th scope="col">PlainText</th>
            <th scope="col">Key</th>
            <th scope="col" style="width: 18%;">Edit</th>
            <th scope="col" style="width: 18%;">Delete</th>
          </tr>
        </thead>
        <tbody v-for="(item, index) in paginatedData" :key="index">
          <tr>
            <th scope="row" style="text-align: center">{{ this.startInt + index + 1 }}</th>
            <td>{{ item.plainText }}</td>
            <td>{{ item.cipherKey }}</td>
            <td style="text-align: center">
              <button class="button-33" :value=item.cipherId @click="edit(item.cipherId)">แก้ไขคำถาม</button>
            </td>
            <td style="text-align: center">
              <button class="button-33" :value=item.cipherId @click="deleteById(item.cipherId)">ลบคำถาม</button>
            </td>
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
  name: 'TypeQuestion',
  components: {
    MyNavBar,
    MyButtonbarBar,
    Pagination,
  },
  data() {
    return {
      score: "easy",
      type: "",
      typeId: 0,
      data: [],
      startInt: 0,
      currentPage: 1,
      itemsPerPage: 8,
    }
  },
  computed: {
    currentUser() {

      return this.$route.params
    },
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
    },
    async getData() {
      if (this.type == "caesar") {
        this.typeId = 1;
      }
      if (this.type == "vigenere") {
        this.typeId = 2;
      }
      if (this.type == "permutation") {
        this.typeId = 3;
      }
      const token = this.$store.state.auth.user.accessToken;
      axios.get("http://localhost:8080/api/cipher/" + this.score + "/" + this.typeId, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then((response) => {
          this.data = response.data.body
          console.log(this.data)
        },
          (error) => {
            alert("username or password is already used");
            console.log(error)
          })
    }, edit(id) {
      this.$router.push("/admin/question/edit/" + id);
    }, getAddLink(values) {
      this.$router.push("/admin/question/add/" + values);
    },
    deleteById(id) {
      const token = this.$store.state.auth.user.accessToken;
      axios.delete("http://localhost:8080/api/cipher/"+ id, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
        .then(() => {
          alert("Delete Complete")
          this.$router.go(0);
        },
          (error) => {
            alert("error");
            console.log(error)
          })
    }
  },
  mounted() {
    if (this.$route.params.type == "caesar" || this.$route.params.type == "vigenere" || this.$route.params.type == "permutation") {
      this.type = this.$route.params.type;
    }
    else {
      this.$router.push('/admin/');
    }
    this.getData();
  }
};
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

.button-33 {
  background-color: #64ee97;
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

.button-33:hover {
  box-shadow: rgba(154, 219, 179, 0.35) 0 -25px 18px -14px inset, rgba(175, 212, 190, 0.25) 0 1px 2px, rgba(150, 205, 171, 0.25) 0 2px 4px, rgba(44, 187, 99, .25) 0 4px 8px, rgba(44, 187, 99, .25) 0 8px 16px, rgba(44, 187, 99, .25) 0 16px 32px;
  transform: scale(1.05) rotate(-1deg);
}

table {
  border-collapse: collapse;
  width: 100%;
}

th,
td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>