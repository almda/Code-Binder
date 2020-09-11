package CommonWindows;
import Handlers.DataBase;
import Handlers.FileHandler;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DeletingCodeFrame extends JFrame {

    public DeletingCodeFrame(String tableName , String codePath){
        super("Button Delete");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,150);
        setLayout(new BorderLayout());
        Color col = new Color(120,120,120);
        getContentPane().setBackground(col);


        JPanel top = new JPanel(new BorderLayout());
        JPanel down = new JPanel(new BorderLayout());
        JLabel enter = new JLabel("Enter button's name");
        JTextField txt = new JTextField();
        JLabel notice = new JLabel("***Notice! The code will be permanently deleted, make sure you have a backup");
        JButton del = new JButton("Delete");

        top.setBackground(col);
        down.setBackground(col);
        enter.setFont(new Font("Ariel",Font.ITALIC,14));
        enter.setForeground(new Color(60,60,60));
        enter.setBackground(col);
        txt.setBackground(col);
        txt.setBorder(new LineBorder(Color.BLACK));
        notice.setBackground(col);
        notice.setForeground(new Color (60,60,60));
        del.setBackground(col);
        del.setForeground(new Color(60,60,60));
        del.setBorderPainted(true);
        del.setBorder(new LineBorder(Color.BLACK));

        top.add(enter,BorderLayout.NORTH);
        top.add(txt,BorderLayout.SOUTH);
        down.add(notice,BorderLayout.NORTH);
        down.add(del,BorderLayout.SOUTH);
        add(top,BorderLayout.NORTH);
        add(down,BorderLayout.SOUTH);

        del.addActionListener(e -> {
            if(!txt.getText().isEmpty()) {
                DataBase.delete(txt.getText(), tableName);
                if(new FileHandler(codePath+"\\"+txt.getText()+".java").deleteFile())
                    JOptionPane.showMessageDialog(top,"The Button and all its context are successfully deleted");
                else JOptionPane.showMessageDialog(top,"Delete failed, try again");
                dispose();
            }
            else JOptionPane.showMessageDialog(top,"Enter Button's Name");
        });
    }

}
