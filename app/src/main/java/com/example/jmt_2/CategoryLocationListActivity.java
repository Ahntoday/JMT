package com.example.jmt_2;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CategoryLocationListActivity extends AppCompatActivity {
    LocationAdapter adapter;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_location_list);

        ListView listView = (ListView) findViewById(R.id.search_store_listView);
        textView = (TextView) findViewById(R.id.searchText);
        button = (Button) findViewById(R.id.backButton);
        adapter = new LocationAdapter();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String location = getIntent().getStringExtra("location");
        textView.setText(location);

        if (location.equals("전철우")) {
            adapter.addItem(new CategoryLocationListItem("숯과닭발", R.drawable.jcu_sutdarkbal, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("곱창고", R.drawable.jcu_gobchanggo, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("청담이상", R.drawable.jcu_chungdam, 5, 0, location, getResources().getString(R.string.cafe_bar)));
            adapter.addItem(new CategoryLocationListItem("한신포차용봉점", R.drawable.jcu_hansin, 3, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("상무초밥용봉점", R.drawable.jcu_sangmuchobab, 4, 0, location, "일식"));
        } else if (location.equals("예대")) {
            adapter.addItem(new CategoryLocationListItem("한우촌", R.drawable.yd_hanwoochon, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("맛골", R.drawable.yd_matgol, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("훈이비어", R.drawable.yd_hoonebeer, 4, 0, location, getResources().getString(R.string.cafe_bar)));
            adapter.addItem(new CategoryLocationListItem("왕손주먹구이", R.drawable.yd_wangson, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("국밥연가", R.drawable.yd_gukbabyeonga, 4, 0, location, "한식"));
        } else if (location.equals("상대")) {
            adapter.addItem(new CategoryLocationListItem("뽀글이와둘둘이", R.drawable.sd_bboddul, 4, 0, location, "분식"));
            adapter.addItem(new CategoryLocationListItem("박준기감자탕", R.drawable.sd_parkjunki, 3, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("흠향원", R.drawable.sd_heumhyangwon, 3, 0, location, "중식"));
            adapter.addItem(new CategoryLocationListItem("마루", R.drawable.sd_maru, 3, 0, location, "일식"));
            adapter.addItem(new CategoryLocationListItem("춘부집", R.drawable.sd_chunbuzib, 5, 0, location, "한식"));
        } else if (location.equals("후문")) {
            adapter.addItem(new CategoryLocationListItem("피가로(전남대점)", R.drawable.hm_pigaro, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("서래갈매기", R.drawable.hm_seorae, 4, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("당신을기다릴지도몰라요", R.drawable.hm_dangkiyo, 5, 0, location, getResources().getString(R.string.cafe_bar)));
            adapter.addItem(new CategoryLocationListItem("예향정전남대점", R.drawable.usa_yehyangjeong, 3, 0, location, "한식"));
            adapter.addItem(new CategoryLocationListItem("스텀블온(STUMBLEON)", R.drawable.hm_stumbleon, 4, 0, location, "양식"));
            adapter.addItem(new CategoryLocationListItem("도쿄스테이크(전남대점)", R.drawable.hm_tokyosteak, 5, 0, location, "일식"));
            adapter.addItem(new CategoryLocationListItem("애상마라탕", R.drawable.hm_aesang, 3, 0, location, "중식"));
        } else if (location.equals("정문")) {
            adapter.addItem(new CategoryLocationListItem("월", R.drawable.jm_wal, 4, 0, location, "일식"));
            adapter.addItem(new CategoryLocationListItem("베를린", R.drawable.jm_berelin, 5, 0, location, getResources().getString(R.string.cafe_bar)));
            adapter.addItem(new CategoryLocationListItem("전대밤바다", R.drawable.jm_jeondaebambada, 3, 0, location, "일식"));
            adapter.addItem(new CategoryLocationListItem("K2 돈까스", R.drawable.jm_k2dongas, 4, 0, location, "양식"));
            adapter.addItem(new CategoryLocationListItem("까치통닭", R.drawable.jm_ggachi, 3, 0, location, getResources().getString(R.string.chicken_pizza)));
        }

        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CategoryLocationListItem item = (CategoryLocationListItem) adapter.getItem(position);
//
//                if (button.getBackground() == getResources().getDrawable(R.drawable.ic_save_18dp)) {
//                    button.setBackground(getResources().getDrawable(R.drawable.ic_save_selected_18dp));
//                } else {
//                    button.setBackground(getResources().getDrawable(R.drawable.ic_save_18dp));
//                }
//
//            }
//        });

    }

    class LocationAdapter extends BaseAdapter {
        ArrayList<CategoryLocationListItem> items = new ArrayList<>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CategoryLocationListItem item) {
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
            CategoryLocationListItemView view = null;
            if (convertView == null) {
                view = new CategoryLocationListItemView(getApplicationContext());
            } else {
                view = (CategoryLocationListItemView) convertView;
            }

            CategoryLocationListItem item = items.get(position);

            view.setStoreName(item.getStoreName());
            view.setImage(item.getResId());
            view.setReviewNum(item.getReviewNum());
            view.setPlace(item.getPlace());
            view.setMenu(item.getMenu());
            view.setRatingBar(item.getStarRate());

            return view;
        }
    }
}
