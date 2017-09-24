package com.example.firmiana.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        //定义一个button
        Button button_1 = (Button) findViewById(R.id.button_1);
        //为button设置监听器
        button_1.setOnClickListener(new View.OnClickListener(){
            @Override
            //重写onClick方法，让其以确定的大小显示定好的文本
            public void onClick(View v){
                Toast.makeText(FirstActivity.this, "go to second activity, mother fucker",
                        Toast.LENGTH_SHORT).show();
                //显式使用Intent
                //Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //startActivity(intent);

                //隐式使用Intent
                //Intent intent = new Intent("com.example.firmiana.activitytest.ACTION_START");
                //startActivity(intent);

                //隐式intent添加category
                //Intent intent = new Intent("com.example.firmiana.activitytest.ACTION_START");
                //intent.addCategory("com.example.firmiana.activitytest.MY_CATEGORY");
                //startActivity(intent);

                //使用显示Intent启动下一个活动并且传递一个字符串
                //String data = "hello mother fucker";
                //Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                //intent.putExtra("extra_fucker", data);
                //startActivity(intent);

                //使用Intent传递数据
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_item:
                Toast.makeText(this, "you add one ass hole", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "you remove one ass hole", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:if (resultCode == RESULT_OK){
                String returnedData = data.getStringExtra("data_return");
                Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
