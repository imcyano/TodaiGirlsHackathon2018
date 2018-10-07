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
import android.widget.NumberPicker;


public class DialogFragment extends android.support.v4.app.DialogFragment {

    int hourNumber;
    int minuteNumber;
    NumberPicker hour;
    NumberPicker minute;



    public interface OnDialogButtonClickListener {
        void onPositiveClick(int hour, int minute);

        void onNegativeClick();
    }

    private OnDialogButtonClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnDialogButtonClickListener) getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Dialog dialog = new Dialog(getActivity());
        // タイトルを非表示に
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーンにする
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        WindowManager manager = getActivity().getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int dialogWidth = (int) (metrics.widthPixels * 0.8);//Activityの横幅に対して0.8倍の大きさ
        int dialogHeight = (int) (metrics.heightPixels * 0.5);//Activityの縦幅に対して0.8倍の大きさ
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = dialogWidth;
        lp.height = dialogHeight;
        dialog.getWindow().setAttributes(lp);


        hour = dialog.findViewById(R.id.hour);
        minute = dialog.findViewById(R.id.minute);
        Button positiveButton = dialog.findViewById(R.id.positive_button);
        Button negativeButton = dialog.findViewById(R.id.close_button);

        hour.setMaxValue(6);
        minute.setMaxValue(59);

        // OKボタンのリスナー
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourNumber = hour.getValue();
                minuteNumber = minute.getValue();
                listener.onPositiveClick(hourNumber, minuteNumber);
                Log.d("hour", hourNumber + "munite" + minuteNumber);
                //ダイアログを閉じる処理
                dismiss();
            }
        });
        // Closeボタンのリスナー
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialog;
    }


}