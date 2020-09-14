package com.example.postboxoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

public class showimagesinactivity extends AppCompatActivity {
MaterialToolbar tol;
    ImageView iv,iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showimagesinactivity);
        tol=findViewById(R.id.showimagetoolbar);
        setSupportActionBar(tol);
        iv=findViewById(R.id.backbutton);
        iv2=findViewById(R.id.imvshowfull);
        String url=getIntent().getStringExtra("url");
        Picasso.get().load(Uri.parse(url))
                .error(R.drawable.default_profile)
                .placeholder(R.drawable.default_profile)
                .into(iv2);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
