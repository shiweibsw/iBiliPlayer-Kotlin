package com.knightdavion.kotlin.ibiliplayer.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.Gravity
import android.view.MenuItem
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.ui.fragment.HomePageFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    var homePageFragment: HomePageFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragments()
    }

    private fun initFragments() {
        homePageFragment = HomePageFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, homePageFragment).show(homePageFragment).commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        drawerLayout.closeDrawer(Gravity.START)
        when (p0.itemId) {
            R.id.item_home -> {

            }
        }
        return false
    }

}
