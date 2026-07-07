# TombolApp

Aplikasi Android sederhana (Java + XML, tanpa Kotlin / Jetpack Compose) yang menampilkan
satu tombol Material Design bertuliskan **"Tekan Saya"** di tengah layar. Saat ditekan,
muncul Toast **"Tombol berhasil ditekan!"**.

## Struktur Project

```
TombolApp/
├── build.gradle                 # Konfigurasi build root
├── settings.gradle             # Definisi modul & repository
├── gradle.properties           # Flag AndroidX
├── gradle/wrapper/
│   └── gradle-wrapper.properties
└── app/
    ├── build.gradle            # Dependency modul (material, constraintlayout)
    ├── proguard-rules.pro
    └── src/main/
        ├── AndroidManifest.xml
        ├── java/com/example/tombolapp/
        │   └── MainActivity.java
        └── res/
            ├── drawable/bg_button_rounded.xml   (opsional)
            ├── layout/activity_main.xml
            └── values/
                ├── colors.xml
                ├── strings.xml
                └── themes.xml
```

## Cara Menjalankan

1. Buka **Android Studio** → **File → Open** → pilih folder `TombolApp`.
2. Tunggu **Gradle Sync** selesai (butuh koneksi internet untuk mengunduh dependency).
3. Pilih emulator (AVD) atau perangkat fisik (aktifkan USB Debugging).
4. Klik **Run ▶ (Shift + F10)**.
5. Tekan tombol **"Tekan Saya"** → muncul Toast **"Tombol berhasil ditekan!"**.

## Dependency

- `com.google.android.material:material:1.12.0` — MaterialButton & tema Material.
- `androidx.constraintlayout:constraintlayout:2.1.4` — memusatkan tombol.
- `androidx.appcompat:appcompat:1.7.0`

## SDK

- `compileSdk` / `targetSdk`: 34 (Android 14)
- `minSdk`: 21 (Android 5.0+)
