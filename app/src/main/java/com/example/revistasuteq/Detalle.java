package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Detalle extends AppCompatActivity {

    TextView titulo;
    Button btnpdf;
    TextView txt_doi, txt_keywords, txt_abs, txt_aut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle dato= this.getIntent().getExtras();
        String id=dato.getString("id");
        final String url=dato.getString("url");
        String descripcion=dato.getString("abs");
        String title=dato.getString("title");
        String doi= dato.getString("doi");
        String key=dato.getString("key");
        String aut=dato.getString("aut");
        //Toast toast1 = Toast.makeText(getApplicationContext(),title, Toast.LENGTH_SHORT);
        //toast1.show();

        titulo=findViewById(R.id.txt_title_detalle);
        titulo.setText(title);
        txt_doi=findViewById(R.id.txt_detalle_doi);
        txt_doi.setText("DOI: "+doi);
        btnpdf=findViewById(R.id.btn_pdf);
        txt_keywords=findViewById(R.id.txt_detalle_keywords);
        txt_keywords.setText("Palabras clave: "+key);
        txt_abs=findViewById(R.id.txt_detalle_abstract);
        txt_abs.setText(descripcion);
        txt_aut=findViewById(R.id.txt_detalle_authors);
        txt_aut.setText("Autores: "+aut);
        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }
}