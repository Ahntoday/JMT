package com.example.jmt_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class FilteringActivity extends AppCompatActivity {

    Button buttonsMenu[] = new Button[9];
    Button buttonsPlace[] = new Button[6];
    Button buttonsKeyword[] = new Button[9];
    String menuMent[] = {"맛있는 식사", "든든한 백반", "매콤한 떡볶이", "꾸덕한 파스타", "싱싱한 회초밥", "알싸한 마라탕", "바삭한 치킨", "간편한 식사", "시원한 맥주"};

    Boolean buttonsMenuState[] = new Boolean[9];
    Boolean buttonsPlaceState[] = new Boolean[6];
    Boolean buttonsKeywordState[] = new Boolean[9];

    Button buttonComplete;
    ImageButton buttonClose;

    String textPlace;
    String textMenu;
    String textMenuMent;
    String textKeyword;

    int tagPlace;
    int tagMenu;
    int tagKeyword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        buttonsMenu[0] = (Button) findViewById(R.id.filterButton_allMenu);
        buttonsMenu[1] = (Button) findViewById(R.id.filterButton_koreanFood);
        buttonsMenu[2] = (Button) findViewById(R.id.filterButton_schoolFood);
        buttonsMenu[3] = (Button) findViewById(R.id.filterButton_westernFood);
        buttonsMenu[4] = (Button) findViewById(R.id.filterButton_japaneseFood);
        buttonsMenu[5] = (Button) findViewById(R.id.filterButton_chineseFood);
        buttonsMenu[6] = (Button) findViewById(R.id.filterButton_chickenPizza);
        buttonsMenu[7] = (Button) findViewById(R.id.filterButton_fastFood);
        buttonsMenu[8] = (Button) findViewById(R.id.filterButton_cafePub);

        buttonsPlace[0] = (Button) findViewById(R.id.filterButton_allPlace);
        buttonsPlace[1] = (Button) findViewById(R.id.filterButton_backGate);
        buttonsPlace[2] = (Button) findViewById(R.id.filterButton_sangdaeGate);
        buttonsPlace[3] = (Button) findViewById(R.id.filterButton_frontGate);
        buttonsPlace[4] = (Button) findViewById(R.id.filterButton_artGate);
        buttonsPlace[5] = (Button) findViewById(R.id.filterButton_geoncheolwooGate);

        buttonsKeyword[0] = (Button) findViewById(R.id.filterButton_cheapMoney);
        buttonsKeyword[1] = (Button) findViewById(R.id.filterButton_eatAlone);
        buttonsKeyword[2] = (Button) findViewById(R.id.filterButton_homework);
        buttonsKeyword[3] = (Button) findViewById(R.id.filterButton_comeOutFast);
        buttonsKeyword[4] = (Button) findViewById(R.id.filterButton_atmosphere);
        buttonsKeyword[5] = (Button) findViewById(R.id.filterButton_teamProject);
        buttonsKeyword[6] = (Button) findViewById(R.id.filterButton_comeAgain);
        buttonsKeyword[7] = (Button) findViewById(R.id.filterButton_diningTogether);
        buttonsKeyword[8] = (Button) findViewById(R.id.filterButton_badPlace);

        for (int i = 0; i < buttonsMenuState.length; i++) {
            buttonsMenuState[i] = false;
        }

        for (int i = 0; i < buttonsPlaceState.length; i++) {
            buttonsPlaceState[i] = false;
        }

        for (int i = 0; i < buttonsKeywordState.length; i++) {
            buttonsKeywordState[i] = false;
        }

        buttonComplete = (Button) findViewById(R.id.buttonComplete);
        buttonClose = (ImageButton) findViewById(R.id.buttonClose);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainActivity();
            }
        });

        for (int i = 0; i < buttonsMenu.length; i++) {
            buttonsMenu[i].setTag(i);
            buttonsMenu[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;

                    for (Button tempButton : buttonsMenu) {
                        int position = (Integer) v.getTag();

                        if (newButton == tempButton) {
                            if (buttonsMenu[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsMenu);
                                setButtonUnSelected(buttonsMenu[position]);
                                buttonsMenuState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsMenu);
                                setButtonSelected(buttonsMenu[position]);
                                buttonsMenuState[position] = true;
                            }
                        }
                        textMenu = buttonsMenu[position].getText().toString();
                        textMenuMent = menuMent[position];
                        tagMenu = position;
                    }
                }
            });
        }

        for (int i = 0; i < buttonsPlace.length; i++) {
            buttonsPlace[i].setTag(i);
            buttonsPlace[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;
                    int position = (Integer) v.getTag();
                    for (Button tempButton : buttonsPlace) {
                        if (newButton == tempButton) {
                            if (buttonsPlace[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsPlace);
                                setButtonUnSelected(buttonsPlace[position]);
                                buttonsPlaceState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsPlace);
                                setButtonSelected(buttonsPlace[position]);
                                buttonsPlaceState[position] = true;
                            }
                        }
                    }
                    if (position == 0) {
                        textPlace = "전대";
                    } else {
                        textPlace = buttonsPlace[position].getText().toString();
                    }
                    tagPlace = position;

                }
            });
        }

        for (int i = 0; i < buttonsKeyword.length; i++) {
            buttonsKeyword[i].setTag(i);
            buttonsKeyword[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button newButton = (Button) v;
                    int position = (Integer) v.getTag();
                    for (Button tempButton : buttonsKeyword) {
                        if (newButton == tempButton) {
                            if (buttonsKeyword[position].getCurrentTextColor() == getResources().getColor(R.color.colorMainGreen)) {
                                setAllButtonUnselected(buttonsKeyword);
                                setButtonUnSelected(buttonsKeyword[position]);
                                buttonsKeywordState[position] = false;
                            } else {
                                setAllButtonUnselected(buttonsKeyword);
                                setButtonSelected(buttonsKeyword[position]);
                                buttonsKeywordState[position] = true;
                            }
                        }
                    }
                    textKeyword = buttonsKeyword[position].getText().toString();
                    tagKeyword = position;
                }
            });
        }

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonComplete.getCurrentTextColor() == getResources().getColor(R.color.colorWhite)) {
                    setButtonCompleteUnselected();
                } else {
                    setButtonCompleteSelected();
                }

                if (checkAllButtonUnselected(buttonsMenuState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else if (checkAllButtonUnselected(buttonsPlaceState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else if (checkAllButtonUnselected(buttonsKeywordState)) {
                    setButtonCompleteUnselected();
                    makeToast();
                } else {
                    gotoMainActivity();
                }


            }
        });


    }

    private void gotoMainActivity() {
        Intent intent = new Intent(FilteringActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("textPlace", textPlace);
        intent.putExtra("textMenu", textMenu);
        intent.putExtra("textMenuMent", textMenuMent);
        intent.putExtra("textKeyword", textKeyword);
        intent.putExtra("tagPlace", Integer.toString(tagPlace));
        intent.putExtra("tagMenu", Integer.toString(tagMenu));
        intent.putExtra("tagKeyword", Integer.toString(tagKeyword));
        startActivity(intent);
    }

    private void setButtonUnSelected(Button button) {
        button.setBackgroundResource(R.drawable.button_filter);
        button.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
    }

    private void setButtonSelected(Button button) {
        button.setBackgroundResource(R.drawable.button_filter_selected);
        button.setTextColor(getResources().getColor(R.color.colorMainGreen));
    }

    private void setButtonCompleteSelected() {
        buttonComplete.setBackgroundResource(R.drawable.button_select_complete_selected);
        buttonComplete.setBackgroundColor(getResources().getColor(R.color.colorMainGreen));
        buttonComplete.setTextColor(getResources().getColor(R.color.colorWhite));
    }

    private  void setButtonCompleteUnselected() {
        buttonComplete.setBackgroundResource(R.drawable.button_select_complete);
        buttonComplete.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
    }


    private boolean checkAllButtonUnselected(Boolean buttonState[]) {
        for (int i = 0; i < buttonState.length; i++) {
            if (buttonState[i] == true)
                return false;
        }
        return true;
    }

    private void setAllButtonUnselected(Button buttons[]) {
        for (Button tempButton : buttons) {
            setButtonUnSelected(tempButton);
        }
    }

    private void makeToast() {
        Toast.makeText(this, "앗! 선택되지 않은 정보가 있어요!", Toast.LENGTH_SHORT).show();
    }
}
