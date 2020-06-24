

package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        BasicCrypto crypto = new BasicCrypto();

        try {
            Socket s1 = new Socket("127.0.0.1", 1201);
            DataInputStream din = new DataInputStream(s1.getInputStream());
            DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String msgin = "";
            String msgout = "";

            while(!msgin.equalsIgnoreCase("end")) {
                msgout = br.readLine();
                String enc = new String(crypto.encrypt(msgout.getBytes()));
                System.out.println("Encrypted :" + enc);
                dout.writeUTF(enc);
                msgin = din.readUTF();
                String dec = new String(crypto.decrypt(msgin.getBytes()));
                System.out.println("Before decryption :" + msgin);
                System.out.println("Friend:" + dec);
            }
        } catch (Exception var10) {
        }

    }
}
