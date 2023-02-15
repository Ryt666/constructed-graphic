package com.company;

import jdk.dynalink.Operation;

import javax.swing.*;
import java.awt.*;

public class Main extends JComponent{

    //x  = (8 * Math.pow(y[0],4) -  Math.pow(y[0],3) + 64 * y- 8);
    static public int a = 10;
    static public JFrame window ;

    private int width;
    private int height;

    public void paint(Graphics g)
    {




        super.paint(g);
        width = getWidth(); // сохраняем текущую ширину панели
        height = getHeight(); // и высоту

        drawGrid(g); // рисуем сетку
        drawAxis(g); // рисуем оси
        drawGraphic(g);


    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);  //задаем серый цвет

        for(int x=width/2; x<width; x+=1){  // цикл от центра до правого края
            g.drawLine(x, 0, x, height);    // вертикальная линия

        }

        for(int x=width/2; x>0; x-=1){  // цикл от центра до леваого края
            g.drawLine(x, 0, x, height);   // вертикальная линия
        }

        for(int y=height/2; y<height; y+=1){  // цикл от центра до верхнего края
            g.drawLine(0, y, width, y);    // горизонтальная линия
        }

        for(int y=height/2; y>0; y-=1){  // цикл от центра до леваого края
            g.drawLine(0, y, width, y);    // горизонтальная линия
        }
    }

    private void drawAxis(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(width/2, 0, width/2, height);
        g.drawLine(0, height/2, width, height/2);
    }

    private Color graphicColor = Color.GREEN;

    private void drawGraphic(Graphics g) {

        g.setColor(graphicColor);
        Operation operation = x -> (8 * Math.pow(x[0],4) -  Math.pow(x[0],3) + 64 * x[0]- 8);  // переводим значение синуса в координату нашей системы
        for(int i=-width/2;i<width;i++)
        {
            g.drawLine(i+width/2,(int) (-operation.exucute(i)+height/2),i+1+width/2,(int) (-operation.exucute(i+1)+height/2));
        }
        /*g.setColor(graphicColor); // устанавливаем цвет графика
        for(int x=0; x<width; x++){           // делаем цикл с левой стороны экрана до правой
            int realX = x - width/2;   // так, как слева от оси OX минус, то отнимаем от текущей точки центральную точку
            double rad = realX/30.0;   // переводим текущую коориднату в радианы, 30 пикселей по ширине == 1 радиану
            double sin = Math.sin(rad);       // вычисляем синус угла
            int y = (int) (width/2 +sin*90);  // (int) (height/2 + (Math.pow(realX,3) - 2 * Math.pow(realX,2) - (Math.pow(a,2) - a -1)*realX +(Math.pow(a,2) -a)));  // переводим значение синуса в координату нашей системы

            g.drawOval(x, height-y, 2, 2);   // рисуем кружок в этой точке
            System.out.println("x = "+realX+" | "+ "y = "+y);
        }*/
    }

    @FunctionalInterface
    public interface Operation{

        double exucute(double... nums);
    }


    public static void main(String[] args) {
        window = new JFrame("Func");
        window.setSize(900,900);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.getContentPane().add(new Main());
    }
}