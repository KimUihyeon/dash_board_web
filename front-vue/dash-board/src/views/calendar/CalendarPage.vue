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
                    :onButtonClick='showEventAddModal'
                    :onResizeEvnet="eventUpdate"
                    :eventClick="showEventEditModal"
                    :onDropEvent="eventUpdate"
                />
            </div>
        </div>
        <EventFromModal 
            ref="eventFromModal"
            :cals='getAllCalendar'
            :submitAfterHandle='eventFormSubmit'
            :deleteHandle='eventDelete'
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
import { data, delay, alert, logger } from '../../util'

const name = 'CalendarPage';
const components = { Calendar ,EventFromModal, ItmeAdd, CalendarTagList };

export default {
    name, components,
    data: () => ({
        modal : { 
            show : false,
        },
    }),
    mounted(){
        const param = { userId : 'admin@admin.com' };
        this.$store.dispatch('fetch_my_calendars', param );
    },
    computed : {
        ...mapGetters(['getAllCalendar', 'getEvents']),
    },
    methods: {
        isEmptyCalendar(){ return data.isEmptyArr(this.getAllCalendar) },
        showEventModal(){ this.modal.show = false; delay.immediately(()=>{this.modal.show = true}); },
        showEventAddModal(){ 
            if(this.isEmptyCalendar()) {
                alert.elMessageBox({
                    vueObject : this ,
                    type : alert.prop.type.error,
                    message: '캘린더를 먼저 생성해 주세요.'
                })

                return;
            }

            this.$refs.eventFromModal.setEventObj({}); 
            this.showEventModal();
        },
        showEventEditModal(e){ 
            const event = {...this.getEvents.filter(t=>t.id==e.id)[0]};
            this.$refs.eventFromModal.setEventObj(event);
            this.showEventModal(); 
        },
        eventFormSubmit(e){            
            logger.dev(`${name} : eventFormSubmit`, e );
            if(data.isNull(e.id)){ // Event 추가
                this.$store.dispatch('save_event', { event : e }).then(res=>{
                    alert.addSuccessAlert(this);
                }).catch(err=>{
                    alert.serverErrorAlert(this);
                })
            }else {  // Event 수정
                let event = {
                    ...this.getEvents.filter(t=> t.id==e.id)[0],
                    title : e.title,
                    context : e.context,
                    edate : e.edate,
                    sdate : e.sdate,
                }

                this.$store.dispatch('patch_event', { event }).then(res=>{
                    alert.editSuccessAlert(this);
                }).catch(err=>{
                    alert.serverErrorAlert(this);
                });
            }
        },
        eventUpdate(e){

            let event = {
                ...this.getEvents.filter(t=> t.id==e.id)[0],
                sdate : e.startStr,
                edate : e.endStr,
            }

            this.$store.dispatch('patch_event', { event })
        },
        eventDelete(e){
            const okCallback = ()=>{
                this.$store.dispatch('delete_event', { event : e })
                    .then(res=> { 
                        alert.deleteSuccessAlert(this);
                        this.modal.show = false; 
                    })
                    .catch(err=>{ alert.serverErrorAlert(this); });
            }
            
            alert.elConfirm({
                vueObject: this,
                type : 'Warning',
                confirmMsg : `[${e.title}] 일정을 삭제하시겠습니까?`,
                title : '할일 삭제',
                okCallback ,
                cancelCallback : ()=>{}, });
        },


        calendarCheckHandle(checked, id){
            this.calendarUpdate({id , checked });
        },
        calendarInputHandle(v , param){
            if(!data.validation(param.keyWord, 'text' , [0, 15])){
                alert.elMessageBox({
                    vueObject : this ,
                    type : alert.prop.type.error,
                    message: '캘린더는 1 ~ 15자 미만 입니다.'
                })
                return ;
            }

            const cal = { title : param.keyWord, color : "#04D3FC" };
            this.$store.dispatch('save_calendar' , { cal }).then(res=>{
                alert.addSuccessAlert(this);
            });
        },
        calendarUpdate({id, title , color, checked}){
            const cal = { ...this.getAllCalendar.filter((t)=> t.id == id)[0] };
            let isChanged = false;

            if(data.isNotNullAndDirty(cal.title, title)){ cal.title = title; isChanged = true;}
            if(data.isNotNullAndDirty(cal.color, color)){ cal.color = color; isChanged = true;}
            if(data.isNotNullAndDirty(cal.checked, checked)){ cal.checked = checked; isChanged = true;}

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
    background: #21212185;
}
</style>
<style>
.v-calendar-weekly__day-label{
    text-align: left !important;
}
.fc-col-header-cell-cushion , .fc-daygrid-day-number{
    color: #fff !important;
}
</style>