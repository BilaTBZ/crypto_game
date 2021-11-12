package businesLogic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import model.CryptoCurrency;

import java.text.DecimalFormat;
import java.util.List;

@Getter
@NoArgsConstructor
public class MarketService {

    private List<CryptoCurrency> currencies = StaticLoader.currencies;
    private double cash = 10000;

    public void buy(CryptoCurrency currency, Double investedMoney) throws Exception {
        if (roundDouble(investedMoney).equals(roundDouble(cash))) {
            investedMoney = cash;
        }
        if (investedMoney > cash) {
            throw new Exception("You don't have enough money!");
        }
        cash -= investedMoney;
        currency.buy(investedMoney);
    }

    public void sell(CryptoCurrency currency, Double amount) throws Exception {
        if (roundDouble(amount).equals(roundDouble(currency.getHolding()))) {
            amount = currency.getHolding();
        }
        if (amount > currency.getHolding()) {
            throw new Exception("You don't have enough " + currency.getName() + "!");
        }
        cash += currency.sell(amount);
    }

    private String roundDouble(Double number) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(number);
    }

}
