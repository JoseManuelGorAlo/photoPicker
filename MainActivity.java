package com.example.prueba6photopicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String>imagenesListSelections= new ArrayList<String>(5);
    private ImageView[] arrayViews= new ImageView[5];

    ImageView imageperson;
    TextView textCamera, textGallery;
    ImageButton imagecamera, imagegallery, imageexit;
    private static final int SELECT_FILE = 1;
    private String selectedImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IU();
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getColor(R.color.blue)));
        imagegallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                    ImagePicker.with(MainActivity.this)
                                            .galleryOnly()
                                            .start(SELECT_FILE);
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        imagecamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext()).withPermissions(Manifest.permission.CAMERA)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 2);
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        imageexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //mensaje();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();

            // Obtener el nombre de la imagen seleccionada
            String imageName = String.valueOf(getFileNameFromUri(selectedImageUri));

            // Guardar el nombre de la imagen en la variable
            selectedImageName = imageName;

            // Mostrar mensaje de éxito con el nombre de la imagen guardada
            Toast.makeText(MainActivity.this, "Imagen guardada: " + selectedImageName, Toast.LENGTH_SHORT).show();

            // Resto del código para guardar la imagen y realizar las operaciones necesarias

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageperson.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            imageperson.setImageURI(selectedImageUri);
        }
    }

    private ArrayList<String> getFileNameFromUri(Uri uri) {
        imagenesListSelections= new ArrayList<>(5);
        String fileName = "unknown";
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex != -1) {
                fileName = cursor.getString(nameIndex);
                imagenesListSelections.add(fileName);
                Log.d("arrayList", imagenesListSelections.toString());
            }
            cursor.close();
        }

        return imagenesListSelections;
    }

    private void IU() {

        arrayViews[0]=findViewById(R.id.imageperson);
        arrayViews[1]=findViewById(R.id.imageperson2);
        arrayViews[2]=findViewById(R.id.imageperson3);
        arrayViews[3]=findViewById(R.id.imageperson4);
        arrayViews[4]=findViewById(R.id.imageperson5);

        imagecamera = findViewById(R.id.imageCamera);
        imagegallery = findViewById(R.id.galeriaView);
        imageexit = findViewById(R.id.imageView5);
    }
    private void mensaje(){
      //  Log.d("arrayList", imagenesListSelections.toString());
    }
}
