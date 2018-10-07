package com.example.shimonoakari.hackathons2018app_1;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;


public class DarranDialogFragment extends android.support.v4.app.DialogFragment {

    TextView difDaraanText;
    TextView daraanText;
    ImageView darashibaGood;
    ImageView darashibaBad;
//    変数宣言
    int daraanPoints;
    int daraanPoints2;

    public interface OnDialogButtonClickListener {
        void onPositiveClick(int hour, int minute);

        void onNegativeClick();
    }



    private OnDialogButtonClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        listener = (OnDialogButtonClickListener) getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトルを非表示に
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーンにする
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_darrandialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager manager = getActivity().getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int dialogWidth = (int) (metrics.widthPixels * 0.8);//Activityの横幅に対して0.8倍の大きさ
        int dialogHeight = (int) (metrics.heightPixels * 0.8);//Activityの縦幅に対して0.8倍の大きさ
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = dialogWidth;
        lp.height = dialogHeight;
        dialog.getWindow().setAttributes(lp);


//        AssetManager assets = getResources().getAssets();
//        Typeface typeface = Typeface.createFromAsset(assets, "fonts/yourfont.ttf");
//        difDaraanText.setTypeface(typeface);


        //ボタン表示
        Button next_Button = dialog.findViewById(R.id.nextButton);
        Button close_Button = dialog.findViewById(R.id.closeButton);

        //画像を表示させる
        //imageView.setImageResource(画像のID)
        //mImageView.setImageResource(R.drawable.rabbit);
        if (daraanPoints2 > 0 ) {
            darashibaBad = dialog.findViewById(R.id.darashibaView);
            darashibaBad.setImageResource(R.drawable.darashiba5);
        }else{
            darashibaGood = dialog.findViewById(R.id.darashibaView);
            darashibaGood.setImageResource(R.drawable.darashiba2);
        }

        //だら〜んポイントを表示させる
        daraanPoints = getArguments().getInt("daraanPoint");
        daraanPoints2 = getArguments().getInt("daraanPoint2");

        difDaraanText = dialog.findViewById(R.id.daraanPoint);
        daraanText = dialog.findViewById(R.id.textDaraanPoint);

        String fugo = "";

        if(daraanPoints2 > 0){
            fugo = "+";
        }else if(daraanPoints2 < 0){
            fugo = "-";
        }

        daraanText.setText("合計：" + daraanPoints + " " +"だらーん" + "！");
        difDaraanText.setText("今日は..." + " " + fugo + daraanPoints2 + " " + "だらーん");

        // OKボタンのリスナー
        next_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ダイアログを閉じる処理
                dismiss();
            }
        });
        // Closeボタンのリスナー
        close_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             dismiss();
            }
        });

        return dialog;
    }


}