package com.theikdi.recyclerviewapi.hotel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.theikdi.recyclerviewapi.R;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> implements Filterable {

    List<Hotels> hotels, hotelsFiler;
    private Context context;
    private RecyclerViewClickListener mListener;
    HotelFilter filter;

    public HotelAdapter(List<Hotels> hotels, Context context, RecyclerViewClickListener listener) {
        this.hotels = hotels;
        this.hotelsFiler = hotels;
        this.context = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public HotelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotels_list, parent, false);
        return new MyViewHolder(view, mListener);
    }

    //@SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.MyViewHolder holder, int position) {
        holder.name.setText(hotels.get(position).getName());
        RequestOptions requestOptions = new RequestOptions();
        //requestOptions.skipMemoryCache(true);
        //requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        //requestOptions.placeholder(R.drawable.logo);
        //requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(hotels.get(position).getPhoto())
                .apply(requestOptions)
                .into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private ImageView photo;
        private TextView name;
        private RelativeLayout mRowContainer;

        public MyViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            photo = itemView.findViewById(R.id.hotels_img);
            name = itemView.findViewById(R.id.hotels_name);
            mRowContainer = itemView.findViewById(R.id.row_layout);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
            photo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_layout:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                case R.id.hotels_img:
                    mListener.onLoveClick(photo, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new HotelFilter((ArrayList<Hotels>) hotelsFiler,this);
        }
        return filter;
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }
}
