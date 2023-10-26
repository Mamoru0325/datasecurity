<template>
  <div class="hello">
    <h1 style="text-align: center;">{{ msg }}</h1>
    <br>
    <div class="row">
      <div class="card" style="width: 22rem; margin: auto; border: 1px solid #000;">
        <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบซีซาร์</h5>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
            content.</p>
            <RouterLink to="/cizar"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
      <div class="card" style="width: 22rem; margin: auto; border: 1px solid #000;">
        <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบ Vigenere</h5>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
            content.</p>
            <RouterLink to="/vigenere"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
      <div class="card" style="width: 22rem; margin: auto; border: 1px solid #000;">
        <img src="https://html.com/wp-content/uploads/flamingo.webp" class="card-img-top" alt="...">
        <div class="card-body">
          <h5 class="card-title">การถอดรหัสแบบ Permutation</h5>
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
            content.</p>
          <RouterLink to="/permutation"><a class="btn btn-primary" style="color:#000000">อ่านเพิ่มเติม</a></RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { RouterLink } from 'vue-router';

export default {
    name: 'HelloWorld',
    props: {
        msg: String
    },
    components: { RouterLink }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
