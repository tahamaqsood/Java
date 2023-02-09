package coding.insight.cleanuiloginregister;

import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;


public class dashboardActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    Button submit;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        changeStatusBarColor();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);



        EditText c_loc = findViewById(R.id.c_location);
        EditText d_loc = findViewById(R.id.d_location);

        submit = findViewById(R.id.cirLoginButton);


        submit.setOnClickListener(view -> {
            viewItems.clear();
            String loc_c = c_loc.getText().toString();
            String loc_d = d_loc.getText().toString();


            getJSON("http://192.168.100.128/java/getSpecifiedData.php?From=" + loc_c + "&To=" + loc_d);

        });

    }

    private void getJSON(final String urlWebService) {

        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mAdapter = new RecyclerAdapter(dashboardActivity.this, viewItems);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json).append("\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {

        // To get text for From
        JSONArray jsonArrayFrom = new JSONArray(json);
        String[] From = new String[jsonArrayFrom.length()];
        for (int i = 0; i < jsonArrayFrom.length(); i++) {
            JSONObject obj = jsonArrayFrom.getJSONObject(i);

            From[i] = obj.getString("Place_From");
            String To = obj.getString("Place_To");
            String Bus = obj.getString("Busses");


            Holidays holidays = new Holidays(From[i], To, Bus);
            viewItems.add(holidays);
        }


    }


    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
    }
}