package com.dicoding.picodiploma.sejiwaproject.features.team

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class DetailTeamAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragmentList = arrayListOf<Fragment>()
    private val fragmentTitle = arrayListOf<String>()

    fun populateFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence = fragmentTitle[position]
}
