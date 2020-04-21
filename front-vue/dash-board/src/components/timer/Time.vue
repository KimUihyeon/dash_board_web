<template>
    <div class="blurred-box">
        <div class="time">
            {{dateTime.split(' ')[1]}}
        </div>
        <div class="font-noto-sans date">
            {{dateTime.split(' ')[0]}}
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

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@900&display=swap');

.date {
    font-size: 16px;
}
.time {
    text-shadow: 3px 3px 3px #fff;
    color:#fff;
    font-size: 56px;
    font-family: Avenir, Helvetica, Arial, sans-serif;
}
.font-noto-sans {
    font-family: 'Noto Sans KR', sans-serif;
}
</style>