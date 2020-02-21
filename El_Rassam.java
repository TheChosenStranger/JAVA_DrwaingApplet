/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Moustafa Ghareeb
 */
public class El_Rassam extends Applet {

    Thread th1;
    String[] fonts ;
    Graphics2D g2d;
    String mystring=" Move with keys ";
    Point p1 ;
    Point p2 ; 
    int max = 50;
    int x1,x2;
    int y1,y2;
    boolean draw_mode;
    Color color;
    float thickness;
    Lines[] l;
    Lines[] l_undo;
    int lines;
    int lines2;
    Button red;
    Button green;
    Button blue;
    Button black;
    Button thick;
    Button mid;
    Button thin;
    Button undo;
    Button redo;

    public void init() {
        lines = 0;
        x1= 0;
        x2= 0;
        y1= 0;
        y2= 0;
        color = Color.BLACK;
        thickness = 1;
        l = new Lines[max];
        l[0] = new Lines();
        l_undo = new Lines[max];
        l_undo[0] = new Lines();
        red = new Button("Red");
        green = new Button("Green");
        blue = new Button("Blue");
        black = new Button("Black");
        thick = new Button("Thick");
        mid = new Button("Mid");
        thin = new Button("Thin");
        undo = new Button("Undo");
        redo = new Button("Redo");
        
        red.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                 color = Color.RED;
                }});
        green.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    color = Color.GREEN;
                }
                });
        blue.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){    
                    color = Color.BLUE;
                }
                });
        black.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){ 
                    
                     color = Color.BLACK;
                }
                });
        thin.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    
                    thickness =1;
                }
                });
        mid.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    thickness = 2;
                }
                });
        thick.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    thickness=4;
                }
                });
        undo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    if(lines>0 && lines2>=0)
                        {
                            lines--;
                            l_undo[lines2] = l[lines];
                            lines2++;
                            l[lines] = new Lines();
                            
                            repaint();
                        }        
                }
                });
        redo.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    if(lines>0 && lines2>=0)
                        {
                            lines++;
                            lines2--;
                            l[lines] = l_undo[lines2];
                            l_undo[lines2] = new Lines();
                            repaint();
                        }        
                }
                });
        this.addMouseListener(new MouseListener()
                {
                    public void mousePressed (MouseEvent e)
                            {
                                p1 = MouseInfo.getPointerInfo().getLocation();
                                x1= p1.x-5;
                                y1 = p1.y-50;
                                l[lines] = new Lines();
                                l[lines+1]= new Lines();
                                draw_mode = true;
                            }
                    public void mouseReleased (MouseEvent e)
                            {   
                                p2 = MouseInfo.getPointerInfo().getLocation();
                                x2 = p2.x-5;
                                y2 = p2.y-50;
                                l[lines].Update(x1,y1, x2, y2, thickness, color);
                                lines++;
                                draw_mode = false;
                                repaint();
                            }
                    public void mouseClicked (MouseEvent e)
                            {;}
                    public void mouseEntered (MouseEvent e)
                            {;}
                    public void mouseExited (MouseEvent e)
                            {;}
                });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                p2 = MouseInfo.getPointerInfo().getLocation();
                x2 = p2.x-5;
                y2 = p2.y-50;
                l[lines].Update(x1,y1, x2, y2, thickness, color);                                
                repaint();
            }
            public void mouseMoved(MouseEvent e) {
                 ;
            }
        });
        add(black);
        add(red);
        add(green);
        add(blue);
        add(thick);
        add(mid);
        add(thin);
        add(undo);
        add(redo);
    }

    public void paint(Graphics g)
    {   
        // if (draw_mode)
        {
            g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(l[lines].GetStroke()));
            g.setColor(l[lines].GetColor());
            g.drawLine(l[lines].GetX1(), l[lines].GetY1(), l[lines].GetX2(), l[lines].GetY2());
         }
        if (lines>=1){
        for(int i=0;i<lines;i++)
        {
            g2d.setStroke(new BasicStroke(l[i].GetStroke()));
            g.setColor(l[i].GetColor());
            g.drawLine(l[i].GetX1(), l[i].GetY1(), l[i].GetX2(), l[i].GetY2());
        }}
    }
    public class Lines
    {
        private int x_1,y_1;
        private int x_2,y_2;
        private float stroke;
        private Color col;
        Lines()
        {x_1=x_2=y_1=y_2=0;stroke=1;col=Color.BLACK;}
        public void Update(int x,int y, int w,int z,float s,Color c)
        {
            x_1=x;
            x_2=w;
            y_1=y;
            y_2=z;
            stroke = s;
            col=c;
        }
        public int GetX1(){return x_1;}
        public int GetY1(){return y_1;}
        public int GetX2(){return x_2;}
        public int GetY2(){return y_2;}
        public float GetStroke(){return stroke;}
        public Color GetColor(){return col;}
    
    }
}
