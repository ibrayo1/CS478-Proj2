package com.example.cs478_proj2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PictureActivity extends AppCompatActivity {

    private static final String TAG = "PictureActivity";
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        mContext = PictureActivity.this;

        // get the string sent from the main activity
        Bundle extras = getIntent().getExtras();

        String phName = extras.getString("phone_name").replace(" ", "_").toLowerCase();

        // get the photo image by id and assign it by id
        ImageView phoneImage = (ImageView) findViewById(R.id.phone_image);
        phoneImage.setImageResource(getResources().getIdentifier(phName,"drawable", getPackageName()));

        // on click listener for the image
        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // start the web activity with position
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("phone_pos", extras.getInt("phone_pos"));
                startActivity(intent);
            }
        });
    }
}
