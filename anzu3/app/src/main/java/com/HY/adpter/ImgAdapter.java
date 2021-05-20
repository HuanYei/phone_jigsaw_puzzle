package com.HY.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.HY.mypingtu.R;
import java.util.List;
public class ImgAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;
    public ImgAdapter(Context context,List<String> list){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view;
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.sucai_img,parent,false);//加载布局
        }
        else{
            view = convertView;
        }
        String myType= (String) getItem(position);    //获取当前项的MyType实例
        //获取控件实例
        ImageView mytypeImage=(ImageView)view.findViewById(R.id.imgs);
        Bitmap img = BitmapFactory.decodeFile(list.get(position));
        mytypeImage.setImageBitmap(img);

        return  view;
    }

}
