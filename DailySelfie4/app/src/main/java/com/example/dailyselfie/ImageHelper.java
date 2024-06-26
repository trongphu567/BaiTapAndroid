package com.example.dailyselfie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


public class ImageHelper {

    private static final String LOG_TAG = ImageHelper.class.getSimpleName();

    public static void setImageFromFilePath(String imagePath, ImageView imageView, int targetW, int targetH) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(imagePath, bmpOptions);
        int photoW = bmpOptions.outWidth;
        int photoH = bmpOptions.outHeight;

        // determine scale factor
        int scaleFactor = Math.max(photoW / targetW, photoH / targetH);

        // decode the image file into a Bitmap
        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmpOptions);
        imageView.setImageBitmap(bitmap);
    }

    public static void setImageFromFilePath(String imagePath, ImageView imageView) {
        setImageFromFilePath(imagePath, imageView, 120, 160);
    }
}