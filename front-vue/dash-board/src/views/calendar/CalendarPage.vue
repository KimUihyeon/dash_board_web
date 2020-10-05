<template>
    <div class="calendar-container margin-10 flex-1">

        <div class="calendar-body display-f margin-10 padding-5">
            <div class="calendar-left display-h-f width-full padding-r-15">
                <div class="flex-1 padding-5 text-align-l">
                    <div class="calendar-tags">    
                        <CalendarTagList 
                            :deleteClickHandle='tagDeleteHandle'
                            :updateSubmitHandle='tagUpdateHandle'
                            :tags='getAllCalendar'/>`

                    </div>
                </div>
                <div class="add-area">
                    <ItmeAdd
                        width="100%"
                        height="100%"
                        placeholder="캘린더 태그 추가"
                        marginTop="5px"
                        iconColor="#ACFFCF"
                        backgroundColor="#ffffff61"
                        fontColor="#fff"
                        fontSize="12px"
                        iconSize="17px"
                        :inputEnterKeyPress_handle="addTagInputHandle"/>
                    
                </div>
            </div>
            <div class="calendar-right flex-1">
                <div class="calendar-header text-align-l margin-10 padding-5 font-size-23">
                    <el-button size="mini" @click="()=>{$refs.calendar.prev()}" icon="el-icon-arrow-left" circle/>
                    <span class="calendar-yymm margin-l-10 margin-r-10 text-bold">{{ calendar.end.year }}. {{ calendar.start.month | monthFormat }}</span>
                    <el-button  size="mini" @click="()=>{$refs.calendar.next()}" icon="el-icon-arrow-right" circle></el-button>
                    
                    <el-button size="mini" @click="reg" type="primary" round>새 이벤트 +</el-button>
                </div>

                    <v-calendar
                        ref="calendar"
                        v-model="calendar.value"
                        color="primary"
                        locale='ko'
                        :day-format="(e)=>e.day"
                        :show-month-on-first='false'
                        :weekdays="[0, 1, 2, 3, 4, 5, 6]"
                        :type="'month'"
                        :events="calendar.events"
                        :event-overlap-mode="'stack'"
                        :event-overlap-threshold="30"
                        :event-color="(e)=>e.color"
                        @click:more="showEvent"
                        @click:event="showEvent"
                        @change="updateCalcendar"
                        
                    ></v-calendar>
                <v-sheet height="600">
                </v-sheet>
            </div>
        </div>
        <EventFromModal 
            :title='modal.title'
            :cals='getAllCalendar'
            :submitAfterHandle='formSubmit'
            :showModal='modal.show'
        />
    </div>
</template>

<script>
import Calendar from '../../components/calendar/Calendar';
import CalendarTagList from '../../components/calendar/CalendarTagList';
import EventFromModal from '../../components/calendar/EventFromModal';
import ItmeAdd  from '../../components/todo/ItmeAdd';
import { mapGetters } from 'vuex';
import { data, delay, alert } from '../../util'

const name = 'CalendarPage';
const components = { Calendar ,EventFromModal, ItmeAdd, CalendarTagList };

export default {
    name,
    components,
    data: () => ({
        tags : [],
        calendar : {
            value: '',
            events: [],
            colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
            start: '',
            end: '',
        },
        modal : { 
            show : false,
            title : '',
        },
    }),
    mounted(){
        this.$store.dispatch('fetch_my_calendars');
    },
    filters: {
        monthFormat: (value) => {
            if(value < 10) {
                return '0' + value;
            }
            return value;
        }
    },
    computed : {
        ...mapGetters(['getAllCalendar'])
    },
    methods: {
        reg(){
            this.modal.show = false;
            delay.immediately(()=>{this.modal.show = true});
        },
        formSubmit(event){
            console.log(event);
            //console.log({start : this.calendar.start , end : this.calendar.end});

            this.$store.dispatch('save_event', { event })
            //this.updateCalcendar({start : this.calendar.start , end : this.calendar.end});
        },
        addTagInputHandle(v , param){
            if(!data.validation(param.keyWord, 'text' , [1, 15])){
                alert.elMessageBox({ vueObject : this, type : 'error', message: '캘린더는 15자 미만 입니다.' });
                return ;
            }
            
            let cal = {
                title : param.keyWord,
                color : "#f0f",
                description : "테스트 설명"
            }
            
            this.$store.dispatch('save_calendar' , { cal });
        },
        updateCalcendar({ start, end }) {
            this.calendar.start = start;
            this.calendar.end = end;

            // 업데이트 로직
            this.calendar.events = this.test_getEventService();
        },
        tagUpdateHandle({id, name}){
            console.log(id);
            console.log(name);
        },
        tagDeleteHandle({id}){
            console.log(id + '삭제됨');
        },
        showEvent(e,t) {
            console.log(e);
        },
        test_getEventService(){
            console.log('업데이트');
            
            return  [
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
        }
    },
};
</script>


<style scoped>
.calendar-tags{
    overflow: auto;
    max-height: 600px;
}
.calendar-left{
    max-width: 230px;
}
.add-area{
    height: 50px;
}
.calendar-container{
    border: 1px solid #fff;
}
</style>
<style>
.v-calendar-weekly__day-label{
    text-align: left !important;
}
</style>