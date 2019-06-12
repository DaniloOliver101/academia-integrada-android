package com.example.webservicesapi;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ExecutionException;

public class TreinosActivity extends AppCompatActivity {
    TextView nome;
    Button btBuscar;
    ListView listViewTreinos;
    String pEmail;

    List<Treinos> listaTreinosAPI;
    ArrayList<String> listaTreinosAlunos = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    Bundle params;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treinos);

        nome = findViewById(R.id.textViewNome);
        btBuscar = findViewById(R.id.btBuscar);
        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                int strId = params.getInt("id");
                String strNome = params.getString("name");
                String strEmail = params.getString("email");
                String strProfile = params.getString("profile");
                pEmail = strEmail;

                nome.setText(String.format("Id: %s Nome: %s Email: %s", Integer.toString(strId), strNome, strEmail));
            }
        }
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("LogTReinos","Botão Buscar Clicado");
                Log.v("LogEmail",pEmail);

                try {
                    listaTreinosAPI = new HttpService(pEmail).execute().get();
                    listaTreinosAlunos.clear();
                    for (Treinos treinos : listaTreinosAPI){
                        listaTreinosAlunos.add(treinos.getTreino());

                    }
                    adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,listaTreinosAlunos
                    );
                    listViewTreinos.setAdapter(adapter);
                    Toast.makeText(getApplicationContext(),
                            listaTreinosAlunos.size() + " registros recuperado(s)",
                            Toast.LENGTH_LONG).show();
//                    listViewTreinos.setVisibility(View.VISIBLE);


                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (ExecutionException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
