package com.example.jmt_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    Context context;
    ArrayList<CommentItem> items = new ArrayList<>();

    public CommentAdapter (Context context) { this.context = context; }

    public CommentAdapter(Context context, ArrayList<CommentItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(ArrayList<CommentItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(CommentItem item) {items.add(item);}

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemView view = null;

        if (convertView == null) {
            view = new CommentItemView(context);
        } else {
            view = (CommentItemView) convertView;
        }

        CommentItem item = items.get(position);

        view.setNickname(item.getNickname());
        view.setComment(item.getComment());
        view.setImageView(item.getBitmap());
        //view.setImageView(item.getResId());

        return view;
    }
}
