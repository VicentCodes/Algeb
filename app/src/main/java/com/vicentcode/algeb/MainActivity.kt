package com.vicentcode.algeb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.algeb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater).apply{
            setContentView(root)
            cvNumComp.setOnClickListener {
                val intent = Intent(applicationContext, numComplejosAC::class.java)
                startActivity(intent)

            }
            cvMatrices.setOnClickListener {
                val intent = Intent(applicationContext, Matrices::class.java)
                startActivity(intent)
            }
            cvSistema.setOnClickListener {
                val intent = Intent(applicationContext, SistemasEcuaciones::class.java)
                startActivity(intent)
            }



    }






}


}



