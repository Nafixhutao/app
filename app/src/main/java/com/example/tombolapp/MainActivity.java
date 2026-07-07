package com.example.tombolapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.draw_view);

        // ===== Palet warna =====
        findViewById(R.id.color_black).setOnClickListener(v -> drawView.setColor(Color.BLACK));
        findViewById(R.id.color_pink).setOnClickListener(v -> drawView.setColor(Color.parseColor("#FF5470")));
        findViewById(R.id.color_cyan).setOnClickListener(v -> drawView.setColor(Color.parseColor("#4DE1C1")));
        findViewById(R.id.color_blue).setOnClickListener(v -> drawView.setColor(Color.parseColor("#3B82F6")));
        findViewById(R.id.color_eraser).setOnClickListener(v -> {
            drawView.setEraser();
            Toast.makeText(this, "Mode penghapus", Toast.LENGTH_SHORT).show();
        });

        // ===== Tombol aksi =====
        Button btnUndo = findViewById(R.id.btn_undo);
        Button btnClear = findViewById(R.id.btn_clear);

        btnUndo.setOnClickListener(v -> drawView.undo());
        btnClear.setOnClickListener(v -> {
            drawView.clear();
            Toast.makeText(this, "🗑 Kanvas dikosongkan", Toast.LENGTH_SHORT).show();
        });
    }
}
