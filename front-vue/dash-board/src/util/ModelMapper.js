import { format } from './DateUtil';


/**
 * fullCalendar Object
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

const calendarEventToCalendarObejct = (c, e) =>{
}
const tofullCalendarModel = (serverCalendar, serverEvent) =>{
    let c = serverCalendar;
    let e = serverEvent;
     
    let event = {
        ...serverEvent,
        start: format( e.sdate, 'yyyy-MM-DD'),
        end:  format( e.edate, 'yyyy-MM-DD'),
        backgroundColor : c.color,
        borderColor :  c.color,

        // backgroundColor: ""
    }
    return event;
}


const toServerCalendarModel = (fullCalendar) =>{
    let evnet = {
    }
}


export const modelMapper = {
    tofullCalendarModel,
}
