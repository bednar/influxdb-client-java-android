package com.influxdb.influxdb_client_java_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.domain.HealthCheck;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text_view);
        new AsyncHealthCheck().execute();
    }

    private class AsyncHealthCheck extends AsyncTask<Void, Void, HealthCheck> {
        @Override
        protected HealthCheck doInBackground(Void... voids) {
            InfluxDBClient client = InfluxDBClientFactory.create("http://10.100.0.103:9999",
                    "my-user", "my-token".toCharArray());

            return client.health();
        }

        @Override
        protected void onPostExecute(HealthCheck healthCheck) {
            text.setText(String.format("InfluxDB health: %s", healthCheck));
        }
    }
}
