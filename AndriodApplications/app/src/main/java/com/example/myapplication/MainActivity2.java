package com.example.myapplication;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView autologin = findViewById(R.id.linktosignup);

        autologin.setPaintFlags(autologin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        autologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        EditText name = findViewById(R.id.lgusername);
        EditText pass = findViewById(R.id.lgpassword);
        Button submit = findViewById(R.id.login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String fpass = pass.getText().toString();

                String Url = "http://192.168.0.105:80/java/login.php";


                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equals("ok")){
                            Toast.makeText(MainActivity2.this,"You're successfully logged in.",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity2.this,"Incorrect login password." + response,Toast.LENGTH_LONG).show();
                        }



                    }
                }, error -> {
                    Toast.makeText(MainActivity2.this,"" + error , Toast.LENGTH_LONG).show();
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("username", fname);
                        params.put("password", fpass);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });



}
}