package com.example.jmt_2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class RecyclerviewAdaptor extends RecyclerView.Adapter<RecyclerviewAdaptor.ViewHolder> {
    private ArrayList<CardData> cardData;

    public RecyclerviewAdaptor(ArrayList<CardData> cardData) {
        this.cardData = cardData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_view, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(cardData.get(i).title);
        viewHolder.textView2.setText(cardData.get(i).content);
        viewHolder.imageView.setImageResource(cardData.get(i).img);
    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RoundedImageView imageView;
        public TextView textView;
        public TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (RoundedImageView) itemView.findViewById(R.id.cardImageView);
            textView = (TextView) itemView.findViewById(R.id.cardTitle);
            textView2 = (TextView) itemView.findViewById(R.id.cardContent);
        }
    }
}

class CardData {
    public String title;
    public String content;
    public int img;
    public CardData(String title, String content, int img) {
        this.title = title;
        this.content = content;
        this.img = img;
    }
}
