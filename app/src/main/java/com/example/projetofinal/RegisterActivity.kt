package com.example.projetofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var senhaConfirmEditText: EditText
    private lateinit var erroTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.emailEditText)
        senhaEditText = findViewById(R.id.senhaEditText)
        senhaConfirmEditText = findViewById(R.id.senhaConfirmEditText)
        erroTextView = findViewById(R.id.erroTextView)
    }

    fun btCadastrarOnClick(view: View) {
        Log.v("teste", emailEditText.text.toString())


        var email = emailEditText.text.toString()
        var senha = senhaEditText.text.toString()
        var senhaConfirm = senhaConfirmEditText.text.toString()

        if(email.isEmpty() || senha.isEmpty() || senhaConfirm.isEmpty()){
            erroTextView.text = "Todos os campos devem estar preenchidos!"
            erroTextView.visibility = TextView.VISIBLE
            return
        }

        if(senha.length < 6){
            erroTextView.text = "Senha deve ter no mínimo 6 caracteres!"
            erroTextView.visibility = TextView.VISIBLE
            return
        }


        if(senha != senhaConfirm){
            erroTextView.text = "Senhas diferentes!"
            erroTextView.visibility = TextView.VISIBLE
            return
        }


        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    erroTextView.text = "Erro: ${task.exception?.message}"
                    erroTextView.visibility = TextView.VISIBLE
                }
            }

    }
}

