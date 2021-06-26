package crw.design.patterns.observer;

public class ObserverImpl implements Observer {
    @Override
    public void sendMessage(String messageData) {
        System.out.println("observer need to sendMessage :" + messageData);
    }
}
