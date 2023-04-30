import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainWindow extends JFrame {

    private final JTextArea editField;
    private final JComboBox fileSettings;
    private final JComboBox fontStyles;
    private final JSpinner fontSizeSpinner;
    private  JButton colorButton;
    JPanel head;
    MainWindow(){
        super("Editor");

        String[] settings = {
                "File",
                "Save",
                "Save as",
                "Load",
                "Exit"
        };
        String[] styles = {
                "Font styles",
                "Bold",
                "Plain",
                "Italic"
        };
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        editField = new JTextArea();
        editField.setLineWrap(true);
        fileSettings = new JComboBox(settings);
        fontStyles = new JComboBox(styles);
        colorButton = new JButton("Choose text color");
        SpinnerModel model = new SpinnerNumberModel(35,15,99,1);
        fontSizeSpinner = new JSpinner(model);
        fontSizeSpinner.setSize(50, 80);
        editField.setFont(new Font("...",Font.PLAIN,35));
        JScrollPane scrollPane = new JScrollPane(editField);
        fileSettings.addActionListener(e -> {
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
                         StringBuilder text = new StringBuilder();
                         while((textLine=reader.readLine())!=null){
                             text.append(textLine);
                             text.append("\n");
                         }
                         editField.setText(text.toString());
                     }
                 }
                 if(fileSettings.getSelectedIndex()==4){
                     System.exit(0);
                 }
                }catch (IOException ex){
                JOptionPane.showMessageDialog(null, "Something was wrong !&#7?");
            }
        });
        fontStyles.addActionListener(e -> {
            Font font = editField.getFont();
            if(fontStyles.getSelectedIndex()==1){
                editField.setFont(new Font("...",Font.BOLD,font.getSize()));
                fontStyles.setSelectedIndex(0);
            }
            if(fontStyles.getSelectedIndex()==2){
                editField.setFont(new Font("...",Font.PLAIN,font.getSize()));
                fontStyles.setSelectedIndex(0);
            }
            if(fontStyles.getSelectedIndex()==3){
                editField.setFont(new Font("...",Font.ITALIC,font.getSize()));
                fontStyles.setSelectedIndex(0);
            }
        });

        fontSizeSpinner.addChangeListener(e -> {
            Font font = editField.getFont();
            int size = (Integer) fontSizeSpinner.getValue();
            editField.setFont(new Font("...",font.getStyle(),size));
        });

        colorButton.addActionListener(e ->{
            Color color = JColorChooser.showDialog(null,"Choose the color",Color.BLACK);
            Font font = editField.getFont();
            editField.setForeground(color);
        });

        Container area = getContentPane();
        head = new JPanel();
        head.setLayout(new FlowLayout());
        head.add(fileSettings);
        head.add(fontStyles);
        head.add(fontSizeSpinner);
        head.add(colorButton);
        area.add(head,BorderLayout.NORTH);
        area.add(scrollPane,BorderLayout.CENTER);
        setSize(800,600);
        setLocation(300,100);
    }
}
