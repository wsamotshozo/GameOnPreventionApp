package com.example.tagged_game;

import java.io.IOException;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;
import android.util.Log;


	public class BluetoothWriteThread extends Thread{
        private BluetoothSocket socket;
        private String msg;
        public BluetoothWriteThread(BluetoothSocket socket,String msg)
        {
                this.socket=socket;
                this.msg=msg;
        }
        public void run()
        {
                if (socket == null) 
                {
                        
                        return;
                }
                try {                        
                        OutputStream os = socket.getOutputStream();
                        byte [] send=DataDeal.plusHead(msg.getBytes().length);
                        os.write(send);
                        
                        os.write(msg.getBytes());
                        
                        os.flush();
                } catch (IOException e) {
                // TODO Auto-generated catch block
                        
                        e.printStackTrace();
                }        
        }
}

