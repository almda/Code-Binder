package TransitionWindows.Subjects;
import Handlers.DataBase;
import TransitionWindows.ChoosePathFrame;
import TransitionWindows.SubSubjects.SubFrame1of12;
import TransitionWindows.abstractSubFr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* Class hold subject buttons 1-6
 * **/

public class SubjectsFrame extends abstractSubFr {

    public SubjectsFrame(String pastingPath) {
        super(pastingPath, 102);
        SetNamesFromDB();
        HandleButtons();

        back.addActionListener(e -> {
            ChoosePathFrame cpf = new ChoosePathFrame();
            cpf.setVisible(true);
            dispose();
        });

        next_page.addActionListener(e -> {
            SecondSubFr tsf = new SecondSubFr(pastingPath);
            tsf.setVisible(true);
            dispose();
        });
    }


    protected void HandleButtons(){
        SpecificClick(b1,1); SpecificClick(b2,2); SpecificClick(b3,3);
        SpecificClick(b4,4); SpecificClick(b5,5); SpecificClick(b6,6);
    }

    /**
     * setting up the names for all the buttons from DB
     * **/
    @Override
    protected void SetNamesFromDB() {
        ResultSet rs = DataBase.SelectFromTable("Subjects");
        try {
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(3);
                if(id==1)b1.setText(name);
                else if(id==2)b2.setText(name);
                else if(id==3)b3.setText(name);
                else if(id==4)b4.setText(name);
                else if(id==5)b5.setText(name);
                else b6.setText(name);
            }
            revalidate();
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(this, "A problem has occurred, please restart", "Alert",2);}
    }

}//class
