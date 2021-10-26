package market;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrency {

    private String name;
    private Double price;
    private Double holding;
    private Double investedMoney;

    protected void changePriceRelatively(Double percent) throws Exception {
        if (percent < -100) {
            throw new Exception("Minimum is -100%");
        }
        price *= (1 + (percent / 100));
    }

    protected double buy(Double investedMoney) {
        Double amount = investedMoney/price;
        this.investedMoney += investedMoney;
        holding += amount;
        return amount;
    }

    protected double sell(Double amount) throws Exception {
        if (amount > holding) {
            throw new Exception("You have less then you want to sell");
        }
        holding -= amount;
        Double returningMoney = amount*price;
        this.investedMoney -= returningMoney;
        return returningMoney;
    }

}

