package apk.cn.notepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ExitApplication.getInstance().addActivity(this);

        //按钮点击事件
        Button create = (Button) findViewById(R.id.create);
        Button backNote = (Button) findViewById(R.id.backNote);
        create.setOnClickListener(this);
        backNote.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.create:
                //保存事件
                createNote();
                //返回主界面
                Intent main = new Intent(AddActivity.this,MainActivity.class);
                startActivity(main);
                break;

            case R.id.backNote:
                //返回
                finish();
                break;
        }
    }

    //新建笔记
    public void createNote(){
        //生成时间
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String date = format.format(today);
        //获取内容
        EditText contextTv = (EditText) findViewById(R.id.createNoteContext);
        String context = contextTv.getText().toString();

        //将其写入文件

        save(date,context);


    }

    //保存信息至文件
    public void save(String date,String context) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //开打data文件
            out = openFileOutput("notepad", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(context+"-"+date);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
