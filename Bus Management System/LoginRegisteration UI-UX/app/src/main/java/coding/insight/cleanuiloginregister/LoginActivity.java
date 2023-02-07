package coding.insight.cleanuiloginregister;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);


    EditText name = findViewById(R.id.editTextEmail);
    EditText pass = findViewById(R.id.editTextPassword);
    Button submit = findViewById(R.id.cirLoginButton);

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String fname = name.getText().toString();
            String fpass = pass.getText().toString();
            String URL = "http://192.168.100.128/java/login.php";

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("ok")){
                        Toast.makeText(LoginActivity.this, "You're successfully logged on", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),dashboardActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(LoginActivity.this, "Login Credentials Are Not Correct!", Toast.LENGTH_SHORT).show();

                    }
                }
            },error -> {
                Toast.makeText(LoginActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }){
                @Override
                protected Map<String, String> getParams (){
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("username",fname);
                    params.put("password",fpass);
                    return params;

                }

            };
            requestQueue.add(stringRequest);

        }
    });


    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
