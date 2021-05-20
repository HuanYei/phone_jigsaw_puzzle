package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.Sql.DBDao;
import com.HY.Sql.PHB;
import com.HY.dao.ToastUtil;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private Button dlzc;
//    private TextView username;
//    private TextView usermoney;
//    protected void onRestart() {
//        super.onRestart();
//        username.setText(sp.getString("ptname","未登录"));
//        usermoney.setText("金币："+sp.getString("money","0000"));
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sp=getSharedPreferences("first",MODE_PRIVATE);
//        username=findViewById(R.id.username);
//        usermoney=findViewById(R.id.usermoney);
        ed=sp.edit();
        dlzc=findViewById(R.id.dlzc);
        dlzc.setOnClickListener(this);
        String pdjd="";
        if (sp.getBoolean("pd",true)){
            pdjd="切换到困难模式                         ";
        }else {
            pdjd="切换到简单模式                         ";
        }
//        username.setText(sp.getString("ptname","未登录"));
//        usermoney.setText("金币："+sp.getString("money","0000"));

        final String data[]={pdjd,"清空当前模式排行榜","关于拼图","联系作者 来图定制"};
        final ListView listView=findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);//新建并配置ArrayAapeter

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    if (sp.getBoolean("pd",true)){
                        ed.putBoolean("pd",false);
                        ed.commit();
                        data[0]="切换到简单模式";
                        Toast.makeText(Main3Activity.this,"已切换到困难模式",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        ed.putBoolean("pd",true);
                        ed.commit();
                        data[0]="切换到困难模式";
                        Toast.makeText(Main3Activity.this,"已切换到简单模式",Toast.LENGTH_SHORT).show();
                        finish();
                    }
//                    adapter.notifyDataSetChanged();刷新adapter
                }else if (position==1){
                    Toast.makeText(Main3Activity.this,"请长按清除数据",Toast.LENGTH_SHORT).show();


                }else if (position==2){
                    Intent intent=new Intent(Main3Activity.this,Main4Activity.class);
                    startActivity(intent);
                }else if (position==3){
                    try {
                        //第二种方式：可以跳转到添加好友，如果qq号是好友了，直接聊天
                        String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + "2630954066";//uin是发送过去的qq号码
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(Main3Activity.this,"请检查是否安装QQ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            boolean pdsc=false;
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    if (pdsc){
                        if (sp.getBoolean("pd",true)){
                            DBDao.getInstance().clearAll();
                            for(int i=1;i<=10;i++){
                                PHB phb=new PHB(0,i,"0000","00","000");
                                DBDao.getInstance().insert(phb);
                            }
                            Toast.makeText(Main3Activity.this,"已清空当前模式排行榜",Toast.LENGTH_LONG).show();
                        }else {
                            DBDao.getInstance().clearAll2();
                            for(int i=1;i<=10;i++){
                                PHB phb=new PHB(0,i,"0000","00","000");
                                DBDao.getInstance().insert2(phb);
                            }
                            Toast.makeText(Main3Activity.this,"已清空当前模式排行榜",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(Main3Activity.this,"请再次长按确定删除",Toast.LENGTH_LONG).show();
                        pdsc=true;
                    }

                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dlzc:
                Intent intent=new Intent(Main3Activity.this,LogActivity.class);
                startActivity(intent);
                break;

        }
    }
}
