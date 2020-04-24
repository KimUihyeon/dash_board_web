
/**
 *  Store 작성법
 * @since 20.04.25 김의현
 * 
 *  state = { property : '' }
 *  getters = { functionName(){} },
 *  actions = { functionName(){} },
 *  mutation = { functionName(){} },
 */

import { date , data } from '../../util'

const state = {
    time :  date.now(),
    interval : {},

}

const getters = {
    currentDateTime : function (state) {
      return state.timer.time;
    },
}

const actions = {
    synchronizDateTimeThead : function (context){
        if(!data.isNull(context.state.timer.interval)){
          clearInterval(context.state.timer.interval)
        }
        
        let process = () => { 
          context.commit('synchronizDateTime');
          console.log('synchronizDateTime');
        }
        context.state.timer.interval = setInterval(process,1000);
      }

}

const mutation = {
    synchronizDateTime : function (state, payload){
      state.timer.time = date.now();
    }

}


export const timerStoreBudle = {
    name : 'timer',
    state,
    getters,
    actions,
    mutation
}