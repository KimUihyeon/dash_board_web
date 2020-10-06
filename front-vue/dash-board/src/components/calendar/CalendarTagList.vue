<template>
    <div>
        <div class="calendar-tag-item" v-for="(d, i) in tags" v-bind:key="i" >
            <CalendarTagItem 
                :value='d.title'
                :id="d.id"
                :color="d.color"
                :deleteClickHandle='deleteClickHandle'
                :updateSubmitHandle='updateSubmitHandle'
                />
            <!-- <CalendarTagItem 
                :label="'calendar-tag-item-' + d.id"
                :value='d.name'
                :id="d.id"
                :deleteClickHandle='deleteClickHandle'
                :updateSubmitHandle='updateSubmitHandle'
                /> -->
        </div>
    </div>
</template>


<script>
import CalendarTagItem from './CalendarTagItem';

let components = { CalendarTagItem };
let props = {
    tags : Array,
    deleteClickHandle : { 
        type :  Function,
        default : (a)=>{}
    },
    updateSubmitHandle : { 
        type :  Function,
        default : (a)=>{}
    },
}

export default {
    components,
    props,
}
</script>