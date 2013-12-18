package com.example.tagged_game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tagged_game.AcceptThread.TagHandler;
import com.example.tagged_game.BluetoothManager.DiscoverHandler;

public class MainActivity extends Activity {

	List<String> devicesDiscovered; // defined a string list member varialble
	List<String> devicesAddress;
	List<BluetoothDevice> pairedDevices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		devicesDiscovered = new ArrayList<String>(); // initialized list to an
														// array type;
		devicesAddress = new ArrayList<String>(); // initializing the list of
													// device address

		// check if the phone has bluetooth
		BluetoothManager testBluetooth = new BluetoothManager();
		if (!testBluetooth.isSupported(this)) {
			Toast.makeText(getApplicationContext(),
					"Sorry your phone doesnt support bluetooth!!! =)",
					Toast.LENGTH_LONG).show();
			return;

		}

		// call method to indicate interest in bluetooth devices

		// when a device gets discovered do following...
		testBluetooth.setOndiscoverhandler(new DiscoverHandler() {

			@Override
			public void onDiscover(String deviceName, String Address) {
				// Pair to device
				devicesDiscovered.add(deviceName); // add the Names of the
													// devices captured to the
													// list
				devicesAddress.add(Address); // adding the address captured to a
												// list
				displayPairedDevices();
				displayDiscoveredDevices(devicesDiscovered);

				// TODO Auto-generated method stub
			}
		});
		TagHandler tag = new AcceptThread.TagHandler() {
			
			@Override
			public void onTag(JSONObject message) 
			{
				runOnUiThread(new  Runnable() {
					public void run() {
						Toast.makeText(getApplicationContext(),
								"You've been tagged!!! =)",
								Toast.LENGTH_LONG).show();
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
			}
		};
		AcceptThread thread = new AcceptThread(tag);
		// thread.run();
		thread.start();

		testBluetooth.registerBroadcastReceiver(this);

		setContentView(R.layout.activity_main);

		ListView List = (ListView) findViewById(R.id.discovered_devices);
		List.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String address = devicesAddress.get(position);
				pairAndTagDevice(address);
			}

		});
		
		ListView List1 = (ListView) findViewById(R.id.paired_devices);
        List1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
            	pairAndTagDevice(pairedDevices.get(position).getAddress());
            	
            

                        // Put in your code here, what you wanted trigger :)
                }
        
  });

	}

	// Display a list of the paired devices
	public void displayPairedDevices() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		Set<BluetoothDevice> bondedDevices = mBluetoothAdapter
				.getBondedDevices();

		List<String> s = new ArrayList<String>();
		pairedDevices = new ArrayList<BluetoothDevice>();
		for (BluetoothDevice bt : bondedDevices) {
			s.add(bt.getName());
			pairedDevices.add(bt);
		}
		ListView List = (ListView) findViewById(R.id.paired_devices);

		List.setAdapter(new ArrayAdapter<String>(this, R.layout.device_row, s));
	}

	public void displayDiscoveredDevices(List<String> devices) {

		ListView List = (ListView) findViewById(R.id.discovered_devices);

		List.setAdapter(new ArrayAdapter<String>(this, R.layout.device_row,
				devices));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void pairAndTagDevice(String address) {
		ConnectThread connect = new ConnectThread(address);
		connect.start();
		try {
			connect.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		displayPairedDevices();
	}

}
