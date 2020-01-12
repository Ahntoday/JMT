package com.example.jmt_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PopUpActivity extends Activity {

    private Context mContext;
    private TextView welcome;
    private TextView jmt;
    private TextView KoreanWelcome;

    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        mContext = getApplicationContext();

        TextView welcome = findViewById(R.id.welcome);
        Spannable span = new SpannableString(welcome.getText());
        span.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorMainGreen)), 0, span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        welcome.setText(span);

//        TextView jmt = findViewById(R.id.jmt);
//        Spannable span2 = new SpannableString(jmt.getText());
//        span2.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorMainGreen)), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        jmt.setText(span2);
//
//        TextView KoreanWelcome = findViewById(R.id.KoreanWelcome);
//        Spannable span3 = new SpannableString(KoreanWelcome.getText());
//        span3.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorMainGreen)), 0, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        KoreanWelcome.setText(span3);

        findViewById(R.id.close).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.close:
                    mOnClose(v);
                    break;
            }
        }

    };

    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    private void gotoMainActivity() {
        Log.e("test", "buttowon");
        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}