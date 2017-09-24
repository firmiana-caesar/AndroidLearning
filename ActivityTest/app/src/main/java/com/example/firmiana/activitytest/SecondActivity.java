package com.example.firmiana.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*
        Intent intent = getIntent();
        String data = intent.getStringExtra("extra_fucker");
        Toast.makeText(SecondActivity.this, data, Toast.LENGTH_SHORT).show();
        */

        //定义一个button
        Button button_2 = (Button) findViewById(R.id.button_2);
        //为button设置监听器
        button_2.setOnClickListener(new View.OnClickListener(){
            @Override
            //重写onClick方法，让其以确定的大小显示定好的文本
            public void onClick(View v){
                Toast.makeText(SecondActivity.this, "now you will see my website",
                        Toast.LENGTH_SHORT).show();

                //使用隐式Intent打开我的博客
                String my_website = "http://www.firmiana-cae.cn";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(my_website));
                startActivity(intent);
            }
        });

        //定义一个拨打电话的button
        Button button_cell = (Button) findViewById(R.id.button_cell);
        //为该按钮添加监听器
        button_cell.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10000"));
                startActivity(intent);
            }
        });

        //定义一个返回数据的button
        Button button_return = (Button) findViewById(R.id.button_return);
        //为该按钮添加监听器
        button_return.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.putExtra("data_return", "fuck you muther fucker");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
