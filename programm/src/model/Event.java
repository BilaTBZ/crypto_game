package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements EventInterface {

    /**
     * format: announced that he hated
     * completed message will be for instance Elon Musk announced that he hated bitcoin
     */
    private String message;
    private Double minCurrencyInfluence;
    private Double maxCurrencyInfluence;

    public String triggerEvent(CryptoCurrency cryptoCurrency){
        Double currencyInfluence = randomDouble(minCurrencyInfluence, maxCurrencyInfluence);
        try {
            cryptoCurrency.changePriceRelatively(currencyInfluence);
        } catch (Exception e) {
            System.out.println(e);
        }
        String sign = "";
        if(currencyInfluence > 0) {
            sign = "+";
        }
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return message + " " + cryptoCurrency.getName() + ". " + sign + df.format(currencyInfluence) + "%";
    }

    private Double randomDouble(Double rangeMin, Double rangeMax) {
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

}
