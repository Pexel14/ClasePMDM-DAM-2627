package com.example.activityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvPersonaSeleccionada;
    private Button btnSeleccionarPersona;

    private ActivityResultLauncher<Intent> seleccionPersonaLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvPersonaSeleccionada = findViewById(R.id.tvPersonaSeleccionada);
        btnSeleccionarPersona = findViewById(R.id.btnSeleccionarPersona);

        seleccionPersonaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                        if(data != null) {
                            String persona = data.getStringExtra("PERSONA_SELECCIONADA");
                            tvPersonaSeleccionada.setText("Persona seleccionada: " + persona);
                        }

                    } else {
                        tvPersonaSeleccionada.setText("No se ha seleccionado a nadie");
                    }
                }
        );
    }

    public void seleccionarpersona(View view) {
        Intent intent = new Intent(this, SeleccionActivity.class);
        seleccionPersonaLauncher.launch(intent);
    }

}