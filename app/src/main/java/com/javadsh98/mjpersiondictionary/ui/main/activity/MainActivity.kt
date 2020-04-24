package com.javadsh98.mjpersiondictionary.ui.main.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.javadsh98.mjpersiondictionary.R
import com.javadsh98.mjpersiondictionary.databinding.ActivityMainBinding
import com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite.FavoriteFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.History.HistoryFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.more.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding
    lateinit var root: View

    //fragments
    lateinit var  homeFragment: HomeFragment
    lateinit var  favoriteFragment: FavoriteFragment
    lateinit var  historyFragment: HistoryFragment
    lateinit var  moreFragment: MoreFragment

    //viewmodel
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        root = binding.root
        setContentView(root)


        initfragments()
        setupViewmodel()
        setupBottomNavigation()

    }

    private fun setupBottomNavigation() {
        //use viewmodel position
        when(viewModel.getPosition()) {
            BottomPosition.HOME -> {
                bottomnavigation_main_menu.selectedItemId = R.id.menu_home
                setupFragment(homeFragment)
            }
            BottomPosition.FAVORITE -> {
                bottomnavigation_main_menu.selectedItemId = R.id.menu_favorite
                setupFragment(favoriteFragment)
            }
            BottomPosition.HISTORY -> {
                bottomnavigation_main_menu.selectedItemId = R.id.menu_history
                setupFragment(historyFragment)
            }
            BottomPosition.MORE -> {
                bottomnavigation_main_menu.selectedItemId = R.id.menu_more
                setupFragment(moreFragment)
            }
        }
        //listener
        root.bottomnavigation_main_menu.setOnNavigationItemSelectedListener(this)
    }

    private fun setupViewmodel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        when(id){
            R.id.menu_home -> {
                setupFragment(homeFragment)
                viewModel.setPosition(BottomPosition.HOME)
            }
            R.id.menu_favorite -> {
                setupFragment(favoriteFragment)
                viewModel.setPosition(BottomPosition.FAVORITE)
            }
            R.id.menu_history -> {
                setupFragment(historyFragment)
                viewModel.setPosition(BottomPosition.HISTORY)
            }
            R.id.menu_more -> {
                setupFragment(moreFragment)
                viewModel.setPosition(BottomPosition.MORE)
            }
        }
        return true
    }

    fun setupFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_main_fragment, fragment).commit()
    }

    fun initfragments(){
        homeFragment = HomeFragment.newInstance()
        homeFragment.retainInstance = true
        favoriteFragment = FavoriteFragment.newInstance()
        favoriteFragment.retainInstance = true
        historyFragment = HistoryFragment.newInstance()
        historyFragment.retainInstance = true
        moreFragment = MoreFragment.newInstance()
        moreFragment.retainInstance = true
    }

}
