/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solids_2d.visual.application;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author finne
 */

public class Console extends JFrame {

    private final JScrollPane jScrollPane1;

    public Console() {
        super();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setEditable(false);
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.lightGray);
        jScrollPane1.setViewportView(textArea);JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL) {
            @Override
            public boolean isVisible() {
                return true;
            }
        };
        // if appropriate, uncomment
        scrollBar.putClientProperty("JScrollBar.fastWheelScrolling", Boolean.TRUE);
        jScrollPane1.setVerticalScrollBar(scrollBar);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        pack();
        System.setOut(printStream);
    }


    private PrintStream printStream = new PrintStream(new FilteredStream(new ByteArrayOutputStream()));
    private JTextArea textArea;

    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
        }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            System.err.println("#################################");
            textArea.setCaretPosition(0);
            textArea.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b, off, len);
            textArea.append(aString);
            String text = textArea.getText();

            int lastR = text.lastIndexOf("\r");
            int lastN = text.lastIndexOf("\n");

            //System.err.println(lastN + " " + lastR);



            if(lastR == 0 || lastN == (lastR+1)) {

            }else{
                int startIndex = -1;
                for(int i = lastR; i >= 0; i--){
                    if(i == 0){
                        startIndex = 0;
                    }
                    if(text.charAt(i) == '\n'){
                        startIndex = i;
                        break;
                    }
                }

                if(startIndex != -1){
                    String begin = text.substring(0, startIndex+1);
                    String end = text.substring(lastR +1);
                    textArea.setText(begin + end);
                }
            }
            textArea.setCaretPosition(textArea.getDocument().getLength());


        }
    }


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Console.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Console c = new Console();
        c.setVisible(true);
        try {
            Thread.sleep(1000);
            for (int i = 0; i < 100; i++) {
                Thread.sleep(30);
                System.out.println(i);
//                c.textArea.append("   " + i + "\n");
//                c.textArea.setCaretPosition(c.textArea.getDocument().getLength());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}