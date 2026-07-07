package com.example.tombolapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ambil referensi tombol dari layout
        MaterialButton btnTekan = findViewById(R.id.btn_tekan);

        // Aksi saat tombol ditekan
        btnTekan.setOnClickListener(view ->
                Toast.makeText(
                        MainActivity.this,
                        "Tombol berhasil ditekan!",
                        Toast.LENGTH_SHORT
                ).show()
        );
    }
}
