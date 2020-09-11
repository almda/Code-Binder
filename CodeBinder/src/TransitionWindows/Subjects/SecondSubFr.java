package TransitionWindows.Subjects;
import Handlers.DataBase;
import TransitionWindows.abstractSubFr;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class hold subject buttons 7-12
 * **/

public class SecondSubFr extends abstractSubFr {
    public SecondSubFr(String pastingPath) {
        super(pastingPath,132);
        next_page.setVisible(false);
        SetNamesFromDB();
        HandleButtons();

        back.setText("<==");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubjectsFrame sf = new SubjectsFrame(pastingPath);
                sf.setVisible(true);
                dispose();
            }
        });
    }

    protected void HandleButtons(){
        SpecificClick(b1,7); SpecificClick(b2,8); SpecificClick(b3,9);
        SpecificClick(b4,10); SpecificClick(b5,11); SpecificClick(b6,12);
    }

    @Override
    protected void SetNamesFromDB() {
        ResultSet rs = DataBase.SelectFromTable("Subjects");
        try {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(3);
                if (id == 7) b1.setText(name);
                else if (id == 8) b2.setText(name);
                else if (id == 9) b3.setText(name);
                else if (id == 10) b4.setText(name);
                else if (id == 11) b5.setText(name);
                else if (id==12) b6.setText(name);
            }
            revalidate();
        }
        catch (SQLException ex) { JOptionPane.showMessageDialog(this, "A problem has occurred, please restart", "Alert",2);}
    }

}//class78
