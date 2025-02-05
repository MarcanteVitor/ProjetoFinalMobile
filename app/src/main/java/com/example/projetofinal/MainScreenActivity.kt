package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        auth = FirebaseAuth.getInstance()
        val userEmailTextView: TextView = findViewById(R.id.userEmailTextView)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        val user = auth.currentUser
        if (user != null) {
            userEmailTextView.text = "Logado como: ${user.email}"
        } else {
            // Se o usuário não estiver autenticado, volta para a tela de login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
