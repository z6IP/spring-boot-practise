// 登录 request parameters
export interface LoginRequest {
  phone: string;
  code: string;
}

// unified response data type
export interface ApiResponse {
  code: number;
  msg: string;
  data: any;
}