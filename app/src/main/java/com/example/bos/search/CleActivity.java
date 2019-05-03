package com.example.bos.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class CleActivity extends AppCompatActivity {

    private EditText Title ;
    private Button Btitle   ;

    private List<cours> coursList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter , adapter;



    private DatabaseReference mCourdatabase;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cle);

        Btitle = (Button) findViewById(R.id.boutton_titre);
        Title = (EditText) findViewById(R.id.edite_title) ;

        firebaseAuth = FirebaseAuth.getInstance();

        mCourdatabase = FirebaseDatabase.getInstance().getReference("cours");

        recyclerView = (RecyclerView) findViewById(R.id.result_list);

         adapter = new MoviesAdapter(coursList , this);

         //     mAdapter = new MoviesAdapter(coursList);
         // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setItemAnimator(new DefaultItemAnimator());
         recyclerView.setAdapter(adapter);







        Btitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchText = Title.getText().toString();

                Query f = mCourdatabase.orderByChild("cle1").startAt(searchText).endAt(searchText+"\uf8ff");
                f.addListenerForSingleValueEvent(valueEventListener);

                Query f1 = mCourdatabase.orderByChild("cle2").startAt(searchText).endAt(searchText+"\uf8ff");
                f1.addListenerForSingleValueEvent(valueEventListener);

                Query f2 = mCourdatabase.orderByChild("cle3").startAt(searchText).endAt(searchText+"\uf8ff");
                f2.addListenerForSingleValueEvent(valueEventListener);


            }
        });

}






    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists())
            { coursList.clear(); }
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cours crs = snapshot.getValue(cours.class);
                    coursList.add(crs);

                    adapter.notifyDataSetChanged();

                }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


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
                startActivity(new Intent(CleActivity.this,AccueilActivity.class));
break;
            case R.id.refreshMenu :
                startActivity(new Intent(CleActivity.this,FirstActivity.class));
break;
            case R.id.sendMenu :
                startActivity(new Intent(CleActivity.this,SendActivity.class));
break;
            case R.id.searchMenu :
                startActivity(new Intent(CleActivity.this,RechercheActivity.class));
break;
            case R.id.propMenu :
        }
        return super.onOptionsItemSelected(item);
    }

}