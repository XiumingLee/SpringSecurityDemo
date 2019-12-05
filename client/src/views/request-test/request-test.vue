<template>
    <div class="request-test">
        <button @click="jsonRequest('参数')">发送Json请求</button>
        <br/>
        <br/>
        <button @click="formRequest">发送Form请求</button>
    </div>
</template>

<script>
    import {jsonRequest,formRequest} from "../../api/request-test";

    export default {
        props: {},
        data() {
            return {
                params:{
                    id:'22',
                    name:'Xiuming',
                    address:'China'
                }
            }
        },
        methods:{
            jsonRequest(val){
                console.log(val);
                jsonRequest(this.params).then(res=>{
                    console.log(res); //正常
                }).catch(err => {
                    console.log(err.response.data.message); //异常
                });
            },
            formRequest(){
                formRequest(this.params).then(res=>{
                    console.log(res); //正常
                }).catch(err => {
                    console.log(err.response.data.message); //异常
                });
            }
        }
    };
</script>

<style scoped>

</style>
