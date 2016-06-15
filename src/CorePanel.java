/**
 * @(#)CorePanel.java
 *
 *
 * @author furkan mumcu
 * @version 1.00 2014/5/12
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class CorePanel extends JPanel
{
    JPanel menuPanel;
    JButton mainmenuButton;
    String name;

    public CorePanel()
    {
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(980, 40));
        mainmenuButton = new JButton ( "mainmenu" );
        menuPanel.add ( mainmenuButton );
        add ( "South", menuPanel );
    }

    public void addBackAction( ActionListener goBack )
    {
        mainmenuButton.addActionListener( goBack );
    }


}