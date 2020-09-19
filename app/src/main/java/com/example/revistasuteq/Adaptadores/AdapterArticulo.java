package com.example.revistasuteq.Adaptadores;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.Model.Articulo;
import com.example.revistasuteq.Model.Revista;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapterArticulo extends RecyclerView.Adapter<AdapterArticulo.ViewHolder> {

    private Context Ctx;
    private List<Articulo> lstArticulos;
    private OnItemClickListener listener;


    public AdapterArticulo(Context mCtx, List<Articulo> articulos, OnItemClickListener listener) {
        this.lstArticulos = articulos;
        Ctx = mCtx;
        this.listener = listener;
    }
    @NonNull
    @Override
    public AdapterArticulo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.articulos, null);
        return new AdapterArticulo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articulo articulo = lstArticulos.get(position);
        holder.section.setText(articulo.getSection());
        holder.title.setText(articulo.getTitle());
        holder.date.setText(articulo.getDate());
        holder.bind(articulo.getSectionid(),articulo.getUrl(),articulo.getDescripcion(),articulo.getTitle(), articulo.getDoi(),articulo.getKeywords(), articulo.getAuthors(),listener);
    }

    @Override
    public int getItemCount() {
        return lstArticulos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView section;
        TextView title;
        TextView date;
        TextView sectionid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            section = itemView.findViewById(R.id.txt_section_article);
            title = itemView.findViewById(R.id.txt_titulo_article);
            date = itemView.findViewById(R.id.txt_autores_article);
            sectionid = itemView.findViewById(R.id.txt_id_article);
        }
        public void bind(final String name, final String url, final String descripcion, final String title, final String doi, final String keywords, final String authors, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name, url, descripcion, title, doi,keywords, authors, getAdapterPosition());

                }
            });

        }


    }
    public  interface OnItemClickListener{
        void onItemClick(String name,String url,String descripcion, String title, String doi, String keywords,String authors, int position);

    }
}
