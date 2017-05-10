package com.example.jay.parsejson.Views;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsejson.Models.Utilities.Constants;
import com.example.jay.parsejson.R;
import com.squareup.picasso.Picasso;


/**
 * Created by jay on 5/5/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    private static final String EXTRA_PRICE = "EXTRA_PRICE";
    private static final String EXTRA_INSTRUCTIONS = "EXTRA_INSTRUCTIONS";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((TextView)findViewById(R.id.name)).setText(extras.getString(EXTRA_NAME));
        ((TextView)findViewById(R.id.category)).setText(extras.getString(EXTRA_CATEGORY));
        ((TextView)findViewById(R.id.price)).setText((int) extras.getDouble(EXTRA_PRICE) + "");
        ((TextView)findViewById(R.id.instructions)).setText(extras.getString(EXTRA_INSTRUCTIONS));
        Picasso.with(this)
                .load(Constants.PHOTO_URL + extras.getString(EXTRA_IMAGE_RES_ID))
                .centerCrop()
                .fit()
                .into((ImageView)findViewById(R.id.imageView));
        // ((ImageView)findViewById(R.id.imageView)).setImageDrawable(
        //        ContextCompat.getDrawable(this, extras.getInt(EXTRA_IMAGE_RES_ID)));

    }
}
