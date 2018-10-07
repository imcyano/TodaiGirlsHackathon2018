package com.example.shimonoakari.hackathons2018app_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LIstAdapterTest extends ArrayAdapter<ListContentTest> {
    private int mResource;
    private List<ListContentTest> mItems;
    private LayoutInflater mInflater;
    private Context mContext;

    /**
     * コンストラクタ
     *
     * @param context  コンテキスト
     * @param resource リソースID
     * @param items    リストビューの要素
     */
    public LIstAdapterTest(Context context, int resource, List<ListContentTest> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }

        ListContentTest item = mItems.get(position);
        ImageView imageView = view.findViewById(R.id.imageView);
        int icon_number = mContext.getResources().getIdentifier("icon_"+item.getmImageView(),"drawable", mContext.getPackageName());
        imageView.setImageResource(icon_number);


        TextView textView1 = view.findViewById(R.id.textView1);
        textView1.setText(item.getmName());


        TextView textView3 = view.findViewById(R.id.textView3);
        textView3.setText(item.getmScore());

        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText(item.getmOrder());
    return view;
    }
}
