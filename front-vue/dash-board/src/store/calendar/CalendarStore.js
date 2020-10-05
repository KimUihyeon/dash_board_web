import { rest } from "../../util/Rest";
import { calendarService , eventService } from '../../services'

/**
 *  Store 작성법
 * @since 20.04.21 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */



const state = {
    allCal : [],
    selectedIds : [],
    response_cal : [],
}

const getters = {
    getAllCalendar : function (state){
        return state.calendar.allCal;
    },
    getSelectedIds : function (state){
        return state.calendar.selectedIds;
    },
    getResponse_Cal : function (state){
        return state .calendar.response_cal;
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
    fetch_my_calendars : function (context){
        const userId = 'admin@admin.com';
        return new Promise((resolve , reject)=>{
            calendarService.getUserCalendar(userId).then(res =>{
                console.log(res);
                resolve(res)
            }).catch(err=>{
                reject(err);
            });
        })
    },
    save_calendar : function (context , payload) {
        return new Promise((resolve , reject)=>{
            calendarService.addCalendar(payload.cal).then(res =>{
                console.log(res);
                context.commit('ADD_ALL_CALENDAR', {cals : res})
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
            const event = payload.event;

            eventService.addEvent(event , loginId).then(res=>{
                resolve(res);
            }).catch(err=>{
                reject(err);
            })
        })
    }
}

const mutation = {
    SET_ALL_CALENDAR : function (state , payload) {
        state.calendar.allCal = payload.cals;
    },
    ADD_ALL_CALENDAR : function (state , payload) {
        state.calendar.allCal.push(payload.cals);
    },
    SET_RSEPONSE_CALENDAR : function (state , payload) {
        state.calendar.response_cal = payload.cals;
    },
    SET_SELECTED_CAL_ID : function (state , payload) {
        state.calendar.selectedIds = payload.calIds;
    },
}


export const calendarStoreBudle = {
    name : 'calendar',
    state,
    getters,
    actions,
    mutation
}