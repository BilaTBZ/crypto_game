package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCouple implements EventInterface {

    private Event defaultEvent;
    private Event inUseEvent;
    private boolean inUse = false;

    @Override
    public String triggerEvent(CryptoCurrency cryptoCurrency) {
        Event event;
        if (inUse) {
           event = inUseEvent;
        } else {
            event = defaultEvent;
        }
        inUse = !inUse;
        return event.triggerEvent(cryptoCurrency);
    }
}
