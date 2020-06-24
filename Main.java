package com.company;

import java.io.*;

import java.net.Socket;


public class Main {

    public static void main(String[] args) {

        Crypto crypto=new BasicCrypto();
        try {


            Socket s1 = new Socket("127.0.0.7", 1001);


            DataInputStream din = new DataInputStream(s1.getInputStream());
            DataOutputStream dout = new DataOutputStream(s1.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String in = "", out = "";
            do {


                out = br.readLine();
                String enc=new String(crypto.encrypt(out.getBytes())); //encrypting

                System.out.println("Encrypted :" + enc);
                dout.writeUTF(enc);
                in = din.readUTF();
                String dec=new String(crypto.decrypt(in.getBytes()));//decrypting
                System.out.println("Before decryption :"+in);
                System.out.println("Friend:" + dec);// print chat


            }while (!out.equals("bye"));
            System.out.println("Server closed");
            s1.close();

        } catch (Exception e) {
            //Handle Exceptions
        }
    }
}
