<template>
    <div class="calendar-container margin-10 flex-1">

        <div class="calendar-body display-f margin-10 padding-5">
            <div class="calendar-left display-h-f width-full padding-r-15">
                <div class="flex-1 padding-5 text-align-l">
                    <div class="calendar-tags">    
                        <CalendarTagList 
                            :deleteClickHandle='calendarDelete'
                            :updateSubmitHandle='calendarUpdate'
                            :checkChangedHandle='calendarCheckHandle'
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
                        :inputEnterKeyPress_handle="calendarInputHandle"/>
                    
                </div>
            </div>
            <div class="calendar-right flex-1">
                <Calendar :events="getEvents"
                    :onButtonClick='showEventModal'
                    :onResizeEvnet="eventUpdate"
                    :onDropEvent="eventUpdate"
                />
            </div>
        </div>
        <EventFromModal 
            :title='modal.title'
            :cals='getAllCalendar'
            :submitAfterHandle='eventFormSubmit'
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
        ...mapGetters(['getAllCalendar', 'getEvents']),
    },
    methods: {
        showEventModal(){ this.modal.show = false; delay.immediately(()=>{this.modal.show = true}); },
        eventFormSubmit(event){
            if(data.isNull(event.id)){ // Event 추가
                this.$store.dispatch('save_event', { event }).then(res=>{
                    alert.addSuccessAlert(this);
                }).catch(err=>{
                    alert.serverErrorAlert(this);
                })
            }else {  // Event 수정
                this.eventUpdate();
            }
            
        },
        eventAdd(){

        },
        eventUpdate(e){
            console.log(e)
        },
        eventDelete({ event }){
            const okCallback = ()=>{
                console.log(event.id , '삭제');
            }
            
            alert.elConfirm({
                vueObject: this,
                type : 'Warning',
                confirmMsg : 'event 삭제하시겠습니까??',
                title : '할일 삭제',
                okCallback ,
                cancelCallback, });
        },


        calendarCheckHandle(checked, id){
            this.calendarUpdate({id , checked });
        },
        calendarInputHandle(v , param){
            if(!data.validation(param.keyWord, 'text' , [0, 15])){
                this.alert('캘린더는 15자 미만 입니다.','error');
                return ;
            }

            const cal = { title : param.keyWord, color : "#04D3FC" };
            this.$store.dispatch('save_calendar' , { cal }).then(res=>{
                alert.addSuccessAlert(this);
            });
        },
        calendarUpdate({id, title , color, checked}){
            const findCalendar = this.getAllCalendar.filter((t)=> t.id == id)[0];
            const cal = { ...findCalendar };
            let isChanged = false;

            if(!data.isNull(title) && cal.title != title ){ cal.title = title; isChanged = true;}
            if(!data.isNull(color) && cal.color != color ){ cal.color = color; isChanged = true;}
            if(!data.isNull(checked) && cal.checked != checked ){ cal.checked = checked; isChanged = true;}

            if(isChanged){
                this.$store.dispatch('patch_calendar', { cal }).then((res)=>{ 
                    alert.editSuccessAlert(this);
                }).catch(err =>{
                    alert.serverErrorAlert(this);
                })
            }
        },
        calendarDelete({id}){
            this.$store.dispatch('remove_calendar', { cal : { id } }).then((res)=>{
                alert.deleteSuccessAlert(this);
            }).catch(err =>{
                alert.serverErrorAlert(this);
            })
        },
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