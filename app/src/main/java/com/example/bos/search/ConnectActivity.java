package com.example.bos.search;

import android.app.ProgressDialog;
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
import com.google.firebase.database.FirebaseDatabase;

public class ConnectActivity extends AppCompatActivity {

    private Button btnConnect;
    private TextView txt1;
    private FirebaseAuth firebaseAuth;
    private EditText userEmail, userPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        btnConnect = (Button) findViewById(R.id.btnconnect);
        txt1 = (TextView) findViewById(R.id.textinscription);
        userEmail = (EditText) findViewById(R.id.emailC);
        userPassword = (EditText) findViewById(R.id.passwordC);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(ConnectActivity.this, FirstActivity.class));
        }


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate1(userEmail.getText().toString() ,userPassword.getText().toString() )) {
                    validate(userEmail.getText().toString(), userPassword.getText().toString());
                }

            }
        });


        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ConnectActivity.this, InscriptionActivity.class);
                startActivity(intent);

            }
        });

    }


    private void validate(String mail, String password) {

        progressDialog.setMessage("Connection ! ");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(ConnectActivity.this, "Connection Réussie ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ConnectActivity.this, FirstActivity.class));
                } else {
                    Toast.makeText(ConnectActivity.this, "Erreur , vérifiez vote mail et mot de passe ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validate1(String mail , String password  ) {
        boolean reslut = false;


        if (mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Svp Entrez tous les Informations", Toast.LENGTH_SHORT).show();

        } else {
            reslut = true;
        }
        return reslut;
    }


}
