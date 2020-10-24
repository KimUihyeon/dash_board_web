<template>
    <el-dialog
        :title="MOD == 'ADD' ? '일정 추가' : '일정 수정'"
        :visible.sync="show"
        :close-on-click-modal="false"
    >
    
        <ValidationObserver v-slot="{ handleSubmit }">
            <el-form ref="form" label-width="120px">
                <div>
                    <ValidationProvider rules="required|" name="email" v-slot="{ errors }" immediate>
                        <el-form-item label="일정명">
                            <el-input
                                type="text"
                                @keydown.enter.native="
                                    () => {
                                        nextFouse(errors, $refs.start);
                                    }
                                "
                                placeholder="일정을 적어주세요."
                                ref="event"
                                v-model="cloneEvnet.title"
                                size="small"
                            >
                            </el-input>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div>
                    <ValidationProvider name="password" v-slot="{ errors }" rules="required">
                        <el-form-item label="시작일">
                            <el-date-picker
                                v-model="startEndDate"
                                type="daterange"
                                align="cePnter"
                                ref="start"
                                unlink-panels
                                range-separator="To"
                                start-placeholder="Start date"
                                end-placeholder="End date"
                                :change="()=>
                                    { nextFouse(null, $refs.memo);}"
                            >
                            </el-date-picker>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>
                
                <div>       
                    <ValidationProvider name="required|" v-slot="{ errors }" rules="required">
                        <el-form-item label="캘린더">
                            <el-select v-model="cloneEvnet.calendarId" placeholder="Select">
                                <SelectBox :items='cals'/>
                            </el-select>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div>
                    <el-form-item label="메모">
                        <el-input 
                            type="textarea" 
                            :rows="5" 
                            ref='memo'
                            placeholder="메모"
                            v-model="cloneEvnet.context"
                            size="small">
                        </el-input>
                    </el-form-item>
                </div>
                <div style="text-align: center;">
                    <el-button 
                        size="small" 
                        type="primary" 
                        @click="handleSubmit(submit)" 
                        round>{{ MOD == 'ADD' ? '추가' : '수정' }}</el-button>

                    <el-button
                        size="small"
                        type="danger"
                        @click="modalClose"
                        round>나가기</el-button
                    >

                    <el-button
                        v-if="MOD == 'EDIT'"
                        size="small"
                        type="danger"
                        @click="()=>{ deleteHandle(cloneEvnet) }"
                        round>일정 삭제</el-button
                    >
                </div>
            </el-form>
        </ValidationObserver>
    </el-dialog>
</template>

<script>
import { data, alert, rest, date, delay } from '../../util';
import { accountService } from '../../services';
import SelectBox from '../../components/calendar/SelectBox';

const name = 'EventFormModal';
const props = { 
    cals : Array,
    event : Object,
    title : String,
    showModal: Boolean , 
    submitAfterHandle : { 
        type : Function , 
        default :  () => {}
    },
    deleteHandle : { 
        type : Function , 
        default :  () => {}
    },
};
const components = {
    SelectBox,
}

export default {
    name, props, components,
    data() {
        return {
            // eventObj : {},
            // cloneEvnet: {
            //     title: '',
            //     sdate: '',
            //     edate: '',
            //     icon : '',
            //     context : '',
            //     calendarId : -1,
            // },
            show: false,
            MOD : 'EDIT' |  'ADD',
            eventObj : {},
            cloneEvnet: {},
            startEndDate: '',
        };
    },
    methods: {
        modalClose(){ return new Promise((resolve , reject)=>{ this.show = false; resolve() })},
        nextFouse(validationErr, nextElement) { if (data.isNull(validationErr)) { nextElement.focus()} },
        submit() { this.modalClose().then(res=>{ this.submitAfterHandle(this.cloneEvnet) }); },
        setEventObj(e){ this.eventObj = e; },
        newEventObj(e){
            return {
                title: '',
                sdate: '',
                edate: '',
                icon : '',
                context : '',
                calendarId : this.cals[0].id,
            }
        },
        copyEventObj(e){ 
            return {
                id : e.id,
                title: e.title,
                sdate: e.sdate,
                edate: e.edate,
                icon :e.icon,
                context : e.context,
                calendarId : e.calendarId,
            }
        },

        init(e) {
            if(data.isNull(e)) {
                this.cloneEvnet = this.newEventObj(e);
                this.MOD = 'ADD';
                this.startEndDate = '';
            }else {
                this.cloneEvnet = this.copyEventObj(e);
                this.MOD = 'EDIT';
                const edate = date.format(e.sdate , 'yyyy-MM-DD');
                const sdate = date.format(e.edate , 'yyyy-MM-DD');
                this.startEndDate = [edate, sdate];
            }
        },
    },
    watch: {
        showModal(v) { this.show = v; this.init(this.eventObj); },
        startEndDate(v) {
            if(data.isNull(v)){
                return;
            }

            const format = 'YYYY-MM-DD';

            let start = date.format(v[0], format);
            let end = date.format(v[1], format);

            this.cloneEvnet.sdate = start;
            this.cloneEvnet.edate = end;
        },
    },
};
</script>

<style>
/* pc */
.validation-box {
    position: absolute;
    display: block;
    line-height: 10px;
    font-size: 11px;
    color: red;
    text-indent: 5px;
    bottom: -15px;
}
.el-date-editor {
    width: 100% !important;
}
.el-range-separator {
    width: 30px !important;
}
.el-form-item__content > div {
    width: 100%;
}

@media only all and (min-width: 768px) {
    .el-dialog {
        min-width: 500px;
        width: 30% !important;
    }
    .el-form-item__label {
        width: 120px !important;
    }
    .el-form-item__content {
        margin-left: 120px !important;
    }
    .el-form-item {
        margin-bottom: 25px !important;
    }
}

/* mobile */
@media all and (max-width: 768px) {
    .el-dialog {
        width: 95% !important;
    }
    .el-form-item__label {
        width: 80px !important;
    }
    .el-form-item__content {
        margin-left: 80px !important;
    }
    .el-form-item {
        margin-bottom: 19px !important;
    }
}
</style>
