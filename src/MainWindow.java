import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainWindow extends JFrame {
    MainWindow(){
        super("My editor");
        String[] items = {
                "...",
                "save",
                "save as",
                "load"
        };
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextArea editField = new JTextArea(32,75);
        JComboBox fileSettings = new JComboBox(items);
        fileSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileSettings.getSelectedIndex() == 1) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt",false));
                        editField.write(writer);
                        fileSettings.setSelectedIndex(0);
                    }
                     if(fileSettings.getSelectedIndex()==2){


                     }
                    }catch (IOException ex){
                }
            }
        });
        Container area = getContentPane();
        area.setLayout(new FlowLayout());
        area.add(fileSettings);
        area.add(editField);
        setSize(800,600);
        setLocation(300,100);
    }
}
