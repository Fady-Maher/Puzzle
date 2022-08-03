package com.example.puzzle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.puzzle.R;
import com.example.puzzle.classes.AnimalsPuzzle;

import java.util.ArrayList;

public class RecyclePuzzleAdapter extends RecyclerView.Adapter<RecyclePuzzleAdapter.PuzzleViewHolder> {
    ArrayList<AnimalsPuzzle> list_news = null;
    Context context;
    OnClickListenerItemPuzzle onClickListenerItem;

    public void setOnClickListenerItem(OnClickListenerItemPuzzle onClickListenerItem) {
        this.onClickListenerItem = onClickListenerItem;
    }

    public RecyclePuzzleAdapter(ArrayList<AnimalsPuzzle> list_news, Context context) {
        this.list_news = list_news;
        this.context = context;
    }

    @NonNull
    @Override
    public PuzzleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_popular_courses, null, false);
        return new RecyclePuzzleAdapter.PuzzleViewHolder(view, onClickListenerItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PuzzleViewHolder holder, int position) {
        AnimalsPuzzle animals = list_news.get(position);
        holder.txt_animal.setText(animals.getAnimal_name());
        holder.animal_img.setImageResource(animals.getAnimal_img());
    }

    @Override
    public int getItemCount() {
        return list_news.size();
    }


    public class PuzzleViewHolder extends RecyclerView.ViewHolder {
        ImageView animal_img;
        TextView txt_animal, txt_dec;

        public PuzzleViewHolder(@NonNull View itemView, OnClickListenerItemPuzzle onClickListenerItem) {
            super(itemView);
            animal_img = itemView.findViewById(R.id.animal_img);
            txt_animal = itemView.findViewById(R.id.txt_animal_name);
            txt_dec = itemView.findViewById(R.id.txt_animal_dec);
            txt_dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerItem.onClick(list_news.get(getAdapterPosition()));
                }
            });
        }
    }
}
