package com.example.animexplorer

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rv_anime: RecyclerView
    private val list = ArrayList<Anime>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Mengatur warna ActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.navy)))
        window.statusBarColor = ContextCompat.getColor(this, R.color.navy)

        rv_anime = findViewById(R.id.rv_anime)
        rv_anime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Menambahkan opsi menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                // Intent untuk membuka halaman profil
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListAnime(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_synopsis)
        val dataPhoto = resources.getStringArray(R.array.data_photos)
        val dataEpisode = resources.getStringArray(R.array.data_episode)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataDuration = resources.getStringArray(R.array.data_duration)
        val dataStudio = resources.getStringArray(R.array.data_studio)
        val listanime = ArrayList<Anime>()
        for (i in dataName.indices) {
            val anime = Anime(dataName[i], dataDescription[i], dataPhoto[i], dataEpisode[i], dataGenre[i], dataDuration[i], dataStudio[i])
            listanime.add(anime)
        }
        return listanime
    }

    private fun showRecyclerList() {
        rv_anime.layoutManager = LinearLayoutManager(this) // Corrected property name
        val ListAnimeAdapter = ListAnimeAdapter(list) // Ensure the adapter name matches your class
        rv_anime.adapter = ListAnimeAdapter
    }
}
