package com.example.instacat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController = findNavController(R.id.navigationHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navigationController)

    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()

}