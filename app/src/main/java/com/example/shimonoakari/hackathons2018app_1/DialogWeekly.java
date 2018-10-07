package com.example.shimonoakari.hackathons2018app_1;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


public class DialogWeekly extends android.support.v4.app.DialogFragment {

ImageView darashibaGood;
ImageView darashibaBad;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Dialog dialog = new Dialog(getActivity());


        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーンにする
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_dialog_weekly);

        //フォント
        AssetManager assets = getResources().getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "yourfont.ttf");

        TextView myNameText = dialog.findViewById(R.id.textView7);
        final String number1 = getArguments().getString("Number1");
        myNameText.setText(number1);
        myNameText.setTypeface(typeface);

        TextView myRankText = dialog.findViewById(R.id.dialogMessageText2);
        final int myRank = getArguments().getInt("myRank");
        final int sumNumber = getArguments().getInt("sumNumber");
        myRankText.setText(myRank + "番目/" + sumNumber + "人中");
        myRankText.setTypeface(typeface);

        TextView darapoint = dialog.findViewById(R.id.textView5);
        darapoint.setText("1000");
        darapoint.setTypeface(typeface);

        TextView title = dialog.findViewById(R.id.dialogTitleText);
        title.setText("今週のふりかえり");
        title.setTypeface(typeface);

        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager manager = getActivity().getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int dialogWidth = (int) (metrics.widthPixels * 0.8);//Activityの横幅に対して0.8倍の大きさ
        int dialogHeight = (int) (metrics.heightPixels * 0.9);//Activityの縦幅に対して0.8倍の大きさ
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = dialogWidth;
        lp.height = dialogHeight;
        dialog.getWindow().setAttributes(lp);

        if (myRank > 1) {
            darashibaGood = dialog.findViewById(R.id.weeklydarashiba);
            darashibaGood.setImageResource(R.drawable.darashiba7);
        } else {
            darashibaBad = dialog.findViewById(R.id.weeklydarashiba);
            darashibaBad.setImageResource(R.drawable.darashiba3);
        }

            // Closeボタンのリスナー
            dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            dialog.findViewById(R.id.twitterButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String twitterMessage1 = "今週のダランキングは、" + sumNumber + "人中" + myRank + "番目でした！";
                    String twitterMessage2 = "\n ちなみにダラキングは" + number1 + "でした〜";
                    String twitterMessage3 = "\n #だらりつ";
                    String messsage = Uri.encode(twitterMessage1 + twitterMessage2 + twitterMessage3);
                    intent.setData(Uri.parse("twitter://post?message=" + messsage));
                    startActivity(intent);
                }
            });

            return dialog;
    }
}
