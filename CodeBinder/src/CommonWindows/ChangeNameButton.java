package CommonWindows;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChangeNameButton extends JDialog {
    protected JButton button;
    private JTextField text;

    public ChangeNameButton(){
        setLayout(new BorderLayout());
        setSize(new Dimension(500,130));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(51,0,0)));
        JLabel label = new JLabel("Choose name:");
        label.setFont(new Font("Ariel",Font.BOLD,12));
        label.setForeground(Color.WHITE);
        text = new JTextField();
        text.setPreferredSize(new Dimension(400,25));
        button = new JButton("Submit");
        button.setFont(new Font("David",Font.ITALIC,16));
        button.setBorderPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.black);
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.black);
        panel.add(text);
        JPanel p = new JPanel(new BorderLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        p2.setBackground(Color.black);
        p2.add(label);
        p.setBackground(Color.black);
        p.add(p2,BorderLayout.CENTER);
        add(p, BorderLayout.NORTH);
        add(panel,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
    }

    public void addConfirmListener(ActionListener listener) {
        button.addActionListener(listener);
    }

    public String getTextFieldText() {
        return text.getText();
    }

}
