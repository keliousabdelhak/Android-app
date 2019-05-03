package com.example.bos.search;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SpcialiteActivity extends AppCompatActivity {

    private EditText Title ;
    private Button Btitle ;


    private RecyclerView mResultList ;

    private DatabaseReference mCourdatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spcialite);

        firebaseAuth = FirebaseAuth.getInstance();

        mCourdatabase = FirebaseDatabase.getInstance().getReference("cours");

        Title = (EditText) findViewById(R.id.edite_title);
        Btitle = (Button)  findViewById(R.id.boutton_titre);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        Btitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = Title.getText().toString();

                firebaseSearch(searchText);

                //    firebaseSearch();

            }
        });

    }

    private void firebaseSearch(String searchText) {

        Toast.makeText(SpcialiteActivity.this,"Recherche En Cours" , Toast.LENGTH_SHORT).show();

        //    Query firebasesearchQuery = mCourdatabase.orderByChild("name").startAt(searchText).endAt(searchText+ "\uf8ff");

        FirebaseRecyclerAdapter<cours , CourViewHolderS> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<cours, CourViewHolderS>(

                cours.class,
                R.layout.list,
                CourViewHolderS.class,
                mCourdatabase.orderByChild("spécialité").startAt(searchText).endAt(searchText+"\uf8ff")



        ) {
            @Override
            protected void populateViewHolder(final CourViewHolderS viewHolder, cours model, int position) {

                viewHolder.setDetails(getApplicationContext() ,  model.getTitre_cours() , model.getDomaine() , model.getSpécialité() , model.getDescription() , model.getDate());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(SpcialiteActivity.this,AffichageActivity.class);
                        intent.putExtra("name",viewHolder.cour_Titre.getText().toString());
                        startActivity(intent);
                    }
                });

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    //View Holder class

    public static class CourViewHolderS extends RecyclerView.ViewHolder {

        View mView ;
        TextView cour_Titre;

        public CourViewHolderS(View itemView) {
            super(itemView);

            mView = itemView  ;

            cour_Titre = (TextView) mView.findViewById(R.id.texte_titre);



        }

        public void setDetails(Context ctx , String courTitre , String courDomaine  , String courSpecliaite , String coursDescripion , String courdate ) {
            TextView cour_Titre = (TextView) mView.findViewById(R.id.texte_titre);
            TextView cour_Domaine = (TextView) mView.findViewById(R.id.texte_domaine);
            TextView cours_Specialite = (TextView) mView.findViewById(R.id.text_spec);
            TextView cours_Description = (TextView) mView.findViewById(R.id.text_desc);
            TextView cours_Date = (TextView) mView.findViewById(R.id.text_date);

            cour_Titre.setText(courTitre+"");
            cour_Domaine.setText(courDomaine+"");
            cours_Specialite.setText(courSpecliaite+"");
            cours_Description.setText(coursDescripion+"");
            cours_Date.setText(courdate+"");


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
                startActivity(new Intent(SpcialiteActivity.this,AccueilActivity.class));
break;
            case R.id.refreshMenu :
                startActivity(new Intent(SpcialiteActivity.this,FirstActivity.class));
break;
            case R.id.sendMenu :
                startActivity(new Intent(SpcialiteActivity.this,SendActivity.class));
break;
            case R.id.searchMenu :
                startActivity(new Intent(SpcialiteActivity.this,RechercheActivity.class));
break;
            case R.id.propMenu :
        }
        return super.onOptionsItemSelected(item);
    }

}
