<template>
    <div>
        
        <div class="calendar-header text-align-l margin-10 padding-5 font-size-23">
            <el-button size="mini" @click="()=>{this.prevMonth()}" icon="el-icon-arrow-left" circle/>
            <span class="calendar-yymm margin-l-10 margin-r-10 text-bold">{{date}} </span>
            <!-- {{ calendar.end.year }}. {{ calendar.start.month | monthFormat }} -->
            <el-button  size="mini" @click="()=>{this.nextMonth()}" icon="el-icon-arrow-right" circle></el-button>
            
            <el-button size="mini" @click="onButtonClick" type="primary" round>새 이벤트 +</el-button>
        </div>
        <div>
            <full-calendar ref="fullCalendar" :options="calendarOptions" />    
        </div>
        
    </div> 
    
</template>

<script>
import FullCalendar from '@fullcalendar/vue'
import dayGridPlugin from '@fullcalendar/daygrid'
import interactionPlugin from '@fullcalendar/interaction'
import koLocale from '@fullcalendar/core/locales/ko';

const ko_locate = ['일', '월', '화', '수', '목', '금', '토'];
const name = 'Calendar';
const components = { 'full-calendar' : FullCalendar }
const props = {
    events : Array,
    onButtonClick : {
        type : Function,
        default : () => {}
    },
    onEventFormSubmit : {
        type : Function,
        default : (event) => {console.log(event)}
    },
    onResizeEvnet : {
        type : Function,
        default : (info) => {
            console.log(info);
                
            if (!confirm("Are you sure about this change?")) {
                info.revert();
            }
        },
    },
    onDropEvent : {
        type : Function,
        default : (info) => {
            console.log(info);
            if (!confirm("Are you sure about this change?")) {
                info.revert();
            }
        },
    },
}


export default {
    name, components, props,
    mounted(){
        this.el.fullCalendar = this.$refs.fullCalendar;
        this.createDate();
    },
    data() {
        return {
            date : '',
            el:{
                fullCalendar : {}
            },
            calendarOptions: {
                height: 650,
                locale : koLocale,
                initialView: 'dayGridMonth', 
                plugins: [ dayGridPlugin, interactionPlugin ],
                headerToolbar :false, // 툴바 가리기 

                eventStartEditable: true , // 이벤트 관련
                eventDurationEditable : true , // 이벤트 드레그 허용
                eventResizableFromStart : true, // 이벤트 리사이즈 허용
                
                dateClick: this.handleDateClick,
                eventResize : this.onResizeEvnet,
                eventDrop : this.onDropEvent,
                events: this.events
            }
        }
    },
    methods: {
        nextMonth : function() { this.el.fullCalendar.getApi().next(); this.createDate(); },
        prevMonth : function() { this.el.fullCalendar.getApi().prev(); this.createDate(); },
        createDate : function(){ const date =  this.el.fullCalendar.getApi().currentData.viewTitle; this.date = date; },
        handleDateClick: function(arg) {
            alert('date click! ' + arg.dateStr)
        },
        dateUpdate : function (info) {
            console.log(info.event)
            console.log(info)
            alert(info.event.title + " was dropped on " + info.event.start.toISOString());

            if (!confirm("Are you sure about this change?")) {
                info.revert();
            }
        }
    }
}


/**
event  : {
    allDay: true
    allow: null
    classNames: Array(0)
    constraint: null
    display: "auto"
    durationEditable: undefined
    
    

    id: ""
    title: "event 1"
    backgroundColor: ""
    borderColor: ""
    groupId: ""

    endStr: "2020-10-09"
    end: Fri Oct 09 2020 00:00:00 GMT+0900 (대한민국 표준시)

    startStr: "2020-10-07"
    start: Wed Oct 07 2020 00:00:00 GMT+0900 (대한민국 표준시)

    extendedProps: Object
    overlap: null
    source: EventSourceApi
    startEditable: undefined
    textColor: ""
    url: ""
}
*/
</script>

