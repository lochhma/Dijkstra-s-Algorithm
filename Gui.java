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
    Node[] myNodes = new Node[8];
    int bob = 0;

    public int mousex;
    public int mousey;
    public int x;
    public int y;
    public int rad = 100;
    public int circleTwo=0;

    int tall = 1450;
    int wide = 1900;//backup values in case fullscreen is exited

    public void mouseExited(MouseEvent e){System.out.println("exit");}//wack
    public void mouseEntered(MouseEvent e){System.out.println("enter");}

    public void mouseReleased(MouseEvent e){System.out.println("release");}

    public void mousePressed(MouseEvent e){System.out.println("press");}

    public void mouseMoved(MouseEvent e){System.out.println("move");}

    public void mouseClicked(MouseEvent e){
        mousex=e.getX();
        mousey=e.getY();
        System.out.println("click at"+mousex+", "+mousey);
        //circular object collision

        x = mousex;
        y = mousey;

        for(circleTwo=0; circleTwo<7;){myNodes[circleTwo] = new Node(mousex,mousey);}circleTwo++;
        /* for(int circleOne=0; circleOne<7; circleOne++){
        for(int circleNum=0; circleNum<7; circleNum++){
        System.out.println(Math.sqrt((myNodes[circleNum].x-myNodes[circleOne].x)*(myNodes[circleNum].x-myNodes[circleOne].x)+
        (myNodes[circleNum].y-myNodes[circleOne].y)*(myNodes[circleNum].y-myNodes[circleOne].y)));
        }
        }*/
        this.repaint();

    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        //g2.fillRect(9, 54,2000,2000);
        g2.setColor(Color.RED);
        for(int print=0; print<7; print++){
            g2.fillOval(myNodes[print].x-50, myNodes[print].y-50, rad, rad);
        }
        //System.out.println(x);
    } //paint

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        String output = "a";
        if(cmd == "Stats"){output="its statistics, its supposed to be boring";};
        System.out.println(output);
    }

    public Gui()
    {
        // initialise instance variables

        myNodes[0] = new Node(10,10);
        /*myNodes[1] = new Node(10,10);
        myNodes[2] = new Node(10,10);
        myNodes[3] = new Node(10,10);
        myNodes[4] = new Node(10,10);
        myNodes[5] = new Node(10,10);
        myNodes[6] = new Node(10,10);
        myNodes[7] = new Node(10,10);*/
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
