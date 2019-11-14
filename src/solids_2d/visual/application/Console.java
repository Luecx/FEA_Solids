/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solids_2d.visual.application;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * @author finne
 */

public class Console extends JFrame {

    public Console() {
        super();
        this.setAlwaysOnTop(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        textArea = new JTextArea(24, 80);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));


        System.setOut(printStream);
        this.add(textArea);

        pack();
    }

    private PrintStream printStream = new PrintStream(new FilteredStream(new ByteArrayOutputStream()));
    private JTextArea textArea;

    int currentLine = 0;

    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
        }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
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

            if(lastR == 0 || lastN == (lastR+1)) return;

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
                //System.err.println(startIndex + " #### " + lastR);
            }

        }
    }


    public static void main(String[] args) {
        new Console().setVisible(true);

//        System.out.println("hallo");
//        System.out.println("hallo");
//        System.out.println("hallo");
//        System.out.println("hallo");
//        System.out.println("hallo");
        System.out.println("Hallo");
        System.out.println("Hallo");
        System.out.println("Hallo");
        System.out.print("\rtest");
        System.out.print("\rtest");
        System.out.print("\rtest");
        System.out.print("\rtest");
        System.out.print("\rtest");
    }
}