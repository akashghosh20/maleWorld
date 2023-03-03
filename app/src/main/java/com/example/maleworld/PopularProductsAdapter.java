package com.example.maleworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.maleworld.models.NewProductsModel;
import com.example.maleworld.models.PopularProductsModel;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {

    private final Context context;
    List <PopularProductsModel> list;

    public PopularProductsAdapter(Context context, List<PopularProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getName());
        holder.price.setText((list.get(position).getPrice()).toString());
    }

    @Override
    public int getItemCount() {

        if(list==null){
            return 0;
        }
        else{
            return list.size();
        }
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.all_img);
            name = itemView.findViewById(R.id.all_product_name);
            price = itemView.findViewById(R.id.all_price);

        }
    }
}