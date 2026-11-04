package com.example.intentsimplicitosweb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    public void onClickAbrirURL(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://universidadeuropea.com"));
        startActivity(intent);
    }

    public void onClickMarcar(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+34600909309319"));
        startActivity(intent);
    }

    public void onClickMandarEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String []{"uncorreocualquiera@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensaje que le vas a enviar por correo");

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Elige un remitente"));
        } else {
            Toast.makeText(this, "No se ha encontrado una app para el correo", Toast.LENGTH_SHORT).show();
        }
    }

    public void playVideo(View view) {
        VideoView vvVideo = findViewById(R.id.vvVideo);

        String uriPath = "android.resource://" + getPackageName() + "/" + R.raw.video;

        vvVideo.setVideoURI(Uri.parse(uriPath));
        vvVideo.start();
    }

}