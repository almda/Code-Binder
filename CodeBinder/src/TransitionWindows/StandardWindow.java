package TransitionWindows;
import Handlers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.lang.System.exit;
import CommonWindows.*;

public abstract class StandardWindow extends JFrame {
    private String pastingPath; //where the code need to be pasted
    private String codePath; //holds -->  C://...//Linked List
    private String headline; //for success message
    private String tableName; //holds table name in MyDatabase

    public StandardWindow(String pastingPath, String codePath, String headline, String tableName){
        super(headline);
        this.headline = headline;
        this.pastingPath=pastingPath;
        this.codePath=codePath;
        this.tableName=tableName;
        CreateView();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600,400);
    }

    private void CreateView(){
        Color color = new Color(80,80,80);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51,0,0)));

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);
        JPanel mid = new JPanel(new FlowLayout());
        mid.setBackground(color);

        JButton add = new JButton("Add");
        add.setBackground(new Color(51,51,51));
        add.setForeground(new Color(0,153,255));
        add.addActionListener(e -> {
            AddingCodeFrame acf = new AddingCodeFrame(codePath, pastingPath, tableName);
            acf.setVisible(true);
            dispose();
        });

        JButton delete = new JButton("Delete");
        delete.setBackground(new Color(51,51,51));
        delete.setForeground(new Color(0,153,255));
        delete.addActionListener(e -> {
            DeletingCodeFrame dcf = new DeletingCodeFrame(tableName,codePath);
            dcf.setVisible(true);
        });

        JButton back = new JButton("<<<");
        back.setBackground(new Color(51,51,51));
        back.setForeground(new Color(0,153,255));
        back.addActionListener(e -> {
            ChoosePathFrame sf = new ChoosePathFrame();
            dispose();
            sf.setVisible(true);
        });

        JButton exit = new JButton("Exit");
        exit.setBackground(new Color(51,51,51));
        exit.setForeground(new Color(0,153,255));
        exit.addActionListener(e -> exit(1));
        panel.add(mid);

        JPanel down = new JPanel(new BorderLayout());
        down.setBackground(color);
        panel.add(down,BorderLayout.SOUTH);

        JPanel leftdown = new JPanel(new FlowLayout());
        leftdown.setBackground(color);
        JPanel rightdown = new JPanel(new FlowLayout());
        rightdown.setBackground(color);
        down.add(leftdown,BorderLayout.WEST);
        down.add(rightdown,BorderLayout.EAST);
        leftdown.add(back);
        leftdown.add(exit);
        rightdown.add(add);
        rightdown.add(delete);
        SetUpCommands(mid);
    }

    /**
     * talking to the HandlersPack.DataBase and setting up all the commands**/
    private void SetUpCommands(JPanel mid){
        ResultSet rs = DataBase.SelectFromTable(tableName);
        try {
            while (rs.next()){
                String commandName = rs.getString(1);
                String commandPath = rs.getString(2);
                JButton btn = new JButton(commandName);
                btn.setBackground(new Color(180,180,180));
                btn.setForeground(new Color(80,80,80));
                btn.setPreferredSize(new Dimension(75,30));
                mid.add(btn);
                btn.addActionListener(e -> {
                    try {
                        new FileHandler(pastingPath).CopyFile(commandPath+"\\"+commandName+".java");//till Linked List
                        JOptionPane.showMessageDialog(mid, "Code Pasted Successfully", "--"+commandName+" of "+headline+"--",1);
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(mid, "Problem finding file", "Alert",2);
                    }
                });
            }
            revalidate();
        }
        catch (SQLException ex){JOptionPane.showMessageDialog(mid, "Unexpected Problem - Please Restart", "Alert",2);}
    }
}
