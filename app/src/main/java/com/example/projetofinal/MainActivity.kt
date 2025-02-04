package com.example.projetofinal

//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import com.google.firebase.auth.FirebaseAuth
//import android.util.Log
//import android.util.Patterns
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    fun criarUsuario(email: String, senha: String, callback: (Boolean, String?) -> Unit) {
//        val auth = FirebaseAuth.getInstance()
//
//        if (email.isEmpty() || senha.isEmpty()) {
//            callback(false, "Email e senha não podem estar vazios")
//            return
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            callback(false, "Email inválido")
//            return
//        }
//
//        if (senha.length < 6) {
//            callback(false, "A senha deve ter pelo menos 6 caracteres")
//            return
//        }
//
//        auth.createUserWithEmailAndPassword(email, senha)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
//                    Log.i("MainActivity", "Usuário criado com sucesso!")
//                    callback(true, null)
//                } else {
//                    val exception = task.exception
//                    val errorMessage = exception?.message ?: "Erro desconhecido ao criar usuário"
//                    Toast.makeText(this, "Erro ao criar usuário: $errorMessage", Toast.LENGTH_SHORT).show()
//                    Log.e("MainActivity", "Erro ao criar usuário: $errorMessage")
//                    callback(false, errorMessage)
//                }
//            }
//    }
//}

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val senhaEditText: EditText = findViewById(R.id.senhaEditText)
        val criarUsuarioButton: Button = findViewById(R.id.criarUsuarioButton)

        criarUsuarioButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                criarUsuario(email, senha) { sucesso, erro ->
                    if (sucesso) {
                        // Sucesso ao criar conta
                        println("Usuário criado com sucesso!")
                    } else {
                        // Exibir mensagem de erro
                        println("Erro ao criar usuário: $erro")
                    }
                }
            } else {
                println("Preencha todos os campos!")
            }
        }
    }

    private fun criarUsuario(email: String, senha: String, callback: (Boolean, String?) -> Unit) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }
}
