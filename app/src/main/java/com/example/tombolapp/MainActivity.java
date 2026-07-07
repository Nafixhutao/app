package com.example.tombolapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DbHelper db;
    private TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);

        Button btnTekan = findViewById(R.id.btn_tekan);
        tvCount = findViewById(R.id.tv_count);

        // Tampilkan nilai tersimpan dari SQLite (lanjut, tidak mulai dari 0)
        tvCount.setText(String.valueOf(db.getCount()));

        // Tekan: tambah 1, simpan ke SQLite, perbarui tampilan
        btnTekan.setOnClickListener(view -> {
            int total = db.increment();
            tvCount.setText(String.valueOf(total));
            Toast.makeText(
                    MainActivity.this,
                    "✔ Total penekanan: " + total,
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Tahan lama: reset ke 0
        btnTekan.setOnLongClickListener(view -> {
            db.reset();
            tvCount.setText("0");
            Toast.makeText(MainActivity.this, "↺ Counter direset ke 0", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
