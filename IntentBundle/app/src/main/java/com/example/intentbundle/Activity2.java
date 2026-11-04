package com.example.intentbundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private TextView tvNombreRecibido;
    private TextView tvEdadRecibida;
    private ListView lvListaRecibida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNombreRecibido = findViewById(R.id.tvNombreRecibido);
        tvEdadRecibida = findViewById(R.id.tvEdadRecibida);
        lvListaRecibida = findViewById(R.id.lvListaRecibida);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        if(extras != null) {
            String nombre = extras.getString("Nombre");
            String edad = extras.getString("Edad");
            ArrayList<String> lista = extras.getStringArrayList("Lista");

            tvNombreRecibido.setText(nombre);
            tvEdadRecibida.setText(edad);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
            lvListaRecibida.setAdapter(adapter);
        }

    }
}