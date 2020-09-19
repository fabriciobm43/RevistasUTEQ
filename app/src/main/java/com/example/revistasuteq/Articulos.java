package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import com.example.revistasuteq.Adaptadores.AdapterArticulo;
import com.example.revistasuteq.Adaptadores.AdapterRevista;
import com.example.revistasuteq.Model.Articulo;
import com.example.revistasuteq.Model.Revista;
import com.example.revistasuteq.WebService.Asynchtask;
import com.example.revistasuteq.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Articulos extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;
    List<Articulo> articulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);

        Bundle dato= this.getIntent().getExtras();
        String id=dato.getString("ids");
        //Toast toast1 = Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT);
        //toast1.show();

        articulos = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rec_articulos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id,
                datos, Articulos.this, Articulos.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlista=new JSONArray(result);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject articulo=JSONlista.getJSONObject(i);
            articulos.add(new Articulo(
                    "Seccion: "+articulo.getString("section"),
                    articulo.getString("title"),
                    "Año publicación: "+ articulo.getString("date_published"),
                    articulo.getString("section_id"),
                    direccion(articulo.getString("galeys"),0),
                    articulo.getString("abstract"),
                    articulo.getString("doi"),
                    keywords(articulo.getString("keywords")),
                    authors(articulo.getString("authors"))));
        }

        AdapterArticulo adapter=new AdapterArticulo(Articulos.this, articulos, new AdapterArticulo.OnItemClickListener() {
            @Override
            public void onItemClick(String name,String url,String descripcion, String title, String doi, String keywords, String authors, int position) {
                Intent intent=new Intent(Articulos.this, Detalle.class);
                intent.putExtra("id", name);
                intent.putExtra("url",url);
                intent.putExtra("abs", descripcion);
                intent.putExtra("title", title);
                intent.putExtra("doi", doi);
                intent.putExtra("key", keywords);
                intent.putExtra("aut",authors);
                startActivity(intent);
                //Uri uri= Uri.parse(url);
                //Intent intent1=new Intent(Intent.ACTION_VIEW,uri);
                //startActivity(intent1);
            }
        });
        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
    }

    public String direccion(String url, int index) throws JSONException{
        String aux="";
        JSONArray JSONlista=new JSONArray(url);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject articulo=JSONlista.getJSONObject(i);
            if(index==0){
                aux=articulo.getString("UrlViewGalley");
                return aux;
            }else{
                aux=articulo.getString("UrlViewGalley");
                return aux;
            }
        }
        return aux;
    }
    public String keywords(String url) throws JSONException{
        String aux="";
        JSONArray JSONlista=new JSONArray(url);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject articulo=JSONlista.getJSONObject(i);
            if(i==0){
                aux=articulo.getString("keyword");
                return aux;
            }else{
                aux= articulo.getString("keyword") + ", " + aux;
                return aux;
            }
        }
        return aux;
    }
    public String authors(String url) throws JSONException{
        String aux="";
        JSONArray JSONlista=new JSONArray(url);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject articulo=JSONlista.getJSONObject(i);
            if(i==0){
                aux=articulo.getString("nombres");
                return aux;
            }else{
                aux= articulo.getString("nombres") + ", " + aux;
                return aux;
            }
        }
        return aux;
    }
}