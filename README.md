## netty入门教程


LineBasedFrameDecoder

依次遍历ByteBuf中的可读字节，判断是否是"\n"或者"\r\n"，如果是就以此为结束标志，从可读索引到结束位置区间的字节组成一行


StringDecoder

将接收到的对象转成字符串

DelimiterBasedFrameDecoder

自定义分隔符作为结束标志

FixedLengthFrameDecoder

固定长度解码器，按照指定的长度对消息进行解码