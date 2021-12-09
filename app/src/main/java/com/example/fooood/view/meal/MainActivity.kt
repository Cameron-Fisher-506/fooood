package com.example.fooood.view.meal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.fooood.R
import com.example.fooood.databinding.ActivityMainBinding
import com.example.fooood.view.BaseActivity
import com.example.fooood.view.menu.MenuActivity
import com.example.fooood.view.search.SearchActivity

class MainActivity : BaseActivity() {
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
                R.id.search -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.menu -> startActivity(Intent(this, MenuActivity::class.java))
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