package hu.petrik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Varos> cities = new ArrayList<>();
    private String base_url = "https://retoolapi.dev/xRxTQq/varosok";


    private Button vissza;
    private TextView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        RequestTask task = new RequestTask(base_url, "GET");
        task.execute();

        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyIntent = new Intent(ListActivity.this, MainActivity.class);
                ListActivity.this.startActivity(MyIntent);
            }
        });

    }

    private void init(){
        vissza = findViewById(R.id.vissza);
        lista = findViewById(R.id.jason);
    }

    private class RequestTask extends AsyncTask<Void, Void, Response> {
        String requestUrl;
        String requestType;
        String requestParams;

        public RequestTask(String requestUrl, String requestType, String requestParams) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
            this.requestParams = requestParams;
        }

        public RequestTask(String requestUrl, String requestType) {
            this.requestUrl = requestUrl;
            this.requestType = requestType;
        }

        @Override
        protected Response doInBackground(Void... voids) {
            Response response = null;
            try {
                switch (requestType) {
                    case "GET":
                        response = RequestHandler.get(requestUrl);
                        break;
                    case "POST":
                        response = RequestHandler.post(requestUrl, requestParams);
                        break;
                    case "PUT":
                        response = RequestHandler.put(requestUrl, requestParams);
                        break;
                    case "DELETE":
                        response = RequestHandler.delete(requestUrl + "/" + requestParams);
                        break;
                }

            } catch (IOException e) {
                Toast.makeText(ListActivity.this,
                        e.toString(), Toast.LENGTH_SHORT).show();
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Response response) {
            super.onPostExecute(response);

            Gson converter = new Gson();
            if (response.getResponseCode() >= 400) {
                Toast.makeText(ListActivity.this,
                        "Hiba történt a kérés feldolgozása során", Toast.LENGTH_SHORT).show();
                Log.d("onPostExecuteError: ", response.getContent());
            }
            switch (requestType) {
                case "GET":
                    Varos[] varosArray = converter.fromJson(response.getContent(), Varos[].class);
                    cities.clear();
                    cities.addAll(Arrays.asList(varosArray));
                    break;
                case "POST":
                    Varos varosok = converter.fromJson(response.getContent(), Varos.class);
                    cities.add(0, varosok);

                    break;
                case "PUT":
                    Varos updateVaros = converter.fromJson(response.getContent(), Varos.class);
                    cities.replaceAll(varos1 -> varos1.getId() == updateVaros.getId() ? updateVaros : varos1);

                    break;

            }
        }
    }
}