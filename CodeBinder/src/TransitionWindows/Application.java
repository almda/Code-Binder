package TransitionWindows;
import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String [] args){
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(new Color(50,50,50)));
        UIManager.put("InternalFrame.titleFont", new Font("Ariel", Font.ITALIC, 16));
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> new ChoosePathFrame().setVisible(true));
    }
}