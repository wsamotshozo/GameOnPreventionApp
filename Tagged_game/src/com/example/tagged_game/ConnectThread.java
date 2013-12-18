package com.example.tagged_game;

import java.io.IOException;
import java.io.StringWriter;

import org.json.JSONException;
import org.json.JSONObject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class ConnectThread extends Thread {
	private final BluetoothSocket mmSocket;
	private final String deviceAddress;

	BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	

	public ConnectThread(String deviceAddress) {
		// Use a temporary object that is later assigned to mmSocket,
		// because mmSocket is final
		BluetoothSocket tmp = null;
		this.deviceAddress = deviceAddress;

		// Get a BluetoothSocket to connect with the given BluetoothDevice
		try {
			// MY_UUID is the app's UUID string, also used by the server code
			BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(deviceAddress);
			tmp = device.createRfcommSocketToServiceRecord(AcceptThread.MY_UUID);
		} catch (IOException e) {
		}
		mmSocket = tmp;
	}

	public void run() {
		// Cancel discovery because it will slow down the connection
		mBluetoothAdapter.cancelDiscovery();

		try {
			// Connect the device through the socket. This will block
			// until it succeeds or throws an exception
			mmSocket.connect();
		} catch (IOException connectException) {
			// Unable to connect; close the socket and get out
			try {
				mmSocket.close();
			} catch (IOException closeException) {
			}
			return;
		}

		// Do work to manage the connection (in a separate thread)
		manageConnectedSocket(mmSocket);
	}

	/** Will cancel an in-progress connection, and close the socket */
	public void cancel() {
		try {
			mmSocket.close();
		} catch (IOException e) {
		}
	}

	public void manageConnectedSocket(BluetoothSocket socket) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("name", "String sent");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BluetoothWriteThread writer = new BluetoothWriteThread(socket, obj.toString());
		writer.start();

	}
}
