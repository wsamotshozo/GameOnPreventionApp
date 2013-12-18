package com.example.tagged_game;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class BluetoothManager 
{
	private DiscoverHandler discoverHandler;
	
	interface DiscoverHandler  // Definition of a class that can be interacted with immediately 
	{
		void onDiscover(String deviceName, String Address);
	}
	
	public boolean isSupported(Context context)
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			
			return false;
		    // Device does not support Bluetooth
		}
		mBluetoothAdapter.startDiscovery();
		return true;

	}
	// This method allows your app to get the users bluetooth name and address
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            discoverHandler.onDiscover(device.getName(), device.getAddress());
	            // Add the name and address to an array adapter to show in a ListView
	           // mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	        }
	    }
	};
	
	public void setOndiscoverhandler(DiscoverHandler sethandler)
	
	{
		this.discoverHandler = sethandler;
		
		
		
	}
	// Declares that your app is going to use the bluetooth technology
	public void registerBroadcastReceiver(Context context)
	{
		// Register the BroadcastReceiver
				IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
				context.registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
				/* Intent discoverableIntent = new
						Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
						discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
						context.startActivity(discoverableIntent);
						*/
	}
	
	public void getlistOfPairedDevices()
	{
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		if (pairedDevices.size() > 0) {
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		    	//mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		      //  R.id.paired_devices.add(device.getName() + "\n" + device.getAddress());
		    }
		}
	}
	
	
}
