
## Override

只支持http

Android访问地址为ws://ip:port/websocket



## 信令设计

 

1. 登录成功，返回个人信息，用来显示用户的在线状态

   ```json
   {
   	"eventName":"__login_success",
   	"data":{
           "userID":"userId",
           "avatar":"...jpg"
       }
   }
   ```

   

2. 邀请加入房间

   ```json
   # 服务器负责转发
   {
       	"eventName":"__invite",
      	 	"data":{
       		"inviteId":"userId",
               "userList":"userId,usrId,userId"  #逗号分割
       }
   }
   ```

3. 对方已响铃

   ```json
   
   ```

   

































