<template>
    <div>
        <div>{{ttt}}</div>
        <Test></Test>
        <Search></Search>
    </div>
</template>


<script>
import Test from '@/components/Test'
import Search from '@/components/common/Search'
export default {
    name :  'TestPage',
    components : { Test , Search},
    data(){
        return {
        }
    },

    computed : {
        ttt (){
            return  this.$store.state.storeData
        }
    },
    mounted() {
        console.log(this.$store.state.storeData);
        console.log('testset')
    },
    methods:{
        
    }
}
</script>