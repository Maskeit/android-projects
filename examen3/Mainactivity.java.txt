package com.maskeit.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;

import com.google.android.material.search.SearchBar;
import com.maskeit.weather.Interfaces.InterfaceApi;
import com.maskeit.weather.Models.MausamData;
import com.maskeit.weather.Models.main;
import com.maskeit.weather.Models.weather;
import com.maskeit.weather.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentdate = format.format(new Date());

        binding.date.setText(currentdate);
        fetchWeather("Manzanillo");

        binding.floatinActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ocultarTeclado();
                if(TextUtils.isEmpty(binding.searchCityEdittext.getText().toString())){
                    binding.searchCityEdittext.setError("Por favor ingrese una ciudad!");
                    return;
                }
                String CITY_NAME = binding.searchCityEdittext.getText().toString();
                fetchWeather(CITY_NAME);
            }
        });

    }


    //ocultar teclado despues de buscar
    void ocultarTeclado(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(binding.searchCityEdittext.getApplicationWindowToken(), 0);

    }

    void fetchWeather(String cityname){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceApi interfaceApi = retrofit.create(InterfaceApi.class);
        Call<MausamData> call = interfaceApi.getData(cityname, "2023daedd7419729aa899cfdc2fcd00f","metric");
        call.enqueue(new Callback<MausamData>() {
            @Override
            public void onResponse(Call<MausamData> call, Response<MausamData> response) {
                if(response.isSuccessful()){
                    MausamData mausamData = response.body();
                    main to = mausamData.getMain();
                    binding.mainTempValue.setText("Temp: "+ String.valueOf(to.getTemp()) + "\u2103");
                    binding.maxTempValue.setText("Max Temp: "+String.valueOf(to.getTemp_max()) + "\u2103");
                    binding.minTempValue.setText("Min Temp: "+String.valueOf(to.getTemp_min()) + "\u2103");
                    binding.pressureValue.setText("Presión: "+String.valueOf(to.getPressure()) + " hPa");
                    binding.humidityValue.setText("Humedad: "+String.valueOf(to.getHumidity()) + "%");

                    binding.cityName.setText("Ciudad: " + mausamData.getName());

                    List<weather> description = mausamData.getWeather();

                    for(weather data : description){
                        binding.description.setText("Tiempo: " + data.getDescription());
                    }
                }
            }

            @Override
            public void onFailure(Call<MausamData> call, Throwable t) {

            }
        });

    }


}