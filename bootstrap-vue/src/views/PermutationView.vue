<template>
  <div>
    <MyNavBar />
    <br>
    <h1>This is an Permutation page</h1>
    <br>
    <MyButtonbar />
  </div>
</template>
  
<script>
// @ is an alias to /src
import MyButtonbar from '@/components/MyButtonBar.vue'
import MyNavBar from '@/components/MyNavBar.vue'
export default {
  name: 'PermutationView',
  components: {
    MyButtonbar,
    MyNavBar
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