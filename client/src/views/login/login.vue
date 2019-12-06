<template>
    <div class="login">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="用户名">
                <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="密 码">
                <el-input type="password" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('form')">登录</el-button>
                <el-button @click="resetForm('form')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {doLogin} from "../../api/login";
    import system_utils from "../../utils/system-utils"
    export default {
        data() {
            return {
                form: {
                    username: '',
                    password: '',
                }
            }
        },
        /** methods开始*/
        methods: {
            submitForm(formName) {
                console.log(formName);
                console.log(this.form);
                doLogin(this.form).then(res=>{
                    if (res.flag == true) {
                        system_utils.setLogin();
                        this.$router.push({ path:system_utils.getToUrl()});
                    }
                }).catch(err=>{
                    console.log(err);
                });
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
        /** methods结束*/

    };
</script>

<style scoped>
    .login{
        margin: auto;
        width: 500px;
    }

</style>
