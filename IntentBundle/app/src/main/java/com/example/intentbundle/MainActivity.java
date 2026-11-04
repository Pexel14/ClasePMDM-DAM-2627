package com.example.intentbundle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre;
    private EditText etEdad;
    private Button btnEnviarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        btnEnviarDatos = findViewById(R.id.btnEnviarDatos);

        btnEnviarDatos.setOnClickListener(v -> enviarDatos());

    }

    public void enviarDatos() {
        String nombre = etNombre.getText().toString();
        String edad = etEdad.getText().toString();

        ArrayList<String> lista = new ArrayList<>();

        lista.add("Elemento 1");
        lista.add("Elemento 2");

        Intent intent = new Intent(this, Activity2.class);

        Bundle extras = new Bundle();
        extras.putString("Nombre", nombre);
        extras.putString("Edad", edad);
        extras.putStringArrayList("Lista", lista);

        intent.putExtras(extras);
        startActivity(intent);

    }

}