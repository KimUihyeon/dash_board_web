import Vue from 'vue'
import Vuex from 'vuex'

import { appStoreBudle } from './common/AppStore';
import { userStoreBudle } from './common/UserStore';
import { locationStoreBudle } from './common/LocationSotre';
import { calendarStoreBudle } from './calendar/CalendarStore';
import { todoStoreBudle } from './todo/TodoStore';
import { weatherStoreBudle } from './weather/WeatherStore';
import { timerStoreBudle } from './common/TimerStore'

Vue.use(Vuex);


let stores = [ appStoreBudle, userStoreBudle , locationStoreBudle , todoStoreBudle , weatherStoreBudle ,timerStoreBudle , calendarStoreBudle ];

/**
 *  state : {
 *    time : '',
 *    user : {},
 *    location : {},
 *    weather : {},
 *    todo : {},
 *    calendar : {},
 * }
 */

let state = {},
   getters = {},
   mutations = {},
   actions = {};

stores.forEach(store=>{
  let name = store.name;
  state[name] = store.state;

  for(var getFunc in store.getters){
    getters[getFunc] = store.getters[getFunc];
  }

  for(var mutationFunc in store.mutation){
    mutations[mutationFunc] = store.mutation[mutationFunc];
  }

  for(var actFunc in store.actions){
    actions[actFunc] = store.actions[actFunc];
  }
})


// console.log(actions);


if( process.env.NODE_ENV.indexOf('dev') !== -1 ){
  console.log(state);
}

export default new Vuex.Store({
  state : {
    ...state,
  },
  getters: {
    ...getters,
  },
  mutations: {
    ...mutations,
  },
  actions: {
    ...actions,
  },
  modules: {
  }
})
