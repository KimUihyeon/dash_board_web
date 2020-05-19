import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


/** element UI */
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/codepen-bg-blurbox.css';
import '@/assets/css/element-ui-custom.css';
import '@/assets/css/default.css';
import '@/assets/css/nav.css';
Vue.use(ElementUI);


/** vuetify 아이콘만ㅁ 쓰는중 ..! */
import vuetify from './plugins/vuetify';


/** vee-validation */
import { ValidationProvider , extend , ValidationObserver } from 'vee-validate';
import { required, email } from 'vee-validate/dist/rules';

extend('email', {
  ...email,
  message : '이메일 형식이어야 합니다.'
});

extend('minMax', {
  validate(value , {min , max }){
    return value.length >= min && value.length <=max;
  },
  params : ['min','max'],
  message : '{min}자 이상 {max}자 이하로 입력해주세요.'
});


extend('required', {
  ...required,
  message: '필수입력 입니다.'
});

extend('passwordConfirm', {
  params: ['target'],
  validate(value, { target }) {
    return value === target;
  },
  message: '패스워드가 일치하지 않습니다.'
});

extend('noTrim', {
  validate(value) {
    let trimValue = value.trim().replace(/ /g, value);
    return trimValue === value;
  },
  message: '띄어쓰기는 사용할수 없습니다.'
});


Vue.component('ValidationObserver' , ValidationObserver);
Vue.component('ValidationProvider' , ValidationProvider);

/** dotenv */
import dotenv from 'dotenv'
dotenv.config();


new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
