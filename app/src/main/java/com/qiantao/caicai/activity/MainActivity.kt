package com.qiantao.caicai.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.qiantao.caicai.R
import com.qiantao.caicai.adapter.ViewPagerAdapter
import com.qiantao.caicai.databinding.ActivityMainBinding
import com.qiantao.caicai.fragment.DiscoveryFragment
import com.qiantao.caicai.fragment.MenuFragment
import com.qiantao.caicai.fragment.SearchFragment
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {
    private var mBinding: ActivityMainBinding? = null
    private val mTitleStrs = arrayOf("分类", "发现", "搜索")
    private var mTabs: List<ImageView>? = null
    private var mLastIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        mTabs = ArrayList(Arrays.asList(mBinding!!.ivMenu, mBinding!!.ivDiscovery, mBinding!!.ivSearch))
        val fragmentList = ArrayList(Arrays.asList(MenuFragment(), DiscoveryFragment(), SearchFragment()))
        val adapter = ViewPagerAdapter(supportFragmentManager, fragmentList)
        mBinding!!.vpMain.adapter = adapter
        mBinding!!.vpMain.addOnPageChangeListener(this)
        mBinding!!.clickListener = this//为导航栏底部三个按钮设置

        //默认选中第一个fragment
        setCurrentPage(0)
    }

    /**
     * 设置当前页面的位置、标题、底部Tab

     * @param currentIndex 当前的位置
     */
    private fun setCurrentPage(currentIndex: Int) {
        mTabs!![mLastIndex].isSelected = false//上个tab选中状态设置为false
        mBinding!!.vpMain.currentItem = currentIndex//当前viewpager的位置
        mBinding!!.title = mTitleStrs[currentIndex]//当前页面下toolbar的标题
        mTabs!![currentIndex].isSelected = true//当前tab选中状态设置为true
        mLastIndex = currentIndex
    }

    //底部导航栏三个按钮的点击监听
    override fun onClick(v: View) {
        val currentIndex = mTabs!!.indexOf(v)
        if (mLastIndex != currentIndex) {
            setCurrentPage(currentIndex)
        }

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        //根据viewpager滑动的位置设置当前页面
        setCurrentPage(position)
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    companion object {
        private val TAG = "MainActivity"
    }
}
