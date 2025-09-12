<template>
    <div class="app-container">
        <el-card class="upload-card">
            <template #header>
                <div class="card-header">
                    <h2>阿里云OSS图片上传</h2>
                </div>
            </template>

            <!-- 上传区域 -->
            <div class="upload-section">
                <el-upload
                    ref="uploadRef"
                    class="upload-demo"
                    :action="uploadUrl"
                    :before-upload="beforeUpload"
                    :on-success="handleSuccess"
                    :on-error="handleError"
                    :show-file-list="false"
                    :loading="uploading"
                    drag
                    accept="image/*"
                >
                    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
                    <div class="el-upload__text">
                        将图片拖到此处，或<em>点击上传</em>
                    </div>
                    <template #tip>
                        <div class="el-upload__tip">
                            支持jpg/png格式，文件大小不超过10MB
                        </div>
                    </template>
                </el-upload>
            </div>

            <!-- 进度显示区域 -->
            <div v-if="uploading" class="loading-section">
                <el-progress :percentage="uploadProgress" :status="progressStatus"></el-progress>
                <p>正在上传图片...</p>
            </div>

            <!-- 图片预览区域 -->
            <div v-if="uploadedImages.length > 0" class="preview-section">
                <h3>上传成功的图片</h3>
                <div class="image-grid">
                    <div
                        v-for="(image, index) in uploadedImages"
                        :key="index"
                        class="image-item"
                    >
                        <el-image
                            :src="image.url"
                            :preview-src-list="imageUrls"
                            :initial-index="index"
                            fit="cover"
                            class="preview-image"
                        >
                            <template #error>
                                <div class="image-error">
                                    <el-icon><picture-filled /></el-icon>
                                    <span>加载失败</span>
                                </div>
                            </template>
                        </el-image>
                        <div class="image-actions">
                            <el-button
                                type="primary"
                                size="small"
                                @click="copyUrl(image.url)"
                            >
                                复制链接
                            </el-button>
                            <el-button
                                type="danger"
                                size="small"
                                @click="removeImage(index)"
                            >
                                删除
                            </el-button>
                        </div>
                        <div class="image-info">
                            <p>文件名: {{ image.name }}</p>
                            <p>上传时间: {{ image.uploadTime }}</p>
                        </div>
                    </div>
                </div>
            </div>
        </el-card>
    </div>
</template>

<script setup lang="ts">
// 导入必要的 Vue 函数和 Element Plus 组件
import { ref, computed } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { UploadFilled, PictureFilled } from '@element-plus/icons-vue'
import type { UploadProps, UploadFile } from 'element-plus'

// 定义 TypeScript 接口
interface ImageInfo {
    name: string
    url: string
    uploadTime: string
}

// 响应式状态变量
const uploadRef = ref()
const uploading = ref(false)
const uploadProgress = ref(0)
const progressStatus = ref<'' | 'success' | 'exception' | 'warning'>('')
const uploadedImages = ref<ImageInfo[]>([])

// 上传接口地址（会被 Vite 代理）
const uploadUrl = '/api/upload'

// 计算属性：用于图片预览
const imageUrls = computed(() => uploadedImages.value.map(img => img.url))

// 上传前的文件校验
const beforeUpload: UploadProps['beforeUpload'] = (file) => {
    const isImage = file.type.startsWith('image/')
    const isLt10M = file.size / 1024 / 1024 < 10

    if (!isImage) {
        ElMessage.error('只能上传图片格式的文件!')
        return false
    }
    if (!isLt10M) {
        ElMessage.error('上传图片大小不能超过 10MB!')
        return false
    }

    // 开始上传，设置状态
    uploading.value = true
    uploadProgress.value = 0
    progressStatus.value = ''

    // 模拟进度条更新
    const timer = setInterval(() => {
        if (uploadProgress.value < 90) {
            uploadProgress.value += 10
        } else {
            clearInterval(timer)
        }
    }, 200)

    return true
}

// 上传成功回调
const handleSuccess: UploadProps['onSuccess'] = (response: any, file: UploadFile) => {
    uploading.value = false
    uploadProgress.value = 100
    progressStatus.value = 'success'

    if (response && typeof response === 'string' && response.startsWith('http')) {
        const imageInfo: ImageInfo = {
            name: file.name || '未知文件',
            url: response,
            uploadTime: new Date().toLocaleString()
        }
        uploadedImages.value.unshift(imageInfo)
        ElMessage.success('图片上传成功!')
    } else {
        ElMessage.error('上传失败: ' + (response || '未知错误'))
    }

    // 2秒后重置进度条
    setTimeout(() => {
        uploadProgress.value = 0
        progressStatus.value = ''
    }, 2000)
}

// 上传失败回调
const handleError: UploadProps['onError'] = (error) => {
    uploading.value = false
    uploadProgress.value = 0
    progressStatus.value = 'exception'
    console.error('Upload error:', error)
    ElMessage.error('上传失败，请稍后重试')

    setTimeout(() => {
        progressStatus.value = ''
    }, 2000)
}

// 复制图片链接到剪贴板
const copyUrl = async (url: string) => {
    try {
        await navigator.clipboard.writeText(url)
        ElMessage.success('链接已复制到剪贴板')
    } catch (err) {
        // 兜底方案：使用传统方法
        const textArea = document.createElement('textarea')
        textArea.value = url
        document.body.appendChild(textArea)
        textArea.select()
        document.execCommand('copy')
        document.body.removeChild(textArea)
        ElMessage.success('链接已复制到剪贴板')
    }
}

// 删除图片记录
const removeImage = async (index: number) => {
    try {
        await ElMessageBox.confirm(
            '确定要删除这张图片吗？',
            '确认删除',
            {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }
        )
        uploadedImages.value.splice(index, 1)
        ElMessage.success('删除成功')
    } catch {
        ElMessage.info('已取消删除')
    }
}
</script>

<style scoped>
/* 应用容器样式 */
.app-container {
    min-height: 100vh;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    padding: 20px;
    display: flex;
    justify-content: center;
    align-items: flex-start;
}

/* 上传卡片样式 */
.upload-card {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
}

.card-header h2 {
    margin: 0;
    color: #409eff;
    text-align: center;
}

/* 上传区域样式 */
.upload-section {
    margin-bottom: 30px;
}

.upload-demo {
    width: 100%;
}

/* 加载状态样式 */
.loading-section {
    text-align: center;
    margin: 20px 0;
}

.loading-section p {
    margin-top: 10px;
    color: #666;
}

/* 预览区域样式 */
.preview-section {
    margin-top: 30px;
}

.preview-section h3 {
    color: #333;
    margin-bottom: 20px;
}

/* 图片网格布局 */
.image-grid {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
}

.image-item {
    border: 1px solid #dcdfe6;
    border-radius: 8px;
    overflow: hidden;
    background: #fff;
    transition: box-shadow 0.3s;
}

.image-item:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.preview-image {
    width: 100%;
    height: 200px;
    cursor: pointer;
}

.image-error {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 200px;
    color: #999;
}

.image-actions {
    padding: 10px;
    display: flex;
    gap: 10px;
    justify-content: center;
    background: #f5f7fa;
}

.image-info {
    padding: 10px;
    font-size: 12px;
    color: #666;
    background: #fafafa;
}

.image-info p {
    margin: 5px 0;
    word-break: break-all;
}

/* 响应式设计 */
@media (max-width: 768px) {
    .app-container {
        padding: 10px;
    }

    .image-grid {
        grid-template-columns: 1fr;
    }

    .image-actions {
        flex-direction: column;
    }
}
</style>