package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class PlayOUTActivity extends AppCompatActivity {
private TextView ptime;
private TextView busu;
    private TextView zjptime;
    private TextView zjbusu;
    String pid;
    String xptime;
    String xbusu;
    String xzjptime;
    String xzjbusu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_out);
        ptime=findViewById(R.id.ptime);
        busu=findViewById(R.id.busu);
        zjptime=findViewById(R.id.zjptime);
        zjbusu=findViewById(R.id.zjbusu);
        Intent intent=getIntent();
        pid=intent.getStringExtra("pid");
//        xzjbusu=intent.getStringExtra("zjbusu");
//        xzjptime=intent.getStringExtra("zjptime");
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    String   httpUrl = Fwqdizhi.dizhi+ "/getbusu/"+pid;
                    Log.e("ss","ss"+httpUrl);
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content= EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=1;
                        msg.obj=content;
                        handler.sendMessage(msg);


                    }
                }catch (Exception e){
                    Message msg=new Message();
                    msg.what=404;
                    handler.sendMessage(msg);
                }
            }
        };
        t.start();
        Button button=findViewById(R.id.but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PlayOUTActivity.this,MainActivity.class);
                finish();
                startActivity(intent1);
            }
        });
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 处理消息时需要知道是成功的消息还是失败的消息
            switch (msg.what) {
                case 1:

                    String zhi=msg.obj.toString();
                    Log.e("结果",zhi);

                    xbusu=zhi.substring(0,zhi.indexOf("a"));
                    xptime=zhi.substring(zhi.indexOf("a")+1,zhi.indexOf("b"));
                    xzjbusu=zhi.substring(zhi.indexOf("b")+1,zhi.indexOf("c"));
                    xzjptime=zhi.substring(zhi.indexOf("c")+1,zhi.length());

                    busu.setText(xbusu);
                  zjbusu.setText(xzjbusu);
                  ptime.setText(xptime);
                  zjptime.setText(xzjptime);
                    break;
                case 404:
                    Toast.makeText(PlayOUTActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();

                    break;
            }

        }
    };
}
