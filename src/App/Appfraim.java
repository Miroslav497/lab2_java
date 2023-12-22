package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Appfraim extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private Double memoryValue = 0.0;

    JButton buttonMemoryPlus = new JButton("M+");
    JButton buttonMemoryClear = new JButton("MC");

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldM;


   
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y,Double z) {
        return (1/Math.sqrt(x)+Math.cos(Math.exp(y))+Math.cos(z*z))/Math.pow((Math.log(Math.pow(1+z, 2)) + (Math.pow(Math.exp(Math.cos(y))+Math.pow(Math.sin(Math.PI*x), 2), 1/2))), 1/3);
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y,Double z) {
        return Math.pow(y+Math.pow(x, 3), 1/3)/Math.log(z);
    }
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	Appfraim.this.formulaId = formulaId;
               
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    public Appfraim() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize())
        ;
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ= new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());


        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
       // hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(60));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        //hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        //hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(textFieldZ);
       // hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
       // hboxVariables.add(textFieldZ);

        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y,z);
                    else
                        result = calculate2(x, y,z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Appfraim.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        JButton buttonMemoryPlus = new JButton("M+");///
        textFieldM = new JTextField("0", 300);//для того, чтобы растянуть, нужно сделать 300, по умолчанию должно быть 20
        textFieldM.setMaximumSize(textFieldM.getPreferredSize());
        buttonMemoryPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double newValue = Double.parseDouble(textFieldResult.getText()) + memoryValue;
                    memoryValue = newValue;
                    textFieldM.setText(memoryValue.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Appfraim.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMemoryClear = new JButton("MC");
        buttonMemoryClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                memoryValue = 0.0;
                textFieldM.setText(memoryValue.toString());
            }
        });



        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);
       // hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(10));
        //hboxButtons.add(Box.createHorizontalGlue());

        hboxButtons.add(buttonCalc);
        //hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
       // hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);

        Box hboxMemory = Box.createHorizontalBox();
       //hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(buttonMemoryPlus);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(textFieldM);
        //hboxMemory.add(Box.createHorizontalGlue());
        //hboxMemory.add(Box.createHorizontalGlue());
       hboxMemory.add(buttonMemoryClear);
        hboxMemory.add(Box.createHorizontalStrut(20));
       // hboxMemory.add(Box.createHorizontalGlue());


        contentBox.add(hboxMemory);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);


    }
}
    // Главный метод класса
    /*public static void main(String[] args) {
    	Appfraim frame = new Appfraim();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}*/
