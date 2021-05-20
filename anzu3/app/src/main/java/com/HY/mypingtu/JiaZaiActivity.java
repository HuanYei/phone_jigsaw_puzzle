package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.jar.JarEntry;

public class JiaZaiActivity extends AppCompatActivity {
    private Timer timer;
    private TextView ppmtext;
    String pid;
    String ppm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jia_zai);
        Intent intent=getIntent();
        ppm=intent.getStringExtra("ppm");
        pid=intent.getStringExtra("pid");

        ppmtext=findViewById(R.id.ppm);
        ppmtext.setText(ppm);


        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String   httpUrl = Fwqdizhi.dizhi+ "/playppz/"+pid;
                    Log.e("ss","网站"+httpUrl);
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content=EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=1;
                        msg.obj=content;
                        mHandler.sendMessage(msg);
                        Log.e("ppm上传成功","dz");
                    }
                }catch (Exception e){
                    Message msg=new Message();
                    msg.what=404;
                    mHandler.sendMessage(msg);
                }
            }
        },0,200);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
// (2) 使用handler处理接收到的消息
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                if (msg.obj.toString().equals("1")){
                    timer.cancel();
                    finish();
                    Intent intent1=new Intent(JiaZaiActivity.this,Main18Activity.class);
                    intent1.putExtra("pid",pid);
                    startActivity(intent1);
                }
            }else if(msg.what == 404) {
                Toast.makeText(JiaZaiActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(JiaZaiActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
    };

    public void onClickCopy(View v) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText("嗨！朋友,快来跟我玩一局生死对决吧。" +
                "如已下载，请打开Jigsaw puzzle,输入匹配码"+ppm+"开始游戏。" +
                "未下载请点击 "+Fwqdizhi.dizhi+"/media");
        Toast.makeText(this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
    }
}
