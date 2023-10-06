package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
//    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result:TextView = findViewById(R.id.result)
        val button: Button = findViewById(R.id.button)
        val viewModel:MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        button.setOnClickListener {
            viewModel.getData()
        }

        viewModel.uiState.observe(this) {uiState ->
            when(uiState) {
                is MyViewModel.UiState.Empty -> result.text = ""
                is MyViewModel.UiState.Processing -> result.text = "Processing..."
                is MyViewModel.UiState.Result -> result.text = uiState.title
                is MyViewModel.UiState.Error -> result.text = "Error!"
            }
        }

//        button.setOnClickListener {
//            scope.launch {
//                result.text = CoroutinesWorker.executeWorks()
//            }
//        }


        //Not good to start GlobalScope in mainActivity because it become a root for this activity
        // and don't allow to work garbage collector -> leak of memory
        //Just example
//        button.setOnClickListener {
//            MainScope().launch {
//                result.text = CoroutinesWorker.executeWorks()
//            }
//        }

        //Listener for CALLBACK HELL
//        button.setOnClickListener {
//            BackgroundWorker.executeWorks {
//                val handler = Handler(Looper.getMainLooper())
//
//                handler.post {
//                    result.text = it
//                }
//            }
//        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        scope.cancel()
//    }
}
