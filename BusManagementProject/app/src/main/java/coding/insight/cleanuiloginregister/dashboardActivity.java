package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class dashboardActivity extends AppCompatActivity {
        ListView listView, listView2, listView3;
        Button submit;
        CardView hiderCard_code, mainCard_code;
        TextView showInCardFROM, showInCardTO, showInCardBUS;
        RecyclerView recyclerView_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
          changeStatusBarColor();


        listView = findViewById(R.id.listViewFrom);
        listView2 = findViewById(R.id.listViewTo);
        listView3 = findViewById(R.id.listViewBus);
        showInCardFROM = findViewById(R.id.cardIDFROM);
        showInCardTO = findViewById(R.id.cardIDTO);
        showInCardBUS = findViewById(R.id.cardIDBUS);
        hiderCard_code = findViewById(R.id.hiderCard);
        mainCard_code = findViewById(R.id.mainCard);
//        recyclerView_code = findViewById(R.id.recyclerView);


        hiderCard_code.setVisibility(View.INVISIBLE);
        mainCard_code.setVisibility(View.INVISIBLE);

        EditText c_loc = findViewById(R.id.c_location);
        EditText d_loc = findViewById(R.id.d_location);

        submit = findViewById(R.id.cirLoginButton);

        submit.setOnClickListener(view -> {
            String loc_c = c_loc.getText().toString();
            String loc_d = d_loc.getText().toString();

            hiderCard_code.setVisibility(View.VISIBLE);
            mainCard_code.setVisibility(View.VISIBLE);

            getJSON("http://192.168.100.128/java/getSpecifiedData.php?From=" + loc_c + "&To=" + loc_d);
        });
    }


    private void getJSON(final String urlWebService) {

        @SuppressLint("StaticFieldLeak")
        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
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
            showInCardFROM.setText(From[i]);
        }

//        For get text for To
        ArrayAdapter<String> FromAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, From);
        listView.setAdapter(FromAdapter);

        JSONArray jsonArrayTo = new JSONArray(json);
        String[] To = new String[jsonArrayTo.length()];
        for (int i = 0; i < jsonArrayTo.length(); i++) {
            JSONObject obj = jsonArrayTo.getJSONObject(i);

            To[i] = obj.getString("Place_To");
            showInCardTO.setText(To[i]);
        }
        ArrayAdapter<String> ToAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, To);
        listView2.setAdapter(ToAdapter);


//        To get text of Busses

        JSONArray jsonArrayBus = new JSONArray(json);
        String[] bus = new String[jsonArrayBus.length()];
        for (int i = 0; i < jsonArrayBus.length(); i++) {
            JSONObject obj = jsonArrayBus.getJSONObject(i);

            bus[i] = obj.getString("Busses");
            showInCardBUS.setText(bus[i]);
        }
        ArrayAdapter<String> BusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bus);
        listView3.setAdapter(BusAdapter);

    }

    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.TRANSPARENT);
        window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
    }



    }

