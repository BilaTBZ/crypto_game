package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements EventInterface {

    /**
     * format: announced that he hated
     * completed message will be for instance Elon Musk announced that he hated bitcoin
     */
    private String message;
    private Double currencyInfluence;

    public String triggerEvent(CryptoCurrency cryptoCurrency){
        try {
            cryptoCurrency.changePriceRelatively(currencyInfluence);
        } catch (Exception e) {
            System.out.println(e);
        }
        return message + " " + cryptoCurrency.getName();
    }

}
