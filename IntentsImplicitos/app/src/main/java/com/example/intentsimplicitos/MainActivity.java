package com.example.intentsimplicitos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFotoCamara;

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

        ivFotoCamara = findViewById(R.id.ivFotoCamara);
    }

    //ActivityResult para abrir la camara
    private final ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    ivFotoCamara.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(this, "No se ha hecho ninguna foto", Toast.LENGTH_SHORT).show();
                }
            }
    );

    //ActivityResult para abrir la galeria
    private final ActivityResultLauncher<Intent> galeriaLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imagenSeleccionada = result.getData().getData();
                    if(imagenSeleccionada != null) {
                        ivFotoCamara.setImageURI(imagenSeleccionada);
                    } else {
                        Toast.makeText(this, "No se ha seleccionado ninguna foto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> contenidoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imagenSeleccionada = result.getData().getData();
                    if(imagenSeleccionada != null) {
                        ivFotoCamara.setImageURI(imagenSeleccionada);
                    } else {
                        Toast.makeText(this, "No se ha seleccionado ninguna foto del almacenamiento", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show();
                }
            }
    );

    public void onOpenCamaraClick(View v) {
        Intent intentHacerFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            camaraLauncher.launch(intentHacerFoto);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "No se ha encontrado una aplicación para la camara", Toast.LENGTH_SHORT).show();
        }
    }

    public void onOpenGaleriaClick(View v) {
        Intent intentAbrirGaleria = new Intent(MediaStore.ACTION_PICK_IMAGES);

        try {
            galeriaLauncher.launch(intentAbrirGaleria);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se ha encontrado una aplicación para la galeria", Toast.LENGTH_SHORT).show();
        }
    }

    public void onOpenAlmacenamientoClick(View v) {
        Intent intentAbrirAlmacenamiento = new Intent(Intent.ACTION_GET_CONTENT);

        try {
            contenidoLauncher.launch(intentAbrirAlmacenamiento);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No se ha encontrado una aplicación para seleccionar imagenes", Toast.LENGTH_SHORT).show();
        }
    }
}