/**
 * Dijkstras Algorithm
 * @lochhma
 * @V1
 */
import java.util.Objects;
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
    int nodes = 20;
    Node[] myNodes = new Node[nodes];


    

    public int mousex;
    public int mousey;
    public int x;
    public int y;
    public int dia = 50;
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
        System.out.println("*click at"+mousex+", "+mousey);
        //circular object collision
        myNodes[circleTwo] = new Node(mousex,mousey);
        circleTwo++;
        if(circleTwo>=nodes){circleTwo=0;};
        System.out.println("**click at "+mousex+", "+mousey+" circle number "+circleTwo);
        //please change this so that it will only work if the

        for(int circleNum=0; circleNum<nodes; circleNum++){

            if(Math.sqrt((myNodes[circleNum].x-mousex)*(myNodes[circleNum].x-mousex)+
                    (myNodes[circleNum].y-mousey)*(myNodes[circleNum].y-mousey))<dia){System.out.println("kill me");};
        }


        this.repaint();
    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        for(int print=0; print<nodes; print++){
            g2.fillOval(myNodes[print].x-(dia/2), myNodes[print].y-(dia/2), dia, dia);
        }
    } //paint

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        String output = "a";
        if(Objects.equals(cmd, "Move/Place Node")){output="its statistics, its supposed to be boring";};
        System.out.println(output);
    }

    public Gui()
    {
        // initialise instance variables
        for(int circleNum=0; circleNum<nodes; circleNum++){
            myNodes[circleNum] = new Node(1,1);}

        setTitle("Dijkstra's Algorithm");//name of the window
        //===============================================================================================
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);

        menu = new JMenu("Menu");
        menuBar.add(menu);

        menuItem=new JMenuItem("Save");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Load");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Move/Place Node");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Selector");
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
