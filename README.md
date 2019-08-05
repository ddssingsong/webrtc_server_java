
## Override

只支持http

Android访问地址为ws://ip:port/websocket

## 分支-master
可以运行的demo

## 分支-dev

**信令设计**

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
           "room":"room",
           "roomSize":9,
           "mediaType":0/1,  // 0 视频 1 语音
       	"inviteID":"userId",
           "userList":"userId,usrId,userId"  #逗号分割
       }
   }
   
   1. 创建房间
   2. 发送邀请
   3.
   ```

   

3. 对方已响铃

   ```json
   {
       "eventName":"__ring",
       "data":{
           "inviteID":"userId",
           "fromID":"myId"
       }
   }
   ```
   
   

4. 加入房间

  ```json
  {
      "eventName":"__join",
      "data":{
          "room":"room",
          "userID":"myId"
      }
  }
  
  {
      "eventName":"__peers",
      "data":{
          "connections":[],
          "userID":"myId"
      }
  }
  
  
  ```
  
  
  
  
  
  
  
  
  
5. 拒绝接听

    ```json
   {
       "eventName":"__reject",
       "data":{
           "inviteID":"userId",
           "fromID":"myId",
           "rejectType":"0/1"   //0 拒绝  1 busy  
       }
   }
    ```

   



































