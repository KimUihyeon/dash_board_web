<template>
    <span class="sns-login-button-area" :style="getStyle">
        <a>
            <div :class="'sns-login-button ' + iconClass" @click="clickHandle"></div>
        </a>
    </span>
</template>

<script>
import { data } from '../../util';

const name = 'SnsLoginButton';

export default {
    name,
    props: {
        size: String,
        margin: String,
        padding: String,
        iconClass: String,
        clickHandle: {
            type: Function,
            default: () => {},
        },
    },
    computed: {
        getStyle() {
            let styleResult = '';
            if (!data.isNull(this.size)) {
                styleResult += 'width: ' + this.size + ';';
                styleResult += 'height: ' + this.size + ';';
            }
            if (!data.isNull(this.margin)) {
                styleResult += 'margin: ' + this.margin + ';';
            }
            if (!data.isNull(this.padding)) {
                styleResult += 'padding: ' + this.padding + ';';
            }
            return styleResult;
        },
        getIcon() {
            let result = 'kakao-login-icon';

            return result;
        },
    },
};
</script>

<style scoped>
.sns-login-button-area {
    display: inline-block;
}
.sns-login-button {
    width: 100%;
    height: 100%;
    border-radius: 3px;
}
</style>
