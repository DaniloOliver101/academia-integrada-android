package com.example.webservicesapi;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HttpService extends AsyncTask<Void, Void, List<Treinos>> {
    private String stringUrl;

    public HttpService(){}
    public HttpService(String email) {
        this.stringUrl = "https://academia-web.herokuapp.com/apis/aluno/getTreino.php?email="+email;
        Log.d("url", this.stringUrl);
    }



    @Override
    protected List<Treinos> doInBackground(Void... voids) {

        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "UFT-8");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {

                resposta.append(scanner.nextLine());
            }
//            Log.v("LogResponse",resposta);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type treinosType = new TypeToken<ArrayList<Treinos>>() {}.getType();
        List<Treinos> listaTreinos;
        listaTreinos = new Gson().fromJson(resposta.toString(), treinosType);
        Log.v("LogGSONSTRING", resposta.toString());
        return listaTreinos;
    }
}