package edu.zjnu.base.gui;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author: 杨海波
 * @date: 2023-03-22 20:15:16
 * @description: todo
 */
public class DrawTool extends JFrame implements MouseListener, MouseMotionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2;
    private JPanel panel;
    private int startX, startY, endX, endY;
    private String toolType = "line";
    private Color color = Color.BLACK;

    public DrawTool() {
        setTitle("Draw Tool");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置菜单栏
        menuBar = new JMenuBar();
        menu = new JMenu("工具");
        menuItem1 = new JMenuItem("直线");
        menuItem2 = new JMenuItem("矩形");
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolType = "line";
            }
        });
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolType = "rectangle";
            }
        });
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // 设置画布
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                switch (toolType) {
                    case "line":
                        g.setColor(color);
                        g.drawLine(startX, startY, endX, endY);
                        break;
                    case "rectangle":
                        g.setColor(color);
                        g.drawRect(startX, startY, endX-startX, endY-startY);
                        break;
                    default:
                        break;
                }
            }
        };
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DrawTool();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        panel.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        panel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
