package com.example.imageprovider_demo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainActivity extends AppCompatActivity {
    private static final String TAG = ".MainActivity";
    private Button mselectimage;
    private static final int PERMISION_REQUEST_CODE = 1;
    private boolean isshouldShowRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mselectimage = findViewById(R.id.selectimage);
        init();
    }

    private void init() {
        mselectimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();//Android 6.0以上检查
            }
        });
    }

    private void checkPermission() {
        //checkSelfPermission方法返回一个静态的Permission_Granted（允许）或者Permission_denied（拒绝）
        int readExStoragePermissonRest = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        //许可授权的结果返回给readExStoragePermissonRest
        /**
         * 如果访问的结果是拒绝，那么就请求这个权限
         */
        if (readExStoragePermissonRest != PackageManager.PERMISSION_GRANTED) {
            /**
             * READ_EXTERNAL_STORAGE是外部的存储
             */
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISION_REQUEST_CODE);
        }


    }

    /**
     * 回调方法
     *
     * @param requestCode  回调方法传回的参数
     * @param permissions  请求的权限放在里面
     * @param grantResults 同意的结果放在里面
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //以下是申请成功的方法
                ImageSelect();
                Log.d(TAG,"response is success");
            } else {
                isshouldShowRequest = shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE);
                if (isshouldShowRequest){
                   ImageSelect();
                }
                Log.d(TAG, "result" + "later" + "----->" + shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE));

            }
        }
    }

    public void ImageSelect() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),SelectActivty.class);
        startActivity(intent);
    }
}