package com.example.wbinternw8part1.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbinternw8part1.R
import com.example.wbinternw8part1.databinding.ActivityMainBinding
import com.example.wbinternw8part1.model.AppState
import com.example.wbinternw8part1.model.DataModel
import com.example.wbinternw8part1.view.aboutapp.AboutAppFragment
import com.example.wbinternw8part1.view.details.DetailsFragment
import com.example.wbinternw8part1.view.main.adapter.MainAdapter
import com.example.wbinternw8part1.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MainFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutApp -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, AboutAppFragment())
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}