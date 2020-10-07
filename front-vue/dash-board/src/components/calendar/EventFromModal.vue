<template>
    <el-dialog
        :title="title"
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
                                    { console.log('change'); nextFouse(null, $refs.memo);}"
                            >
                            <!-- <el-date-picker
                                v-model="startEndDate"
                                type="datetimerange"
                                align="center"
                                ref="start"
                                unlink-panels
                                range-separator="To"
                                :default-time="['12:00:00','12:00:00']"
                                start-placeholder="Start date"
                                end-placeholder="End date"
                            > -->
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
                        round>저장</el-button>

                    <el-button
                        size="small"
                        type="danger"
                        @click="close"
                        round>나가기</el-button
                    >
                </div>
            </el-form>
        </ValidationObserver>
    </el-dialog>
</template>

<script>
import { data, alert, rest, date } from '../../util';
import { accountService } from '../../services';
import SelectBox from '../../components/calendar/SelectBox';

const name = 'EventFormModal';
const props = { 
    title : String,
    showModal: Boolean , 
    submitAfterHandle : { 
        type : Function , 
        default :  () => {}
    },
    event : Object,
    cals : Array
};
const components = {
    SelectBox,
}

export default {
    name, props, components,
    data() {
        return {
            cloneEvnet: {
                title: '',
                sdate: '',
                edate: '',  
                icon : '',
                context : '',
                calendarId : -1,
            },
            startEndDate: '',
            show: false,
            isBlurs: false,
        };
    },
    methods: {
        init(eventObj) {
            if(data.isNull(eventObj)) {
                this.cloneEvnet = {
                    title: '',
                    sdate: '',
                    edate: '',
                    icon : '',
                    context : '',
                    calendarId : this.cals[0].id,
                };
            }else {
                this.cloneEvnet = eventObj;
            }
        },
        submit() {
            this.close();
            setTimeout(()=>{
                console.log(this.cloneEvnet)
                this.submitAfterHandle(this.cloneEvnet);
            },10)

            // accountService
            //     .signup(this.title, this.pw)
            //     .then((res) => {
            //         setTimeout(() => {
            //             this.show = false;
            //         }, 50);
            //     })
            //     .catch((err) => {
            //         this.show = false;
            //         this.alert('error', err);
            //     });
        },
        nextFouse(validationErr, nextElement) {
            if (data.isNull(validationErr)) {
                nextElement.focus();
            }
        },
        alert(type, message) {
            alert.elMessageBox({
                vueObject: this,
                type,
                message,
            });
        },
        close(){
            this.show = false;
        }
    },
    watch: {
        showModal(v) {
            this.show = v;
            this.init();
        },
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
