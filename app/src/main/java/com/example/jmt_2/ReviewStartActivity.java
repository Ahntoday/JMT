package com.example.jmt_2;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewStartActivity extends AppCompatActivity {
    ReviewWritingAdapter adapter;
    String storeName;
    EditText editText;
    TextView textView;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_start);

        final ListView listView = (ListView) findViewById(R.id.search_store_listView);
        editText = (EditText) findViewById(R.id.searchText);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


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

        textView = (TextView)findViewById(R.id.pleaseSelect);

        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(final TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    final String name = editText.getText().toString();
                    Log.e("test", name);
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(ReviewStartActivity.INPUT_METHOD_SERVICE);
                    if (name.trim().getBytes().length <= 0) {
                        Toast.makeText(getApplicationContext(), "식당 이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        editText.setText("");
                        editText.clearFocus();


                        return false;
                    }

//                    ReviewWritingAdapter reviewWritingAdapter = (ReviewWritingAdapter) listView.getAdapter();
//
//                    reviewWritingAdapter.addItem(new ReviewWritingSearchStoreItem(name));

                    database = FirebaseDatabase.getInstance();


//                    database.getReference().child("jmtMarket").child("stores").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            if (name == database.getReference().child("jmtMarket").child("stores").getKey())
//                            nickNameView.setText(dataSnapshot.getValue(UserData.class).getUserName());
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//
//                        }
//                    });

                    database.getReference().child("jmtMarket").child("stores").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            if (name.equals(dataSnapshot.getKey())){
                                textView.setText("리뷰 쓸 식당을 선택해주세요 !");
                                adapter.addItem(new ReviewWritingSearchStoreItem(name));
                                adapter.notifyDataSetChanged();
                            } else {
                                textView.setText("찾으시는 식당이 없어요! 식당을 먼저 등록해주세요 !");
                            }
                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



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


