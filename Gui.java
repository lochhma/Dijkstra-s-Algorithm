/**
 * Dijkstras Algorithm
 * @lochhma & blake
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
    public int dia = 50;
    public int circleTwo = 0;

    int selection = 1;
    public boolean selectStartEnd = false;
    int outputWeight;
    
    int counter = 0;
    int counter2 = 0;
    int tempStor = 0;
    int lineNum = 0;

    int startNode = 0;
    int endNode = 0;

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

        if(selection == 1){
            myNodes[circleTwo] = new Node(mousex,mousey,false,false);}
        circleTwo++;
        if(circleTwo>=nodes){circleTwo=0;
            System.out.println("**click at "+mousex+", "+mousey+" circle number "+circleTwo);}
        if(lineNum==lines){lineNum=0;}

        if(selection == 2){//this is the function that runs whenever there is a mouse button detected on a circle and the select mode is on
            for(int circleNum=0; circleNum<nodes; circleNum++){
                if(Math.sqrt((myNodes[circleNum].x-mousex)*(myNodes[circleNum].x-mousex)+(myNodes[circleNum].y-mousey)*(myNodes[circleNum].y-mousey))<dia/2)
                {System.out.println("kill me "+circleNum);myNodes[circleNum].selected=true;counter++;System.out.println(counter);}

                if(counter==1){
                    if(myNodes[circleNum].selected){
                        tempStor=circleNum;}
                }
                if(counter==2){
                    myEdges[lineNum] = new edges(circleNum,tempStor,0);
                    lineNum++;
                    for(int circleDel=0; circleDel<nodes; circleDel++){
                        myNodes[circleDel].selected=false;
                    };
                    counter=0;System.out.println(circleNum);
                    //this is where it asks for the weight for the edge when you do the 2nd selection
                    inputDialog test = new inputDialog("Select line weight");
                    test.setLocationRelativeTo(this);
                    test.setVisible(true);
                    String reply=test.getText();
                    System.out.println("selected weight of "+reply);
                    outputWeight = Integer.parseInt(reply);
                    //this section inputs the weight into the class
                    myEdges[lineNum-1].weight=outputWeight;
                    //this is used to check all of the weights in the array, however it is very long and only used for testing purposes
                    for(int edgeWeight=0; edgeWeight<20; edgeWeight++){
                        System.out.println("===============");
                        System.out.println(myEdges[edgeWeight].weight);
                        System.out.println(myNodes[edgeWeight].x);
                        System.out.println(myNodes[edgeWeight].y);
                    }
                }

            }
        }
        if(selection==3){//this is the function that runs whenever there is a mouse button detected on a circle and the select mode is on
            for(int circleNum2=0; circleNum2<nodes; circleNum2++){
                if(Math.sqrt((myNodes[circleNum2].x-mousex)*(myNodes[circleNum2].x-mousex)+(myNodes[circleNum2].y-mousey)*(myNodes[circleNum2].y-mousey))<dia/2)
                {System.out.println("kill me v2 "+circleNum2);myNodes[circleNum2].selectStartEnd=true;counter2++;System.out.println(counter2);}

                if(counter2==1){
                    if(myNodes[circleNum2].selectStartEnd){
                        startNode=circleNum2;
                    }
                }
                if(counter2>=2){
                    if(myNodes[circleNum2].selectStartEnd){
                        endNode=circleNum2;
                    }
                    for(int circleDel=0; circleDel<nodes; circleDel++){ // this clears all of the selected nodes so there is not more than 2 selections
                        myNodes[circleDel].selectStartEnd=false;
                    };
                }
            }

        }

        this.repaint();
        System.out.println(selection);
    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        Line2D lin;
        for(int print=0; print<lines; print++){
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(3));
            lin = new Line2D.Float(myNodes[myEdges[print].pos1].x, myNodes[myEdges[print].pos1].y, myNodes[myEdges[print].pos2].x, myNodes[myEdges[print].pos2].y);
            g2.draw(lin);
        }//From Blakes script

        for(int print=0; print<nodes; print++){
            g2.setColor(Color.RED);
            if(myNodes[print].selected){g2.setColor(Color.BLUE);}
            if(myNodes[print].selectStartEnd){g2.setColor(Color.GREEN);}
            g2.fillOval(myNodes[print].x-(dia/2), myNodes[print].y-(dia/2), dia, dia);//swaps colour based on what is selected.
        }
    } //paint

    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        String output = "a";
        switch(cmd){
            case "Select Nodes":
                output="Selecting nodes"; selection=2;
                break;
            case "Place Node":
                output="Placing nodes"; selection=1;
                break;
            case "Start and End selection":
                output="selecting start/end"; selection=3;
                break;
        };
        System.out.println(output);
    }

    public Gui()
    {
        // initialise instance variables
        for(int circleNum=0; circleNum<nodes; circleNum++){
            myNodes[circleNum] = new Node(1,1,false,false);}

        for(int lineNumber=0; lineNumber<lines; lineNumber++){//From Blakes Script
            myEdges[lineNumber] = new edges(nodes-1,nodes-1,0);} //This initialises all the edges at the start, so it stops giving errors

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

        menuItem=new JMenuItem("Place Node");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem=new JMenuItem("Select Nodes");
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem=new JMenuItem("Start and End selection");
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
