package developer.abdulaziz.traffic_laws.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.traffic_laws.Fragments.MainFragments.AboutFragment
import developer.abdulaziz.traffic_laws.Fragments.MainFragments.HomeFragment
import developer.abdulaziz.traffic_laws.Fragments.MainFragments.LikedFragment
import developer.abdulaziz.traffic_laws.R

class MainPagerFragment(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> LikedFragment()
            else -> AboutFragment()
        }
    }
}