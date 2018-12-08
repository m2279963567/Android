package apk.cn.myapk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实现无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //实现全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //将activity加入
        ExitApplication.getInstance().addActivity(this);

        //登陆按钮点击事件
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        //退出按钮点击事件
        Button closeBtn = (Button) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            //登陆按钮
            case R.id.loginBtn:
                login();
                break;

            //退出按钮
            case R.id.closeBtn:
                ExitApplication.getInstance().exit(this);
                break;
        }
    }

    //登陆按钮功能
    public void login(){
        EditText tvName = (EditText) findViewById(R.id.loginName);
        EditText tvPwd = (EditText) findViewById(R.id.loginPwd);
        String user = tvName.getText().toString().trim();
        String pwd =  tvPwd.getText().toString().trim();

        if(pwd.equals("admin")){
           //登陆成功，实现页面跳转
            Intent main = new Intent(LoginActivity.this,MainActivity.class);
            main.putExtra("user",user);
            startActivity(main);
        }
        else{
            if(user.length()==0 || pwd.length()==0){
                Toast.makeText(this,"用户名或密码输入不能为空！",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"用户名或密码输入错误！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

