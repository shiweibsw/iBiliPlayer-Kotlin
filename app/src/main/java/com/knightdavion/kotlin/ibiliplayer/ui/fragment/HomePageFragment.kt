package com.knightdavion.kotlin.ibiliplayer.ui.fragment

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.LinearLayout
import com.flyco.tablayout.SlidingTabLayout
import com.knightdavion.kotlin.ibiliplayer.App
import com.knightdavion.kotlin.ibiliplayer.R
import com.knightdavion.kotlin.ibiliplayer.adapter.HomePagerAdapter
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.GameCentreActivity
import com.knightdavion.kotlin.ibiliplayer.ui.activitys.MainActivity
import com.knightdavion.kotlin.ibiliplayer.view.NoScrollViewPager
import kotlinx.android.synthetic.main.fragment_home_page.view.*
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.startActivity


class HomePageFragment : SupportFragment() {
    var navigationHeaderLayout: LinearLayout? = null
    var mViewPager: NoScrollViewPager? = null
    var mSlidingTabs: SlidingTabLayout? = null
    var mToolbar: Toolbar? = null

    companion object {
        fun newInstance(): HomePageFragment {
            return HomePageFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater!!.inflate(R.layout.fragment_home_page, container, false)
        navigationHeaderLayout = rootView.navigationHeaderLayout
        mToolbar = rootView.toolbar
        mViewPager = rootView.viewPager
        mSlidingTabs = rootView.slidingTabs
        initView()
        return rootView
    }

    fun initView() {
        mToolbar?.title = ""
        (activity as MainActivity).setSupportActionBar(mToolbar)
        var mHomeAdapter: HomePagerAdapter = HomePagerAdapter(childFragmentManager, App.instance)
        mViewPager?.adapter = mHomeAdapter
        mSlidingTabs?.setViewPager(mViewPager)
        mViewPager?.currentItem = 1
        navigationHeaderLayout?.setOnClickListener {
            val activity = activity
            if (activity is MainActivity) {
                activity.toggleDrawer()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_action_game -> {
                activity.startActivity<GameCentreActivity>()
            }
            R.id.id_action_download -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
