package crw.design.patterns.observer;

import java.util.ArrayList;

public class SendMessageObject implements Subject {

    private String messageData ;

    private ArrayList<Observer> observerArrayList;

    public SendMessageObject() {
        this.observerArrayList = new ArrayList<>();
    }
    @Override
    public void addObserver(Observer observer) {
        this.observerArrayList.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        this.observerArrayList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerArrayList) {
            System.out.println("notify observer to update data : " + observer);
            observer.sendMessage(this.messageData);

        }
    }
}
