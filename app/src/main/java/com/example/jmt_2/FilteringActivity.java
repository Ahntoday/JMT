package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FilteringActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    Button buttons[] = new Button[24];
    Button buttonComplete;
    Button buttonClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        buttons[0] = (Button) findViewById(R.id.filterButton0);
        buttons[1] = (Button) findViewById(R.id.filterButton1);
        buttons[2] = (Button) findViewById(R.id.filterButton2);
        buttons[3] = (Button) findViewById(R.id.filterButton3);
        buttons[4] = (Button) findViewById(R.id.filterButton4);
        buttons[5] = (Button) findViewById(R.id.filterButton5);
        buttons[6] = (Button) findViewById(R.id.filterButton6);
        buttons[7] = (Button) findViewById(R.id.filterButton7);
        buttons[8] = (Button) findViewById(R.id.filterButton8);
        buttons[9] = (Button) findViewById(R.id.filterButton9);
        buttons[10] = (Button) findViewById(R.id.filterButton10);
        buttons[11] = (Button) findViewById(R.id.filterButton11);
        buttons[12] = (Button) findViewById(R.id.filterButton12);
        buttons[13] = (Button) findViewById(R.id.filterButton13);
        buttons[14] = (Button) findViewById(R.id.filterButton14);
        buttons[15] = (Button) findViewById(R.id.filterButton15);
        buttons[16] = (Button) findViewById(R.id.filterButton16);
        buttons[17] = (Button) findViewById(R.id.filterButton17);
        buttons[18] = (Button) findViewById(R.id.filterButton18);
        buttons[19] = (Button) findViewById(R.id.filterButton19);
        buttons[20] = (Button) findViewById(R.id.filterButton20);
        buttons[21] = (Button) findViewById(R.id.filterButton21);
        buttons[22] = (Button) findViewById(R.id.filterButton22);
        buttons[23] = (Button) findViewById(R.id.filterButton23);
        buttonComplete = (Button) findViewById(R.id.buttonComplete);
        buttonClose = (Button) findViewById(R.id.closeButton);


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity();
            }
        });

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setTag(i);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;

                    for (Button tempButton : buttons) {
                        if (newButton == tempButton) {
                            int position = (Integer) v.getTag();

                            if (buttons[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                buttons[position].setBackgroundResource(R.drawable.button_filter);
                                buttons[position].setTextColor(getResources().getColor(R.color.colorUnSelectedText));
                            } else {
                                buttons[position].setBackgroundResource(R.drawable.button_filter_selected);
                                buttons[position].setTextColor(getResources().getColor(R.color.colorMainGreen));
                            }
                        }
                    }

                }
            });
        }

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonComplete.getCurrentTextColor() == getResources().getColor(R.color.colorWhite)) {
                    buttonComplete.setBackgroundResource(R.drawable.button_select_complete);
                    buttonComplete.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
                } else {
                    buttonComplete.setBackgroundResource(R.drawable.button_select_complete_selected);
                    buttonComplete.setBackgroundColor(getResources().getColor(R.color.colorMainGreen));
                    buttonComplete.setTextColor(getResources().getColor(R.color.colorWhite));
                }

                gotoMainActivity();
            }
        });
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(FilteringActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
