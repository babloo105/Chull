package com.example.opengallery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button abutton;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.selectedImage);
        abutton = findViewById(R.id.opengallery) ;

        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    }
                    else{
                        OpenImageFromGallery();

                    }
                }
                else{
                    OpenImageFromGallery();
                }
            }
        });
    }

    private void OpenImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults [0] ==PackageManager.PERMISSION_GRANTED){
                    OpenImageFromGallery();
                }
                else{
                    Toast.makeText(this,  "Permission denied...",Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK && resultCode==IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());
        }

        }
    }

