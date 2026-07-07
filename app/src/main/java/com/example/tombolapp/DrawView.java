package com.example.tombolapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Kanvas gambar sederhana: corat-coret dengan jari.
 * Menyimpan tiap goresan (Path + warna + tebal) agar bisa di-redraw dan dihapus.
 */
public class DrawView extends View {

    /** Satu goresan = path + kuas-nya. */
    private static class Stroke {
        final Path path;
        final Paint paint;
        Stroke(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }

    private final ArrayList<Stroke> strokes = new ArrayList<>();
    private Path currentPath;
    private int currentColor = Color.BLACK;
    private float currentWidth = 12f;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /** Buat objek Paint baru sesuai warna & tebal saat ini. */
    private Paint newPaint() {
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(currentColor);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(currentWidth);
        return p;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Stroke s : strokes) {
            canvas.drawPath(s.path, s.paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath = new Path();
                currentPath.moveTo(x, y);
                strokes.add(new Stroke(currentPath, newPaint()));
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentPath != null) {
                    currentPath.lineTo(x, y);
                }
                break;

            case MotionEvent.ACTION_UP:
                currentPath = null;
                break;

            default:
                return false;
        }
        invalidate(); // minta gambar ulang
        return true;
    }

    /** Ganti warna kuas. */
    public void setColor(int color) {
        currentColor = color;
    }

    /** Ganti ke mode penghapus (menggambar dengan warna kanvas = putih). */
    public void setEraser() {
        currentColor = Color.WHITE;
    }

    /** Hapus semua goresan. */
    public void clear() {
        strokes.clear();
        invalidate();
    }

    /** Batalkan goresan terakhir (undo). */
    public void undo() {
        if (!strokes.isEmpty()) {
            strokes.remove(strokes.size() - 1);
            invalidate();
        }
    }
}
