package com.codestown.whatsweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText cityEditText;
    Button getWeatherButton;
    TextView weatherTextView;
    String apiKey = "5366964becb9b94d4b68e01077745974";
    //String url = "api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
    public void getWeather(View view){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://api.openweathermap.org/data/2.5/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        Weatherapi weatherapi = retrofit.create(Weatherapi.class);
        Call<SerializableObjects> serializableObjectsCall =
                weatherapi.getweather(cityEditText.getText().
                        toString().trim(),apiKey);
        serializableObjectsCall.enqueue(new Callback<SerializableObjects>() {
            @Override
            public void onResponse(Call<SerializableObjects> call, Response<SerializableObjects> response) {
                if (response.code()==404){
                    Toast.makeText(MainActivity.this, "Invalid City Name", Toast.LENGTH_SHORT).show();
                }else if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }else{
                    SerializableObjects serializableObjects = response.body();
                    WeatherModal weatherModal = serializableObjects.getWeatherModal();
                    Double temp = weatherModal.getTemp();
                    int temperature = (int) (temp-273.15);
                    weatherTextView.setText(String.valueOf(temperature) + " C");

                }
            }

            @Override
            public void onFailure(Call<SerializableObjects> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

    }

    private void initview() {
        cityEditText = findViewById(R.id.cityEditText);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        weatherTextView = findViewById(R.id.weatherTextView);
    }
}