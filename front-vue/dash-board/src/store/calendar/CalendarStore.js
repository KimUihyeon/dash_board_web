import { rest } from "../../util/Rest";
import { calendarService , eventService } from '../../services'
import { date , modelMapper } from "../../util";

/**
 *  Store 작성법
 * @since 20.04.21 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */


 const calendarRelrationEventToArray = (cals) => {
     let events = [];
     console.log(cals);
     cals.filter(t=> t.checked).forEach((c)=>{
         c.events.forEach(e=>{
             events.push(modelMapper.tofullCalendarModel(c, e));
         })
     })
     console.log(events);
     return events;
 }


const state = {
    allCal : [],
    events : []
}

const getters = {
    getAllCalendar : function (state){
        return state.calendar.allCal;
    },
    getEvents : function (state){
        return state.calendar.events;
    }
}

const actions = {
    initialization_cal : function (context){
        return new Promise((resolve , reject)=>{
            context.commit('SET_ALL_CALENDAR', { cals : [] });
            context.commit('SET_SELECTED_CAL_ID', { cals : [] });
            context.commit('SET_RSEPONSE_CALENDAR', { cals : [] });
        });
    },
    fetch_my_calendars : function (context , payload){
        const { userId  }= payload ;
        return new Promise((resolve , reject)=>{
            calendarService.getUserCalendar(userId).then(res =>{

                context.commit('SET_ALL_CALENDAR', { cals : res });
                context.commit('SYNC_EVENT');
                resolve(res)
            }).catch(err=>{
                reject(err);
            });
        })
    },
    save_calendar : function (context , payload) {
        return new Promise((resolve , reject)=>{
            calendarService.addCalendar(payload.cal).then(res =>{
                let cal = { ...res , events : [] }
                context.commit('ADD_ALL_CALENDAR', { cal })
                resolve(res);
            }).catch(err =>{
                console.log(err);
                reject(err);
            })
        })
    },
    remove_calendar : function (context, payload) {
        return new Promise((resolve , reject)=>{
            calendarService.deleteCalendar(payload.cal).then(res=>{
                context.commit('REMOVE_CALENDAR', {cal : res})
                context.commit('SYNC_EVENT');
                resolve(res);
            }).catch(err=>{
                console.log(err)
                reject(err);
            })

        })
    },
    patch_calendar : function (context , payload) {
        return new Promise((resolve , reject)=>{
            calendarService.updateCalendar(payload.cal).then(res =>{
                context.commit('PATCH_CALENDAR', {cal : res})
                context.commit('SYNC_EVENT');
                resolve(res);
            }).catch(err =>{
                console.log(err);
                reject(err);
            })
        })
    },
    save_event : function (context , payload) {
        return new Promise((resolve , reject)=>{
            const loginId = 'admin@admin.com';
            const { event } = payload;
            event.sdate += ' 00:00:00';
            event.edate += ' 23:59:59';

            eventService.addEvent(event , loginId).then(res=>{
                context.commit('ADD_EVENT',{ event : res}) 
                context.commit('SYNC_EVENT');
                resolve(res); 
            }).catch(err=>{ 
                reject(err);
            })
        })
    },
    patch_event : function(context , payload){
        return new Promise((resolve , reject)=>{
            const { event } = payload;
            const serverEvent = modelMapper.toServerEventModel(event);
            serverEvent.sdate += ' 00:00:00';
            serverEvent.edate += ' 23:59:59';

            eventService.updateEvent(serverEvent).then(res=>{
                
                context.commit('PATCH_EVENT', { event : res })
                context.commit('SYNC_EVENT');
            }).catch(err=>{

            })

            
        });
    }
}

const mutation = {
    SET_ALL_CALENDAR : function (state , payload) {
        state.calendar.allCal = payload.cals;
    },
    ADD_ALL_CALENDAR : function (state , payload) {
        state.calendar.allCal.push(payload.cal);
    },
    REMOVE_CALENDAR : function (state, payload){
        let { cal } = payload;
        state.calendar.allCal = state.calendar.allCal
                                .filter(t=> t.id !== cal.id);
    },
    PATCH_CALENDAR : function (state , payload) {
        let { cal } = payload;
        
        state.calendar.allCal = state.calendar.allCal.map(t=>{
            if(t.id === cal.id){
                return {
                    ...cal,
                    events : t.events
                }
            }
            return t;
        })
    },
    SET_RSEPONSE_CALENDAR : function (state , payload) {
        state.calendar.response_cal = payload.cals;
    },
    SET_SELECTED_CAL_ID : function (state , payload) {
        state.calendar.selectedIds = payload.calIds;
    },
    SYNC_EVENT : function (state) {
        let cals = state.calendar.allCal;
        let events = calendarRelrationEventToArray(cals);
        console.log('SYNC_EVENT -> ', date.now());
        
        
        state.calendar.events = events;
    },
    ADD_EVENT : function (state, payload) {
        let { event } = payload;

        state.calendar.allCal = state.calendar.allCal.map(t=> {
            if(t.id == event.calendarId) {
                t.events.push(payload.event)
            }
            return t;
        })
    },
    PATCH_EVENT : function (state , payload){
        let { event } = payload;
        console.log(event);

        let allCal = [...state.calendar.allCal];
        allCal.forEach(c=>{
            if(c.id == event.calendarId){
                c.events.forEach(e=>{
                    if(e.id == event.id){
                        e.edate = event.edate;
                        e.sdate = event.sdate;
                    }
                })
            }
        })

        state.calendar.allCal = allCal;
    }
}


export const calendarStoreBudle = {
    name : 'calendar',
    state,
    getters,
    actions,
    mutation
}