<template>
    <div style="background-color:#fff9f5;">
        <MyNavBar />
        <br>
        <div class="container" style="height: 80vb;">
            <div>
                <h1 style="text-align: center;">แก้ไขโจทย์ ({{ this.type }})</h1>
            </div>
            <div class="row" style="margin-top: 2%;">
                <div class="col-md-8">
                    <label for="inputCity" class="form-label">ประเภทของการเข้ารหัส</label>
                    <input type="text" class="form-control" id="ceaser" :value="this.type" disabled>
                </div>
                <div class="col-md-4">
                    <label for="score" class="form-label">Level</label>
                    <select id="score" class="form-select" v-model="score">
                        <option value="easy" required>ระดับง่าย</option>
                        <option value="moderate">ระดับปานกลาง</option>
                        <option value="hard">ระดับยาก</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label for="plainText" class="form-label">PlainText</label>
                    <input type="text" class="form-control" aria-label="plainText" id="plainText" v-model="plainText"
                        :class="v$.plainText.$error === true ? 'text-fields-error' : 'text-fields'">
                    <p class="text-red-500 text-xs font-thin" v-for="error of v$.plainText.$errors" :key="error.$uid">
                        {{ error.$message }}
                    </p>
                </div>
                <div class="col">
                    <label for="Key" class="form-label">Key<span style="color: red;" v-if="currentCaesar"> (กรณี Caesar
                            ให้พิมพ์เฉพาะตัวเลข)</span><span style="color: red;" v-if="currentVir"> (Vigenere
                            ให้พิมพ์เป็นตัวอักษร )</span> <span style="color: red;" v-if="currentPer"> (Permutation Key =
                            "3,1,2" คือ ตำแหน่ง 1 ไป ช่อง 2 ตำแหน่ง 2 ไป ช่อง 3 ตำแหน่ง 3 ไป ช่อง 1 )</span></label>
                    <input type="text" class="form-control" aria-label="Key" id="Key" v-model="Key"
                        :class="v$.Key.$error === true ? 'text-fields-error' : 'text-fields'">
                    <p class="text-red-500 text-xs font-thin" v-for="error of v$.Key.$errors" :key="error.$uid">
                        {{ error.$message }}
                    </p>
                </div>
            </div>

            <button class="button-33" @click="tranfrom()">โชว์ค่าที่ทำการเข้ารหัสแล้ว</button>

            <div class="col" style="margin-top: 2%;">
                <label for="CipherText" class="form-label">CipherText</label>
                <input type="text" class="form-control" :value="this.CipherText" disabled>
            </div>
            <div style="display: flex;justify-content: center;align-items: center;margin-top: 5%;">
                <button class="button-33" style="" @click="save()">บันทึกข้อมูล</button>
            </div>
        </div>
        <MyButtonbarBar />
    </div>
</template>
      
<script>
import MyNavBar from '@/components/MyNavBar.vue'
import useVuelidate from "@vuelidate/core";
import { required, minLength, maxLength } from "@vuelidate/validators";
import MyButtonbarBar from '@/components/MyButtonBar.vue'
import axios from 'axios';
export default {
    name: 'EditQuestion',
    components: {
        MyNavBar,
        MyButtonbarBar,
    },
    data() {
        return {
            v$: useVuelidate(),
            score: "easy",
            type: "",
            Key: "",
            plainText: "",
            CipherText: "",
            id: 0,
            typeId: 0,
        }
    }, validations() {
        return {
            plainText: { required, min: minLength(2), max: maxLength(50), $autoDirty: true },
            Key: { required, max: maxLength(50), $autoDirty: true },
        };
    },
    computed: {
        currentUser() {
            return this.$route.params
        }, currentCaesar() {
            if (this.type == "caesar") {
                return true;
            } else {
                return false;
            }
        }, currentPer() {
            if (this.type == "permutation") {
                return true;
            } else {
                return false;
            }
        }, currentVir() {
            if (this.type == "vigenere") {
                return true;
            } else {
                return false;
            }
        }
    }, methods: {
        async tranfrom() {
            this.v$.$touch();
            if (!this.v$.$error) {
                let data = {
                    type: this.typeId,
                    plainText: this.plainText,
                    cipherKey: this.Key,
                    level: this.score
                };
                const token = this.$store.state.auth.user.accessToken;
                axios.post("http://localhost:8080/api/cipher/precreate", data, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
                    .then((response) => {
                        this.CipherText = response.data.body.cipherText
                        //console.log(response.data.body)
                    },
                        (error) => {
                            alert("Error");
                            console.log(error)
                        })
            }
        }, setTypeId() {
            if (this.type == "caesar") {
                this.typeId = 1;
            }
            if (this.type == "vigenere") {
                this.typeId = 2;
            }
            if (this.type == "permutation") {
                this.typeId = 3;
            }
        }, async save() {
            this.v$.$touch();
            if (!this.v$.$error) {
                let data = {
                    cipherId: this.id,
                    type: this.typeId,
                    plainText: this.plainText,
                    cipherKey: this.Key,
                    level: this.score
                };
                //console.log(data)
                const token = this.$store.state.auth.user.accessToken;
                axios.put("http://localhost:8080/api/cipher/", data, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
                    .then(() => {
                        //console.log("Created Success")
                        this.$router.push("/admin/question/type/" + this.type)
                    },
                        (error) => {
                            alert("Error");
                            console.log(error)
                        })
            }
        }, setValues() {
            this.id = this.$route.params.type
            const token = this.$store.state.auth.user.accessToken;
            axios.get("http://localhost:8080/api/cipher/" + this.id, { headers: { "Content-Type": "application/json", "Authorization": 'Bearer ' + token } })
                .then((response) => {
                    this.type = response.data.body.type
                    this.plainText = response.data.body.plainText
                    this.Key = response.data.body.cipherKey
                    this.score = response.data.body.level
                    this.setTypeId();
                },
                    (error) => {
                        this.$router.push("/admin/question/")
                        console.log(error)
                    })
        }
    },
    mounted() {
        this.setValues();
    }
};
</script>
<style scoped>
input,
textarea {
    padding: 8px;
    margin-bottom: 20px;
    border: 1px solid #1c87c9;
    outline: none;
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

p {
    color: #f90000;
}</style>