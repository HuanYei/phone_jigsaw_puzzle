package com.HY.dao;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.Sql.DBDao;
import com.HY.Sql.PHB;
import com.HY.mypingtu.R;

import java.util.List;

public class ToastUtil {
    //显示文本+图片的Toast
    private static String data[] =new String[6];
    public static void showImageToas(Context context){
        //填充数据
        data[0]="名次   用时   步数   图片   日期";
        List<PHB> list= DBDao.getInstance().query();
        for (int i=0;i<list.size();i++){
            String mc=list.get(i).getMc()+"";
            if (mc.length()==1){
                mc="0"+mc;
            }
            String time=list.get(i).getTime()+"";
            if (time.length()==1){
                time="0"+time;
            }
            String tu="";
            switch (list.get(i).getMosi()){
                case "1":
                    tu="皮卡丘";
                    break;
                case "2":
                    tu="亚索";
                    break;
                case "3":
                    tu="女孩";
                    break;
            }
            data[i+1]=mc+"      "+time+"      "+list.get(i).getBusu()+"      "+tu +"      "+list.get(i).getDay();
        }
        View toastview= LayoutInflater.from(context).inflate(R.layout.toast_image_layout,null);
        ListView listView=toastview.findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,data);//新建并配置ArrayAapeter
        listView.setAdapter(adapter);
          //要提示的文本
        Toast toast=new Toast(context);   //上下文
        toast.setGravity(Gravity.CENTER,0,0);   //位置居中
        toast.setDuration(Toast.LENGTH_LONG);  //设置短暂提示
        toast.setView(toastview);   //把定义好的View布局设置到Toast里面
        toast.show();
    }
}

