package com.example.puzzle.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzle.R;
import com.example.puzzle.classes.Animals;

import java.util.ArrayList;


public class RecycleAnimalsAdapter extends RecyclerView.Adapter<RecycleAnimalsAdapter.NewsViewHolder> {

    ArrayList<Animals>list_news=null;
    Context context;
    OnClickListenerItem onClickListenerItem;

    public void setOnClickListenerItem(OnClickListenerItem onClickListenerItem) {
        this.onClickListenerItem = onClickListenerItem;
    }

    public RecycleAnimalsAdapter(ArrayList<Animals> list_news, Context context) {
        this.list_news = list_news;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_card,null,false);
        return new NewsViewHolder(view,onClickListenerItem);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Animals animals=list_news.get(position);

        holder.txt_animal.setText(animals.getAnimal_name());
        holder.txt_count.setText(animals.getAnimal_count());
        holder.imageView.setImageResource(animals.getAnimal_img());

    }

    @Override
    public int getItemCount() {
        return list_news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt_animal,txt_count;
        public NewsViewHolder(@NonNull View itemView, OnClickListenerItem onClickListenerItem) {
            super(itemView);
            imageView=itemView.findViewById(R.id.card_view_image);
            txt_animal=itemView.findViewById(R.id.txt_animal);
            txt_count=itemView.findViewById(R.id.txt_count);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerItem.onClick(list_news.get(getAdapterPosition()));
                }
            });

        }
    }




}
