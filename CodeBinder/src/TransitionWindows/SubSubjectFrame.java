package TransitionWindows;
import CommonWindows.ChangeNameButton;
import Handlers.DataBase;
import TransitionWindows.Subjects.SubjectsFrame;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.exit;

/**
 * this class is the basic window to choose headline for specific subject**/

public abstract class SubSubjectFrame extends JFrame {
    int SubjectId;
    protected String pastingPath,codePath;
    protected JButton b1,b2,b3,b4,b5,b6,b7,b8,back;

    public SubSubjectFrame(String pastingPath, String codePath, String title, int SubjectId){
        super(title);
        this.pastingPath=pastingPath;
        this.codePath=codePath;
        this.SubjectId=SubjectId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(600,300);
        getContentPane().setBackground(new Color(51,51,51));
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(40,40,40)));
        CreateView();  //view of this class
        setUpFromDB(); //set buttons according to data base
        HandleButtons();  //buttons of this class
    }

    private void CreateView(){
        setLayout(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(2,4));
        Color color = new Color(51,51,51);
        center.setBackground(color);
        add(center,BorderLayout.CENTER);

        setUpButtons(center,color);

        JPanel down = new JPanel(new BorderLayout());
        down.setBackground(new Color(51,51,51));
        add(down,BorderLayout.SOUTH);

        JPanel down_left = new JPanel(new FlowLayout());
        down_left.setBackground(new Color(51,51,51));
        down.add(down_left,BorderLayout.WEST);

        back = new JButton("<<  ");
        setUpBottomButton(back);
        down_left.add(back);
        back.addActionListener(e -> {
            SubjectsFrame sf = new SubjectsFrame(pastingPath);
            sf.setVisible(true);
            dispose();
        });

        JButton exit = new JButton("Exit");
        setUpBottomButton(exit);
        exit.addActionListener(e -> exit(1));
        down_left.add(exit);
    }

    protected void setUpButtons(JPanel center, Color color){
        b1 = new JButton(); b2 = new JButton(); b3 = new JButton(); b4 = new JButton();
        b5 = new JButton(); b6 = new JButton(); b7 = new JButton(); b8 = new JButton();
        setUpCenterButton(center,b1,color);setUpCenterButton(center,b2,color);
        setUpCenterButton(center,b3,color);setUpCenterButton(center,b4,color);
        setUpCenterButton(center,b5,color);setUpCenterButton(center,b6,color);
        setUpCenterButton(center,b7,color);setUpCenterButton(center,b8,color);
    }

    private void setUpCenterButton(JPanel panel, JButton button,Color color){
        if(!button.getText().isEmpty())
            button.setFont(new Font("Ariel",Font.ITALIC,14));
        button.setBackground(color);
        button.setForeground(new Color(230,200,200));
        panel.add(button);
    }

    private void setUpBottomButton(JButton button){
        button.setBackground(new Color(51,51,51));
        button.setForeground(new Color(0,153,255));
        button.setFont(new Font("Arial",Font.BOLD,13));
        button.setBorderPainted(false);
    }

    private void HandleButtons(){
        SpecificClick(b1, SubjectId,1);SpecificClick(b2, SubjectId,2);SpecificClick(b3, SubjectId,3);SpecificClick(b4, SubjectId,4);
        SpecificClick(b5, SubjectId,5);SpecificClick(b6, SubjectId,6);SpecificClick(b7, SubjectId,7);SpecificClick(b8, SubjectId,8);
    }

    protected void setUpFromDB(){
        ResultSet rs = DataBase.SelectFromHeadlinesTable(SubjectId);
        try {
            while (rs.next()){
                int id = rs.getInt(2);
                String name = rs.getString(3);
                if(id==1)b1.setText(name);
                else if(id==2)b2.setText(name);
                else if(id==3)b3.setText(name);
                else if(id==4)b4.setText(name);
                else if(id==5)b5.setText(name);
                else if(id==6)b6.setText(name);
                else if(id==7)b7.setText(name);
                else b8.setText(name);
            }
            revalidate();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(this, "A problem has occurred, please restart", "Alert",2);}
    }

    private void SpecificClick(JButton b, int subjectId, int id){
        b.addActionListener(e -> {
            if(b.getText()==null){
                ChangeNameButton cnb = new ChangeNameButton();
                cnb.setVisible(true);
                cnb.addConfirmListener(e1 -> {
                    String name = cnb.getTextFieldText();
                    cnb.dispose();
                    DataBase.UpdateHeadline(subjectId, id, name,codePath+"\\"+id);
                    DataBase.createNewTable(name.replace(' ','_'));
                    SubjectsFrame sf = new SubjectsFrame(pastingPath);
                    sf.setVisible(true);
                    dispose();
                });
            }
            else {
                new StandardWindowImp(pastingPath,codePath+"\\"+id,b.getText(),b.getText().replace(' ','_')).setVisible(true);
                dispose();
            }
        });
    }
}
