package com.example.dangolk.imageeffects;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView myImageView, myOutputView;
    Drawable myFlower;
    Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        myImageView = (ImageView) findViewById(R.id.inputView);
        myOutputView = (ImageView) findViewById(R.id.outputView);

        myFlower = ResourcesCompat.getDrawable(getResources(), R.drawable.linux, null);
        bitmapImage = ((BitmapDrawable) myFlower).getBitmap();

        Bitmap newphoto = invertImage(bitmapImage);

        myOutputView.setImageBitmap(newphoto);
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
