<template>
    <div class="calendar-container margin-10 flex-1">

        <div class="calendar-body display-f margin-10 padding-5">
            <div class="calendar-left display-h-f width-full padding-r-15">
                <div class="flex-1 padding-5 text-align-l">
                    <div class="calendar-tags">    
                        <CalendarTagList 
                            :deleteClickHandle='calendarDelete'
                            :updateSubmitHandle='calendarUpdate'
                            :tags='getAllCalendar'/>

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
                <Calendar :events="[
                    { title: 'event 1', date: '2020-10-01' },
                    { title: 'event 2', date: '2020-10-02' }]"
                    :onButtonClick='showModal'
                    :onResizeEvnet="(e)=>{  }"
                    :onDropEvent="(e)=>{  }"
                />
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
    name, components,
    data: () => ({
        modal : { 
            show : false,
            title : '',
        },
    }),
    mounted(){
        this.$store.dispatch('fetch_my_calendars');
    },
    computed : {
        ...mapGetters(['getAllCalendar'])
    },
    methods: {
        showModal(){ this.modal.show = false; delay.immediately(()=>{this.modal.show = true}); },
        formSubmit(event){
            console.log(event);
            this.$store.dispatch('save_event', { event })
        },
        addTagInputHandle(v , param){
            if(!data.validation(param.keyWord, 'text' , [0, 15])){
                alert.elMessageBox({ vueObject : this, type : 'error', message: '캘린더는 15자 미만 입니다.' });
                return ;
            }
            const cal = { title : param.keyWord, color : "#04D3FC" };

            this.$store.dispatch('save_calendar' , { cal });
        },
        calendarUpdate({id, title , color}){
            const findCalendar = this.getAllCalendar.filter((t)=> t.id == id)[0];
            const cal = { ...findCalendar };
            let isChanged = false;

            if(!data.isNull(title) && cal.title != title ){ cal.title = title; isChanged = true;}
            if(!data.isNull(color) && cal.color != color ){ cal.color = color; isChanged = true;}

            if(isChanged){
                this.$store.dispatch('patch_calendar', { cal }).then((res)=>{

                }).catch(err =>{
                    
                })
            }
        },
        calendarDelete({id}){
            console.log(id + '삭제됨');
            console.log();

            const cal = { id };
            this.$store.dispatch('remove_calendar', { cal });
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