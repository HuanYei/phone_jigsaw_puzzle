package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.HY.adpter.ImgAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SUcaiActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private ImgAdapter adapter;
    private ImgAdapter adapter2;
    private ListView mShowPathLv;
    private ListView mShowPathLv2;
    private List<String> zon;
    private List<String> zon1=new ArrayList<>();
    private List<String> zon2=new ArrayList<>();
    private Button button;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent=new Intent(SUcaiActivity.this,MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucai);
        mShowPathLv = (ListView) findViewById(R.id.lv_show_path);
        mShowPathLv2 = (ListView) findViewById(R.id.lv_show_path2);
        zon=getImagePathFromSD();
        zon.addAll(getImagePathFromSD2());
        listzon();
        button=findViewById(R.id.zj);
        button.setOnClickListener(this);
        adapter =new ImgAdapter(SUcaiActivity.this,zon1);
        adapter2=new ImgAdapter(SUcaiActivity.this,zon2);
        Log.e("结果",""+getImagePathFromSD().toString());
        mShowPathLv.setAdapter(adapter);
        mShowPathLv2.setAdapter(adapter2);
        sp=getSharedPreferences("first",MODE_PRIVATE);
        mShowPathLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("url",zon1.get(position));
                Intent intent1=null;
                if (sp.getBoolean("pd",true)){
                    intent1=new Intent(SUcaiActivity.this,Main2Activity.class);
                }else {
                    intent1=new Intent(SUcaiActivity.this,Main16Activity.class);
                }

                intent1.putExtra("p_id",""+4);
                intent1.putExtra("psucai","2");
                intent1.putExtra("url",zon1.get(position));
                Log.e("url",zon1.get(position));
                startActivity(intent1);
            }
        });
        mShowPathLv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent1=null;
                if (sp.getBoolean("pd",true)){
                    intent1=new Intent(SUcaiActivity.this,Main2Activity.class);
                }else {
                    intent1=new Intent(SUcaiActivity.this,Main16Activity.class);
                }
                intent1.putExtra("p_id",""+4);
                intent1.putExtra("psucai","2");
                intent1.putExtra("url",zon2.get(position));
                Log.e("url",zon2.get(position));
                startActivity(intent1);
            }
        });
    }
    public void dj(){

    }


    private void listzon() {
        for (int i=0;i<zon.size();i++){
            if (i%2==0){
                zon1.add(zon.get(i));
            }else {
                zon2.add(zon.get(i));
            }
        }
    }

    /**
     * 从sd卡获取图片资源
     * @return
     */
    private List<String> getImagePathFromSD() {
        // 图片列表
        List<String> imagePathList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "/Download";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }
    private List<String> getImagePathFromSD2() {
        // 图片列表
        List<String> imagePathList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStorageDirectory().toString() + File.separator
                + "/图片大全";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                imagePathList.add(file.getPath());
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }
    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp") ) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.zj){
            Intent intent=new Intent(SUcaiActivity.this,DKSHUcaiActivity.class);
            startActivity(intent);
        }
    }

}
