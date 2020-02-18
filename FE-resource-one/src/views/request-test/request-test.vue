<template>
    <div class="request-test">
        <button @click="getMethod('/user')">获取用户信息</button>
        <br/>
        <br/>
        <button @click="getMethod('/needAdmin')">needAdmin</button>
        <br/>
        <br/>
        <div>
            {{msg}}
        </div>
    </div>
</template>

<script>
    import {getRequest} from "../../api/request-test";

    export default {
        props: {},
        data() {
            return {
                msg:''
            }
        },
        methods:{
            getMethod(url){
                getRequest(url).then(res=>{
                    this.msg = res;
                    console.log(res); //正常
                }).catch(err => {
                    console.log(err.response.data.message); //异常
                });
            },
        }
    };
</script>

<style scoped>

</style>
