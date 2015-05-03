# Hub
The mechanics of event/listener in Java are not easy to using, SIGNAL/SLOT is core in QT
this project using the Hub class to imitate SIGNAL/SLOT of QT in JAVA.
## Problems
*订阅消息能成功，取消订阅时，由于传入的lambda的地址与订阅时不同，因此取消失败
*后面发出的消息，有可能提前收到
*可以考虑使用Method实现
*如果订阅的消息类型和发布的消息类型不同，无法在编码的过程中，产生编译器告警
