package com.example.user.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    String test_Wifi = "Ryu's";
    int len = 0;
    List<ScanResult> scanResult;
    WifiManager wm;
    public void searchWifi() {
        scanResult= wm.getScanResults();
        Toast.makeText(getApplication() ,"wifi scan\nindex 0: "+ scanResult.get(0).SSID+"\nsize: "+scanResult.size(),Toast.LENGTH_LONG).show();
        len = scanResult.size();
        connectWifi(test_Wifi);
    }
    public void connectWifi(String name){
        for(int i = 0; i<len; i++){
            if(scanResult.get(i).SSID.equals(name)){
                Toast.makeText(getApplication(),"==",Toast.LENGTH_LONG).show();

            }
        }
    }
    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                searchWifi();
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2475-9007"));
                        startActivity(intent);
                    }
                }

        );
        findViewById(R.id.button2).setOnClickListener(
                new Button.OnClickListener(){
                    //Wifi Scan
                    public void onClick(View v){
                        wm = (WifiManager) getSystemService(WIFI_SERVICE);
                        wm.startScan();
                        IntentFilter filter = new IntentFilter();
                        filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                        registerReceiver(wifiReceiver, filter);

                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
