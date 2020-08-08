package com.example.imageprovider_demo;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class SelectActivty extends AppCompatActivity {
    private static final String TAG=".SelectActivity";
    public static final int LOADER_ID = 1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_select);
//        ContentResolver contentResolver = getContentResolver();
//        Uri imageuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        contentResolver.query(imageuri,null,null,null,null);
        initLoaderManager();
    }

    private void initLoaderManager() {
        LoaderManager loadermanager = LoaderManager.getInstance(this);
        loadermanager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id==LOADER_ID){
                    return new CursorLoader(SelectActivty.this,MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new String[]{"_data","_display_name","date_added"},
                            null,null,"date_added DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                if (data != null) {
                    String[] columnNames = data.getColumnNames();
                    while(data.moveToNext()){
                        Log.d(TAG,"==============================");
                        for (String columnName:columnNames){
                            Log.d(TAG,columnName+"=="+data.getString(data.getColumnIndex(columnName)));
                        }
                        Log.d(TAG,"======================");
                    }
                    data.close();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });

    }

}
