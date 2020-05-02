<template>
    <span>
        <span class="time-box">
            <div class="date">
                {{dateTime.split(' ')[0]}}
            </div>
            <div class="time">
                {{dateTime.split(' ')[1]}}
            </div>
        </span>
    </span>
</template>

<script>
import moment from 'moment';
import { mapGetters } from 'vuex'
export default {
    name : 'Time',
    mounted() {
        this.$store.dispatch('synchronizDateTimeThead');
    },
    computed : {
        ...mapGetters({
            dateTime : 'currentDateTime'
        })
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@900&display=swap');
.time , .date{
    margin: 3px;
}
.time-box {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    color: #2c3e50;
    font-size: 12px;
    text-align: center;
    /* text-shadow: 1px 1px 1px #000; */
}
</style>