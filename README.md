# The Community Project

## 第一天

### 1.1明确项目内容

> 做一个论坛式的项目

### 1.2大概的技术栈

> spring boot,git

### 1.2 初始化github厂库

>1. 初始化一个厂库
>2. 本地的git init
>3. 然后设置远程分支 git remote add origin “git地址”
>4. git push -u origin master 

### 1.3 申请一个github App

> 1. 找到github 网站下面的API
> 2. [找到关于Developer下的APP的OAuth Apps 可以看到说明文档](https://docs.github.com/en/free-pro-team@latest/developers/apps/creating-an-oauth-app)
> 3. 照着步骤操作就行了
>
> 

### 1.4完成使用github作为第三方登录的功能

>
>
>1. 根据官网的[api文档](https://docs.github.com/en/free-pro-team@latest/developers/apps/authorizing-oauth-apps)建立接口
>2. 将用户数据通过ajax交互传到前端还存到session中

```yml
# spring config
server:
  port: 80 #server port
spring:
  thymeleaf:
    cache: false # 关闭模板引擎缓存
  datasource: # 数据源配置
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: abcd0123
    url: jdbc:mysql://localhost:3306/community?serverTimezone=UTC
community:
  github:
    clientId: 088456cd97b4eb2a2bbf
    redirectUri: http://localhost/github/authorizedCallback
    scope: user
    clientSecret: 50fe47b4e7ab5238523b3e9d35a750f9498c57b5
    accessTokenUrl: https://github.com/login/oauth/access_token
    userInfoUrl: https://api.github.com/user
```

