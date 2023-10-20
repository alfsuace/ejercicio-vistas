package com.alfsuace.ejerciciovistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alfsuace.ejerciciovistas.features.dog.presentation.DogActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_main))
        setupView()

    }

    private fun setupView(){
        findViewById<Button>(R.id.action_show_dog_view).setOnClickListener {
            startActivity(Intent(this, DogActivity::class.java))
        }
    }


}