/**
 * Dijkstras Algorithm
 * @lochhma
 * @V1
 */
import java.util.Scanner;  //Keyboard input.
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.net.URL;
import java.io.IOException;
public class Gui extends JFrame implements ActionListener,MouseListener
{
    // instance variables - replace the example below with your own
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    public int mousex;
    public int mousey;
    public int x;
    public int y;
    Node[] myNodes = new Node[10];
    int tall = 1450;
    int wide = 1900;//backup values in case fullscreen is exited

    
    public void mouseExited(MouseEvent e){System.out.println("exit");}

    public void mouseEntered(MouseEvent e){System.out.println("enter");}

    public void mouseReleased(MouseEvent e){System.out.println("release");}

    public void mousePressed(MouseEvent e){System.out.println("press");}

    public void mouseMoved(MouseEvent e){System.out.println("move");}

    public void mouseClicked(MouseEvent e){
        mousex=e.getX();
        mousey=e.getY();
        System.out.println("click at"+mousex+", "+mousey);

        x = mousex;
        y = mousey;
        this.repaint();

    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        //g2.fillRect(9, 54,2000,2000);
        g2.setColor(Color.RED);
        for(int print=0; print<7; print++){
            g2.fillOval(myNodes[print].x-50, myNodes[0].y-50, 100, 100);
        }
        System.out.println(x);
        //g2.drawRect(109, 154, 100, 100);

        /*int xStart=9;//the offset from 0 to be visually at 0
        int xEnd=400;
        int yStart=54;//the offset from 0 to be visually at 0
        int yEnd=400;
        Line2D lin = new Line2D.Float(xStart,yStart,xEnd,yEnd);
        g2.draw(lin);*///this is the part that handles drawing one line and is to be reimplemented thater
    } //paint

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        String output = "a";
        if(cmd == "Stats"){output="its statistics, its supposed to be boring";};
        System.out.println(output);
    }

    /**
     * Constructor for objects of class Usefull
     */
    public Gui()
    {
        // initialise instance variables
        setTitle("Dijkstra's Algorithm");//name of the window
        //===============================================================================================
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);

        menu = new JMenu("Menu");
        menuBar.add(menu);

        menuItem=new JMenuItem("Stats");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Load");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem=new JMenuItem("New Node");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //===============================================================================================

        myNodes[0] = new Node(300,150);
        myNodes[1] = new Node(100,150);
        myNodes[2] = new Node(200,150);
        myNodes[3] = new Node(400,150);
        myNodes[4] = new Node(500,150);
        myNodes[5] = new Node(600,150);
        myNodes[6] = new Node(700,150);
        myNodes[7] = new Node(800,150);

        addMouseListener(this);//activates the mouse detection
        this.getContentPane().setPreferredSize(new Dimension(wide,tall));//needed in caase of exiting fullscreen
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);//Fullscreens the window
        //this.repaint();
        this.pack();
        this.toFront();
        this.setVisible(true);
    }

}
