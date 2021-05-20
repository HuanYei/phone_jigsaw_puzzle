package com.HY.mypingtu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.dao.XY;
import com.HY.dao.getPhotoFromPhotoAlbum;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import pub.devrel.easypermissions.EasyPermissions;

public class ZDYActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private static final int REQ_CROP = 873;
    private static final int REQ_PIC = 434;
    private Uri mImageUri;
    private Uri mSmallUri;
    private final String TAG = getClass().getSimpleName();
    //在自己手机本地选择一个路径用于存储
    private String path = "/sdcard/DCIM/Camera/";
    private Button photo;
    private Button imgs;
    private LinearLayout tankuang;
    private Button quxiao;
    private ImageView imagebj;
    private ImageView left;
    private ImageView don;
    private ImageView paly;
    private TextView XuanXiang;
    private TextView bctp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zdy);
        photo=findViewById(R.id.photo);
        imgs=findViewById(R.id.imgs);
        tankuang=findViewById(R.id.tankauang);
        quxiao=findViewById(R.id.quxiao);
        imagebj=findViewById(R.id.bjtt);
        tankuang.setVisibility(View.GONE);
        left=findViewById(R.id.up);
        paly=findViewById(R.id.paly);
        don=findViewById(R.id.don);
        paly.setVisibility(View.GONE);
        XuanXiang=findViewById(R.id.xuanXiang);
        bctp=findViewById(R.id.bctp);
        //绑定点击事件
        imagebj.setOnClickListener(this);
        quxiao.setOnClickListener(this);
        photo.setOnClickListener(this);
        imgs.setOnClickListener(this);
        left.setOnClickListener(this);
        don.setOnClickListener(this);
        paly.setOnClickListener(this);
        XuanXiang.setOnClickListener(this);
        bctp.setOnClickListener(this);
        //滑动事件
        imagebj.setOnTouchListener(new View.OnTouchListener() {
            private float startX, startY, offsetX, offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //按下
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    //松开
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) { // left
                                Toast.makeText(ZDYActivity.this,"已经没有啦，往前翻翻吧",Toast.LENGTH_SHORT).show();
                            } else if (offsetX > 5) { // right
                                if (mSmallUri!=null&&sp.getString("mUrl","0").equals("0")){
                                    tuicu=false;
                                    showMyDialog(); //点击BACK弹出对话框
                                }else {
                                    Intent intent = new Intent(ZDYActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    finish();
                                    overridePendingTransition(0, 0);//关闭动画
                                }
                            }else {
                                tupiandj();
                            }
                        } else {
                            if (offsetY < -5) { // up
                            } else if (offsetY > 5) { // down

                            }else {
                                tupiandj();
                            }
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        sp=getSharedPreferences("first",MODE_PRIVATE);
        ed=sp.edit();
        ed.putInt("tu_id",3);
        ed.commit();
        //保存后
        if (!sp.getString("mUrl","0").equals("0")){
            String uri=sp.getString("mUrl","0");
            int width=sp.getInt("mwidth",0);
            int heigth=sp.getInt("mheigth",0);
            mSmallUri=Uri.parse(uri);
            Bitmap bitmap=null;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mSmallUri));
                if (width!=0)bitmap=XY.zoomBitmap(bitmap,width,heigth);
                imagebj.setImageBitmap(bitmap);
                paly.setVisibility(View.VISIBLE);
                bctp.setVisibility(View.VISIBLE);
                bctp.setText("取消保存");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //判断模式
        TextView Mosi=findViewById(R.id.mosi);
        if (sp.getBoolean("pd",true)){

            Mosi.setText("简单模式");;
        }else {

            Mosi.setText("困难模式");;
        }
    }
    //图片点击
    boolean tuicu=true;
    int pd=1;
    public void tupiandj(){
        //开启权限
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.CAMERA",
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE"},101);
        }

        pd++;
        if (pd%2==0){
            tankuang.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(ZDYActivity.this,R.anim.view_anim);
            tankuang.startAnimation(animation);
        }else {
            Animation animation = AnimationUtils.loadAnimation(ZDYActivity.this,R.anim.out);
            tankuang.startAnimation(animation);
            tankuang.setVisibility(View.GONE);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击图片
            case R.id.don:
                Toast.makeText(ZDYActivity.this,"已经没有啦，往前翻翻吧",Toast.LENGTH_SHORT).show();
                break;
            case R.id.quxiao:

                Animation animation = AnimationUtils.loadAnimation(ZDYActivity.this,R.anim.out);
                tankuang.startAnimation(animation);
                tankuang.setVisibility(View.GONE);
                break;
            //打开相机
            case R.id.photo:
                go();
                break;
                //打开相册
            case R.id.imgs:
                goPhotoAlbum();
                break;
            case R.id.up:
                if (mSmallUri!=null&&sp.getString("mUrl","0").equals("0")){
                    tuicu=false;
                    showMyDialog(); //点击BACK弹出对话框
                }else {
                Intent intent=new Intent(ZDYActivity.this, MainActivity.class);
                startActivity(intent);
                finish();}
                overridePendingTransition(0, 0);//关闭动画
                break;
            case R.id.paly:
                Intent intent1=null;
                if (sp.getBoolean("pd",true)){
                    intent1=new Intent(ZDYActivity.this,Main2Activity.class);
                }else {
                    intent1=new Intent(ZDYActivity.this,Main16Activity.class);
                }
               intent1.putExtra("p_id",""+4);
               intent1.putExtra("url",mSmallUri.toString());
               intent1.putExtra("psucai","0");
                Log.e("url",mSmallUri.toString());

                startActivity(intent1);
                break;
            case R.id.xuanXiang:
            Intent intent2=new Intent(ZDYActivity.this,Main3Activity.class);
            startActivity(intent2);
            break;
            case R.id.bctp:
                if (sp.getString("mUrl","0").equals("0")){
                    bctp.setText("取消");
                    Toast.makeText(ZDYActivity.this,"已成功保存图片",Toast.LENGTH_SHORT).show();
                    ed.putString("mUrl",mSmallUri.toString());
                    ed.commit();
                }else {
                    Toast.makeText(ZDYActivity.this,"已取消保存",Toast.LENGTH_SHORT).show();
                    ed.putString("mUrl","0");

                    ed.commit();
                    bctp.setText("保存");
                }
                break;

        }
    }
    //激活相机操作
    private void go() {
        //点击打开相机
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置输出路径
        path = path+getNowTime()+".jpg";
        mImageUri = FileProvider.getUriForFile(ZDYActivity.this, "com.example.android_mvp_dagger", new File(path));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mImageUri);
        startActivityForResult(intent,102);
    }
    //激活相册操作
    private void goPhotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQ_PIC);
    }
    /*用时间创建图片文件，防重名*/
    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }
    //提供一个方法获取当前时间
    private String getNowTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    //裁剪
    private void crop() {

        /*新建用于存剪裁后图片的文件，并转化为Uri*/
        File imageFile = createImageFile();
        mSmallUri = Uri.fromFile(imageFile);
        Log.i(TAG, "crop: " + mSmallUri);
        Log.i(TAG, "crop: " + mImageUri);

        int height = getWindowManager().getDefaultDisplay().getHeight();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(mImageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("aspectX", XY.getScreenRealWidth(ZDYActivity.this));
        intent.putExtra("aspectY", XY.getScreenRealHeight(ZDYActivity.this));
        intent.putExtra("scale", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        intent.putExtra("return-data", false);//设置为不返回缩略图
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mSmallUri);//设置大图保存到文件
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//保存的图片格式
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQ_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQ_PIC) {//选取图片
            mImageUri = data.getData();
            crop();
        } else if (requestCode == REQ_CROP) {//剪裁
            try {
                if (mSmallUri != null) {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mSmallUri));
                    bitmap=XY.zoomBitmap(bitmap,imagebj.getWidth(),imagebj.getHeight());
                    imagebj.setImageBitmap(bitmap);
                    paly.setVisibility(View.VISIBLE);
                    bctp.setVisibility(View.VISIBLE);
                    tupiandj();
                } else {
                    Log.i(TAG, "onActivityResult: Uri is null");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if (requestCode==102){
            crop();
        }
    }
    /**
     * 退出时弹出对话框，确定保存数据
     *
     * @chendong 2016年6月1日
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mSmallUri!=null&&sp.getString("mUrl","0").equals("0")){
                tuicu=true;
                showMyDialog(); //点击BACK弹出对话框
            }else {
                finish();
            }

        }
        return false;
    }

    private void showMyDialog() {
        // 创建退出对话框
        AlertDialog isExit = new AlertDialog.Builder(ZDYActivity.this).create();
        // 设置对话框标题
        isExit.setTitle("提示");
        // 设置对话框消息
        isExit.setMessage("确定要退出吗，图片将不会保存");
        // 添加选择按钮并注册监听
        isExit.setButton(Dialog.BUTTON_NEUTRAL,"确定",listener);
        isExit.setButton(Dialog.BUTTON_NEGATIVE,"保存并退出", listener);
        isExit.setButton(Dialog.BUTTON_POSITIVE,"取消", listener);
        // 显示对话框
        Log.e("显示","可以");
        isExit.show();
    }

    /**
     * 监听对话框里面的button点击事件
     */
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case Dialog.BUTTON_NEUTRAL:// "确认"按钮退出程序
                    if (tuicu){
                        finish();
                    }else {
                        Intent intent = new Intent(ZDYActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);//关闭动画
                    }

                    break;
                case Dialog.BUTTON_NEGATIVE:// 保存并退出
                    ed.putString("mUrl",mSmallUri.toString());
                    ed.putInt("mwidth", imagebj.getWidth());
                    ed.putInt("mheigth",imagebj.getHeight());
                    ed.commit();
                    if (tuicu){
                        finish();
                    }else {
                        Intent intent = new Intent(ZDYActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);//关闭动画
                    }
                    break;
                default:

                    break;
            }
        }
    };
    @Override
    protected void onRestart() {
        super.onRestart();
        //判断模式
        TextView Mosi=findViewById(R.id.mosi);
        if (sp.getBoolean("pd",true)){

            Mosi.setText("简单模式");
        }else {

            Mosi.setText("困难模式");;
        }
    }
}
