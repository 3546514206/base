package edu.zjnu.base.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author: 杨海波
 * @date: 2023-03-23 08:48:42
 * @description: todo
 */


public class RectangleTool extends JFrame {
    private JPanel canvas;
    private Point start, end;

    public RectangleTool() {
        setTitle("Rectangle Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (start != null && end != null) {
                    g.drawRect(
                            Math.min(start.x, end.x),
                            Math.min(start.y, end.y),
                            Math.abs(end.x - start.x),
                            Math.abs(end.y - start.y)
                    );
                }
            }
        };

        canvas.setBackground(Color.WHITE);
        canvas.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                start = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                end = e.getPoint();
                canvas.repaint();
                start = null;
                end = null;
            }
        });
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                end = e.getPoint();
                canvas.repaint();
            }
        });
        getContentPane().add(canvas);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RectangleTool();
            }
        });
    }
}