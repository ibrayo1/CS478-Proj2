package com.example.cs478_proj2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SpecsActivity extends AppCompatActivity {

    private static final String TAG = "SpecsActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specs);

        mContext = this;

        // get the string sent from the main activity
        Bundle extras = getIntent().getExtras();

        String phName = extras.getString("phone_name");

        // get the photo image by id and assign it by id
        ImageView phoneImage = (ImageView) findViewById(R.id.specs_thumb);
        phoneImage.setImageResource(getResources().getIdentifier(phName.replace(" ", "_").toLowerCase(),"drawable", getPackageName()));

        // set the name of the phone
        TextView phoneName = (TextView) findViewById(R.id.specs_ph);
        phoneName.setText(phName);

        // set the price and the size
        TextView priceAndSizeSpec = (TextView) findViewById(R.id.spec_price);
        priceAndSizeSpec.setText(extras.getString("spec_price").replace(",", " in - $"));

        // find recycler view by id
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.phone_spec_list);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()){
            @Override // set this to false so that recycler view is non scrollable
            public boolean canScrollVertically() {
                return false;
            }
        });

        // create a new adapter and set the recycler view with the adapter
        SpecListAdapter adapter = new SpecListAdapter(mContext, getIntent().getStringArrayExtra("spec_list"));
        mRecyclerView.setAdapter(adapter);
    }
}
