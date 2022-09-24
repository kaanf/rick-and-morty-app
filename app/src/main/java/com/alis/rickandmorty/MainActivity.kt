package com.alis.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alis.rickandmorty.databinding.ActivityMainBinding

const val TAG = "Application.Tag"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    private val epoxyController: CharacterDetailsEpoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)

        viewModel.updateCharacter(id = 15)
        viewModel.characterByIdHolder.observe(this) { response ->
            response?.let {
                epoxyController.response = response
            } ?: run {
                Toast.makeText(this@MainActivity, "Network call unsuccessful.", Toast.LENGTH_LONG).show()
                return@observe
            }
        }
    }
}