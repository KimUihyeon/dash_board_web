<template>
    <div>
        <div>
            <h3>
                <span>
                    <input
                        type="checkbox" 
                        v-model="cloneItem.todoComplate" 
                        v-on:change="changed_handle"/>
                </span>
                <span>
                    {{cloneItem.title}}
                </span>
            </h3>
            <h6>{{cloneItem.memo}}</h6>

            <span>{{cloneItem.id}}</span>
        </div>
    </div>
</template>

<style scoped>

</style>

<script>
export default {
    name : 'TodoItem',
    data()  {
        return {
            cloneItem : {}
        }
    },
    props : {
        item : Object,
    },
    mounted(){
        this.cloneItem = this.item;
    },
    methods:{
        changed_handle(){
            this.$store.dispatch('todoItemUpdate', { todoItem : this.cloneItem});
        },
    }
    
}
</script>