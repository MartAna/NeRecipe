package ru.netology.nerecipe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}