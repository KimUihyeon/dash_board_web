<template>
    <div>
        <CustomSwichBar
            :changedEvent='changedSwitch_handle'
            :defaultValue='selected_portal'
            :inactiveValue='portals[1].label'
            :activeValue='portals[0].label'
            :inactiveColor='portals[0].color'
            :activeColor='portals[1].color'
        />
        <el-input
            size="mini" 
            v-model="keyword" 
            @keydown.enter.native='keyPress_handle'/>
    </div>
</template>

<style scoped>
.switchContext {
    color: #fff;
    font-size: 13px;
}
</style>


<script>
import CustomSwichBar from "./custome/CustomSwichBar";
let portals = [
    { 
        label : 'naver' ,
        color : '#2F7336',
        baseUrl : 'https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query=@KEYWORD@'
    },
    { 
        label : 'google' , 
        color : '#AA3A38',
        baseUrl : 'https://www.google.com/search?q=@KEYWORD@&oq=@KEYWORD@&sourceid=chrome&ie=UTF-8'
    },
]

export default {
    name : 'SearchBar',
    components : {
        CustomSwichBar
    },
    data(){
        return {
            keyword : '',
            selected_portal : '',
            portals : portals,
        }
    },
    methods: {
        keyPress_handle () {
            console.log(this.portals.filter(t=> t.label === this.selected_portal));
            let url = this.portals.filter(t=> t.label === this.selected_portal)[0]
                .baseUrl
                .replace(/@KEYWORD@/g , this.keyword);

            var win = window.open(url, '_blank');
            win.focus();
        },
        changedSwitch_handle(v){
            this.selected_portal = v;
        }
    },

}
</script>