package com.example.fooood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.fooood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        wireUI()
        attachNavController()
    }

    private fun wireUI() {
        this.binding.bottomNavigationView.selectedItemId = R.id.home
        this.binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

            }
            true
        }
    }

    private  fun attachNavController() {
        this.navController = Navigation.findNavController(this, R.id.navHostFagment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(this.navController, null)
    }
}