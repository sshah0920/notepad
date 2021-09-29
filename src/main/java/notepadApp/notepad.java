package notepadApp;

import java.awt.*;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.filechooser.FileNameExtensionFilter;


public class notepad extends JFrame implements ActionListener, WindowListener {
    JTextArea textArea = new JTextArea();
    File file_name_container;


    public notepad() {
        Font text_font = new Font("Arial", Font.PLAIN, 12);
        Container text_container = getContentPane();
        JMenuBar menuBar = new JMenuBar();
        JMenu file_menu = new JMenu("File");
        JMenu edit_menu = new JMenu("Edit");
        JMenu help_menu = new JMenu("About");

        text_container.setLayout(new BorderLayout());
        JScrollPane scroll_app = new JScrollPane(textArea);
        scroll_app.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll_app.setVisible(true);

        textArea.setFont(text_font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        text_container.add(scroll_app);

        menuBar.add(file_menu);
        menuBar.add(edit_menu);
        menuBar.add(help_menu);

        JMenuItem fileMenu1 = new JMenuItem("New");
        fileMenu1.addActionListener(this);
        file_menu.add(fileMenu1);
        JMenuItem fileMenu2 = new JMenuItem("Open");
        fileMenu2.addActionListener(this);
        file_menu.add(fileMenu2);
        JMenuItem fileMenu3 = new JMenuItem("Save");
        fileMenu3.addActionListener(this);
        file_menu.add(fileMenu3);
       /* JMenuItem fileMenu4 = new JMenuItem("Print");
        fileMenu4.addActionListener(this);
        file_menu.add(fileMenu4);*/
        file_menu.addSeparator();
        JMenuItem fileMenu5 = new JMenuItem("Exit");
        fileMenu5.addActionListener(this);
        file_menu.add(fileMenu5);

        JMenuItem editMenu1 = new JMenuItem("Cut");
        editMenu1.addActionListener(this);
        edit_menu.add(editMenu1);
        JMenuItem editMenu2 = new JMenuItem("Copy");
        editMenu2.addActionListener(this);
        edit_menu.add(editMenu2);
        JMenuItem editMenu3 = new JMenuItem("Paste");
        editMenu3.addActionListener(this);
        edit_menu.add(editMenu3);

        JMenuItem helpMenu1 = new JMenuItem("About Editor");
        helpMenu1.addActionListener(this);
        help_menu.add(helpMenu1);

        setJMenuBar(menuBar);

        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));

        addWindowListener(this);

        setSize(800, 600);
        setTitle("New Untitled File.txt - Notepad");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser file_chooser = new JFileChooser();
        if (e.getActionCommand().equals("New")) {
            this.setTitle("Untitled File.txt - Notepad");
            textArea.setText("");
            file_name_container = null;
        } else if (e.getActionCommand().equals("Open")) {
            int open = file_chooser.showDialog(null, "Open");
            if (open == JFileChooser.APPROVE_OPTION) {
                try {
                    File openFile = file_chooser.getSelectedFile();
                    OpenFile(openFile.getAbsolutePath());
                    this.setTitle(openFile.getName() + " - Notepad");
                    file_name_container = openFile;
                } catch (IOException exp) {

                }
            }
        } else if (e.getActionCommand().equals("Save")) {
            if (file_name_container != null) {
                file_chooser.setCurrentDirectory((file_name_container));
                file_chooser.setSelectedFile(file_name_container);
            } else {
                file_chooser.setSelectedFile(new File("Untitled File.txt"));
            }

            int save = file_chooser.showSaveDialog(null);
            if (save == file_chooser.APPROVE_OPTION) {
                try {
                    File saveFile = file_chooser.getSelectedFile();
                    SaveFile(saveFile.getAbsolutePath());
                    this.setTitle(saveFile.getName() + " - Notepad");
                    file_name_container = saveFile;
                } catch (IOException excep) {

                }
            }
        } else if (e.getActionCommand().equals("Exit")) {
            Exiting();
        } else if (e.getActionCommand().equals("Copy")) {
            textArea.copy();
        } else if (e.getActionCommand().equals("Paste")) {
            textArea.paste();
        } else if (e.getActionCommand().equals("About Editor")) {
            JOptionPane.showMessageDialog(this, "Created by: Samarth Shah \nVersion: 1.0.0 \nFunctions: Cut, Copy, Paste, Open Existing File, Create New File, Save a File \nNext Update: Print a File" , "Editor Information", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getActionCommand().equals("Cut")) {
            textArea.cut();
        }
    }

    public void OpenFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String str;
        textArea.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        while ((str = br.readLine()) != null) {
            textArea.setText(textArea.getText() + str + " \r \n");
        }
        setCursor((new Cursor(Cursor.DEFAULT_CURSOR)));
        br.close();
    }

    public void SaveFile(String savefile) throws IOException {
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(savefile));
        dos.writeBytes(textArea.getText());
        dos.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void windowClosing(WindowEvent e) {
        Exiting();
    }

    public void Exiting() {
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
