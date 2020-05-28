package com.javadsh98.mjpersiondictionary.ui.main.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.databinding.ActivityMainBinding
import com.javadsh98.mjpersiondictionary.ui.main.fragment.FragmentFactoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        //following code must before super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = FragmentFactoryImpl()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        root = binding.root
        setContentView(root)

        val navController = findNavController(R.id.container_main_fragment)
        //connect bottomnavigation with navController
        bottomnavigation_main_menu.setupWithNavController(navController)

        //connect toolbar with navController
        setSupportActionBar(toolbar_main)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.favoriteFragment
            , R.id.historyFragment, R.id.moreFragment))
        toolbar_main.setupWithNavController(navController, appBarConfiguration)

    }

}
