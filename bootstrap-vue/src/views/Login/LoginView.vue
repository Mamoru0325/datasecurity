<template>
    <section class="vh-100" style="background-color: blanchedalmond;">
        <div style="text-align: right;">
            <button style="font-size:30px;margin-right: 1%;" @click="closeweb()"><font-awesome-icon icon="close" /></button>
        </div>
        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                        class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <form>


                        <div class="divider d-flex align-items-center my-4">
                            <p class="text-center fw-bold mx-3 mb-0" style="font-size: 3ch;">Login Form</p>
                        </div>

                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="form3Example3">Email address</label>
                            <input type="email" name="username" class="form-control form-control-lg" v-model="form.username"
                                :class="v$.form.username.$error === true ? 'text-fields-error' : 'text-fields'"
                                placeholder="Enter a valid email address" />
                            <p1 class="text-red-500 text-xs font-thin" v-for="error of v$.form.username.$errors"
                                :key="error.$uid">
                                {{ error.$message }}
                            </p1>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-3">
                            <label class="form-label" for="form3Example4">Password</label>
                            <input type="password" name="password" class="form-control form-control-lg"
                                v-model="form.password"
                                :class="v$.form.password.$error === true ? 'text-fields-error' : 'text-fields'"
                                placeholder="Enter password" />
                            <p1 class="text-red-500 text-xs font-thin" v-for="error of v$.form.password.$errors"
                                :key="error.$uid">
                                {{ error.$message }}
                            </p1>
                        </div>



                        <div class="text-center text-lg-start mt-4 pt-2">
                            <a type="button" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;" @click="login">Login</a>
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="/register/user"
                                    class="link-danger">UserRegister</a> / <a href="/register/admin"
                                    class="link-danger">AdminRegister</a></p>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </section>
</template>
<script setup>
import useVuelidate from "@vuelidate/core";
import { required, email, minLength, maxLength } from "@vuelidate/validators";


</script>
<script>
export default {
    name: 'loginView',
    data() {
        return {
            v$: useVuelidate(),
            form: {
                username: "",
                password: ""
            }
        }
    },
    validations() {
        return {
            form: {
                username: {
                    required,
                    email, $autoDirty: true,
                },
                password: { required, min: minLength(6), max: maxLength(30), $autoDirty: true }
            },
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
    created() {
        if (this.loggedIn) {
            this.$router.push("/");
        }
    }, methods: {
        async login() {
            this.v$.$touch();
            if (!this.v$.$error) {
                let user = {
                    username: this.form.username,
                    password: this.form.password,
                };

                this.$store.dispatch("auth/login", user).then(
                    () => {
                        this.$router.push("/user");
                    },
                    (error) => {
                        alert("Email or Password wrong")
                        this.loading = false;
                        this.message =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();
                    }
                );
            }
        },closeweb(){
            this.$router.push("/");
        }
    }
}
</script>
<style scoped>
.divider:after,
.divider:before {
    content: "";
    flex: 1;
    height: 1px;
    background: #eee;
}

.h-custom {
    height: calc(100% - 73px);
}

p1 {
    color: #f90000;
}
</style>