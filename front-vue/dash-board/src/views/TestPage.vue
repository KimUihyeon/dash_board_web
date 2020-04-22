<template>
    <div>
        <div>{{ttt}}</div>
        <Test></Test>
    </div>
</template>


<script>
import Test from '@/components/Test'
export default {
    name :  'TestPage',
    components : { Test },
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