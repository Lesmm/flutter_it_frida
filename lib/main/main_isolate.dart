import 'dart:async';
import 'dart:isolate';

int i = 0;
IntObject intObject = IntObject();

void main() async {
  final recvPort = ReceivePort();
  recvPort.listen((data) {
    print(DateTime.now().toString() + ">>>>" + "【Main】: Receive data =  $data, i = $i, intObject = ${intObject.get()}");
  });

  // 创建一个Isolate实例，1.指定入口函数 2.指定消息通信发送端口
  Isolate isolate = await Isolate.spawn(isolateEntryFunction, recvPort.sendPort);
  print(DateTime.now().toString() + ">>>>" + "【Main】: start...");
}

// isolate入口函数，该函数必须是静态的或顶级函数，不能是匿名内部函数。
void isolateEntryFunction(SendPort sendPort) {
  int counter = 0;

  Timer.periodic(const Duration(seconds: 3), (_) {
    counter++;
    //在单独的Isolate实例中修改i的值
    i++;
    intObject.increase();

    String sendMsg = "Notification data: $counter";
    print(DateTime.now().toString() + ">>>>" + "【Isolate】: counter = $counter, i = $i, intObject = ${intObject.get()}");
    sendPort.send(sendMsg);
  });
}

class IntObject {
  int _i = 0;

  void increase() {
    _i++;
  }

  int get() {
    return _i;
  }
}
