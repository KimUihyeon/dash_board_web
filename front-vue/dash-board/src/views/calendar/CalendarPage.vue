<template>
    <div>
        <div>{{ start.month }} // {{ end.year }}</div>
        <el-button @click="prev"> 이전 </el-button>
        <el-button @click="next"> 다음 </el-button>
        <el-button @click="reg">+</el-button>
        <v-sheet height="600">
            <v-calendar
                ref="calendar"
                v-model="value"
                color="primary"
                locale='ko'
                :day-format='removeString'
                :show-month-on-first='false'
                :weekdays="weekday"
                :type="type"
                :events="events"
                :event-overlap-mode="mode"
                :event-overlap-threshold="30"
                :event-color="getEventColor"
                @click:more="showEvent"
                @click:event="showEvent"
                @change="updateCalcendar"
            ></v-calendar>
        </v-sheet>
        <TaskFrom :showModal='showFormModal'></TaskFrom>
    </div>
</template>

<script>
import Calendar from '../../components/calendar/Calendar';
import TaskFrom from '../../components/calendar/TaskForm';

const name = 'CalendarPage';
const components = { Calendar ,TaskFrom };

export default {
    name,
    components,
    data: () => ({
        type: 'month',
        mode: 'stack',
        weekday: [0, 1, 2, 3, 4, 5, 6],
        value: '',
        events: [],
        colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
        start: '',
        end: '',
        showFormModal : false,
    }),
    methods: {
        reg(){
            this.showFormModal = false;
            setTimeout(() => {
                this.showFormModal = true;
            }, 1);
        },
        prev() {
            this.$refs.calendar.prev();
        },
        next() {
            this.$refs.calendar.next();
        },
        updateCalcendar({ start, end }) {
            this.start = start;
            this.end = end;
            this.events = [
                {
                    color: 'indigo',
                    start: '2020-6-28',
                    end: '2020-6-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-6-28',
                    end: '2020-6-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-6-28',
                    end: '2020-6-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-6-28',
                    end: '2020-6-29',
                    name: 'Meeting',
                },
            ];
        },
        removeString(e){
            return e.day;
        },
        getEventColor(event) {
            return event.color;
        },
        showEvent(e,t) {
            console.log(e);
            // alert(e);
        },
        formatDate(a, withTime) {
            return withTime
                ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
                : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`;
        },
    },
};
</script>
