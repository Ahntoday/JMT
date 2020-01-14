package com.example.jmt_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SituationRecyclerviewAdaptor extends RecyclerView.Adapter<SituationRecyclerviewAdaptor.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private ArrayList<SituationItemData> situationItemData;
    private OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public SituationRecyclerviewAdaptor(ArrayList<SituationItemData> situationItemData) {
        this.situationItemData = situationItemData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.situation_item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(situationItemData.get(position).situation);


    }

    @Override
    public int getItemCount() {
        return situationItemData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;
        private OnItemClickListener onItemClickListener = null;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.situation_button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (button.getCurrentTextColor() == v.getResources().getColor(R.color.colorMainGreen)) {
                        button.setBackgroundResource(R.drawable.button_select_complete_selected);
                        button.setBackgroundColor(v.getResources().getColor(R.color.colorMainGreen));
                        button.setTextColor(v.getResources().getColor(R.color.colorWhite));
                    } else {
                        button.setBackgroundResource(R.drawable.button_filter_selected);
                        button.setTextColor(v.getResources().getColor(R.color.colorMainGreen));
                    }
                }
            });


        }
    }

}

class SituationItemData  {
    public String situation;

    public SituationItemData(String situation) {
        this.situation = situation;
    }
}
