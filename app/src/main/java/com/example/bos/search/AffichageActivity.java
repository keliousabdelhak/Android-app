package com.example.bos.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AffichageActivity extends YouTubeBaseActivity {

    private DatabaseReference mDatabaseRef;
    private VideoView imageView;
    private String s, video, pdf;
    private List<cours> courslist;
    private TextView t1, t2, t3, t4,t5,  downPdf, downVideo;
    private Uri uri;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cour");

        imageView = (VideoView) findViewById(R.id.image);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.back);

        downPdf = (TextView) findViewById(R.id.downPdf);
        downVideo = (TextView) findViewById(R.id.downVideo);

        Button btnpdf = (Button) findViewById(R.id.btnPDF);
        Button btnvideo = (Button) findViewById(R.id.btnVideo);

        downPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf));
                startActivity(browserIntent);
            }
        });

        downVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(video));
                startActivity(browserIntent);
            }
        });


        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(video));
               // startActivity(browserIntent);


                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video));
                intent.setDataAndType(Uri.parse(video), "video/*");
                startActivity(intent);

            }
        });

        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(pdf));
                startActivity(intent);

            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AffichageActivity.this,RechercheActivity.class));
            }
        });


        Intent intent = getIntent();
        s = intent.getStringExtra("name");

        courslist = new ArrayList<>();


        Query query = FirebaseDatabase.getInstance().getReference("cours").orderByChild("titre_cours").equalTo(s);
        query.addListenerForSingleValueEvent(valueEventListener);

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            courslist.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cours cours1 = snapshot.getValue(cours.class);
                    courslist.add(cours1);
                    cours crs = courslist.get(0);
                    // imageView.setImageURI(Uri.parse(crs.getVideo()));
                    // setImage( crs.getVideo());
                    t1.setText(crs.getTitre_cours());
                    t2.setText(crs.getDomaine());
                    t3.setText(crs.getSpécialité());
                    t4.setText(crs.getDescription());
                    video = crs.getVideo();
                    pdf = crs.getPdf();
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void setImage(String textvdieo) {
        imageView.setVideoURI(Uri.parse(textvdieo));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogoutMenu:
                firebaseAuth.signOut();
                finish();
                ;
                startActivity(new Intent(AffichageActivity.this, AccueilActivity.class));
                break;
            case R.id.refreshMenu:
                startActivity(new Intent(AffichageActivity.this, FirstActivity.class));
                break;
            case R.id.sendMenu:
                startActivity(new Intent(AffichageActivity.this, SendActivity.class));
                break;
            case R.id.searchMenu:
                startActivity(new Intent(AffichageActivity.this, RechercheActivity.class));
                break;
            case R.id.propMenu:
        }
        return super.onOptionsItemSelected(item);
    }

}
