package businesLogic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.CryptoCurrency;
import model.EventActor;

import java.util.List;
import java.util.Random;

@Getter
@NoArgsConstructor
public class EventBrain {

    private String latestMessage;
    private List<EventActor> eventActors = StaticLoader.eventActors;
    private List<CryptoCurrency> currencies = StaticLoader.currencies;

    public void triggerEvent() {
        int currencyIndex = randomInt(0, currencies.size()-1);
        CryptoCurrency cryptoCurrency =  currencies.get(currencyIndex);
        int eventActorsIndex = randomInt(0, eventActors.size()-1);
        EventActor event = eventActors.get(eventActorsIndex);
        latestMessage = event.triggerEvent(cryptoCurrency);
    }

    private int randomInt (int min, int max) {
        Random randomNum = new Random();
        int rand = randomNum.nextInt((max-min)+1)+min;
        return rand;
    }

}
