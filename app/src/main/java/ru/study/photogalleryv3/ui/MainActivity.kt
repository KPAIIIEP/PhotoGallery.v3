package ru.study.photogalleryv3.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.study.photogalleryv3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter()
        recyclerView = binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = photoAdapter
        }

        binding.button.setOnClickListener {
            viewModel.fetchPhotos()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState
                        .map { it.isLoading }
                        .distinctUntilChanged()
                        .collect {
                            binding.recyclerView.isVisible = !it
                            binding.loadingView.isVisible = it
                        }
                }
                launch {
                    viewModel.uiState.collect {
                        photoAdapter.update(it.photos)
                    }
                }
                launch {
                    viewModel.uiState
                        .map { it.errorMessage }
                        .collect { text ->
                            if (text != null) {
                                showSnackBar(text)
                                viewModel.userMessageShown()
                            }
                        }
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}