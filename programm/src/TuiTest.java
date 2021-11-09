import businesLogic.EventBrain;
import businesLogic.MarketService;
import model.CryptoCurrency;
import model.EventInterface;

import java.util.List;
import java.util.Random;

public class TuiTest {

    private static EventBrain eventBrain = new EventBrain();
    private static MarketService marketService = new MarketService();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
            eventBrain.triggerEvent();
            Double factor = (double) randomInt(1, 9) / (double) 10;
            int fakeBool = randomInt(1,2);
            if (fakeBool == 1) {
                marketService.buy(randomCurrency(), marketService.getCash()*factor);
            } else {
                CryptoCurrency currency = randomCurrency();
                marketService.sell(currency, currency.getHolding()*factor);
            }
            logStatus();
        }
    }

    private static void logStatus() {
        System.out.println("\n\n---------------------------------\n");
        System.out.println("message: " + eventBrain.getLatestMessage());
        System.out.println("balance: " + marketService.getCash());
        marketService.getCurrencies().forEach(cryptoCurrency -> {
            System.out.println("---");
            System.out.println(cryptoCurrency.getName());
            System.out.println("holding: " + cryptoCurrency.getHolding());
            System.out.println("price: " + cryptoCurrency.getPrice());
        });
    }

    private static CryptoCurrency randomCurrency() {
        List<CryptoCurrency> currencies = eventBrain.getCurrencies();
        int index = randomInt(0, currencies.size()-1);
        return currencies.get(index);
    }

    private static int randomInt (int min, int max) {
        Random randomNum = new Random();
        int rand = randomNum.nextInt((max-min)+1)+min;
        return rand;
    }

}
