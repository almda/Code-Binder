package CommonWindows;
import Handlers.*;
import TransitionWindows.Application;
import TransitionWindows.ChoosePathFrame;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AddingCodeFrame extends JFrame {
    private String codePath;
    private String pastingPath;
    private String tableName;
    private JTextField nametext;

    public AddingCodeFrame(String codePath, String pastingPath, String tableName){
        super("Add New Code");
        this.codePath = codePath;
        this.pastingPath = pastingPath;
        this.tableName = tableName;
        Color col = new Color(70,70,70);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(col);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0,0,90)));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(col);
        add(top,BorderLayout.NORTH);

        nametext = new JTextField();
        nametext.setBackground(col);
        nametext.setForeground(new Color(200,200,200));
        nametext.setFont(new Font("David",Font.ROMAN_BASELINE,15));

        JLabel l1 = new JLabel(" Enter Name: ");
        l1.setForeground(new Color(145,145,145));
        l1.setFont(new Font("David",Font.ITALIC,15));
        top.add(l1,BorderLayout.WEST);
        top.add(nametext,BorderLayout.CENTER);
        JLabel l2 = new JLabel(" Your code:\n");
        l2.setForeground(new Color(145,145,145));
        l2.setFont(new Font("David",Font.BOLD,13));
        top.add(l2,BorderLayout.SOUTH);

        JTextArea codetext = new JTextArea();
        codetext.setBorder(new LineBorder(new Color(64,64,64)));
        codetext.setBackground(new Color(50,50,50));
        codetext.setForeground(new Color(200,200,200));
        add(new JScrollPane(codetext),BorderLayout.CENTER);

        JButton submit = new JButton("Submit");
        submit.setBorder(new LineBorder(new Color(0,0,90)));
        submit.setBackground(new Color(51,51,51));
        submit.setForeground(new Color(0,153,255));
        submit.setFont(new Font("Arial",Font.BOLD,13));
        submit.addActionListener(e -> {
            new FileHandler(codePath).CreateCode(codePath,nametext.getText(),codetext.getText());
            DataBase.insert(nametext.getText(), codePath, tableName);
            ChoosePathFrame cpf = new ChoosePathFrame();
            cpf.setVisible(true);
            dispose();
        });
        add(submit,BorderLayout.SOUTH);
    }
}
