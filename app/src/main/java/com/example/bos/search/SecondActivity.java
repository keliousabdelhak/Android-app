package com.example.bos.search;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {


    private RecyclerView mResultList;
    private DatabaseReference mCourdatabase;
    private TextView t1;
    private ProgressDialog progressDialog ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progressDialog = new ProgressDialog(this);
        mCourdatabase = FirebaseDatabase.getInstance().getReference("cour");
        firebaseAuth = FirebaseAuth.getInstance();


        t1 = (TextView) findViewById(R.id.txtlist);

        mResultList = (RecyclerView) findViewById(R.id.list_cours);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));



        String searchText = t1.getText().toString();
        progressDialog.setMessage("La recherche est En Cours");
        progressDialog.show();
        firebaseSearch(searchText);
        progressDialog.dismiss();

    }

    private void firebaseSearch(String searchText) {




        FirebaseRecyclerAdapter<cours, CourViewHolder1> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<cours, CourViewHolder1>(

                cours.class,
                R.layout.list,
                CourViewHolder1.class,
                //  mCourdatabase.orderByChild("titre_cours").startAt(searchText).endAt(searchText+"\uf8ff")
                mCourdatabase


        ) {


            protected void populateViewHolder(final CourViewHolder1 viewHolder, cours model, int position) {

                viewHolder.setDetails(getApplicationContext(), model.getTitre_cours(), model.getDomaine(), model.getSpécialité(), model.getDescription());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, AffichageActivity.class);
                        intent.putExtra("name", viewHolder.cour_Titre.getText().toString());
                        startActivity(intent);
                    }
                });

            }
        };
        // progressDialog.dismiss();
        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class CourViewHolder1 extends RecyclerView.ViewHolder {

        View mView;
        TextView cour_Titre;

        public CourViewHolder1(View itemView) {
            super(itemView);

            mView = itemView;

            cour_Titre = (TextView) mView.findViewById(R.id.texte_titre);


        }

        public void setDetails(Context ctx, String courTitre, String courDomaine, String courSpecliaite, String coursDescripion) {
            TextView cour_Titre = (TextView) mView.findViewById(R.id.texte_titre);
            TextView cour_Domaine = (TextView) mView.findViewById(R.id.texte_domaine);
            TextView cours_Specialite = (TextView) mView.findViewById(R.id.text_spec);
            TextView cours_Description = (TextView) mView.findViewById(R.id.text_desc);

            cour_Titre.setText(courTitre + "");
            cour_Domaine.setText(courDomaine + "");
            cours_Specialite.setText(courSpecliaite + "");
            cours_Description.setText(coursDescripion + "");


            //  Glide.with(ctx).load(courvideo).into(cour_video);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutMenu :
                firebaseAuth.signOut();
                finish();;
                startActivity(new Intent(SecondActivity.this,AccueilActivity.class));
break;
            case R.id.refreshMenu :
                startActivity(new Intent(SecondActivity.this,FirstActivity.class));
break;
            case R.id.sendMenu :
                startActivity(new Intent(SecondActivity.this,SendActivity.class));
break;
            case R.id.searchMenu :
                startActivity(new Intent(SecondActivity.this,RechercheActivity.class));
break;
            case R.id.propMenu :
        }
        return super.onOptionsItemSelected(item);
    }

}

