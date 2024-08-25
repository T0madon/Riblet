/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.riblet.atividade;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author joaod
 */
public class logica extends Frame{
    
    double tam;
    double h;
    double alfa;
    double dR;
    double dR0;
  //Constructor
    
  logica(double tam, double h, double alfa, double dR, double dR0)
  {
    this.tam = tam;
    this.h = h;
    this.alfa = alfa;
    this. dR = dR;
    this.dR0 = dR0;
    
    //Enables the closing of the window.
    addWindowListener(new MyFinishWindow());
  }  
  //ARGS funcão: Graphics2D g2d, float tam, float dR, float h, float alfa, float angle
  //             float dR0

  public void geraRiblet(Graphics2D g2d, double tam, double h, double alfa, double dR, double dR0){
      
      double radians = Math.toRadians(alfa/2);
      double xR = h * 2 * Math.tan(radians);
      double lineStart = 780 - (tam/2);
      double lineEnd = 780 + (tam/2);
      
//      Como estava antes:
//      GeneralPath base = new GeneralPath();
//      base.moveTo(lineStart, 410);
//      base.lineTo(lineEnd,410);
//      g2d.draw(base);

      Rectangle2D.Double base = new Rectangle2D.Double(lineStart, 410-(4*h) , tam, 4*h);
      g2d.draw(base);
      Area retangulo = new Area(base);
      
      double posAtual = lineStart + (dR0 - (xR/2));
      GeneralPath riblets = new GeneralPath();
      riblets.moveTo(lineStart, 410);
      
      while(posAtual < lineEnd){
          riblets.lineTo(posAtual, 410);
          riblets.lineTo(posAtual+(xR/2), 410 - h);
          riblets.lineTo(posAtual+xR, 410);
          
          
          posAtual = posAtual + dR;
      }
      Area triangulo = new Area(riblets);
      
      retangulo.intersect(triangulo);
      g2d.draw(retangulo);
  }


  public void paint(Graphics g)
  {
    Graphics2D g2d = (Graphics2D) g;

    //Use of antialiasing to have nicer lines.
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

    //The lines should have a thickness of 3.0 instead of 1.0.
    BasicStroke bs = new BasicStroke(3.0f);
    g2d.setStroke(bs);
    GeneralPath tela = new GeneralPath();
    
    tela.moveTo(50, 70);
    tela.lineTo(1510, 70);
    tela.lineTo(1510, 800);
    tela.lineTo(50, 800);
    tela.lineTo(50,70);
    g2d.draw(tela);
    
//    geraRiblet(g2d, 1000, 45, 20, 64, 50); pixels

    geraRiblet(g2d, tam, h, alfa, dR, dR0);


    g2d.setStroke(new BasicStroke(1.0f));
    //Draw a coordinate system.
    drawSimpleCoordinateSystem(1920,1080,g2d);

  }



  /**
  * Draws a coordinate system (according to the window coordinates).
  *
  * @param xmax     x-coordinate to which the x-axis should extend.
  * @param ymax     y-coordinate to which the y-axis should extend.
  * @param g2d      Graphics2D object for drawing.
  */
  public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                Graphics2D g2d)
  {
    int xOffset = 30; 
    int yOffset = 50; 
    int step = 20; 
    String s;
    //Remember the actual font.
    Font fo = g2d.getFont();
    //Use a small font.
    g2d.setFont(new Font("sansserif",Font.PLAIN,9));
    //x-axis.
    g2d.drawLine(xOffset,yOffset,xmax,yOffset);
    //Marks and labels for the x-axis.
    for (int i=xOffset+step; i<=xmax; i=i+step)
    {
      g2d.drawLine(i,yOffset-2,i,yOffset+2);
      g2d.drawString(String.valueOf(i),i-7,yOffset-7);
    }

    //y-axis.
    g2d.drawLine(xOffset,yOffset,xOffset,ymax);

    //Marks and labels for the y-axis.
    s=" "; //for indention of numbers < 100
    for (int i=yOffset+step; i<=ymax; i=i+step)
    {
      g2d.drawLine(xOffset-2,i,xOffset+2,i);
      if (i>2000){s="";}
      g2d.drawString(s+String.valueOf(i),xOffset-25,i+5);
    }

    //Reset to the original font.
    g2d.setFont(fo);
  }
   public static void main(String[] argv)
  {
    double tam = 0;
    double h = 0;
    double angulo = 0;
    double dR = 0;
    double dR0 = 0;
    
    logica f = new logica(tam, h, angulo, dR, dR0);
                
    f.setTitle("GeneralPath example");
    f.setSize(1920,1080);
    f.setVisible(true);
  }
    
}
