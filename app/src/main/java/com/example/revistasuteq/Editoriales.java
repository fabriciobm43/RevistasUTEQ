package com.example.revistasuteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.revistasuteq.Adaptadores.AdapterEditorial;
import com.example.revistasuteq.Adaptadores.AdapterRevista;
import com.example.revistasuteq.Model.Editorial;
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

public class Editoriales extends AppCompatActivity implements Asynchtask {

    RecyclerView recyclerView;
    List<Editorial> editorials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editoriales);

        Bundle dato= this.getIntent().getExtras();
        String id=dato.getString("id");
        //Toast toast1 = Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT);
        //toast1.show();

        editorials = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rec_editoriales);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id,
                datos, Editoriales.this, Editoriales.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray JSONlista=new JSONArray(result);
        for (int i=0; i<JSONlista.length();i++){
            JSONObject editorial=JSONlista.getJSONObject(i);
            editorials.add(new Editorial(editorial.getString("issue_id"),"Vol. "+editorial.getString("volume"), "Num. "+editorial.getString("number"), editorial.getString("year"),editorial.getString("title"),editorial.getString("cover")));

        }
        AdapterEditorial adapter=new AdapterEditorial(Editoriales.this, editorials, new AdapterEditorial.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Intent intent=new Intent(Editoriales.this, Articulos.class);
                intent.putExtra("ids", name);
                startActivity(intent);
            }
        });
        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);

    }
}