Logger

Разработайте такой функционал:
метод класса можно пометить самодельной аннотацией @Log, например, так:

class TestLogging {
@Log
public void calculation(int param) {};
}

При вызове этого метода автоматически в консоль должны логироваться значения параметров.
Например так.

class Demo {
public void action() {
new TestLogging().calculation(6);
}
}

В консоле дожно быть:
executed method: calculation, param: 6 

