package com.example.bos.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SendActivity extends AppCompatActivity {

    private EditText Email, Objet, Msg;
    private TextView Envoyer;
    private  int a ;

    private DatabaseReference databaseReference ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("enseignant");

        Email = (EditText) findViewById(R.id.Email);
        Objet = (EditText) findViewById(R.id.Objet);
        Msg = (EditText) findViewById(R.id.Msg);

        Envoyer = (TextView) findViewById(R.id.textEnvoyer);

        Envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            {
                                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                                emailIntent.putExtra(android.content.Intent.EXTRA_BCC, Email.getText().toString());
                                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Objet.getText().toString());
                                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Msg.getText().toString());

                                emailIntent.setType("plain/text");

                                startActivity(emailIntent);

                            } else {
                                Toast.makeText(SendActivity.this,"N'Exisite pas" , Toast.LENGTH_SHORT).show();
                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue2 , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutMenu :
                firebaseAuth.signOut();
                finish();;
                startActivity(new Intent(SendActivity.this,AccueilActivity.class));
break;
            case R.id.refreshMenu :
                startActivity(new Intent(SendActivity.this,FirstActivity.class));
break;
            case R.id.searchMenu :
                startActivity(new Intent(SendActivity.this,RechercheActivity.class));
break;
            case R.id.propMenu :
        }
        return super.onOptionsItemSelected(item);
    }

}
