<template>
    <div class="container">
      <header class="jumbotron">
        <h3>
          <strong>{{currentUser.type}}</strong>
        </h3>
      </header>
    </div>
  </template>
  
  <script>
  export default {
    name: 'MyProfile',
    computed: {
      currentUser() {
        return this.$route.params
      }
    },
    mounted() {
      if (!this.currentUser) {
        this.$router.push('/login');
      }
    }
  };
  </script>