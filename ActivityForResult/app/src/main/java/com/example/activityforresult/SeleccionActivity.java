package com.example.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.activityforresult.datos.DatosPersonas;

public class SeleccionActivity extends AppCompatActivity {

    private LinearLayout layoutBotones;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        layoutBotones = findViewById(R.id.layoutBotones);

        Button btnPersona;
        for (String persona : DatosPersonas.personas) {
            btnPersona = new Button(this);
            btnPersona.setText(persona);

            btnPersona.setOnClickListener(v -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("PERSONA_SELECCIONADA", persona);
                setResult(RESULT_OK, resultIntent);
                finish();
            });

            layoutBotones.addView(btnPersona);
        }

        btnCancelar = findViewById(R.id.btnCancelar);

        btnCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}