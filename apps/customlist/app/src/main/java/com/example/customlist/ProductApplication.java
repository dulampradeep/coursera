package com.example.customlist;

import android.app.Application;

import java.util.ArrayList;

public class ProductApplication extends Application {
    public static ArrayList<Product> p;
    static int x[]={R.drawable.laptop,R.drawable.memory,R.drawable.hdd,R.drawable.placeholder};

    @Override
    public void onCreate() {
        super.onCreate();
        p=new ArrayList<Product>();
        p.add(new Product(0,"Lenovo IdealPad310","Idealpad 320","The worlds most fastest computer woned by pradeep",true));
        p.add(new Product(1,"Sandisk pendrive","59jhas","hello this is the pendrive of  gb memory and also ability to act as micro",true));
        p.add(new Product(2,"verbatim harddisk","6873","this is the hard disk you ever seen",true));
        p.add(new Product(3,"screen","5353","jhgkjgkjgdsn gkj nkjnjd jkdfdj gjdfkh jlkjgkdgklkjin lfire ka apko product",false));
        p.add(new Product(0,"Lenovo IdealPad310","Idealpad 320","The worlds most fastest computer woned by pradeep",true));
        p.add(new Product(1,"Sandisk pendrive","59jhas","hello this is the pendrive of  gb memory and also ability to act as micro",true));
        p.add(new Product(2,"verbatim harddisk","6873","this is the hard disk you ever seen",true));
        p.add(new Product(3,"screen","5353","jhgkjgkjgdsn gkj nkjnjd jkdfdj gjdfkh jlkjgkdgklkjin lfire ka apko product",false));
        p.add(new Product(0,"Lenovo IdealPad310","Idealpad 320","The worlds most fastest computer woned by pradeep",true));
        p.add(new Product(1,"Sandisk pendrive","59jhas","hello this is the pendrive of  gb memory and also ability to act as micro",true));
        p.add(new Product(2,"verbatim harddisk","6873","this is the hard disk you ever seen",true));
        p.add(new Product(3,"screen","5353","jhgkjgkjgdsn gkj nkjnjd jkdfdj gjdfkh jlkjgkdgklkjin lfire ka apko product",false));
        p.add(new Product(0,"Lenovo IdealPad310","Idealpad 320","The worlds most fastest computer woned by pradeep",true));
        p.add(new Product(1,"Sandisk pendrive","59jhas","hello this is the pendrive of  gb memory and also ability to act as micro",true));
        p.add(new Product(2,"verbatim harddisk","6873","this is the hard disk you ever seen",true));
        p.add(new Product(3,"screen","5353","jhgkjgkjgdsn gkj nkjnjd jkdfdj gjdfkh jlkjgkdgklkjin lfire ka apko product",false));

    }
}
