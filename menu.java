import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.*;

public class menu extends JFrame {

   private JTextArea textArea;
   private JMenuBar menuBar;
   private JMenu menu;
   private JMenuItem item1, item2, item3, item4;
   private Color color;


   public menu() {

       setTitle("Menu");


       textArea = new JTextArea(10, 30);
       menuBar = new JMenuBar();
       menu = new JMenu("Options");
       item1 = new JMenuItem("Date & Time");
       item2 = new JMenuItem("Record to .txt");
       item3 = new JMenuItem("Change color");
       item4 = new JMenuItem("Exit");

       item1.addActionListener(new PrintDateTimeListener());
       item2.addActionListener(new WriteToLogListener());
       item3.addActionListener(new ChangeBackgroundListener());
       item4.addActionListener(new ExitListener());

       menu.add(item1);
       menu.add(item2);
       menu.add(item3);
       menu.add(item4);

       menuBar.add(menu);

       setJMenuBar(menuBar);

       add(textArea);

       setSize(400, 400);

       setVisible(true);
   }

   private class PrintDateTimeListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {

           DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
           LocalDateTime now = LocalDateTime.now();
           String dateTime = dtf.format(now);

           textArea.append("Date & Time: " + dateTime + "\n");
       }
   }

   private class WriteToLogListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           String contents = textArea.getText();

           try {
               FileWriter writer = new FileWriter("log.txt", true);
               writer.write(contents);
               writer.close();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
   }

   private class ChangeBackgroundListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
 
           Random rand = new Random();
           float hue = rand.nextFloat();
           color = Color.getHSBColor(0.08f, 0.5f,0.7f);

           textArea.setBackground(color);
       }
   }

   private class ExitListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
           System.exit(0);
       }
   }

   public static void main(String[] args) {
       menu frame = new menu();
   }
}
	 