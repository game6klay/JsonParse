package com.example.jay.parsejson.Models.Adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsejson.Controllers.Controller;
import com.example.jay.parsejson.Models.POJO.Flower;
import com.example.jay.parsejson.Models.Utilities.Constants;
import com.example.jay.parsejson.R;
import com.example.jay.parsejson.Views.DetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by jay on 5/5/17.
 */

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    public static String TAG = FlowerAdapter.class.getSimpleName();

    private List<Flower> mFlowers;
    private Controller.FlowerCallbackListener listener;

    public FlowerAdapter(Controller.FlowerCallbackListener listener,
                         List<Flower> flowers) {
        this.listener = listener;
        mFlowers = flowers;
    }

    public void addFlower(Flower flower) {
        mFlowers.add(flower);
        notifyDataSetChanged();
    }

    public void addFlowers(List<Flower> flowers) {
        mFlowers.addAll(flowers);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Flower currentFlower = mFlowers.get(position);
        holder.mName.setText(currentFlower.name);
        holder.mCategory.setText(currentFlower.category);
        holder.mPrice.setText(Double.toString(currentFlower.price));
        holder.mInstructions.setText(currentFlower.instructions);

        Picasso.with(holder.itemView.getContext())
                .load(Constants.PHOTO_URL + currentFlower.photo)
                .centerCrop()
                .fit()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mFlowers.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mName, mCategory, mPrice, mInstructions;
        public ImageView mImage;
        public CardView mContainer;

        public Holder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.flowerImage);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            mInstructions = (TextView) itemView.findViewById(R.id.flowerInstruction);
            mContainer = (CardView) itemView.findViewById(R.id.cont_item_root);
            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }
}
