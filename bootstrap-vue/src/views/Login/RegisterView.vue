<template>
    <div class="container-fluid " style="background-color: blanchedalmond;height: 100;">
        <div class="row d-flex justify-content-center align-items-center">

            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1" style="background-color: blanchedalmond;height: 110vh">
                <form>

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0" style="font-size: 3ch;color: black;">Register Form</p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <label class="form-label" for="form3Example3">Email address</label>
                        <input type="email" name="email" class="form-control form-control-lg" v-model="form.email"
                            :class="v$.form.email.$error === true ? 'text-fields-error' : 'text-fields'"
                            placeholder="Enter a valid email address" />
                        <p class="text-red-500 text-xs font-thin" v-for="error of v$.form.email.$errors" :key="error.$uid">
                            {{ error.$message }}
                        </p>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form3Example3">Phone Number</label>
                        <input type="text" name="phoneNumber" class="form-control form-control-lg"
                            v-model="form.phoneNumber"
                            :class="v$.form.phoneNumber.$error === true ? 'text-fields-error' : 'text-fields'"
                            placeholder="Enter Phone Number" required />
                        <p class="text-red-500 text-xs font-thin" v-for="error of v$.form.phoneNumber.$errors"
                            :key="error.$uid">
                            {{ error.$message }}
                        </p>
                    </div>

                    <div class="form-outline mb-4">
                        <label class="form-label" for="form3Example3">Username</label>
                        <input type="text" name="username" class="form-control form-control-lg" v-model="form.username"
                            :class="v$.form.username.$error === true ? 'text-fields-error' : 'text-fields'"
                            placeholder="Enter Username" />
                        <p class="text-red-500 text-xs font-thin" v-for="error of v$.form.username.$errors"
                            :key="error.$uid">
                            {{ error.$message }}
                        </p>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="form3Example4">Password</label>
                        <input type="password" name="password" class="form-control form-control-lg" v-model="form.password"
                            :class="v$.form.password.$error === true ? 'text-fields-error' : 'text-fields'"
                            placeholder="Enter password" />
                        <p class="text-red-500 text-xs font-thin" v-for="error of v$.form.password.$errors"
                            :key="error.$uid">
                            {{ error.$message }}
                        </p>
                    </div>

                    <div class="form-outline mb-3">
                        <label class="form-label" for="form3Example4">Confirm Password</label>
                        <input type="password" name="password2" class="form-control form-control-lg"
                            v-model="form.password2"
                            :class="v$.form.password2.$error === true ? 'text-fields-error' : 'text-fields'"
                            placeholder="Enter password" />
                        <p class="text-red-500 text-xs font-thin" v-for="error of v$.form.password2.$errors"
                            :key="error.$uid">
                            {{ error.$message }}
                        </p>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <a type="submit" class="btn btn-primary btn-lg" @click="register"
                            style="padding-left: 2.5rem; padding-right: 2.5rem;" id="submit">Submit</a>
                        <p class="small fw-bold mt-2 pt-1 mb-0" style="color: #000000;">Have an account? <a href="/login"
                                class="link-danger">Login</a></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
</template>
<script setup>
import useVuelidate from "@vuelidate/core";
import { required, email, minLength, maxLength, sameAs } from "@vuelidate/validators";
// import axios from "axios";


</script>
<script >
//import axios from 'axios'

export default {

    name: 'RegisterView',
    data() {
        return {
            v$: useVuelidate(),
            form: {
                email: null,
                phoneNumber: "",
                username: "",
                password: "",
                password2: ""
            }
        }
    },
    validations() {
        return {
            form: {
                email: {
                    required,
                    email, $autoDirty: true,
                },
                phoneNumber: { required, min: minLength(10), max: maxLength(10), $autoDirty: true },
                username: { required, max: maxLength(30), $autoDirty: true },
                password: { required, min: minLength(8), max: maxLength(30), $autoDirty: true },
                password2: { required, sameAs: sameAs(this.form.password) },
            },
        };
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        },
    },
    mounted() {
        if (this.loggedIn) {
            this.$router.push("/login");
        }
    }, methods: {
        async register() {
            this.v$.$touch();
            if (!this.v$.$error) {
                this.message = "";
                this.successful = false;
                this.loading = true;
                let data = {
                    email: this.form.email,
                    password: this.form.password,
                    username: this.form.username,
                    phoneNumber: this.form.phoneNumber
                };

                this.$store.dispatch("auth/register", data).then(
                    (data) => {
                        this.message = data.message;
                        this.successful = true;
                        this.loading = false;
                        alert(data.message);
                        this.$router.push('/login');
                    },
                    (error) => {
                        alert("username or password is already used");
                        this.message =
                            (error.response &&
                                error.response.data &&
                                error.response.data.message) ||
                            error.message ||
                            error.toString();
                        this.successful = false;
                        this.loading = false;
                    }
                );
            }
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

p {
    color: #f90000;
}
</style>