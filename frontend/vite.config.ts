import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    port: 3000,           // 设置开发服务器端口
    proxy: {
      '/api': {
        target: 'http://localhost:8888',  // 后端服务地址
        changeOrigin: true,               // 改变请求源
        rewrite: (path) => path.replace(/^\/api/, '')  // 路径重写
      }
    }
  }
})