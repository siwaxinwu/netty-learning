## netty入门教程


LineBasedFrameDecoder

使用行尾控制字符(\n或者\r\n)作为分隔符来解析数据

StringDecoder

将接收到的对象转成字符串

DelimiterBasedFrameDecod

自定义的特殊字符作为消息的分隔符

FixedLengthFrameDecoder

固定长度解码器，按照指定的长度对消息进行解码

LengthFieldBasedFrameDecoder

通知指定长度来标识消息，这样就可以自动的处理粘包和半包消息

### ByteBuffer的缺点

1. 长度固定，一旦分配完成不能动态扩展和收缩
2. 只有一个标识位置的指针position，读写的时候需要手动调用filp()和rewind()
3. api功能有限

### ByteBuf的优点

1. 容量可以按需增长（类似ArrayList）
2. 读和写使用不同的index，在读和写两种模式之间切换不需要调用ByteBuffer的filp()方法
3. 支持方法的链式调用，支持引用计数，支持池化