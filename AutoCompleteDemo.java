import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutoCompleteDemo extends JFrame {
    private JTextField textField;
    private JComboBox<String> comboBox;
    private DefaultComboBoxModel<String> model;

    public AutoCompleteDemo() {
        setTitle("AutoComplete Example");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        textField = new JTextField(15);
        comboBox = new JComboBox<>();
        model = new DefaultComboBoxModel<>();
        comboBox.setModel(model);
        comboBox.setEditable(true);
        comboBox.setSelectedItem(null);

        // Add some sample data
        String[] browsers = {"Chrome", "Firefox", "Safari", "Edge", "Opera"};
        for (String browser : browsers) {
            model.addElement(browser);
        }

        // Add key listener to text field for autocomplete
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String text = textField.getText();
                    if (text.isEmpty()) {
                        comboBox.hidePopup();
                    } else {
                        comboBox.setModel(getFilteredModel(text));
                        comboBox.showPopup();
                    }
                });
            }
        });

        add(new JLabel("Choose your browser:"));
        add(textField);
        add(comboBox);

        setVisible(true);
    }

    private DefaultComboBoxModel<String> getFilteredModel(String text) {
        DefaultComboBoxModel<String> filteredModel = new DefaultComboBoxModel<>();
        for (int i = 0; i < model.getSize(); i++) {
            String item = (String) model.getElementAt(i);
            if (item.toLowerCase().startsWith(text.toLowerCase())) {
                filteredModel.addElement(item);
            }
        }
        return filteredModel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AutoCompleteDemo::new);
    }
}
