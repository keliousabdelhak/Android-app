package com.example.bos.search;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InscriptionActivity extends AppCompatActivity {

    private Button regButton;
    private TextView tx1;
    private EditText userPassword, userEmail;
    private FirebaseAuth firebaseAuth;
    String user_Email ,user_Password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);

        regButton = (Button) findViewById(R.id.btndqzazadxqsxd);
        tx1 = (TextView) findViewById(R.id.textconnexion);

        firebaseAuth = FirebaseAuth.getInstance();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(userEmail.getText().toString().trim() , userPassword.getText().toString().trim())) {

                     user_Email = userEmail.getText().toString().trim();
                     user_Password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                UserData();
                                Toast.makeText(InscriptionActivity.this, " Inscription Réussis ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(InscriptionActivity.this , ConnectActivity.class));
                            } else {
                                Toast.makeText(InscriptionActivity.this, " Inscription Erronée , Vérifiez Syntaxe   Email  ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

        tx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InscriptionActivity.this, ConnectActivity.class);
                startActivity(intent);
            }
        });

    }

    /***** METOHODE *****/

    private boolean validate(String mail , String password  ) {
        boolean reslut = false;


        if (mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Svp Entrez tous les Informations", Toast.LENGTH_SHORT).show();

        } else {
            reslut = true;
        }
        return reslut;
    }


    private void UserData()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myref= firebaseDatabase.getReference("etudiant/"+firebaseAuth.getCurrentUser().getUid());
        etudiant etudiant1 = new etudiant(user_Email);
        myref.setValue(etudiant1);
    }


}
