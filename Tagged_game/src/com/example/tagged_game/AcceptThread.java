package com.example.tagged_game;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.tagged_game.BluetoothManager.DiscoverHandler;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

public class AcceptThread extends Thread {
private TagHandler tagHandler;
	
	interface TagHandler  // Definition of a class that can be interacted with immediately 
	{
		void onTag(JSONObject message);
	}
	public static String NAME = "Tag_game_app"; 
	public static UUID MY_UUID = UUID.fromString("726e85db-c14b-4e97-b668-69fd0008390c");
	
	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	private final BluetoothServerSocket mmServerSocket;

	public AcceptThread(TagHandler tag) {
		// Use a temporary object that is later assigned to mmServerSocket,
		// because mmServerSocket is final
		this.tagHandler = tag;
		BluetoothServerSocket tmp = null;
		try {
			// MY_UUID is the app's UUID string, also used by the client code
			tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME,
					MY_UUID);
		} catch (IOException e) {
		}
		mmServerSocket = tmp;
	}

	public void run() {
		BluetoothSocket socket = null;
		// Keep listening until exception occurs or a socket is returned
		while (true) {
			try {
				socket = mmServerSocket.accept();
			} catch (IOException e) {
				break;
			}
			// If a connection was accepted
			if (socket != null) {
				// Do work to manage the connection (in a separate thread)
				manageConnectedSocket(socket);
				try {
					mmServerSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	/** Will cancel the listening socket, and cause the thread to finish */
	public void cancel() {
		try {
			mmServerSocket.close();
		} catch (IOException e) {
		}
	}
	
	public void manageConnectedSocket(BluetoothSocket socket)
	{
		BluetoothReadThread reader = new BluetoothReadThread(socket);
		reader.start();
		try {
			reader.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msg = new String (reader.getReceive());
		try {
			JSONObject receive = new JSONObject(msg);
			tagHandler.onTag(receive);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String test = "hello ";
	}
	
}
