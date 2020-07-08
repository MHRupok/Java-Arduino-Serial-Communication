/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serialtalk;

/**
 *
 * @author mehed
 */
import gnu.io.NRSerialPort;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialTalk extends Thread {

    /**
     * @param args the command line arguments
     */
    String port = "COM5";
    int baudRate = 9600;
    NRSerialPort serial = new NRSerialPort(port, baudRate);

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(serial.getInputStream()));

    }

    SerialTalk() throws IOException, InterruptedException {

        serial.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(serial.getInputStream()));
        PrintWriter pw = new PrintWriter(serial.getOutputStream());

        while (!br.ready()) {

        }
        System.out.println("Connected!");
        Thread.sleep(1000);
        pw.write("AT");
        pw.flush();
//
//        try {
//            for (String line = br.readLine(); line != null; line = br.readLine()) {
//                System.out.println(line);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(SerialTalk.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String line = br.readLine();
//        
//        
//        while (line != null) {
//            
//            System.out.println(line);
//            System.out.println(line.length());
//            line = br.readLine();
//        }
//        
//        while (((line = br.readLine()) != null)) {
//            System.out.println(line);
//            if(line.equalsIgnoreCase("done"))
//            {
//                break;
//            }
//            
//        }

        try {
            while (br.read() != 0) {
                System.out.println("ok");
            }
        } catch (IOException ex) {
            Logger.getLogger(SerialTalk.class.getName()).log(Level.SEVERE, null, ex);
        }
        serial.disconnect();

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        new SerialTalk();

//        pw.write('1');
//        pw.flush();
    }

}
