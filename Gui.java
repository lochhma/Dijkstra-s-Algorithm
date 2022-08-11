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
public class Gui extends JFrame implements ActionListener,MouseListener {
    // instance variables - replace the example below with your own
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem;
    int nodes = 20;
    int lines = 80;

    Node[] myNodes = new Node[nodes];
    edges[] myEdges = new edges[lines];

    public int mousex;
    public int mousey;
    public int toBeX;
    public int toBeY;
    public int dia = 50;
    public int circleTwo = 0;
    public boolean selection = false;
    int counter = 0;
    int tempStor = 0;
    int lineNum = 0;

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

        if(!selection){
            myNodes[circleTwo] = new Node(mousex,mousey,false);}
        circleTwo++;
        if(circleTwo>=nodes){circleTwo=0;
            System.out.println("**click at "+mousex+", "+mousey+" circle number "+circleTwo);}
        if(lineNum==lines){lineNum=0;}

        if(selection){
            for(int circleNum=0; circleNum<nodes; circleNum++){
                if(Math.sqrt((myNodes[circleNum].x-mousex)*(myNodes[circleNum].x-mousex)+(myNodes[circleNum].y-mousey)*(myNodes[circleNum].y-mousey))<dia/2)
                {System.out.println("kill me "+circleNum);myNodes[circleNum].selected=true;counter++;System.out.println(counter);}

                if(counter==1){
                    if(myNodes[circleNum].selected){
                        tempStor=circleNum;}
                }
                if(counter>=2){
                    myEdges[lineNum] = new edges(circleNum,tempStor);
                    lineNum++;
                    for(int circleDel=0; circleDel<nodes; circleDel++){
                        myNodes[circleDel].selected=false;
                    };
                    counter=0;System.out.println(circleNum);}//blah blah words
            }
        }
        this.repaint();
    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        for(int print=0; print<nodes; print++){
            g2.setColor(Color.RED);
            if(myNodes[print].selected){g2.setColor(Color.BLUE);}
            g2.fillOval(myNodes[print].x-(dia/2), myNodes[print].y-(dia/2), dia, dia);
        }
        Line2D lin;
        for(int print=0; print<lines; print++){
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(3));
            lin = new Line2D.Float(myNodes[myEdges[print].pos1].x, myNodes[myEdges[print].pos1].y, myNodes[myEdges[print].pos2].x, myNodes[myEdges[print].pos2].y);
            g2.draw(lin);
        }//From Blakes script
    } //paint

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        String output = "a";
        if(Objects.equals(cmd, "Move/Place Node")){output="its statistics, its supposed to be boring";{if(!selection){selection=true;}else selection=false;}}
        System.out.println(output);
    }

    public Gui()
    {
        // initialise instance variables
        for(int circleNum=0; circleNum<nodes; circleNum++){
            myNodes[circleNum] = new Node(1,1,false);}

        for(int lineNumber=0; lineNumber<lines; lineNumber++){//From Blakes Script
            myEdges[lineNumber] = new edges(1,1);} //This initialises all the edges at the start, so it stops giving errors

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
