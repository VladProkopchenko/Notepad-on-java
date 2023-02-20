import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainWindow extends JFrame {
    MainWindow(){
        super("Editor");

        String[] settings = {
                "File",
                "Save",
                "Save as",
                "Load",
                "Exit"
        };
        String[] size = {
                "10",
                "12",
                "14",
                "16",
                "18"
        };
        String[] styles = {
                "Font styles",
                "Bold",
                "Plain",
                "Italic"
        };
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea editField = new JTextArea(32,77);//32,75
        JEditorPane editorPane = new JEditorPane();
        JScrollPane jsp = new JScrollPane(editField);
        editField.setLineWrap (true);
        editField.setWrapStyleWord (false);
        JComboBox fileSettings = new JComboBox(settings);
        JComboBox fontSize = new JComboBox(size);
        JComboBox fontStyles = new JComboBox(styles);
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
                         JFileChooser fileSaver = new JFileChooser();
                         int ret = fileSaver.showDialog(null, "Save file");
                         if (ret == JFileChooser.APPROVE_OPTION) {
                             File file = fileSaver.getSelectedFile();
                             BufferedWriter writer = new BufferedWriter(new FileWriter(file,false));
                             editField.write(writer);
                             fileSettings.setSelectedIndex(0);
                         }

                     }
                     if(fileSettings.getSelectedIndex()==3){
                         JFileChooser fileLoader = new JFileChooser();
                         int ret = fileLoader.showDialog(null,"Load file");
                         if(ret == JFileChooser.APPROVE_OPTION){
                             File file = fileLoader.getSelectedFile();
                             BufferedReader reader = new BufferedReader(new FileReader(file));
                             String textLine;
                             String text = "";
                             while((textLine=reader.readLine())!=null){
                                 text+=textLine;
                                 text+="\n";
                             }
                             editField.setText(text);
                         }
                     }
                     if(fileSettings.getSelectedIndex()==4){
                         System.exit(0);
                     }
                    }catch (IOException ex){
                }
            }
        });
        fontStyles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fontStyles.getSelectedIndex()==1){
                    editField.setFont(new Font("...", Font.BOLD, 12));
                    fontStyles.setSelectedIndex(0);
                }
                if(fontStyles.getSelectedIndex()==2){
                    editField.setFont(new Font("...", Font.PLAIN, 12));
                    fontStyles.setSelectedIndex(0);
                }
                if(fontStyles.getSelectedIndex()==3){
                    editField.setFont(new Font("...", Font.ITALIC, 12));
                    fontStyles.setSelectedIndex(0);
                }
            }
        });
        fontSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Font font = editField.getFont();
                float size = font.getSize() + 1.0f;
                editField.setFont(font.deriveFont(size));
            }
        });

        Container area = getContentPane();
        JPanel head = new JPanel();

        head.setLayout(new FlowLayout());
        head.add(fileSettings);
        head.add(fontStyles);
        head.add(fontSize);
        area.setLayout(new FlowLayout());
        area.add(head);
        area.add(editField);
        setSize(800,600);
        setLocation(300,100);
    }
}
