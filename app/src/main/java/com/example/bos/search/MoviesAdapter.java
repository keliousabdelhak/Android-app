package com.example.bos.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bos on 09/06/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder>{

    private List<cours> coursList;
    private Context mContext ;

    public MoviesAdapter(List<cours> cousLi, Context context ) {
        coursList = cousLi ;
        mContext = context  ;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titre_cour, Domaine, spécialité , description ;
        public LinearLayout parentlayout ;

        public MyViewHolder(View view) {
            super(view);
            titre_cour = (TextView) view.findViewById(R.id.texte_titre);
            Domaine = (TextView) view.findViewById(R.id.texte_domaine);
            spécialité = (TextView) view.findViewById(R.id.text_spec);
            description = (TextView) view.findViewById(R.id.text_desc);
            parentlayout = (LinearLayout) view.findViewById(R.id.parentlayout);


        }
    }


    public MoviesAdapter(List<cours> moviesList) {
        this.coursList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        cours movie = coursList.get(position);
        holder.titre_cour.setText(movie.getTitre_cours());
        holder.Domaine.setText(movie.getDomaine());
        holder.spécialité.setText(movie.getSpécialité());
        holder.description.setText(movie.getDescription());

        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(  mContext , AffichageActivity.class);
                intent.putExtra("name",  holder.titre_cour.getText().toString());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coursList.size();
    }



}
