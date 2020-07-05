package com.example.connectdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.RowId;

public class ContactsDb {
    public static final String KEY_ROWID="_id";
    public static final String KEY_ROWNAME="_Name";
    public static final String KEY_ROWPASS="_Pass";

    private final String DATABASE_NAME="ContactsDB";
    private final String DATABASE_TABLE="ContactsTable";
    private final int DATABASE_VERSION=1;

    private DBHelper helper;
    private final Context context;
    private SQLiteDatabase ourdatabase;

    public ContactsDb(Context context){
        this.context=context;
    }
    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context con){
            super(con,DATABASE_NAME,null,DATABASE_VERSION);//create a new database
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            /*
                CREATE TABLE ContactsTable(_id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        _Name TEXT NOT NULL,
                                        _Pass TEXT NOT NULL);
             */
           String sql="CREATE TABLE "+DATABASE_TABLE+"("+KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_ROWNAME+" TEXT NOT NULL,"+KEY_ROWPASS+" TEXT NOT NULL);";
           db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table if exists "+DATABASE_TABLE);
            onCreate(db);
        }
    }
    public ContactsDb open() throws SQLException{
        helper=new DBHelper(context);
        ourdatabase=helper.getWritableDatabase();
        return this;
    }
    public void close(){
        helper.close();
    }
    public long createEntry(String name,String pass){
        ContentValues cv=new ContentValues();
        cv.put(KEY_ROWNAME,name);
        cv.put(KEY_ROWPASS,pass);
        return ourdatabase.insert(DATABASE_TABLE,null,cv);
    }
    public String getData(){
        String columns[]=new String[]{KEY_ROWID,KEY_ROWNAME,KEY_ROWPASS};
        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";
        int indexid=c.getColumnIndex(KEY_ROWID);
        int indexname=c.getColumnIndex(KEY_ROWNAME);
        int indexpass=c.getColumnIndex(KEY_ROWPASS);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result+=c.getString(indexid)+":"+c.getString(indexname)+":"+c.getString(indexpass)+"\n";
        }
        c.close();
        return result;
    }
    public long deleteEntry(int rowid){
        return ourdatabase.delete(DATABASE_TABLE,KEY_ROWID+"=?",new String[]{Integer.toString(rowid)});
    }
    public long UpdateEntry(int rowid,String name,String pass){
        ContentValues cv=new ContentValues();
        cv.put(KEY_ROWNAME,name);
        cv.put(KEY_ROWPASS,pass);
        return ourdatabase.update(DATABASE_TABLE,cv, KEY_ROWID+"=?",new String[]{Integer.toString(rowid)});
    }
}
