package com.example.firstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    int[] img;
    String[] names;
    Context context;
    LayoutInflater layoutInflater;
    CustomAdapter(Context context,String[] names,int[] img){
        this.context=context;
        this.names=names;
        this.img=img;
    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.simple_layout2,viewGroup,false);
        }
        ImageView imageView = view.findViewById(R.id.imageV);
        TextView textView = view.findViewById(R.id.textV1);

        imageView.setImageResource(img[i]);
        textView.setText(names[i]);
        return view;
    }
}
