package com.javadsh98.mjpersiondictionary.ui.main.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.javadsh98.mjpersiondictionary.ui.main.fragment.History.HistoryFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.favorite.FavoriteFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.home.HomeFragment
import com.javadsh98.mjpersiondictionary.ui.main.fragment.more.MoreFragment

class FragmentFactoryImpl : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {

            HomeFragment::class.java.name -> HomeFragment()
            FavoriteFragment::class.java.name -> FavoriteFragment()
            HistoryFragment::class.java.name -> HistoryFragment()
            MoreFragment::class.java.name -> MoreFragment()

            else -> super.instantiate(classLoader, className)
        }
    }
}