package apk.cn.notepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitApplication.getInstance().addActivity(this);

        //加载内容
        initNote();

        //按钮点击事件
        Button createNote = (Button) findViewById(R.id.createNote);
        Button outNote = (Button) findViewById(R.id.outNote);
        createNote.setOnClickListener(this);
        outNote.setOnClickListener(this);

        //获取选项内容，跳转查看页面
        lookNote();
    }

    //动态载入备忘录内容
    public void initNote(){
        ListView list = (ListView) findViewById(R.id.note_item);
        //生成动态数组，并且转载数据
        ArrayList<HashMap<String, String>> mylist = load();
        if(mylist.size()==0){
            Toast.makeText(this,"您还没有备忘记录哦，赶紧创建吧",Toast.LENGTH_SHORT).show();
        }
        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
                mylist,//数据来源
                R.layout.my_listitem,//ListItem的XML实现
                //动态数组与ListItem对应的子项
                new String[] {"date", "context"},
                //ListItem的XML文件里面的两个TextView ID
                new int[] {R.id.ItemDate,R.id.ItemText});
        //添加并且显示
        list.setAdapter(mSchedule);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.createNote:
                //新建笔记，跳转至编辑界面
                Intent create = new Intent(MainActivity.this,AddActivity.class);
                startActivity(create);
                break;
            case R.id.outNote:
                //退出备忘录程序
                ExitApplication.getInstance().exit(this);
                break;
        }
    }

    //加载文件中的内容，并以集合返回
    public ArrayList<HashMap<String,String>> load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        String strFile = "notepad";
        try {
            //文件不存在，创建文件
            if(fileIsExists(strFile)){
                in = openFileInput(strFile);
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                ArrayList<HashMap<String,String>> itemList = new ArrayList<HashMap<String, String>>();
                while ((line = reader.readLine()) != null) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String temp[] = line.split("-");
                    map.put("context",temp[0]);
                    map.put("date",temp[1]);
                    itemList.add(map);
                }
                return itemList;
            }else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                FileOutputStream out = openFileOutput(strFile, Context.MODE_APPEND);
                return true;
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    //获取选项内容，跳转查看页面
    public void lookNote(){
        //点击list选项获取内容
        ListView list = (ListView) findViewById(R.id.note_item);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView adapter,View view,int position,long id){
                TextView ItemText = (TextView) view.findViewById(R.id.ItemText);//该句可以得到LISTVIEW中的相应数据
                TextView ItemDate = (TextView) view.findViewById(R.id.ItemDate);


                Intent noteL = new Intent(MainActivity.this,LookActivity.class);
                noteL.putExtra("ItemText",ItemText.getText().toString());
                noteL.putExtra("ItemDate",ItemDate.getText().toString());
                startActivity(noteL);
//                String playerChanged = ItemText.getText().toString() + position;
//                Toast.makeText(MainActivity.this,playerChanged, Toast.LENGTH_SHORT).show();
//                ItemText.setText("111111111111");//改句可以修改相应Item数据的内容（注意:一刷新就没有了）
//                Log.v("TAG",position+ "");
            }
        });

    }

}
