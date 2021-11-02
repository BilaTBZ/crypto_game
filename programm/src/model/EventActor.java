package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventActor {

    private String name;
    private List<EventInterface> events = new ArrayList<>();

    public String triggerEvent(CryptoCurrency cryptoCurrency) {
        int index = randomInt(0, events.size()-1);
        EventInterface event = events.get(index);
        String message = event.triggerEvent(cryptoCurrency);
        return name + " " + message;
    }

    private int randomInt (int min, int max) {
        Random randomNum = new Random();
        int rand = randomNum.nextInt((max-min)+1)+min;
        return rand;
    }
}
