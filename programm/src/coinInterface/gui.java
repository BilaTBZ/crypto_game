package coinInterface;
import javax.swing.*;
import java.awt.*;

class gui{
    public static void main(String args[]){
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {
            e.printStackTrace();
        }
        JFrame f = new JFrame("Button Example");

        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(250,250,250,250));
        p.setLayout(new GridLayout(2, 2));

        JButton b = new JButton("Crypto 1");
        b.setSize(100,100);
        JButton b2 = new JButton("Crypto 2");
        b.setSize(100,100);
        JButton b3 = new JButton("Crypto 3");
        b.setSize(100,100);
        JButton b4 = new JButton("Crypto 4");
        b.setSize(100,100);

        p.add(b);
        p.add(b2);
        p.add(b3);
        p.add(b4);

        f.add(p);


        f.setSize(600,600);
        f.pack();
        f.setVisible(true);
    }
}
