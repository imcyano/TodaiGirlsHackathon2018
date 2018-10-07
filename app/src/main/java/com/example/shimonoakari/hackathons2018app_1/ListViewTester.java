package com.example.shimonoakari.hackathons2018app_1;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewTester extends AppCompatActivity {

    //finalを頭につけると一生上書きできない！
    final private String watashi = "ジョン";
    private ImageView mIcon2;
    private ImageView mIcon3;
    private Button mButton;
    private DialogWeekly dialogWeekly;
    private FragmentManager fragmentManager;
    private String[] names =
            {"みゆ", "あみ", "ポール", "はると", "はるか", "ジョン", "キャサリン", "かな", "アシュリー", "たろう"};
    private int darang = 150;
    private TextView daraKing;
    private ImageButton timerButton;
    private ImageButton kingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.RIGHT);//もう一方ではRIGHTに変更
        getWindow().setExitTransition(slide);
        setContentView(R.layout.activity_list_view_tester);

        timerButton = findViewById(R.id.timerButton);
        kingButton = findViewById(R.id.kingButton);


        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //遷移させる
                Intent intent = new Intent(ListViewTester.this, MainActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(ListViewTester.this, null).toBundle());

            }
        });

        kingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //何もしない
            }
        });

        mIcon2 = findViewById(R.id.imageView2);
        mIcon3 = findViewById(R.id.imageView3);
        mButton = findViewById(R.id.button1);

        mIcon2.setImageResource(R.drawable.crown);
        mIcon3.setImageResource(R.drawable.crown);

        daraKing = findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "yourfont.ttf");
        daraKing.setTypeface(typeface);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ボタンをクリックしたらダイアログを表示させる
                //ダイアログを表示させるのに必要なFragmentマネージャを生成する
                fragmentManager = getSupportFragmentManager();
                //フラグメント用のクラスのインスタンスを作成
                dialogWeekly = new DialogWeekly();
                //↑を使ってダイアログを表示させる
                Bundle bundle = new Bundle();
                bundle.putString("Number1", names[0]);
                bundle.putString("Watashi", watashi);
                bundle.putInt("sumNumber", names.length);

                for (int i = 0; i<names.length ; i++){
                    if (names[i].equals(watashi)){
                        bundle.putInt("myRank", i + 1);
                    }
                }

                dialogWeekly.setArguments(bundle);
                dialogWeekly.show(fragmentManager, "dialog");
            }
        });

        ListView listView = findViewById(R.id.list);


        // リストビューに表示する要素(SampleListItemで定義したもの)を設定

        ArrayList<ListContentTest> listItems = new ArrayList<>();

        // Adapterはリストにはめるだけ


        for (int i = 0; i < 10; i++) {
                //　リストに表示するための値を変数に代入しておく

                int bmp = i + 1;
                String text1 = names[i];
                String text3 = String.valueOf(i + 1) + "位";
                String text2 = "だらーん:" + darang;
                darang = darang - 10;

                // SampleListItemにそれぞれの変数を格納する
                ListContentTest item = new ListContentTest(text3, bmp, text1, text2);

                // リストビューに表示するためのリストにアイテムを追加
                listItems.add(item);
                //余裕あれば１〜３位は豪華な画像、残りはシンプルな画像

            }



        LIstAdapterTest adapter = new LIstAdapterTest(this, R.layout.list_content_test, listItems);
        listView.setAdapter(adapter);

    };
}

