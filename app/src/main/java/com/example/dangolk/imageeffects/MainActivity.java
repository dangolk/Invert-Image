package com.example.dangolk.imageeffects;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView myImageView;
    ImageView myInvertedView;
    Drawable myFlower;
    Bitmap capturedImage, invertedImage;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button myButton = (Button) findViewById(R.id.myButton);

        if(!hasCamera()){
            myButton.setEnabled(false);
        }

        myImageView = (ImageView) findViewById(R.id.inputView);
        myInvertedView = (ImageView) findViewById(R.id.outputView);
    }

    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            capturedImage =  (Bitmap) extras.get("data");
            myImageView.setImageBitmap(capturedImage);

//            invertedImage = invertImage(capturedImage);

            myInvertedView.setImageBitmap(capturedImage);
        }
    }

    public static Bitmap invertImage(Bitmap original){
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());
        int A, R, G, B;
        int pixel;

        int height = original.getHeight();
        int width = original.getWidth();

        for(int x = 0; x < height; x++){
            for(int y = 0 ; y < width; y++){
                pixel = original.getPixel(x,y);
                A = Color.alpha(pixel);
                R = 255 - Color.red(pixel);
                G = 255 - Color.green(pixel);
                B = 255- Color.blue(pixel);
                finalImage.setPixel(x,y, Color.argb(A,R,G,B));
            }
        }

        return finalImage;
    }

}
