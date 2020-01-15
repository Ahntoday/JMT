package com.example.jmt_2;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SituationRecyclerviewAdaptor extends RecyclerView.Adapter<SituationRecyclerviewAdaptor.ViewHolder> {


    private ArrayList<SituationItemData> situationItemData;
    private FrameLayout frameLayout;
    private Context context;

    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);
    public SituationRecyclerviewAdaptor(Context context, ArrayList<SituationItemData> situationItemData) {
        this.context = context;
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
        holder.button.setTag(position);

//        holder.button.setSelected(isItemSelected(position));
        if(mSelectedItems.get(position, false)){
            holder.button.setBackgroundResource(R.drawable.button_select_complete_selected);
            holder.button.setBackgroundColor(context.getResources().getColor(R.color.colorMainGreen));
            holder.button.setTextColor(context.getResources().getColor(R.color.colorWhite));
        }else{
            holder.button.setBackgroundResource(R.drawable.button_filter_selected);
            holder.button.setTextColor(context.getResources().getColor(R.color.colorMainGreen));

        }
    }

    @Override
    public int getItemCount() {
        return situationItemData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public Button button;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            button = (Button) itemView.findViewById(R.id.situation_button);

            button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

//            if (button.getCurrentTextColor() == v.getResources().getColor(R.color.colorMainGreen)) {
//                button.setBackgroundResource(R.drawable.button_select_complete_selected);
//                button.setBackgroundColor(v.getResources().getColor(R.color.colorMainGreen));
//                button.setTextColor(v.getResources().getColor(R.color.colorWhite));
//
//            } else {
//                button.setBackgroundResource(R.drawable.button_filter_selected);
//                button.setTextColor(v.getResources().getColor(R.color.colorMainGreen));
//            }

            switch(position){
                case 0:
                    Log.e("test", "0");
                    ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.situation_frameLayout, new SituationOne()).commitAllowingStateLoss();
                    break;
                case 1:
                    ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.situation_frameLayout, new SituationTwo()).commit();
                    Log.e("test", "1");
                    break;
                case 2:
                    ((AppCompatActivity)v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.situation_frameLayout, new SituationThree()).commit();
                    Log.e("test", "2");
                    break;
            }

            if (mSelectedItems.get(position, false)){
                mSelectedItems.put(position, false);

                button.setBackgroundResource(R.drawable.button_filter_selected);
                button.setTextColor(v.getResources().getColor(R.color.colorMainGreen));
            }else{
                mSelectedItems.put(position, true);
                button.setBackgroundResource(R.drawable.button_select_complete_selected);
                button.setBackgroundColor(v.getResources().getColor(R.color.colorMainGreen));
                button.setTextColor(v.getResources().getColor(R.color.colorWhite));

            }

        }
    }

    private void toggleItemSelected(int position){
        if(mSelectedItems.get(position, false) == true){
            mSelectedItems.delete(position);
            notifyItemChanged(position);
        }else{
            mSelectedItems.put(position, true);
            notifyItemChanged(position);
        }
    }

    private boolean isItemSelected(int position){
        return mSelectedItems.get(position, false);
    }

    public void clearSelectedItem(){
        int position;
        for(int i=0; i<mSelectedItems.size(); i++){
            position = mSelectedItems.keyAt(i);
            mSelectedItems.put(position, false);
            notifyItemChanged(position);

        }
        mSelectedItems.clear();
    }

}

class SituationItemData  {
    public String situation;
    private int viewType;

    public SituationItemData(String situation, int viewType) {
        this.situation = situation;
        this.viewType = viewType;
    }
}
