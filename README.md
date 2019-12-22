
## Override

配合
https://github.com/ddssingsong/webrtc_android
使用的一套服务器java版本

实现基本的信令收发，配合Android端实现基本的呼叫、响铃、挂断、语音通话、视频通话的功能


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

   

5. 加入房间

  ```json
  {
      "eventName":"__join",
      "data":{
          "room":"room",
          "userID":"myId"
      }
  }
  
  返回信息
  {
      "eventName":"__peers",
      "data":{
          "connections":"userId,userId",
          "userID":"myId"
      }
  }
  
  
  ```

  

6. 拒绝接听

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

   

6. offer 和answer

   ```json
    {
              "action":"__offer",
          "data":{
              "sdp":"sdp",
              "userID":"userId"
             }
          } 
      }
   
   
    {
          "action":"__answer",
          "data":{
              "sdp":"sdp",
              "userID":"userId"
             }
          } 
      }
   
   ```

7. ice_candidate

   ```
    {
          "action":"__ice_candidate",
          "data":{
              "userID":"userId"
              "id":"sdpMid",
              "label":"sdpMLineIndex信息",
              "candidate":"sdp信息"
          } 
      }
   
   ```

   
































## 分支介绍

*master*

配合Android端Java版本业务逻辑，实现基本的呼叫、响铃、语音通话、视频通话的功能

*nodejs_copy*

将nodejs版本https://github.com/ddssingsong/webrtc_server_node 使用java写了一遍
