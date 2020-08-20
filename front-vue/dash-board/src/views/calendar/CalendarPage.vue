<template>
    <div class="calendar-container">
        <div class="calendar-header">
            <span @click="()=>{$refs.calendar.prev()}">＜</span>
            <span class="calendar-yymm"><b>{{ end.year | yearFormat }}. {{ start.month | monthFormat }}</b></span>
            <span @click="()=>{$refs.calendar.next()}">＞</span>
            <el-button @click="reg">+</el-button>
        </div>
        
        <div class="calendar-body">
            <div class="calendar-left">
                <ul>
                    <li>tag1</li>
                    <li>tag1</li>
                    <li>tag1</li>
                    <li>tag1</li>
                </ul>
            </div>
            <div class="calendar-right">
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

            </div>
        </div>
        <TaskFrom 
            :submitAfterHandle='formSubmit'
            :showModal='showFormModal'>
        </TaskFrom>
    </div>
</template>

<script>
import Calendar from '../../components/calendar/Calendar';
import TaskFrom from '../../components/calendar/TaskForm';
import {data } from '../../util'

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
    filters: {
        yearFormat: (value) => {
            if(data.isNull(value)){
                return;
            }
            if(value < 10){
                return '000' + value
            }
            else if(value < 100) {
                return '00' + value
            }
            else if(value < 1000){
                return '0' + value
            }
            else {
                return value
            }
        },
        monthFormat: (value) => {
            if(data.isNull(value)){
                return;
            }
            if(value < 10) {
                return '0' + value;
            }
            return value;
        }
    },
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

            // 업데이트 로직
            this.events = [
                {
                    color: 'indigo',
                    start: '2020-8-28',
                    end: '2020-8-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-8-28',
                    end: '2020-8-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-8-28',
                    end: '2020-8-29',
                    name: 'Meeting',
                },
                {
                    color: 'indigo',
                    start: '2020-8-28',
                    end: '2020-8-29',
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


<style scoped>
.calendar-left{
    width: 100%;
    max-width: 230px;
}
.calendar-right{
    flex: 1;
}
.calendar-yymm{
    margin-left: 10px;
    margin-right: 10px;
}
.calendar-container{
    width: 100%;
    margin: 10px;
    margin: 0 auto;
    border: 1px solid #fff;
}
.calendar-header{
    text-align: left;
    font-size: 23px;
    margin: 10px;
    padding: 5px;
}
.calendar-body{
    margin: 10px;
    padding: 5px;
    display: flex;
}
</style>
<style>
.v-calendar-weekly__day-label{
    text-align: left !important;
}
</style>