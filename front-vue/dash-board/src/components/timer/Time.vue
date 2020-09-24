<template>
    <span>
        <span class="time-box text-align-c font-size-12">
            <div class="date margin-3">{{dateTime.split(' ')[0]}}</div>
            <div class="time margin-3">{{dateTime.split(' ')[1]}}</div>
        </span>
    </span>
</template>

<script>
import { mapGetters } from 'vuex'
const name = 'Time';

export default {
    mounted() {
        this.$store.dispatch('thread_timer');
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
.time-box {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    color: #2c3e50;
}
</style>