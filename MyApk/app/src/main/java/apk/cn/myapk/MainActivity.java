package apk.cn.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //实现全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //将activity加入
        ExitApplication.getInstance().addActivity(this);

        //接收传递的值，并赋值给tvName显示
        String user = getIntent().getStringExtra("user");
        TextView tvName = (TextView) findViewById(R.id.loginName);
        tvName.setText(user);

        //实现按钮点击事件
        Button backBtn = (Button) findViewById(R.id.backBtn);
        Button closeBtn = (Button) findViewById(R.id.closeBtn);
        backBtn.setOnClickListener(this);
        closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.backBtn:
                Intent login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login);
                Toast.makeText(this,"返回登陆界面",Toast.LENGTH_SHORT).show();
                break;
            case R.id.closeBtn:
                ExitApplication.getInstance().exit(this);
                break;
        }
    }
}
