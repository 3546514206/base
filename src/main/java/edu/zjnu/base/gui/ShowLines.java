package edu.zjnu.base.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 令x=cost，则y-x^(2/3)=sint
 * 所以：y=sint+(cost)^(2/3)
 * @author guoxinze
 *
 */
public class ShowLines extends JFrame {
    double[] x = new double[360];
    double[] y = new double[360];


    int[] xx = new int[360];
    int[] yy = new int[360];


    int k = 0;

    public ShowLines() {
        this.setTitle("画曲线");
        this.setSize(600, 600);
        this.setLocation(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        ShowLines form2 = new ShowLines();
        form2.setvalue();
        form2.repaint();
    }

    //得到各个点的坐标
    public void setvalue() {
//360度为一个周期，设置数组中的纸
        for (double t = 1; t <= 360; t++) {
//令x=cost，则y-x^(2/3)=sint
//所以：y=sint+(cost)^(2/3)
            x[k] = Math.cos(Math.toRadians(t));
            y[k] = -(Math.sin(Math.toRadians(t)) + Math.cbrt(Math.pow(Math.cos(Math.toRadians(t)), 2.0)));
//使x、y的坐标轴与图形对齐，看起来美观一点
            xx[k] = (int) (x[k] * 100 + 200);
            yy[k] = (int) (y[k] * 100 + 245);
            k++;
        }
    }

    @Override
    public void paint(Graphics g) {
// TODO Auto-generated method stub
        super.paint(g);
        g.setColor(Color.black);
        g.drawPolygon(xx, yy, 360);
        g.setFont(new Font("Tahoma", Font.BOLD, 12));


        g.drawLine(0, 233, 800, 233);//画x轴和y轴
        g.drawString("X", 550, 250);
        g.drawString("Y", 190, 50);
        g.drawLine(200, 0, 200, 803);

    }
}


