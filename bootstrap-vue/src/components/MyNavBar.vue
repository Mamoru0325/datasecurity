<template>
  <nav class="navbar navbar-expand " style="background-color: #8D92A6 ;color: #fff1e6; ">
    <a href="/" class="navbar-brand" style="color: #fff1e6; ">เกมถอดรหัส</a>
    <div class="collapse navbar-collapse " id="navbarSupportedContent">
      <div class="navbar-nav mr-auto">
        <li class="nav-item" v-if="!currentUser">
          <router-link to="/" class="nav-link">
            <font-awesome-icon icon="home" style="color: #fff1e6; "/> Home
          </router-link>
        </li>
        <li class="nav-item" v-if="showUser">
          <router-link to="/user" class="nav-link">
            <font-awesome-icon icon="home" style="color: #fff1e6; " /> Home
          </router-link>
        </li>
        <li class="nav-item" v-if="showAdmin">
          <router-link to="/admin" class="nav-link">
            <font-awesome-icon icon="home" style="color: #fff1e6; "/> Home
          </router-link>
        </li>
        <li class="nav-item" v-if="!currentUser">
          <router-link to="/about" class="nav-link" style="color: #fff1e6; ">About</router-link>
        </li>
        <li v-if="showAdmin" class="nav-item">
          <router-link to="/admin/admin" class="nav-link" style="color: #fff1e6; ">Admin</router-link>
        </li>
        <li v-if="showAdmin" class="nav-item">
          <router-link to="/admin/question" class="nav-link" style="color: #fff1e6; ">Question</router-link>
        </li>
        <li v-if="showAdmin" class="nav-item">
          <router-link to="/admin/feedback" class="nav-link" style="color: #fff1e6; ">Feedback</router-link>
        </li>
        <li v-if="showUser" class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="/user/game" id="navbarDropdown" data-bs-toggle="dropdown" style="color: #fff1e6; ">
            เกมถอดรหัส
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
              <RouterLink class="dropdown-item" to="/user/game"><a class="dropdown-item" >หน้ารวม</a></RouterLink>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li>
              <RouterLink class="dropdown-item" to="/user/game"><a class="dropdown-item" >ระดับง่าย</a></RouterLink>
            </li>
            <li>
              <RouterLink class="dropdown-item" to="/user/game"><a class="dropdown-item" >ระดับกลาง</a></RouterLink>
            </li>
            <li>
              <RouterLink class="dropdown-item" to="/user/game"><a class="dropdown-item"  >ระดับยาก</a></RouterLink>
            </li>
          </ul>
        </li>
        <li v-if="showUser" class="nav-item">
          <router-link to="/user/score" class="nav-link" style="color: #fff1e6; ">Score</router-link>
        </li>
        <li v-if="showUser" class="nav-item">
          <router-link to="/user/feedback" class="nav-link" style="color: #fff1e6; " >Feedback</router-link>
        </li>
        <li v-if="showUser" class="nav-item">
          <router-link to="/user/about" class="nav-link" style="color: #fff1e6; ">About</router-link>
        </li>
      </div>

      <div style="margin-left: auto;">
        <div v-if="!currentUser" class="navbar-nav ml-auto">
          <li class="nav-item">
            <router-link to="/register/user" class="nav-link">
              <font-awesome-icon icon="user-plus" style="color: #fff1e6; "/> Sign Up
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/login" class="nav-link">
              <font-awesome-icon icon="sign-in-alt" style="color: #fff1e6; "/> Login
            </router-link>
          </li>
        </div>

        <div v-if="currentUser" class="navbar-nav ml-auto">
          <li class="nav-item">
            <router-link to="#" class="nav-link" >
              <font-awesome-icon icon="user" style="color: #fff1e6; " />
              {{ currentUser.name }}
            </router-link>
          </li>
          <li class="nav-item">
            <a class="nav-link" @click.prevent="logOut">
              <font-awesome-icon icon="sign-out-alt"  style="color: #fff1e6; "/> LogOut
            </a>
          </li>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/';
export default {

  name: 'MyNavBar',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdmin() {
      if (this.currentUser && this.currentUser.roles[0] === 'ROLE_Admin') {
        return true;
      }

      return false;
    },
    showUser() {
      if (this.currentUser && this.currentUser.roles[0] === 'ROLE_User') {
        return true;
      }

      return false;
    }
  },
  methods: {
    logOut() {
      const token = this.$store.state.auth.user.accessToken;
      console.log("token" + token)
      axios.post(API_URL + 'signout',"" ,{ headers: { "Authorization": 'Bearer ' + token } })
      
      this.$store.dispatch('auth/signout');
      this.$router.push('/login');
      
    }

  }
}
</script>