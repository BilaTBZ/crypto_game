package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCouple implements EventInterface {

    private Event defaultEvent;
    private Event inUseEvent;
    HashMap<CryptoCurrency, Boolean> inUseHashMap = new HashMap<>();


    public String triggerEvent(CryptoCurrency cryptoCurrency) {
        Event event;
        if (isInUse(cryptoCurrency)) {
           event = inUseEvent;
        } else {
            event = defaultEvent;
        }
        toggleInUse(cryptoCurrency);
        return event.triggerEvent(cryptoCurrency);
    }


    private boolean isInUse(CryptoCurrency currency) {
        inUseHashMap.putIfAbsent(currency, false);
        return inUseHashMap.get(currency);
    }

    private void toggleInUse(CryptoCurrency currency) {
        Boolean boolNow = isInUse(currency);
        inUseHashMap.remove(currency);
        inUseHashMap.put(currency, !boolNow);
    }

}
