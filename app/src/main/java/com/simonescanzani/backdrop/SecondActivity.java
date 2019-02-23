package com.simonescanzani.backdrop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SecondActivity extends AppCompatActivity {

    private ImageView expandedImage;

    private LayerDrawable drawableFile;
    private GradientDrawable gradientDrawable;

    private LinearLayout linearLayout;

    private Bitmap foto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(SecondActivity.class.getSimpleName());

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(0);

        expandedImage = findViewById(R.id.expandedImage);
        expandedImage.setImageResource(R.drawable.foto);

        final AppBarLayout mAppBarLayout = findViewById(R.id.appBarLayout);

        linearLayout = findViewById(R.id.linearLayout);
        drawableFile = (LayerDrawable) getResources().getDrawable(R.drawable.layout_background);
        gradientDrawable = (GradientDrawable) drawableFile.findDrawableByLayerId(R.id.sfumatura);

        foto = BitmapFactory.decodeResource(this.getResources(), R.drawable.foto);

        gradientDrawable.setColor(getDominantColor(foto));
        linearLayout.setBackground(drawableFile);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    gradientDrawable.setColor(SecondActivity.this.getResources().getColor(R.color.colorPrimary));
                    gradientDrawable.invalidateSelf();
                    linearLayout.setBackground(drawableFile);

                } else if (isShow) {
                    isShow = false;
                    gradientDrawable.setColor(getDominantColor(foto));
                    gradientDrawable.invalidateSelf();
                    linearLayout.setBackground(drawableFile);
                }
            }
        });
    }

    public static int getDominantColor(Bitmap bitmap) {
        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1, 1, true);
        final int color = newBitmap.getPixel(0, 0);
        newBitmap.recycle();
        return color;
    }

}
