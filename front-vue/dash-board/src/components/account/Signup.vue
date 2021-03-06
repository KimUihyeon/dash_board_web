<template>
    <el-dialog
        title="간편 회원가입"
        :visible.sync="show"
        :close="() => { showModal(); }"
        :close-on-click-modal="false" >
        <ValidationObserver v-slot="{ handleSubmit }">
            <el-form ref="form" label-width="120px">
                <div>
                    <ValidationProvider rules="required|email|minMax:5,30|noTrim" name="email" v-slot="{ errors }" immediate>
                        <el-form-item label="id">
                            <el-input
                                type="text"
                                @keydown.enter.native="() => { nextFouse(errors, $refs.pw); }"
                                :suffix-icon="isIdConfirm ? 'el-icon-check' : ''"
                                placeholder="email 형식 (****@email.com)"
                                ref="id"
                                v-model="id"
                                size="small" >
                                <el-button
                                    slot="append"
                                    v-if="isIdConfirm == false"
                                    @click="() => { emailConfirm(errors); }">중복확인</el-button>
                            </el-input>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div>
                    <ValidationProvider name="password" v-slot="{ errors }" rules="required|minMax:8,20">
                        <el-form-item label="password">
                            <el-input
                                type="password"
                                @keydown.enter.native="() => { nextFouse(errors, $refs.pwConfirm) }"
                                placeholder="영문 숫자 조합 8자리 이상."
                                ref="pw"
                                v-model="pw"
                                size="small"></el-input>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div>
                    <ValidationProvider v-slot="{ errors }" rules="required|minMax:8,20|passwordConfirm:@password">
                        <el-form-item>
                            <el-input
                                type="password"
                                @keydown.enter.native="() => {nextFouse(errors, $refs.name);}"
                                placeholder="패스워드 재입력"
                                ref="pwConfirm"
                                v-model="pwConfirm"
                                size="small"></el-input>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div>
                    <ValidationProvider name="name" v-slot="{ errors }" rules="required|minMax:2,20|noTrim">
                        <el-form-item label="name">
                            <el-input
                                type="text"
                                @keydown.enter.native="() => {
                                        nextFouse(errors, $refs.name);
                                        handleSubmit(formSubmit);}"
                                placeholder="닉네임"
                                ref="name"
                                v-model="name"
                                size="small"></el-input>
                            <span class="validation-box">{{ errors[0] }}</span>
                        </el-form-item>
                    </ValidationProvider>
                </div>

                <div style="text-align: center;">
                    <el-button size="small" type="primary" @click="handleSubmit(formSubmit)" round>가입하기</el-button>
                    <el-button size="small" type="danger" @click="() => { show = false; }" round>나가기</el-button>
                </div>
            </el-form>
        </ValidationObserver>
    </el-dialog>
</template>

<script>
import { data, alert, rest } from '../../util';
import { accountService } from '../../services';
import { delay } from '../../util/Delay';

const name = 'Signup';
const props = { showModal: Boolean };

export default {
    name,
    props,
    data() {
        return {
            id: '',
            pw: '',
            pwConfirm: '',
            name: '',
            show: false,
            isIdConfirm: false,
            isBlurs: false,
        };
    },
    methods: {
        nextFouse(validationErr, nextElement) { if (data.isNull(validationErr)) { nextElement.focus(); }},
        fromReset() {
            this.id = '';
            this.pw = '';
            this.name = '';
            this.isIdConfirm = false;
        },
        formSubmit() {
            if (this.isIdConfirm === false) {
                alert.duplicateCheck(this);
                return;
            }

            accountService.signup(this.id, this.pw, this.name)
                .then((res) => { delay.immediately(()=>{ this.show = false; }) })
                .catch((err) => {
                    this.show = false;
                    alert.serverErrorAlert(this);
                });
        },
        emailConfirm(errors, value) {
            if (!data.isNull(errors)) {
                return;
            } else {
                consoel.log(this.id)
                accountService.duplicateCheck(this.id).then((res) => {
                    if (res === false) {
                        alert.notDuplicateEmailSuccessAlert(this);
                        this.isIdConfirm = true;
                    } else {
                        alert.isDuplicateEmailSuccessAlert(this);
                        this.isIdConfirm = false;
                    }
                });
            }
        },
    },
    watch: {
        showModal(v) { this.show = v; this.fromReset(); },
        id(v) { this.isIdConfirm = false; },
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
        margin-bottom: 15px !important;
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
