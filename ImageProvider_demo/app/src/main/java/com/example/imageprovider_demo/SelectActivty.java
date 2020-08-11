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
    private static final String TAG = ".SelectActivity";
    public static final int LOADER_ID = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_select);
        ContentResolver contentResolver = getContentResolver();
        Uri imageuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(imageuri, null, null, null, null);
        String[] columnNames = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            Log.d(TAG, "==========================");
            for (String columnName : columnNames) {
                Log.d(TAG, columnName+"======"+cursor.getString(cursor.getColumnIndex(columnName)));

            }
        }

        //        initLoaderManager();
    }

    private void initLoaderManager() {
        /**
         * LoadManager是一个加载器，用来进行一些耗时的加载操作，但是在api 28的时候已经废弃，采用ViewModels和ListData来组合加载数据，所以后期更改一下
         */
        LoaderManager loadermanager = LoaderManager.getInstance(this);
        loadermanager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                if (id == LOADER_ID) {
                    return new CursorLoader(SelectActivty.this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_display_name", "date_added"},
                            null, null, "date_added DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                /**
                 * cursor.moveToNext是移动cursor，让它指向下一行
                 * cursor.getColumnIndex 会返回指定列的名字
                 *
                 * */
                if (cursor != null) {
                    //cursor.getColumnNames 返回一个字符串数组的列名
                    String[] columnNames = cursor.getColumnNames();
                    while (cursor.moveToNext()) {
                        Log.d(TAG, "==============================");
                        for (String columnName : columnNames) {
                            Log.d(TAG, columnName + "==" + cursor.getString(cursor.getColumnIndex(columnName)));
                        }
                        Log.d(TAG, "======================");
                    }
                    cursor.close();
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });

    }

}
