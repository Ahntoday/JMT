package com.example.jmt_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReviewStartActivity extends AppCompatActivity {
    ReviewWritingAdapter adapter;
    String storeName;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_start);

        final ListView listView = (ListView) findViewById(R.id.search_store_listView);
        editText = (EditText) findViewById(R.id.searchText);

        adapter = new ReviewWritingAdapter();
        adapter.addItem(new ReviewWritingSearchStoreItem("쭉심"));
        listView.setAdapter(adapter);


        Button button = (Button) findViewById(R.id.backButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReviewWritingSearchStoreItem item = (ReviewWritingSearchStoreItem) adapter.getItem(position);
                storeName = item.getName();
                gotoReviewWritingActivity();
            }
        });

        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    String name = editText.getText().toString();
                    Log.e("test", name);
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                    if (name.trim().getBytes().length <= 0) {
                        Toast.makeText(getApplicationContext(), "댓글을 입력해주세요!", Toast.LENGTH_SHORT).show();
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                        editText.clearFocus();

                        return false;
                    }

//                    ReviewWritingAdapter reviewWritingAdapter = (ReviewWritingAdapter) listView.getAdapter();
//
//                    reviewWritingAdapter.addItem(new ReviewWritingSearchStoreItem(name));

                    adapter.addItem(new ReviewWritingSearchStoreItem(name));
                    adapter.notifyDataSetChanged();

                    inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    editText.setText("");
                    editText.clearFocus();

                    return true;
                }
                return false;
            }

        });


    }

    public void gotoReviewWritingActivity() {
        Intent intent = new Intent(this, ReviewWritingActivity.class);
        intent.putExtra("storeName", storeName);
        startActivity(intent);
    }

    class ReviewWritingAdapter extends BaseAdapter {
        ArrayList<ReviewWritingSearchStoreItem> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ReviewWritingSearchStoreItem item){
            items.add(item);
        }

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
            ReviewWritingSearchStoreItemView view = null;
            if (convertView == null) {
                view = new ReviewWritingSearchStoreItemView(getApplicationContext());
            } else {
                view = (ReviewWritingSearchStoreItemView) convertView;
            }

            ReviewWritingSearchStoreItem item = items.get(position);

            view.setTextView(item.getName());

            return view;
        }
    }


}


