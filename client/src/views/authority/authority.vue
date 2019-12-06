<template>
    <div class="authority">
        <h3>这里是Spring Security权限测试页面</h3>
        <el-button @click="needAdmin">needAdmin权限测试</el-button>
        <el-button @click="needRoot">needRoot权限测试</el-button>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>下面展示返回内容👇</span>
            </div>
            <div style="color: red;">
                {{msg}}
            </div>
        </el-card>
    </div>
</template>

<script>
    import AuthorityService from "../../api/authority.service";
    export default {
        data() {
            return {
                msg:''
            }
        },
        /** methods开始*/
        methods: {
            needAdmin(){
                AuthorityService.doNeedAdmin()
                    .then(res=>{
                        this.msg = res.data;
                    })
                    .catch(err=>{
                        this.$message.error(err);
                    })
            },
            needRoot(){
                AuthorityService.doNeedRoot()
                    .then(res=>{
                        this.msg = res.data;
                    })
                    .catch(err=>{
                        this.$message.error(err);
                    })
            }
        }
        /** methods结束*/
    };
</script>
