package ko.maeng.designpattern.study.observer;

import org.junit.jupiter.api.Test;

class FeedTest {
    @Test
    void tweetAPI(){
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new Lemonde());

        f.notifyObservers("The value of money has fallen.");
        f.notifyObservers("The queen said her favorite book is Bible.");
        f.notifyObservers("I like French wine the most.");
    }
}