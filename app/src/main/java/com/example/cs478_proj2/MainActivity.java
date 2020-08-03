package com.example.cs478_proj2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<String> phoneList;
    private ArrayList<String> prcList;
    private ArrayList<String[]> specList;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        phoneList = makePhoneList(); // call to function which populates the list of phones (phoneList)
        prcList = makePriceList(); // call to function which populates the list of prices (prcList)
        makeSpecList();

        // define the recycler view
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.phone_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // define the list adapter
        this.adapter = new ListAdapter(mContext, phoneList, prcList, (v, position) -> {

            // make an intent for the picture activity
            Intent desIntent = new Intent(mContext, PictureActivity.class);
            desIntent.putExtra("phone_name", phoneList.get(position));
            desIntent.putExtra("phone_pos", position);

            startActivity(desIntent); // start picture activity

        });

        // set recycler view adapter
        mRecyclerView.setAdapter(this.adapter);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {

        // switch statement which opens up activities depending on what the user selected from the context menu
        switch (item.getItemId())
        {
            case R.id.phone_pic_activity: // start photo activity if user wants to see full photo of the phone
                Intent desIntent = new Intent(mContext, PictureActivity.class);
                desIntent.putExtra("phone_name", phoneList.get(adapter.getItemPosition()));
                desIntent.putExtra("phone_pos", adapter.getItemPosition());
                startActivity(desIntent);
                break;
            case R.id.phone_website: // if the user wishes to see official website for phone start that web activity
                Intent webIntent = new Intent(mContext, WebActivity.class);
                webIntent.putExtra("phone_pos", adapter.getItemPosition());
                startActivity(webIntent);
                break;
            case R.id.phone_specs: // start an activity that displays all the specs for a certain phone
                Intent specIntent = new Intent(mContext, SpecsActivity.class);
                specIntent.putExtra("phone_name", phoneList.get(adapter.getItemPosition()));
                specIntent.putExtra("spec_price", prcList.get(adapter.getItemPosition()));
                specIntent.putExtra("spec_list", specList.get(adapter.getItemPosition()));
                startActivity(specIntent);
                break;
        }

        return super.onContextItemSelected(item);
    }

    // function which makes the list of all the phones
    public ArrayList<String> makePhoneList(){
        ArrayList<String> phList = new ArrayList<>();

        // make the list of phones
        phList.add("iPhone Pro 11");
        phList.add("Galaxy S10 Plus");
        phList.add("iPhone 11");
        phList.add("Galaxy Note 10 Plus");
        phList.add("OnePlus 7T");
        phList.add("Google Pixel 3a");

        return phList;
    }

    // function which stores the price and size all of the phones into a list
    public ArrayList<String> makePriceList(){
        ArrayList<String> prcList = new ArrayList<>();

        prcList.add("6.5,999.00");
        prcList.add("6.4,999.99");
        prcList.add("6.1,699.00");
        prcList.add("6.3,1099.99");
        prcList.add("6.55,599.00");
        prcList.add("5.6,399.00");

        return prcList;
    }

    // function which populates the spec list with specs for each phone
    public void makeSpecList(){
        specList = new ArrayList<>();

        specList.add(new String[]{"Weight: 188g", "Dimensions: 144 x 71.4 x 8.1mm", "OS: iOS 13", "Resolution: 2436 x 1125", "CPU: A13 Bionic", "Storage: 64/256/512GB", "Battery:  4 hours longer than XS", "Rear camera: 12MP + 12MP + 12MP", "Front camera: 12MP", "Waterproof: IP68", "Headphone jack: No"});
        specList.add(new String[]{"Weight: 175g", "Dimensions: 157.6 x 74.1 x 7.8mm", "OS: Android 9", "Resolution: QHD+", "CPU: Octa-core chipset", "RAM: 8GB/12GB", "Storage: 128/512GB/1TB", "Battery:  4,100mAh", "Rear camera: 16MP + 12MP + 12MP", "Front camera: 10MP + 8MP", "Waterproof: IP68", "Headphone jack: Yes" });
        specList.add(new String[]{"Weight: 194g", "Dimensions: 150.9 x 75.7 x 8.3mm", "OS: iOS 13", "Resolution: 1762 x 828", "CPU: A13 Bionic", "Storage: 64/128/256GB", "Battery: 1 hour longer than XR", "Rear camera: 12MP + 12MP + 12MP ", "Front camera: 12MP", "Waterproof: IP68", "Headphone jack: No" });
        specList.add(new String[]{"Weight: 196g", "Dimensions: 162.3 x 77.2 x 7.9mm", "OS: Android 9", "Resolution: QHD+", "CPU: Octa-core chipset", "RAM: 12GB", "Storage: 256GB/512GB", "Battery:  4,300mAh", "Rear camera: 16MP + 12MP + 12MP + VGA", "Front camera: 10MP", "Waterproof: IP68", "Headphone jack: NO!" });
        specList.add(new String[]{"Weight: 190g", "Dimensions: 160.9 x 74.4 x 8.1mm", "OS: Android 10.0; OxygenOS 10.0.3", "Resolution: 1080 x 2400", "CPU: Octa-core chipset", "RAM: 8GB", "Storage: 128/256GB", "Battery: 3800mAh", "Rear camera: 48MP + 12MP + 16MP", "Front camera: 16MP"});
        specList.add(new String[]{"Weight: 147g", "Dimensions: 151.30 x 70.10 x 8.20mm", "OS: Android 9", "Resolution: 1080 x 2220", "CPU: Qualcomm Snapdragon 670", "RAM: 4GB", "Storage: 64GB", "Battery: 3000mAh", "Rear camera: 12.2MP", "Front camera: 8MP" });

    }

}
