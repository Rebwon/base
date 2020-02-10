package ko.maeng.designpattern.study.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
