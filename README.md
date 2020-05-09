# 搜他科技
前后端分离开发


### 接口设计
#### 用户注册、登录

  1.注册：
  
    url: /v1.0/user
    
    method: post
    
    param: {"name":"lolo",
            "password":"sun",
	          "phoneNumber":"18961372023",
	          "smsCode":"475143",
	          "inviteCode":"1341283"......}
            
  2.短信验证码:
    
    url: /v1.0/user/captcha
    
    method: get
    
    param: phoneNumber=xxx
    
  3.登录：
  
    url: /v1.0/session
    
    method: post
    
    param: {"username":"lolo", //支持手机号码和用户名登录，前段不做校验，后端先按用户名，为空再按手机号查
            "password":"123"}
            
  4.注销
  
    url: /v1.0/session
    
    method: delete
    
  5.验证邀请码是否有效
  
    url: /v1.0/user/checkCode
    
    method: get
    
    param: invitationCode=xxx
#### 用户后台管理

  1.获得个人信息
    
    url: /v1.0/user
    
    method: get
