package com.example.animexplorer

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ANIME = "key_anime"
    }

    private var anime: Anime? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        // Mengatur warna ActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.navy)))
        window.statusBarColor = ContextCompat.getColor(this, R.color.navy)

        val title: TextView = findViewById(R.id.title)
        val synopsis: TextView = findViewById(R.id.synopsis)
        val photo: ImageView = findViewById(R.id.anime_photo)
        val genre: TextView = findViewById(R.id.data_genre)
        val duration: TextView = findViewById(R.id.data_duration)
        val episode: TextView = findViewById(R.id.data_episode)
        val studio: TextView = findViewById(R.id.data_studio)

        // Mengambil objek Anime dari Intent
        anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ANIME, Anime::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ANIME)
        }

        // Menampilkan informasi Anime
        anime?.let {
            title.text = it.name
            genre.text = it.genre
            duration.text = it.duration
            episode.text = it.Episode
            studio.text = it.studio
            synopsis.text = it.synopsis

            // Load image using Glide
            Glide.with(this)
                .load(it.photo)
                .into(photo)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Menambahkan item menu ke ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)  // Memuat menu_share.xml
        return true
    }

    // Menghandle klik pada item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareAnimeInfo()  // Memanggil fungsi berbagi
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareAnimeInfo() {
        // Ambil data yang ingin dibagikan
        val shareText = """
            Check out this anime!
            Title: ${anime?.name}
            Genre: ${anime?.genre}
            Episode: ${anime?.Episode}
            Duration: ${anime?.duration}
            Studio: ${anime?.studio}
            Synopsis: ${anime?.synopsis}
        """.trimIndent()

        // Membuat Intent berbagi
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }

        // Memeriksa apakah ada aplikasi yang bisa menerima Intent
        val chooser = Intent.createChooser(shareIntent, "Share anime info via:")
        startActivity(chooser)
    }
}
