package businesLogic;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class StaticLoader {

    public static List<CryptoCurrency> currencies = loadCurrencies();
    public static List<EventActor> eventActors = loadEventActors();

    private static List<CryptoCurrency> loadCurrencies() {
        List<CryptoCurrency> list = new ArrayList<>();
        list.add(new CryptoCurrency("BitCoin", (double) 20000));
        list.add(new CryptoCurrency("DogeCoin", 420.69));
        return list;
    }

    private static List<EventActor> loadEventActors() {
        List<EventActor> list = new ArrayList<>();
        list.add(new EventActor("Elon Musk", loadElonEvents()));
        list.add(new EventActor("CIA", loadCIAEvents()));
        list.add(new EventActor("China", loadChinaEvents()));
        return list;
    }

    private static List<EventInterface> loadElonEvents() {
        List<EventInterface> events = new ArrayList<>();
        EventCouple likeHateCouple = new EventCouple();
        likeHateCouple.setInUseEvent(new Event("hates", -37.2, -4.3 ));
        likeHateCouple.setDefaultEvent(new Event("likes", 3.8, 73.9));
        events.add(likeHateCouple);
        events.add(new Event("tweeted something about", -15.4, 30.2));
        EventCouple investCouple = new EventCouple();
        investCouple.setDefaultEvent(new Event("invests in", 2.6, 60.3));
        investCouple.setInUseEvent(new Event("sells all his", -31.5, -1.2));
        return events;
    }

    private static List<EventInterface> loadCIAEvents() {
        List<EventInterface> events = new ArrayList<>();
        events.add(new Event("brings democracy to a communist country and manipulates", -50.0, 99.0));
        events.add(new Event("wants to save oil reserves and manipulates", -50.0, 99.0));
        events.add(new Event("laughs about one of Elon Musk tweets about", -50.0, 99.0));
        return events;
    }

    private static List<EventInterface> loadChinaEvents() {
        List<EventInterface> events = new ArrayList<>();
        EventCouple miningCouple = new EventCouple();
        miningCouple.setDefaultEvent(new Event("forbidds mining of", -45.3, -12.9));
        miningCouple.setInUseEvent(new Event("allows mining of", 25.3, 98.7));
        events.add(miningCouple);
        return events;
    }

}
