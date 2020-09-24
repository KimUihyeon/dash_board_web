<template>
    <div>
        <div class="calendar-tag-item" v-for="(d, i) in tags" v-bind:key="i" >
            <CalendarTagItem 
                :label="'calendar-tag-item-' + d.id"
                :value='d.name'/>
        </div>
    </div>
</template>


<script>
import CalendarTagItem from './CalendarTagItem';

let components = { CalendarTagItem };
let props = {
    tags : Array,
}

export default {
    components,
    props
}
</script>