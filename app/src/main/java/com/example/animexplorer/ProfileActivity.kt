package com.example.animexplorer

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Mengatur warna ActionBar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.navy)))
        window.statusBarColor = ContextCompat.getColor(this, R.color.navy)

        // Inisialisasi elemen UI
        val profileImage: CircleImageView = findViewById(R.id.iv_profile_picture)
        val profileName: TextView = findViewById(R.id.tv_profile_name)
        val profileEmail: TextView = findViewById(R.id.tv_profile_email)


        profileImage.setImageResource(R.drawable.profil)
        profileName.text = getString(R.string.auliya_ikhsana_nasution)
        profileEmail.text = getString(R.string.auliyanasti06_gmail_com)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}