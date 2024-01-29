package com.route.task_implemented.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.route.task_implemented.Constants
import com.route.task_implemented.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromIntent()
    }

    private fun getDataFromIntent(){
        val intent = intent
        binding.userIdPlace.text = intent.getIntExtra(Constants.USERID_KEY,0).toString()
        binding.idPalce.text = intent.getIntExtra(Constants.ID_KEY,0).toString()
        binding.titlePlace.text = intent.getStringExtra(Constants.TITLE_KEY)
        binding.bodyPlace.text = intent.getStringExtra(Constants.BODY_KEY)
    }
}

