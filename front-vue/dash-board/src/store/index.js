import Vue from 'vue'
import Vuex from 'vuex'
import { date } from '../util'

import { appStoreBudle } from './common/AppStore';
import { userStoreBudle } from './common/UserStore';
import { locationStoreBudle } from './common/LocationSotre';
import { todoStoreBudle } from './todo/TodoStore';
import { weatherStoreBudle } from './weather/WeatherStore';

Vue.use(Vuex);


let stores = [ appStoreBudle, userStoreBudle , locationStoreBudle , todoStoreBudle , weatherStoreBudle ];

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
    actions[mutationFunc] = store.actions[mutationFunc];
  }
})


if( process.env.NODE_ENV.indexOf('dev') !== -1 ){
  console.log(state);
}

export default new Vuex.Store({
  state : {
    ...state,
    time : date.now()
  },
  getters: {
    abcd : function (state){
      return 'abcd';
    },
    currentDateTime : function (state) {
      return state.time;
    },
//    ...getters,
  },
  mutations: {
    ...mutations,
    synchronizDateTime : function (state, payload){
      state.time = date.now();
    }
  },
  actions: {
    ...actions,
  },
  modules: {
  }
})
