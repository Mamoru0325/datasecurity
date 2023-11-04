<template>
    <div class="vertical-center" style="background-color:#fff9f5;">
        <div class="container center">
            <div id="notfound" class="text-center ">
                <h1>404 Error Page</h1>
                <h1>😮</h1>
                <h2>Oops! Page Not Be Found</h2>
                <p>Sorry but the page you are looking for does not exist.</p>
                <a href="/">Back to homepage</a>
            </div>
        </div>
    </div>
</template>
  
<script>

export default {
    name: 'ErrorView',
}
</script>

<style>
.center {
    position: fixed;
    top: 50%;
    left: 50%;
    -webkit-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}
</style>
  