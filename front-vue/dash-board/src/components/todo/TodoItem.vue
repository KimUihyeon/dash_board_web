<template>
    <div>
        <div @mouseover="hoverOver" @mouseout="hoverOut">
            <v-card
                class="mx-auto"
                max-width="350"
                shaped="true"
                :elevation="elevation"
                hover="true"
                outlined
            >
                <v-list-item three-line>
                    <v-list-item-content>
                        <!-- <div class="overline mb-4 text-left">type</div> -->
                        <v-list-item-title class="headline mb-1 text-left">
                            <span>
                                <input
                                    type="checkbox" 
                                    v-model="cloneItem.todoComplate" 
                                    v-on:change="changed_handle"/>
                            </span>
                            <span>{{cloneItem.title}}</span>
                        </v-list-item-title>
                        <v-list-item-subtitle class="text-left">{{cloneItem.memo}}</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-card-actions>
                    <v-btn text>Button</v-btn>
                    <v-btn text @click="deleteItem">Button</v-btn>
                </v-card-actions>                
            </v-card>
        </div>
    </div>
</template>

<style scoped>

</style>

<script>
import { alert } from "../../util";
export default {
    name : 'TodoItem',
    data()  {
        return {
            cloneItem : {},
            elevation : 0,
        }
    },
    props : {
        item : Object,
    },
    mounted(){
        this.cloneItem = this.item;
    },
    methods:{
        deleteItem(){
            alert.showConfirm(this, null, '해당 Todo를 삭제하시겠습니까?', )
        },
        hoverOver(){
            this.elevation = 4;
        },
        hoverOut(){
            this.elevation = 0;  
        },
        changed_handle(){
            this.$store.dispatch('todoItemUpdate', { todoItem : this.cloneItem});
        },
    }
    
}
</script>