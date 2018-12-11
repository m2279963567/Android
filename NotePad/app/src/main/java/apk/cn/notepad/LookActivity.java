package apk.cn.notepad;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LookActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        ExitApplication.getInstance().addActivity(this);



        //按钮点击事件
        Button backBtn = (Button) findViewById(R.id.backLook);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LookActivity.this.finish();
            }
        });

        //获取控件
        TextView tvDate = (TextView) findViewById(R.id.date);
        TextView tvContext = (TextView) findViewById(R.id.context);
        //获取信息
        String date = getIntent().getStringExtra("ItemDate");
        String context = getIntent().getStringExtra("ItemText");
        //显示信息
        tvContext.setText(context);
        tvDate.setText("时间："+date);

    }

}
