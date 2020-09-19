package com.example.revistasuteq.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.revistasuteq.Model.Editorial;
import com.example.revistasuteq.Model.Revista;
import com.example.revistasuteq.R;

import java.util.List;

public class AdapterEditorial extends RecyclerView.Adapter<AdapterEditorial.ViewHolder> {

    private Context Ctx;
    private List<Editorial> lstEditorales;
    private OnItemClickListener listener;


    public AdapterEditorial(Context mCtx, List<Editorial> editorials, OnItemClickListener listener) {
        this.lstEditorales = editorials;
        Ctx = mCtx;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterEditorial.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.editoriales, null);
        return new AdapterEditorial.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Editorial editorial = lstEditorales.get(position);
        holder.issue_id.setText(editorial.getIssue_id());
        holder.volume.setText(editorial.getVolume());
        holder.number.setText(editorial.getNumber());
        holder.year.setText(editorial.getYear());
        holder.title.setText(editorial.getTitle());
        Glide.with(Ctx).load(editorial.getCover()).into(holder.cover);
        holder.bind(editorial.getIssue_id(), listener);
    }

    @Override
    public int getItemCount() {
        return lstEditorales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView issue_id;
        TextView volume;
        TextView number;
        TextView year;
        TextView title;
        ImageView cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            issue_id=itemView.findViewById(R.id.txt_ideditorial);
            volume=itemView.findViewById(R.id.txt_volumeeditorial);
            number=itemView.findViewById(R.id.txt_numeroeditorial);
            year=itemView.findViewById(R.id.txt_anioeditorial);
            title=itemView.findViewById(R.id.txt_nombreeditorial);
            cover=itemView.findViewById(R.id.imageneditorial);
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
