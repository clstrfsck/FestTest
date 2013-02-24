package festtest;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Calculator extends JFrame {
    private static final long serialVersionUID  = -1806148636023623536L;
    private static final Font BIGGER_FONT       = new Font("monspaced", Font.PLAIN, 20);

    private final CalculatorOp op = new CalculatorOp();

    private JTextField  answer = new JTextField("0", 12);
    private boolean     result = true;
    private String      lastOp = "=";

    public Calculator() {
        answer.setName("answer");
        answer.setHorizontalAlignment(JTextField.RIGHT);
        answer.setFont(BIGGER_FONT);

        ActionListener numberListener = new NumberListener();
        String buttonOrder = "1234567890 ";
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 4, 4));
        for (int i = 0; i < buttonOrder.length(); i++) {
            String key = buttonOrder.substring(i, i + 1);
            if (key.equals(" ")) {
                JLabel padLabel = new JLabel("");
                padLabel.setName("padLabel");
                buttonPanel.add(padLabel);
            } else {
                JButton button = new JButton(key);
                button.setName(key);
                button.addActionListener(numberListener);
                button.setFont(BIGGER_FONT);
                buttonPanel.add(button);
            }
        }
        ActionListener operatorListener = new OperatorListener();
        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new GridLayout(4, 4, 4, 4));
        String[] opOrder = { "+", "-", "*", "/", "=", "C" };
        for (int i = 0; i < opOrder.length; i++) {
            JButton button = new JButton(opOrder[i]);
            button.setName(opOrder[i]);
            button.addActionListener(operatorListener);
            button.setFont(BIGGER_FONT);
            operatorPanel.add(button);
        }
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(4, 4));
        panel.add(answer, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(operatorPanel, BorderLayout.EAST);
        setContentPane(panel);
        pack();
        setTitle("Calculator");
        setResizable(false);
    }

    private void clear() {
        result = true;
        answer.setText("0");
        lastOp = "=";
        op.setTotal("0");
    }

    class OperatorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("C")) {
                clear();
            } else {
                result = true;
                String displayText = answer.getText();
                if (lastOp.equals("=")) {
                    op.setTotal(displayText);
                } else if (lastOp.equals("+")) {
                    op.add(displayText);
                } else if (lastOp.equals("-")) {
                    op.subtract(displayText);
                } else if (lastOp.equals("*")) {
                    op.multiply(displayText);
                } else if (lastOp.equals("/")) {
                    op.divide(displayText);
                }
                answer.setText(op.getTotalString());
                lastOp = e.getActionCommand();
            }
        }
    }

    class NumberListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String digit = event.getActionCommand();
            if (result) {
                answer.setText(digit);
                result = false;
            } else {
                answer.replaceSelection(digit);
            }
        }
    }
}
