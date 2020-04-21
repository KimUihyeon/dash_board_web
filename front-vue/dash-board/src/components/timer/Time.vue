<template>
    <div>
        <div>
            {{dateTime.split(' ')[0]}}
        </div>
        <div>
            {{dateTime.split(' ')[1]}}
        </div>
        
    </div>
</template>


<script>
import moment from 'moment';
export default {
    name : 'Time',
    props : [ 'dateTime'],
}
</script>