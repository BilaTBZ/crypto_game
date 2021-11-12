import businesLogic.EventBrain;
import businesLogic.MarketService;
import businesLogic.StaticLoader;
import lombok.Data;
import model.CryptoCurrency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

@Data
public class CryptoGame {
    private JButton btnSell1;
    private JButton btnSell4;
    private JButton btnSell3;
    private JButton btnSell2;
    private JButton btnBuy1;
    private JButton btnBuy2;
    private JButton btnBuy3;
    private JButton btnBuy4;
    private JButton btnNextRound;
    private JLabel labelCrypto1;
    private JLabel labelCrypto2;
    private JLabel labelCrypto3;
    private JLabel labelCrypto4;
    private JLabel labelPrice1;
    private JLabel labelPrice2;
    private JLabel labelPrice3;
    private JLabel labelPrice4;
    private JLabel labelHolding1;
    private JLabel labelHolding2;
    private JLabel labelHolding3;
    private JLabel labelHolding4;
    private JLabel labelCurrentBalance;
    private JPanel panelMain;


    private List<CryptoCurrency> currencies = StaticLoader.currencies;
    private MarketService marketService = new MarketService();
    private EventBrain eventBrain = new EventBrain();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Crypto Game");
        frame.setContentPane(new CryptoGame().getPanelMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public CryptoGame() {
        load();
        btnSell1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sell(currencies.get(0));
            }
        });
        btnBuy1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buy(currencies.get(0));
            }
        });
        btnSell2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sell(currencies.get(1));
            }
        });
        btnBuy2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buy(currencies.get(1));
            }
        });
        btnSell3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sell(currencies.get(2));
            }
        });
        btnBuy3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buy(currencies.get(2));
            }
        });
        btnSell4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sell(currencies.get(3));
            }
        });
        btnBuy4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buy(currencies.get(3));
            }
        });
        btnNextRound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newEvent();
            }
        });
    }

    private void sell(CryptoCurrency currency) {
        String inputValue = JOptionPane.showInputDialog("How many " + currency.getName() + " do you want to sell?");
        Double amountToSell;
        if (inputValue == null) {
            return;
        }
        try {
            amountToSell = Double.valueOf(inputValue);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input has to be a number");
            sell(currency);
            return;
        }
        try {
            marketService.sell(currency, amountToSell);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        load();
    }

    private void buy(CryptoCurrency currency) {
        String inputValue = JOptionPane.showInputDialog("How many $ do you want to invest?");
        Double cashToBuy;
        if (inputValue == null) {
            return;
        }
        try {
            cashToBuy = Double.valueOf(inputValue);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Input has to be a number");
            buy(currency);
            return;
        }
        try {
            marketService.buy(currency, cashToBuy);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        load();
    }

    private void newEvent() {
        eventBrain.triggerEvent();
        JOptionPane.showMessageDialog(null, eventBrain.getLatestMessage());
        load();
        labelHolding1.setForeground(getColorForHolding(currencies.get(0)));
        labelHolding2.setForeground(getColorForHolding(currencies.get(1)));
        labelHolding3.setForeground(getColorForHolding(currencies.get(2)));
        labelHolding4.setForeground(getColorForHolding(currencies.get(3)));
    }

    private void load() {
        labelCurrentBalance.setText(roundDouble(marketService.getCash())+"$");
        labelCrypto1.setText(currencies.get(0).getName());
        labelPrice1.setText(roundDouble(currencies.get(0).getPrice())+"$");
        labelHolding1.setText(roundDouble(currencies.get(0).getHolding()));
        labelCrypto2.setText(currencies.get(1).getName());
        labelPrice2.setText(roundDouble(currencies.get(1).getPrice())+"$");
        labelHolding2.setText(roundDouble(currencies.get(1).getHolding()));
        labelCrypto3.setText(currencies.get(2).getName());
        labelPrice3.setText(roundDouble(currencies.get(2).getPrice())+"$");
        labelHolding3.setText(roundDouble(currencies.get(2).getHolding()));
        labelCrypto4.setText(currencies.get(3).getName());
        labelPrice4.setText(roundDouble(currencies.get(3).getPrice())+"$");
        labelHolding4.setText(roundDouble(currencies.get(3).getHolding()));
    }

    private Color getColorForHolding(CryptoCurrency currency) {
        if (currency.getHolding() == 0) {
            return Color.BLACK;
        } else if (currency.getPrice() == currency.getLastPrice()) {
            return Color.BLACK;
        } else if (currency.getPrice() > currency.getLastPrice()) {
            return Color.GREEN;
        } else {
            return Color.RED;
        }
    }

    private String roundDouble(Double number) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(number);
    }
}
