package com.example.revistasuteq.Adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.Model.Revista;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapterRevista extends RecyclerView.Adapter<AdapterRevista.ViewHolder>  {


    private Context Ctx;
    private List<Revista> lstRevistas;
    private OnItemClickListener listener;


    public AdapterRevista(Context mCtx, List<Revista> revistas, OnItemClickListener listener) {
        this.lstRevistas = revistas;
        Ctx = mCtx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterRevista.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.revistas, null);
        return new AdapterRevista.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRevista.ViewHolder holder, int position) {
        Revista revista = lstRevistas.get(position);
        holder.nombre.setText(revista.getNombres());
        holder.descripcion.setText(revista.getDescripcion());
        Glide.with(Ctx).load(revista.getWebsite()).into(holder.imagen);
        holder.id.setText(revista.getId());
        holder.bind(revista.getId(),listener);
    }

    @Override
    public int getItemCount() {
        return lstRevistas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView descripcion;
        ImageView imagen;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_nombre);
            descripcion = itemView.findViewById(R.id.txt_descripcion);
            imagen = itemView.findViewById(R.id.imagen);
            id = itemView.findViewById(R.id.txt_id);
        }
        public void bind(final String name, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name, getAdapterPosition());
                }
            });

        }

    }
    public  interface OnItemClickListener{
        void onItemClick(String name, int position);

    }
}
