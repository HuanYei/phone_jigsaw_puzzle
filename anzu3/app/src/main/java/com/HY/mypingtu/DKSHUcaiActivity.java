package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DKSHUcaiActivity extends AppCompatActivity implements View.OnClickListener {
private Button yantu;
private Button tupianzhox;
private Button qux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dkshucai);
        yantu=findViewById(R.id.yantu);
        tupianzhox=findViewById(R.id.tupiandaquan);
        qux=findViewById(R.id.fanhui);
        yantu.setOnClickListener(this);
        tupianzhox.setOnClickListener(this);
        qux.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yantu:
                Intent intent7 = getPackageManager().getLaunchIntentForPackage("com.haotu.lbspht");
                if (intent7 != null) {
                    intent7.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent7);
                }else {
                    Toast.makeText(DKSHUcaiActivity.this,"手机未安装素材中心，即将跳转下载素材中心",Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("https://www.lanzoui.com/iyIqvh8os8f?p");
                    Intent intent8 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent8);
                }
                break;
            case R.id.tupiandaquan:
                Intent intent10 = getPackageManager().getLaunchIntentForPackage("com.app.jdxsxp");
                if (intent10 != null) {
                    intent10.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent10);
                }else {
                    Toast.makeText(DKSHUcaiActivity.this,"手机未安装图片大全，即将跳转下载图片大全",Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("https://sj.qq.com/myapp/detail.htm?apkName=com.app.jdxsxp");
                    Intent intent11 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent11);
                }
                break;
            case R.id.fanhui:
                finish();
                break;
        }
    }

}
