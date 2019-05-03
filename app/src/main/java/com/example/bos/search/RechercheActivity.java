package com.example.bos.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class RechercheActivity extends AppCompatActivity {

    private Button bT , bD , bS , bC ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        firebaseAuth = FirebaseAuth.getInstance();


        bT = (Button) findViewById(R.id.btn_recherche_titre);
        bD = (Button) findViewById(R.id.btn_recherche_domaine);
        bS = (Button) findViewById(R.id.btn_recherche_spec);
        bC = (Button) findViewById(R.id.btn_recherche_cle);

        bT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheActivity.this , DomaineActivity.class);
                startActivity(intent);
            }
        });

        bS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheActivity.this , SpcialiteActivity.class);
                startActivity(intent);

            }
        });

        bC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheActivity.this , CleActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue3 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutMenu : {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(RechercheActivity.this, AccueilActivity.class));
            } break;
            case R.id.sendMenu : {
                startActivity(new Intent(RechercheActivity.this, SendActivity.class));
            } break;
            case R.id.refreshMenu : {
                startActivity(new Intent(RechercheActivity.this, FirstActivity.class));
            } break;
            case R.id.propMenu :
        }
        return super.onOptionsItemSelected(item);
    }

}
