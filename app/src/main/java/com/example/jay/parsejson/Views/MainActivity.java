package com.example.jay.parsejson.Views;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jay.parsejson.Controllers.Controller;
import com.example.jay.parsejson.Models.Adapters.FlowerAdapter;
import com.example.jay.parsejson.Models.POJO.Flower;
import com.example.jay.parsejson.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Controller.FlowerCallbackListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<Flower> mFlowerList = new ArrayList<>();
    private FlowerAdapter mFlowersAdapter;
    private Controller mController;

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";
    private static final String EXTRA_PRICE = "EXTRA_PRICE";
    private static final String EXTRA_INSTRUCTIONS = "EXTRA_INSTRUCTIONS";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();
    }

    private void configToolbar() {
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void configViews() {
        mRecyclerView = (RecyclerView) this.findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mFlowersAdapter = new FlowerAdapter(this, mFlowerList);
        mRecyclerView.setAdapter(mFlowersAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });

        //addFlowers();
    }

    private void addFlowers() {
        for(int i = 0; i  < 1000; i++) {

            Flower flower = new Flower.Builder()
                    .setName("Filippo")
                    .setPrice(15.2)
                    .setPhoto("AJHAJKS")
                    .build();

            mFlowersAdapter.addFlower(flower);
        }
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

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Flower flower) {
        mFlowersAdapter.addFlower(flower);
    }

    @Override
    public void onFetchProgress(List<Flower> flowerList) {
        mFlowersAdapter.addFlowers(flowerList);
    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }

    @Override
    public void onItemClick(int p) {
        String name = "NAME";
        Flower item = (Flower) mFlowerList.get(p);
        Intent i = new Intent(this, DetailsActivity.class);
        Log.i(name , item.getName());
        Bundle extras = new Bundle();
        extras.putString(EXTRA_NAME, item.getName());
        extras.putString(EXTRA_CATEGORY, item.getCategory());
        extras.putDouble(EXTRA_PRICE,  item.getPrice());
        extras.putString(EXTRA_INSTRUCTIONS,  item.getInstructions());
        extras.putString(EXTRA_IMAGE_RES_ID, item.getPhoto());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);

    }

}
