
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
    __interval : {},
}

const getters = {
  currentDateTime : function (state) {
    return state.timer.time;
  },
}

const actions = {
  thread_timer : function (context){
    if(!data.isNull(context.state.timer.__interval)){
      clearInterval(context.state.timer.__interval)
    }
    
    let process = () => { 
      context.commit('SYNCHRONIZE_DATE_TIME');
      // console.log('synchronizDateTime');
    }
    context.state.timer.__interval = setInterval(process,1000);
  }

}

const mutation = {
  SYNCHRONIZE_DATE_TIME : function (state, payload){
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