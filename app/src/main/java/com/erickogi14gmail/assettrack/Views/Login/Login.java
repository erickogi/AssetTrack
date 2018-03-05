package com.erickogi14gmail.assettrack.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.erickogi14gmail.assettrack.Data.PrefManager;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Views.MainActivity;

public class Login extends AppCompatActivity {

    private EditText edtID,edtPassword;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         prefManager=new PrefManager(Login.this);
        if(prefManager.isLoggedIn()){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
        edtPassword=findViewById(R.id.input_password);
        edtID=findViewById(R.id.input_id);


    }

    public void loginClicked(View view) {
        if(edtID.getText().toString().isEmpty()||edtID.getText().toString().equals("")){
            edtID.setError("Required");
            return;
        }
        if(edtPassword.getText().toString().isEmpty()||edtPassword.getText().toString().equals("")){
            edtPassword.setError("Required");
            return;
        }


        prefManager.setIsLoggedIn(true);
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
    }
}
