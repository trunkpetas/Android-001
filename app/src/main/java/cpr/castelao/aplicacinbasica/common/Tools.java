package cpr.castelao.aplicacinbasica.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

import java.io.IOException;

public class Tools {

    private static Tools instance = new Tools();
    private Context ctx;

    private Tools(){}
    public static Tools init(@NonNull Context ctx){
        instance.ctx = ctx;
        return instance;
    }

    public Bitmap getBitmapFromResID(@DrawableRes int id){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.Source decoder = ImageDecoder.createSource(ctx.getResources(), id);
                return ImageDecoder.decodeBitmap(decoder);
            } else {
                String resourceScheme = "res";
                Uri uri = new Uri.Builder()
                        .scheme(resourceScheme)
                        .path(String.valueOf(id))
                        .build();
                return MediaStore.Images.Media.getBitmap(ctx.getContentResolver(), uri );
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
