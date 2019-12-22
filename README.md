
## Override

配合
https://github.com/ddssingsong/webrtc_android
使用的一套服务器java版本

实现基本的信令收发，配合Android端实现基本的呼叫、响铃、挂断、语音通话、视频通话的功能


Android访问地址为ws://ip:port/websocket

## 分支介绍

*master*

配合Android端Java版本业务逻辑，实现基本的呼叫、响铃、语音通话、视频通话的功能

*nodejs_copy*

将nodejs版本https://github.com/ddssingsong/webrtc_server_node 使用java写了一遍


## 信令相关


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
           "roomSize":"9",
           "mediaType":"1",  // 0 视频 1 语音
       	"inviteID":"userId",
           "userList":"userId,usrId,userId"  #逗号分割
       }
   }
   
   1. 创建房间
   2. 发送邀请
   3.
   ```

   

3. 取消拨出

   ```
   在拨打的过程中取消邀请
   {
       "eventName":"__cancel",
       "data":{
           "inviteID":"userId",
           "userList":"userId,usrId,userId" 
       }
   }
   ```

   

4. 对方已响铃

   ```json
   {
       "eventName":"__ring",
       "data":{
           "inviteID":"userId",
           "fromID":"myId"
       }
   }
   ```

  
    






























