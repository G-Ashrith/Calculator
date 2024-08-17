package Calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener {
    private TextField tf;
    private String expression = "";

    public static void main(String[] args) {
        new Main().Calculator();
    }

    public void Calculator() {
        JFrame f = new JFrame("Calculator");
        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bPlus, bMinus, bMultiply, bDivide, bClear, bEquals;
        JPanel p = new JPanel();
        tf = new TextField(20);

        f.setLayout(new BorderLayout());
        f.add(tf, BorderLayout.NORTH);
        p.setLayout(new GridLayout(4, 4, 10, 10));

        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bPlus = new JButton("+");
        bMinus = new JButton("-");
        bMultiply = new JButton("*");
        bDivide = new JButton("/");
        bClear = new JButton("C");
        bEquals = new JButton("=");

        p.add(b1); p.add(b2); p.add(b3); p.add(bDivide);
        p.add(b4); p.add(b5); p.add(b6); p.add(bMultiply);
        p.add(b7); p.add(b8); p.add(b9); p.add(bMinus);
        p.add(b0); p.add(bClear); p.add(bEquals); p.add(bPlus);

        b0.addActionListener(this); b1.addActionListener(this); b2.addActionListener(this);
        b3.addActionListener(this); b4.addActionListener(this); b5.addActionListener(this);
        b6.addActionListener(this); b7.addActionListener(this); b8.addActionListener(this);
        b9.addActionListener(this); bPlus.addActionListener(this); bMinus.addActionListener(this);
        bMultiply.addActionListener(this); bDivide.addActionListener(this); bClear.addActionListener(this);
        bEquals.addActionListener(this);

        f.add(p, BorderLayout.CENTER);
        f.setSize(300, 400);  // Adjusted size for better layout
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            expression += command;
            tf.setText(expression);
        } 
        else if (command.equals("C")) {
            expression = "";
            tf.setText("");
        } 
        else if (command.equals("=")) {
            try {
                tf.setText(String.valueOf(eval(expression)));
                expression = tf.getText();
            } catch (Exception ex) {
                tf.setText("Error");
                expression = "";
            }
        } 
        else {
            if (!expression.isEmpty() && !Character.isWhitespace(expression.charAt(expression.length() - 1))) {
                expression += " " + command + " ";
                tf.setText(expression);
            }
        }
    }

    private double eval(String expression) {
        String[] tfExpression = expression.split(" ");
        double result = Double.parseDouble(tfExpression[0]);
        for (int i = 1; i < tfExpression.length; i += 2) {
            String op = tfExpression[i];
            double next = Double.parseDouble(tfExpression[i + 1]);

            switch (op) {
                case "+" -> result += next;
                case "-" -> result -= next;
                case "*" -> result *= next;
                case "/" -> result /= next;
                default -> throw new UnsupportedOperationException("Unknown operator: " + op);
            }
        }
        return result;
    }
}
