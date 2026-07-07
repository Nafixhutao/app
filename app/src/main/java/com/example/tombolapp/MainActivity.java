package com.example.tombolapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ambil referensi tombol dari layout (Button biasa agar gaya brutalism murni)
        Button btnTekan = findViewById(R.id.btn_tekan);

        // Aksi saat tombol ditekan
        btnTekan.setOnClickListener(view ->
                Toast.makeText(
                        MainActivity.this,
                        "✔ Tombol berhasil ditekan!",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}
