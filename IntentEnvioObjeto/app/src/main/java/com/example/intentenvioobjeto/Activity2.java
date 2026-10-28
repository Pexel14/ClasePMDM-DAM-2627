package com.example.intentenvioobjeto;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.intentenvioobjeto.model.Persona;

public class Activity2 extends AppCompatActivity {

    private TextView tvPersona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Persona persona = (Persona) getIntent().getSerializableExtra("Persona");

        tvPersona = findViewById(R.id.tvPersona);

        if(persona != null) {
            tvPersona.setText("Nombre: " + persona.getNombre() + " - Edad: " + persona.getEdad());
        }
    }
}