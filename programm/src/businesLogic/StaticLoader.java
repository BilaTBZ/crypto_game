package businesLogic;

import model.CryptoCurrency;
import model.Event;
import model.EventActor;
import model.EventCouple;

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
        EventActor elon = new EventActor();
        elon.setName("Elon Musk");
        EventCouple couple = new EventCouple();
        couple.setDefaultEvent(new Event("hates", -1.4, -99.2));
        couple.setInUseEvent(new Event("loves", 34.1, 308.49));
        elon.getEvents().add(couple);
        elon.getEvents().add(new Event("invests in",0.01, 12.35));
        list.add(elon);
        return list;
    }

}
