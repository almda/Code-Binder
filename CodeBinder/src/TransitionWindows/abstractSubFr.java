package TransitionWindows;
import CommonWindows.ChangeNameButton;
import Handlers.DataBase;
import TransitionWindows.SubSubjects.*;
import TransitionWindows.Subjects.SubjectsFrame;
import javax.swing.*;
import java.awt.*;
import static java.lang.System.exit;

/**
 * abstract function of subject frames
 * in each frame set up: back and forward buttons, foreground color of buttons, setNamesFrom DB
 */

public abstract class abstractSubFr extends JFrame {
    protected String pastingPath,codesPath,name;
    protected JButton b1,b2,b3,b4,b5,b6,back,next_page;
    private int color;

    public abstractSubFr(String pastingPath,int color){
        super("Choose Subject");
        this.pastingPath=pastingPath;
        codesPath = DataBase.SelectDirectory()+"\\CodeBinderFiles"; //contains for example C:...\\Desktop
        name=null;
        this.color=color;  //each Subject Frame has its own color
        setSize(new Dimension(600,300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(51,51,51));
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51,0,0)));
        CreateView();
    }

    private void CreateView(){
        setLayout(new BorderLayout());

        JPanel center = new JPanel(new GridLayout(2,3));
        center.setBackground(new Color(51,51,51));
        add(center,BorderLayout.CENTER);

        //create 6 buttons on each frame, according to the given color
        b1 = new JButton();
        setUpCenterButton(center,b1,color);
        b2 = new JButton();
        setUpCenterButton(center,b2,color-6);
        b3 = new JButton();
        setUpCenterButton(center,b3,color-12);
        b4 = new JButton();
        setUpCenterButton(center,b4,color-18);
        b5 = new JButton();
        setUpCenterButton(center,b5,color-24);
        b6 = new JButton();
        setUpCenterButton(center,b6,color-30);

        //all to bottom section setup
        JPanel down = new JPanel(new BorderLayout());
        down.setBackground(new Color(51,51,51));
        add(down,BorderLayout.SOUTH);

        JPanel down_left = new JPanel(new FlowLayout());
        down_left.setBackground(new Color(51,51,51));
        down.add(down_left,BorderLayout.WEST);

        back = new JButton("<<  ");
        setUpBottomButton(back);
        down_left.add(back);

        JButton exit = new JButton("Exit");
        setUpBottomButton(exit);
        exit.addActionListener(e -> exit(1));
        down_left.add(exit);

        next_page = new JButton("==>");
        setUpBottomButton(next_page);
        down.add(next_page,BorderLayout.EAST);
    }

    protected abstract void SetNamesFromDB();

    /**
     * setting up the click action for all the buttons
     */
    protected abstract void HandleButtons();

    protected void SpecificClick(JButton b, int id){
        b.addActionListener(e -> {
            if (b.getText()==null){
                ChangeNameButton cnb = new ChangeNameButton();
                cnb.setVisible(true);
                cnb.addConfirmListener(e1 -> {
                    name = cnb.getTextFieldText();
                    cnb.dispose();
                    DataBase.updateSubject(id,name);
                    SubjectsFrame sf = new SubjectsFrame(pastingPath);
                    sf.setVisible(true);
                    dispose();
                });
            }
            else {
                String newCodePath = codesPath+"\\Subject"+id;
                if(id==1)new SubFrame1of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==2)new SubFrame2of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==3)new SubFrame3of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==4)new SubFrame4of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==5)new SubFrame5of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==6)new SubFrame6of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==7)new SubFrame7of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==8)new SubFrame8of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==9)new SubFrame9of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==10)new SubFrame10of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else if(id==11)new SubFrame11of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                else new SubFrame12of12(pastingPath,newCodePath,b.getText()).setVisible(true);
                dispose();
            }//else
        });
    }

    private void setUpCenterButton(JPanel panel, JButton button,int color){
        button.setFont(new Font("Ariel",Font.ITALIC,14));
        button.setBackground(new Color(color,color,color));
        button.setForeground(new Color(color-60,color-60,color-60));
        button.setBorderPainted(false);
        panel.add(button);
    }

    private void setUpBottomButton(JButton button){
        button.setBackground(new Color(51,51,51));
        button.setForeground(new Color(0,153,255));
        button.setFont(new Font("Arial",Font.BOLD,13));
        button.setBorderPainted(false);
    }
}
