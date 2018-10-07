package com.example.shimonoakari.hackathons2018app_1;


import android.graphics.Typeface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.Bundle;
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
    private String[] namesNew = {"ジョン", "あみ", "ポール", "はると", "はるか", "みゆ", "キャサリン", "かな", "アシュリー", "たろう"};

    private int darang = 150;
    private TextView daraKing;
    private TextView titleMain;

    ListView listView;
    LIstAdapterTest adapter;

    ArrayList<ListContentTest> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_tester);
        listView = findViewById(R.id.list);

        //iconの名付け
        mIcon2 = findViewById(R.id.imageView2);
        mIcon3 = findViewById(R.id.imageView3);
        mButton = findViewById(R.id.button1);

        mIcon2.setImageResource(R.drawable.crown);
        mIcon3.setImageResource(R.drawable.crown);

        daraKing = findViewById(R.id.textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "yourfont.ttf");
        daraKing.setTypeface(typeface);
        titleMain = findViewById(R.id.textView);
        Log.i("test", "aaaaa");


        //ダランキングの切り替わり
        titleMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test", "bbbbbb");
                names = namesNew;
                // リストビューに表示する要素(SampleListItemで定義したもの)を設定

                for (int i = 0; i < 10; i++) {
                    //　リストに表示するための値を変数に代入しておく

                    int bmp = i + 1;
                    String text1 = names[i];
                    String text3 = String.valueOf(i + 1) + "位";
                    String text2 = darang + "だらーん";
                    darang = darang - 10;

                    // SampleListItemにそれぞれの変数を格納する
                    ListContentTest item = new ListContentTest(text3, bmp, text1, text2);
                    // リストビューに表示するためのリストにアイテムを追加
                    listItems.set(i, item);
                    //余裕あれば１〜３位は豪華な画像、残りはシンプルな画像

                }
                adapter.notifyDataSetChanged();
//                LIstAdapterTest adapter = new LIstAdapterTest(ListViewTester.this, R.layout.list_content_test, listItems);
//                listView.setAdapter(adapter);


            }
        });
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







        // Adapterはリストにはめるだけ
        // リストビューに表示する要素(SampleListItemで定義したもの)を設定
        listItems = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            //　リストに表示するための値を変数に代入しておく

            int bmp = i + 1;
            String text1 = names[i];
            String text3 = String.valueOf(i + 1) + "位";
            String text2 = darang + "だらーん";
            darang = darang - 10;

            // SampleListItemにそれぞれの変数を格納する
            ListContentTest item = new ListContentTest(text3, bmp, text1, text2);

            // リストビューに表示するためのリストにアイテムを追加
            listItems.add(item);
            //余裕あれば１〜３位は豪華な画像、残りはシンプルな画像

        }


        adapter = new LIstAdapterTest(this, R.layout.list_content_test, listItems);
        listView.setAdapter(adapter);

    };
}

