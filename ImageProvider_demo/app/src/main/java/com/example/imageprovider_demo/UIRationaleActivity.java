package com.example.imageprovider_demo;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class UIRationaleActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rational_fragment);
        ContentResolver contentResolver = getContentResolver();
        Uri imageuri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        contentResolver.query(imageuri,null,null,null,null);
    }

}
