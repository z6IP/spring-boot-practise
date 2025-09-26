<template>
    <div class="home-container">
        <!-- Element Plus 布局容器 -->
        <el-container>
            <!-- 页头：包含标题和退出登录按钮 -->
            <el-header class="header">
                <div class="header-content">
                    <h2 class="title">欢迎来到首页</h2>
                    <div class="user-info">
                        <el-button type="primary" @click="logout">
                            <el-icon>
                                <SwitchButton />
                            </el-icon>
                            退出登录
                        </el-button>
                    </div>
                </div>
            </el-header>

            <!-- 主内容区：包含用户信息卡片和功能卡片 -->
            <el-main class="main-content">
                <!-- 用户信息卡片 -->
                <el-card class="welcome-card">
                    <template #header>
                        <div class="card-header">
                            <el-icon>
                                <User />
                            </el-icon>
                            <span>用户信息</span>
                        </div>
                    </template>
                    <div class="user-profile">
                        <el-descriptions :column="1" size="large">
                            <el-descriptions-item label="手机号">
                                {{ userPhone }}
                            </el-descriptions-item>
                            <el-descriptions-item label="登录时间">
                                {{ loginTime }}
                            </el-descriptions-item>
                            <el-descriptions-item label="Token状态">
                                <el-tag type="success">已认证</el-tag>
                            </el-descriptions-item>
                        </el-descriptions>
                    </div>
                </el-card>

                <!-- 功能区域卡片（待开发） -->
                <el-card class="feature-card">
                    <template #header>
                        <div class="card-header">
                            <el-icon>
                                <Setting />
                            </el-icon>
                            <span>功能区域</span>
                        </div>
                    </template>
                    <el-empty description="功能开发中..." />
                </el-card>
            </el-main>
        </el-container>
    </div>
</template>

<script setup lang="ts">
// 导入Vue核心API
import { ref, onMounted } from "vue";
// 导入路由相关
import { useRouter } from "vue-router";
// 导入Element Plus组件和图标
import { ElMessage } from "element-plus";
import { SwitchButton, User, Setting } from "@element-plus/icons-vue";

// 路由实例
const router = useRouter();

// 响应式变量：存储用户信息
const userPhone = ref("");
const loginTime = ref("");

/**
 * 组件挂载时触发：从localStorage读取用户信息
 */
onMounted(() => {
    // 获取本地存储的手机号和登录时间
    const phone = localStorage.getItem("userPhone");
    const time = localStorage.getItem("loginTime");

    // 赋值并格式化时间（转为本地时间字符串）
    if (phone) {
        userPhone.value = phone;
    }
    if (time) {
        loginTime.value = new Date(time).toLocaleString();
    } else {
        // 若未获取到登录时间，默认显示当前时间
        loginTime.value = new Date().toLocaleString();
    }
});

/**
 * 退出登录：清除本地存储 + 跳转登录页
 */
const logout = () => {
    // 清除认证相关的本地存储数据
    localStorage.removeItem("token");
    localStorage.removeItem("userPhone");
    localStorage.removeItem("loginTime");

    // 提示用户并跳转登录页
    ElMessage.success("退出登录成功");
    router.push("/login");
};
</script>

<style scoped>
/* 首页容器：全屏背景 + 基础样式 */
.home-container {
    width: 100%;
    height: 100vh;
    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
}

/* 页头样式：渐变背景 + 阴影 */
.header {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 页头内容区：居中 + 两端对齐 */
.header-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
}

/* 页头标题：白色文字 + 字体样式 */
.title {
    color: white;
    margin: 0;
    font-size: 24px;
    font-weight: 600;
}

/* 用户信息区：基础布局 */
.user-info {
    display: flex;
    align-items: center;
}

/* 主内容区：内边距 + 宽度控制 */
.main-content {
    width: 100%;
    padding: 20px;
}

/* 卡片通用样式：圆角 + 阴影 + 间距 */
.welcome-card,
.feature-card {
    margin-bottom: 20px;
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    width: 100%;
}

/* 卡片头部：图标 + 标题布局 */
.card-header {
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 18px;
    font-weight: 600;
    color: #409eff;
}

.card-header .el-icon {
    font-size: 20px;
}

/* 用户信息卡片内容区：内边距 */
.user-profile {
    padding: 20px 0;
}

/* 深度选择器：调整Descriptions组件样式 */
:deep(.el-descriptions__label) {
    font-weight: 600;
    color: #606266;
}

:deep(.el-descriptions__content) {
    color: #303133;
}

/* 深度选择器：调整按钮样式 */
:deep(.el-button) {
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.3s ease;
}

:deep(.el-button:hover) {
    transform: translateY(-1px);
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
}

/* 深度选择器：调整卡片头部背景 */
:deep(.el-card__header) {
    background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
    border-bottom: 1px solid #e4e7ed;
}
</style>