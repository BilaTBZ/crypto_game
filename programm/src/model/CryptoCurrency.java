package model;

import lombok.Getter;

@Getter
public class CryptoCurrency {

    private String name;
    private Double price;
    private Double holding = (double) 0;
    private Double lastPrice = (double) 0;

    public CryptoCurrency(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    protected void changePriceRelatively(Double percent) throws Exception {
        if (percent < -100) {
            throw new Exception("Minimum is -100%");
        }
        price *= (1 + (percent / 100));
    }

    public double buy(Double investedMoney) {
        Double amount = investedMoney/price;
        lastPrice = price;
        holding += amount;
        return amount;
    }

    public double sell(Double amount) throws Exception {
        if (amount > holding) {
            throw new Exception("You have less then you want to sell");
        }
        holding -= amount;
        Double returningMoney = amount*price;
        return returningMoney;
    }

}

