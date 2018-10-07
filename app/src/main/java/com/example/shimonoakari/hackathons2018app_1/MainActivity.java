package com.example.shimonoakari.hackathons2018app_1;


import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements DialogFragment.OnDialogButtonClickListener {

    //Step1パーツの羅列
    private Chronometer chronometer;
    Button startButton;
    Button stopButton;
    Button inputTimeButton;
    int hour;
    int minute;
    String register;
    int daraanPoint;
    int point;
    int loginPoint = 1;
    // ログインボーナス：1点
    int sameMokuhyouPoint = 1;
    // 目標通り：1点
    int firstPoint = 100;
    // 初期値100
    private DialogFragment dialogFragment;
    private DarranDialogFragment darrandialogFragment;
    private FragmentManager flagmentManager;
    ImageButton timer_Button;
    ImageButton crown_Button;
    int minutes;

    //import option + enter
    //return command + z
    //copy/ command + c/d

    //Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);//もう一方ではRIGHTに変更
        getWindow().setExitTransition(slide);

        setContentView(R.layout.activity_main);



        //Step2レイアウトファイルと紐づける
        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        inputTimeButton = findViewById(R.id.IinputTimeButton);

        timer_Button = findViewById(R.id.timerButton);
        crown_Button = findViewById(R.id.crownButton);


        timer_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //何もしない
            }
        });

        crown_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //遷移させる
                Intent intent = new Intent(MainActivity.this, ListViewTester.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, null).toBundle());
            }
        });

//        AssetManager assets = getResources().getAssets();
//        Typeface typeface = Typeface.createFromAsset(assets, "yourfont.ttf");
//        textFont.setTypeface(typeface);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set the base to the current time just before calling start()
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
//                計測時間をトースト表示
//                Toast.makeText(MainActivity.this, chronometer.getText(), Toast.LENGTH_SHORT).show();
                //(いる場所、表示させるもの、表示長さ)
                register = chronometer.getText().toString();
//                計測時間を：ごとに区切る
                String[] separateRegister = register.split(":", 0);
                Log.d("register", "0 = " + separateRegister[0] + "1 = " + separateRegister[1]);
//                minutesは計測分
                minutes = Integer.parseInt(separateRegister[0]);
//                  secondsは計測秒
                int seconds = Integer.parseInt(separateRegister[1]);
//                secondsが30秒以上の場合計測分１増える
                if (seconds >= 30) {
                    minutes = minutes + 1;
                }

                //                ポイント換算
                daraanPoint = minutes - (hour * 60 + minute);

                if (daraanPoint == 0) {
                    point = firstPoint - sameMokuhyouPoint - loginPoint;
                } else {
                    point = firstPoint + daraanPoint - loginPoint;
                }

                flagmentManager = getSupportFragmentManager();
                //フラグメント用のクラスのインスタンスを作成
                darrandialogFragment = new DarranDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("daraanPoint", point);
                bundle.putInt("daraanPoint2", daraanPoint);
                //dialogへ値を送る
                darrandialogFragment.setArguments(bundle);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //↑を使ってダイアログを表示させる
                        darrandialogFragment.show(flagmentManager, "dialog");
                    }
                }, 1000);
//onActivityの中に入れる
// Select File>New>Folder>Assets Folder
//Click finish
//Right click on assets and create a folder called fonts
//Put your font file in assets > fonts
//Use code below to change your textView's font


            }

        });


    }


    public void nya(View v) {
        //ボタンをクリックしたらダイアログを表示させる
        //ダイアログを表示させるのに必要なFragmentマネージャを生成する
        flagmentManager = getSupportFragmentManager();
        //フラグメント用のクラスのインスタンスを作成
        dialogFragment = new DialogFragment();
        //↑を使ってダイアログを表示させる
        dialogFragment.show(flagmentManager, "dialog");

//        final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

//        final Calendar calendar = Calendar.getInstance();
//        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        final int minute = calendar.get(Calendar.MINUTE);

//        final LinearLayout linearLayout = new LinearLayout(MainActivity.this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        setContentView(linearLayout);


//        final TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
//                        //時計のViewのOKを押したとき
//
//                        //TODO InputTimeButtonの文字を変更する
//                        inputTimeButton.setText(hourOfDay + "時間" + minute1 + "分");
//                        hour = hourOfDay;
//                        minute = minute1;
//
//
//
//
////                        button.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v) {
////                                // エディットテキストのテキストを取得
////                                String text = editText.getText().toString();
////                                // 取得したテキストを TextView に張り付ける
////                                textView.setText(text);
////                        textView3.setText(hourOfDay + "時間" + minute + "分");
////                        linearLayout.addView(textview3,
////                                new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
//
//                    }
//                }, hour, minute, true);
//
//        timePickerDialog.show();
    }

    @Override
    public void onPositiveClick(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        inputTimeButton.setText(hour + "時間" + minute + "分");
    }

    @Override
    public void onNegativeClick() {

    }

}

