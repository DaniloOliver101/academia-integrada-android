package com.example.webservicesapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    String urlWebService = "http://academia-web.herokuapp.com/apis/login.php";
    LinearLayout grupoEntrada;
    EditText email;
    EditText senha;
    Button btEntrar;
    TextView nome;
    StringRequest stringRequest;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        Aluno aluno = new Aluno();

        ActionBar actionBar = getSupportActionBar();

        grupoEntrada = findViewById(R.id.grupoEntrada);
        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextPass);
        nome = findViewById(R.id.textViewNome);
        btEntrar = findViewById(R.id.btLogin);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                if(email.getText().length()==0){
                    email.setError("Campo Obrigatório");
                    email.requestFocus();
                    validado = false;
                } else if(senha.getText().length()==0){
                    senha.setError("Campo Obrigatório");
                    senha.requestFocus();
                    validado = false;
                }
                if(validado){


                    Toast.makeText(getApplicationContext(),"Validando dados por favor aguarde! ",Toast.LENGTH_SHORT).show();
                    validarLogin();
                }
            }
        });

    }
    private void validarLogin(){
            stringRequest = new StringRequest(Request.Method.POST, urlWebService, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("LogLogin", response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean isError = jsonObject.getBoolean("erro");
                        if(!isError){
                            Log.v("LogCorreto","Deu certo!");
                            int id;
                            id = jsonObject.getInt("id");
                            Log.v("LogId", Integer.toString(id));

                            String name = jsonObject.getString("name");
                            Log.v("LogName",name);

                            String userEmail = jsonObject.getString("email");

                            String profile = jsonObject.getString("profile");
                            Log.v("LogProfile",profile);

                            Intent treinos = new Intent(MainActivity.this,TreinosActivity.class);
//                            startActivity(intent);
                            String defaultProfile = "ALUNO";
                                if(jsonObject.getString("profile").equals(defaultProfile)){
                                    Log.v("LogAluno","Perfil Aluno");
                                    Bundle params = new Bundle();
                                    params.putInt("id", id);
                                    params.putString("name", name);
                                    params.putString("email",userEmail);
                                    params.putString("profile", profile);
                                    treinos.putExtras(params);
                                    startActivity(treinos);

                                } else {
                                    Log.v("LogRes","N é aluno");
                                    startActivity(treinos);


                                }
                        }
                    } catch (Exception e ){
                        Log.v("LogDeExcept", e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("LogError", error.getMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("email",email.getText().toString());
                    params.put("senha",senha.getText().toString());
                    return params;

                }
        };
        requestQueue.add(stringRequest);
    }


}