package com.example.firstapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class FoodListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Food> foodlist;

    public FoodListAdapter(Context context, int layout, ArrayList<Food> foodlist) {
        this.context = context;
        this.layout = layout;
        this.foodlist = foodlist;
    }

    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Override
    public Object getItem(int i) {
        return foodlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=new ViewHolder();
        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.foodlist_custom_adptr,viewGroup,false);
        }
        holder.imageView = view.findViewById(R.id.food_img);
        holder.textView = view.findViewById(R.id.food_name);
        Food food = foodlist.get(i);

        holder.textView.setText(food.getName());
        byte[] foodimage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);
        holder.imageView.setImageBitmap(bitmap);

        return view;
    }
}
