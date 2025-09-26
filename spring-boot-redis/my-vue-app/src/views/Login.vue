<template>
    <div class="login-container">
        <el-card class="login-card">
            <!-- 卡片头部 -->
            <template #header>
                <div class="card-header">
                    <el-icon>
                        <Iphone />
                    </el-icon>
                    <span class="title">短信登录</span>
                </div>
            </template>

            <!-- 登录表单 -->
            <el-form @submit.prevent="login" :model="formData" ref="loginFormRef" :rules="rules">
                <!-- 手机号输入框 -->
                <el-form-item prop="phone">
                    <el-input v-model="phone" placeholder="请输入手机号" size="large" :prefix-icon="Iphone" maxlength="11"
                        show-word-limit />
                </el-form-item>

                <!-- 发送验证码按钮 -->
                <el-form-item>
                    <el-button type="primary" @click="sendSms" :disabled="isCountingDown" :loading="isSendingSms"
                        size="large" style="width: 100%">
                        <el-icon>
                            <Message />
                        </el-icon>
                        {{ isCountingDown ? `${countdown}秒后重发` : "发送验证码" }}
                    </el-button>
                </el-form-item>

                <!-- 验证码输入框 + 登录按钮（发送验证码后显示） -->
                <div v-show="isCodeSent">
                    <el-form-item prop="code">
                        <el-input v-model="code" placeholder="请输入验证码" size="large" :prefix-icon="Lock" maxlength="4"
                            show-word-limit />
                    </el-form-item>

                    <el-form-item>
                        <el-button type="success" @click="login" :loading="isLoggingIn" size="large"
                            style="width: 100%">
                            <el-icon>
                                <Check />
                            </el-icon>
                            登录
                        </el-button>
                    </el-form-item>
                </div>

                <!-- 错误/成功提示 -->
                <el-alert v-if="errorMessage" :title="errorMessage" type="error" :closable="false" show-icon />
                <el-alert v-if="successMessage" :title="successMessage" type="success" :closable="false" show-icon />
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { ref, watch, reactive } from "vue";
import { useRouter } from "vue-router";
import { ElMessage, type FormInstance, type FormRules } from "element-plus";
import { Iphone, Message, Lock, Check } from "@element-plus/icons-vue";

// 类型导入
import type { LoginRequest } from "../types/api";
// API导入
import { authApi } from "../api/auth";

// 路由实例
const router = useRouter();

// 表单数据与状态
const phone = ref("18061657639");
const code = ref("");
const errorMessage = ref("");
const successMessage = ref("");
const isCountingDown = ref(false);
const countdown = ref(60);
const isSendingSms = ref(false);
const isLoggingIn = ref(false);
let timer: ReturnType<typeof setInterval>; // 倒计时定时器
const isCodeSent = ref(false); // 验证码是否已发送（控制输入框显示）

// 表单引用与规则
const loginFormRef = ref<FormInstance>();
const formData = reactive({
    phone,
    code,
});
const rules: FormRules = {
    phone: [
        { required: true, message: "请输入手机号", trigger: "blur" },
        {
            pattern: /^1[3-9]\d{9}$/,
            message: "请输入正确的手机号",
            trigger: "blur",
        },
    ],
    code: [
        { required: true, message: "请输入验证码", trigger: "blur" },
        { len: 4, message: "验证码长度为4位", trigger: "blur" },
    ],
};

/**
 * 发送短信验证码
 */
const sendSms = async () => {
    if (!loginFormRef.value) return;

    // 校验手机号
    const valid = await loginFormRef.value
        .validateField("phone")
        .catch(() => false);
    if (!valid) return;

    // 防止重复点击
    if (isCountingDown.value) return;

    // 清空之前的提示
    errorMessage.value = "";
    successMessage.value = "";
    isSendingSms.value = true;

    try {
        const response = await authApi.sendSms(phone.value);
        if (response.code === 0) {
            successMessage.value = "验证码已发送!";
            ElMessage.success("验证码已发送，请查收！");
            isCodeSent.value = true;
            startCountdown(); // 启动倒计时
        } else {
            errorMessage.value = response.msg || "发送失败，请重试。";
            ElMessage.error(response.msg || "发送失败，请重试。");
        }
    } catch (error) {
        const errorMsg = "请求失败，请稍后再试。";
        errorMessage.value = errorMsg;
        ElMessage.error(errorMsg);
    } finally {
        isSendingSms.value = false;
    }
};

/**
 * 用户登录
 */
const login = async () => {
    if (!loginFormRef.value) return;

    // 校验整个表单
    const valid = await loginFormRef.value.validate().catch(() => false);
    if (!valid) return;

    // 清空之前的提示
    errorMessage.value = "";
    successMessage.value = "";
    isLoggingIn.value = true;

    try {
        // 构造登录参数
        const loginData: LoginRequest = {
            phone: phone.value,
            code: code.value,
        };

        const response = await authApi.login(loginData);
        const loginResult = response.data;
        console.log(loginResult);

        // 登录成功：存储信息 + 跳转首页
        if (loginResult.token) {
            localStorage.setItem("token", loginResult.token);
            localStorage.setItem("userPhone", loginResult.phone);
            localStorage.setItem("loginTime", new Date().toISOString());

            successMessage.value = loginResult.message || "登录成功!";
            ElMessage.success(loginResult.message || "登录成功！");
            router.push("/home");
        } else {
            errorMessage.value = loginResult.message || "登录失败，请重试。";
            ElMessage.error(loginResult.message || "登录失败，请重试。");
        }
    } catch (error) {
        const errorMsg = "请求失败，请稍后再试。";
        errorMessage.value = errorMsg;
        ElMessage.error(errorMsg);
    } finally {
        isLoggingIn.value = false;
    }
};

/**
 * 启动验证码倒计时
 */
const startCountdown = () => {
    isCountingDown.value = true;
    countdown.value = 60; // 重置倒计时为60秒

    timer = setInterval(() => {
        if (countdown.value > 0) {
            countdown.value--;
        } else {
            clearInterval(timer);
            isCountingDown.value = false;
        }
    }, 1000);
};

// 监听倒计时状态，清除定时器（避免内存泄漏）
watch(isCountingDown, (newValue) => {
    if (!newValue) {
        clearInterval(timer);
    }
});
</script>

<style scoped>
/* 登录容器：全屏居中 + 渐变背景 */
.login-container {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    padding: 20px;
}

/* 登录卡片：限制宽度 + 阴影效果 */
.login-card {
    width: 100%;
    max-width: 400px;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
    border-radius: 15px;
    overflow: hidden;
}

/* 卡片头部：图标 + 标题居中 */
.card-header {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    font-size: 20px;
    font-weight: 600;
    color: #409eff;
}

.card-header .el-icon {
    font-size: 24px;
}

/* 标题文字渐变 */
.title {
    background: linear-gradient(45deg, #409eff, #67c23a);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
}

/* 表单样式调整 */
:deep(.el-form-item) {
    margin-bottom: 20px;
}

:deep(.el-input__wrapper) {
    border-radius: 8px;
}

/* 按钮样式：圆角 + hover效果 */
:deep(.el-button) {
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.3s ease;
}

:deep(.el-button:hover) {
    transform: translateY(-1px);
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}

/* 提示框样式 */
:deep(.el-alert) {
    border-radius: 8px;
    margin-top: 10px;
}

/* 卡片内部样式 */
:deep(.el-card__header) {
    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    border-bottom: 1px solid #e4e7ed;
}

:deep(.el-card__body) {
    padding: 30px;
}
</style>