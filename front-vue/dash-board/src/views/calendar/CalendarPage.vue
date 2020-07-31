<template>
    <div>
        <div>{{ start.month }} // {{ end.year }}</div>
        <el-button @click="()=>{$refs.calendar.prev()}"> 이전 </el-button>
        <el-button @click="()=>{$refs.calendar.next()}"> 다음 </el-button>
        <el-button @click="reg">+</el-button>
        <v-sheet height="600">
            <v-calendar
                ref="calendar"
                v-model="value"
                color="primary"
                locale='ko'
                :day-format="(e)=>e.day"
                :show-month-on-first='false'
                :weekdays="weekday"
                :type="type"
                :events="events"
                :event-overlap-mode="mode"
                :event-overlap-threshold="30"
                :event-color="(e)=>e.color"
                @click:more="showEvent"
                @click:event="showEvent"
                @change="updateCalcendar"
            ></v-calendar>
        </v-sheet>
        <TaskFrom 
            :submitAfterHandle='formSubmit'
            :showModal='showFormModal'>
        </TaskFrom>
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
        formSubmit(task){
            this.updateCalcendar({start : this.start , end : this.end});
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
            console.log('업데이트');
        },
        showEvent(e,t) {
            console.log(e);
        },
    },
};
</script>
