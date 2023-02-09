package coding.insight.cleanuiloginregister;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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


public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();


        EditText name = findViewById(R.id.editTextName);
        EditText mail = findViewById(R.id.editTextEmail);
        EditText pass = findViewById(R.id.editTextMobile);
        EditText cpass = findViewById(R.id.editTextPassword);

        Button submit = findViewById(R.id.cirRegisterButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String fmail = mail.getText().toString();
                String fpass = pass.getText().toString();
                String fcpass = cpass.getText().toString();

                String URL = "http://192.168.100.128/java/signup.php";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("ok")){
                            Toast.makeText(RegisterActivity.this, "You're successfully signed up.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(RegisterActivity.this, "We're sorry, But something went wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, error -> {
                    Toast.makeText(RegisterActivity.this, "Something went wrong." + error, Toast.LENGTH_SHORT).show();
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
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }



}
