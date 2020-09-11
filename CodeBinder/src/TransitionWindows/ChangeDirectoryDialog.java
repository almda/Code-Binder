package TransitionWindows;
import Handlers.DataBase;
import Handlers.FileHandler;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class ChangeDirectoryDialog extends JDialog {
    ChangeDirectoryDialog(){
        setLayout(new FlowLayout());
        setSize(new Dimension(500,100));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(400,25));
        text.setEditable(false);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        text.setBorder(blackline);
        text.setBackground(Color.black);
        text.setForeground(Color.WHITE);
        text.setText(getPath());
        add(text);
        JButton b = new JButton("Change");
        b.setPreferredSize(new Dimension(80,25));
        b.setBackground(Color.black);
        b.setForeground(Color.WHITE);
        b.addActionListener(e -> {
            JFileChooser f = new JFileChooser();
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            f.showSaveDialog(null);
            text.setText(String.valueOf(f.getSelectedFile()));
        });
        add(b);
        JButton set = new JButton("Set");
        set.setForeground(Color.WHITE);
        set.setBackground(Color.black);
        set.addActionListener(e -> {
            DataBase.updateDirectory(text.getText());
            FileHandler fh = new FileHandler(text.getText());
            fh.createDirectories();
            dispose();
        });
        add(set);
    }

    private String getPath() {
        return DataBase.SelectDirectory();
    }
}