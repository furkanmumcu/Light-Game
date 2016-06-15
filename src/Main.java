/**
 * @(#)Test.java
 *
 * Test application
 *
 * @author furkan mumcu
 * @version 1.00 2014/5/6
 */
import javax.swing.JFrame;
public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame ("ben");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        //GamePanel2 panel2 = new GamePanel2();
        //GamePanel3 panel3 = new GamePanel3();
        //frame.getContentPane().add(panel);
        //frame.getContentPane().add(panel2);
        //frame.getContentPane().add(panel3);
        GameMenu menu = new GameMenu();
        frame.getContentPane().add(menu);
        frame.pack();
        frame.setVisible(true);
    }
}
