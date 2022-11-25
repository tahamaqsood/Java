package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView redirect = (TextView) findViewById(R.id.login);

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });

        EditText name = findViewById(R.id.username);
        EditText mail = findViewById(R.id.email);
        EditText pass = findViewById(R.id.password);
        EditText cpass = findViewById(R.id.confirm_password);

        Button submit = findViewById(R.id.signup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String fmail = mail.getText().toString();
                String fpass = pass.getText().toString();
                String fcpass = cpass.getText().toString();

                String URL = "http://192.168.0.105:80/java/signup.php";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("ok")){
                            Toast.makeText(MainActivity.this, "You're successfully signed up.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity3.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(MainActivity.this, "We're sorry, But something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, error -> {
                    Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                })
                {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("username",fname);
                        params.put("email",fmail);
                        params.put("password",fpass);
                        params.put("confirm_password",fcpass);

                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });



//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String fname = name.getText().toString();
//                String fmail = mail.getText().toString();
//                String fpass = pass.getText().toString();
//                String fcpass = cpass.getText().toString();
//
//
//                String url = "http://192.168.0.105:80/java/signup.php";
//
//                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                       Toast.makeText(MainActivity.this,"You're successfully signed up.",Toast.LENGTH_LONG).show();
//
//                       Intent i = new Intent(getApplicationContext(),MainActivity2.class);
//                       startActivity(i);
//                    }
//                }, error -> {
//                    Toast.makeText(MainActivity.this,"" + error , Toast.LENGTH_LONG).show();
//                }) {
//
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("username", fname);
//                        params.put("email", fmail);
//                        params.put("password", fpass);
//                        params.put("confirm_password", fcpass);
//                        return params;
//                    }
//                };
//                requestQueue.add(stringRequest);
//            }
//        });



    }

}




