package com.connectivity;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {

            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("final.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            boolean check;
            boolean eof = false;



            while (!eof) {
                String line = br.readLine();
                if (line == null) {
                    eof = true;

                } else if (line.isEmpty() || line.trim().equals("") || line.trim().equals("\n")) {
                    System.out.println("Skipped a blank line"); //ignore an empty line

                } else {
                    //System.out.println(line);
                    String[] result = line.split("\\s");

                    int port = 23;
                    if (result.length == 2) {
                        port = Integer.parseInt(result[1]);
                    }

                        check = serverListening(result[0], port );
                        if (check) {
                            bw.write(line + " 'OK'\n");
                            System.out.println("OK");

                        } else {
                            bw.write(line + " 'FAIL'\n");
                            System.out.println("FAIL");
                        }

                    }
                }

            bw.close();
            br.close();
        }

        catch (IOException e){
            System.out.println("Error-" +e.getMessage());
        }
    }



    public static boolean serverListening(String host, int port) {

            Socket s = null;
        try

            {
                s=new Socket();
                s.connect(new InetSocketAddress(host,port),1000);
                return true;
            } catch(
            Exception e)

            {
                return false;
            } finally

            {
                if (s != null)
                    try {
                        s.close();
                    } catch (Exception e) {
                    }
            }
        }
}