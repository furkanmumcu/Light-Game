
/**
 * @(#)GameMenu.java
 *
 * @author furkan mumcu
 * @version 1.00 2014/5/5
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class GameMenu extends JPanel
{
    private final GameMenu GAME_MENU = this;
    private final int NUMBER_OF_LEVELS = 10;

    private JFrame              gameFrame;
    private JPanel            activePanel;
    private JPanel            dialogPanel;
    private JLayeredPane      layeredPane;
    private BufferedImage backGroundImage;
    private boolean                dialog;
    private boolean[]     completedLevels;

    // Constructor
    public GameMenu()
    {
        // Saves the game if player completed the level
        try
        {
            FileInputStream       saveGame = new FileInputStream ( "saveGamebyMenu.sav" );
            ObjectInputStream loadLastGame = new ObjectInputStream ( saveGame );
            completedLevels = ( boolean[] ) loadLastGame.readObject();
            loadLastGame.close();
        }
        catch( Exception except )
        {
            // If player can not complete the level, shows a "game over" screen
            System.out.println ( "Game Over!" );
            completedLevels = new boolean[ NUMBER_OF_LEVELS];

            for ( int i = 0; i < completedLevels.length; i++)
            {
                completedLevels[ i] = false;
            }
        }

        // Creates background panel for menu
        try
        {
            backGroundImage = ImageIO.read( new File("menuBackGroundImage.jpg") );
        }
        catch( IOException exception1 ) {}

        gameFrame = new JFrame      ( "Science of Light" );
        gameFrame.setResizable      ( false );
        gameFrame.setSize           ( new Dimension(980,670) );
        gameFrame.setVisible        ( true );

        layeredPane = gameFrame.getLayeredPane();
        layeredPane.add ( this, Integer.valueOf(1) );

        setBackground    ( Color.gray );
        setPreferredSize ( new Dimension(980, 640) );
        setSize          ( new Dimension(980, 640) );
        setLayout        ( new GridBagLayout() );

        sendTo( mainPanel() );
    }

    // Adds background image to the panek
    public void paintComponent( Graphics backGround )
    {
        super.paintComponent ( backGround );
        backGround.drawImage( backGroundImage, 0, 0, null );
    }

    //
    public void sendTo( JPanel panel )
    {
        if ( panel instanceof CorePanel )
        {
            setLayout ( new FlowLayout() );
            gameFrame.setTitle (( (CorePanel)panel ).name + " - Science of Light" );
        }

        else
        {
            setLayout ( new GridBagLayout() );
            gameFrame.setTitle ( "Science of Light" );
        }

        removeAll ();
        add ( panel );
        activePanel = panel;

        updateUI();
    }

    public JPanel mainPanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground ( Color.lightGray );
        mainPanel.setPreferredSize ( new Dimension(200, 170) );

        JButton playGameButton = new JButton ( "Play Game" );
        playGameButton.setPreferredSize ( new Dimension(150, 50) );

        playGameButton.addActionListener ( new ActionListener() {
            public void actionPerformed( ActionEvent event)
            {
                sendTo( levelsPanel() );
            }
        });

        JButton sandboxButton = new JButton ( "Sandbox" );
        sandboxButton.setPreferredSize ( new Dimension(150, 50) );
		
		/* sandboxButton.addActionListener ( new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) 
			{
				sendTo( sandboxOpts() );
			}
		}); */

        JButton opt = new JButton("Options");
        opt.setPreferredSize ( new Dimension(150,50) );
		
		/* opt.addActionListener(new ActionListener()
		 *{
		 	public void ActionPerformed(ActionEvent event)
		 	{
		 		sendTo( optionsPanel() );
		 	}
		 *}
		 */

        mainPanel.add ( playGameButton );
        mainPanel.add ( sandboxButton );
        mainPanel.add ( opt);

        return mainPanel;
    }

    public JPanel levelsPanel()
    {
        JPanel levelsPanel  = new JPanel ();
        levelsPanel.setBackground ( Color.lightGray );
        levelsPanel.setPreferredSize ( new Dimension(850, 550) );
        levelsPanel.setLayout ( new GridBagLayout() );

        JButton startFromBeginning = new JButton ("Start From Beginning");
        startFromBeginning.setPreferredSize ( new Dimension(175,55) );
        startFromBeginning.addActionListener ( new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                sendTo( new GamePanel() );
            }
        });

        JButton mainmenuButton = new JButton ( "mainmenu" );
        mainmenuButton.addActionListener ( new ReturnMainMenu() );

        levelsPanel.add( startFromBeginning );
        levelsPanel.add ( mainmenuButton );

        return levelsPanel;
    }

    // Go back to main menu
    public class ReturnMainMenu implements ActionListener
    {
        public void actionPerformed( ActionEvent event)
        {
            sendTo( mainPanel() );
        }
    }
}