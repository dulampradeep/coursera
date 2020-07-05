package com.example.postbox;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
import java.io.InputStream;

public class CiscoAnswerFinder extends AsyncTask<ImageView,Void,Void> {

   ImageView iv=null;
    Bitmap b;
    String t;
    String u;
    @Override
    protected Void doInBackground(ImageView... imageViews) {
        iv=imageViews[0];

        try {
            Document d = Jsoup.connect("https://www.premiumexam.net/").get();
            Element img=d.select("img").first();
            String url=img.absUrl("src");
            InputStream in=new java.net.URL(url).openStream();
            b= BitmapFactory.decodeStream(in);
            t=d.title();

        }
        catch (IOException i1){
            i1.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        iv.setImageBitmap(b);

    }
}
