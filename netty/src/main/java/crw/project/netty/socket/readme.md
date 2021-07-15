# Socket网络编程说明
===
网络上两个程序通过一个双向的通讯连接实现数据的交换，这个双向链路的一端为一个Socket.  
Socket通常用来实现客户端和服务端的连接.  
一个Socket由一个IP地址和一个端口号唯一确定.  
  
=== 
1. Server端Listen(监听)某个端口是否有连接请求.  
2. Client向Server发出Connect请求.  
3. Server向Client发回Accept（接收）消息.  
4. Client和Server端就已经建立起来.  
5. Server和Client可以通过Send,write等方法与对方通信.  

--- 

步骤： 
1. 创建Socket  
2. 打开连接到Socket的输入流/输出流  
3. 按照一定的协议对Socket进行读/写操作  
4. 关闭Socket  

---

接口：  
```text
Socket(InetAddress address, int port);
Socket(InetAddress address, int port, boolean stream);
Socket(String host, int port);
# steam指明socket是流socket还是数据报socket
Socket(String host, int port, boolean stream);
Socket(SocketImpl impl);
......
```

```text
Socket client = new Socket("127.0.0.1", 80);
ServerSocket server = new ServerSocket(80);

```

---
```text
bind()
accept()
socket()
connet()
```

