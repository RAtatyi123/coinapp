package com.example.coinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinapp.databinding.ActivityMainBinding
import com.example.coinapp.presentation.Coin.CoinFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Объявите переменную для привязки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Используйте метод inflate для создания экземпляра привязки
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, CoinFragment()) // R.id.nav_host_fragment - это ID контейнера, в который будет добавлен фрагмент
                .commit()
        }

    }
}