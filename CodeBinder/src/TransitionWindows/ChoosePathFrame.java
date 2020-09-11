package TransitionWindows;
import Handlers.DataBase;
import TransitionWindows.Subjects.SubjectsFrame;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import static java.lang.System.exit;

public class ChoosePathFrame extends JFrame {
    private JLabel label;
    private JTextField text;
    private JButton button, enterB;

    public ChoosePathFrame(){
        super("Welcome!");
        setBackground(new Color(0,0,0,0));
        setSize(new Dimension(540,150));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CreateView();
    }

    private void CreateView() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int R = 240;
                    final int G = 240;
                    final int B = 240;

                    Paint p =
                            new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
                                    0.0f, getHeight(), new Color(R, G, B, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint(p);
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        setContentPane(panel);

        //headline label
        label = new JLabel("Please enter path:");
        label.setForeground(new Color(255,255,255));
        label.setFont(new Font("Courier New",Font.ITALIC,20));
        label.setOpaque(false);
        add(label);
        //directory text
        text = new JTextField();
        text.setOpaque(false);
        text.setPreferredSize(new Dimension(400,30));
        add(text);
        //text.setEditable(false); //TODO drop comment
        //directory button
        button = new JButton("...");
        button.setBackground(new Color(51,51,51));
        button.setForeground(new Color(0,153,255));
        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
            text.setText(filename);
        });
        panel.add(button);
        //enter button
        enterB = new JButton("Enter");
        enterB.setBackground(new Color(51,51,51));
        enterB.setForeground(new Color(0,153,255));
        add(enterB);
        enterB.addActionListener(e -> {
            if(text.getText().isEmpty())
                JOptionPane.showMessageDialog(panel, "Please choose a path", "Alert",2);
            else if(DataBase.SelectDirectory()==null)
                JOptionPane.showMessageDialog(panel, "Directory Path is not set", "Alert",2);
            else {
                SubjectsFrame sf = new SubjectsFrame(text.getText());
                sf.setVisible(true);
                dispose();
            }
        });
        //exit button
        JButton exitB = new JButton(" Exit ");
        exitB.setBackground(new Color(51,51,51));
        exitB.setForeground(new Color(0,153,255));
        add(exitB);
        exitB.addActionListener(e -> exit(1));
        //handle files area
        JButton files = new JButton("Files");
        files.setBackground(new Color(51,51,51));
        files.setForeground(new Color(0,153,255));
        files.addActionListener(e -> {
            ChangeDirectoryDialog cdd = new ChangeDirectoryDialog();
            cdd.setVisible(true);
        });
        add(files);
    }
}
