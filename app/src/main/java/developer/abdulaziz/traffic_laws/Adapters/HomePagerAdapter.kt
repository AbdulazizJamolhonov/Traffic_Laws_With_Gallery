package developer.abdulaziz.traffic_laws.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.traffic_laws.Fragments.HomeFragments.BuyuruvchiFragment
import developer.abdulaziz.traffic_laws.Fragments.HomeFragments.ImtiyozliFragment
import developer.abdulaziz.traffic_laws.Fragments.HomeFragments.OgohlantiruvchiFragment
import developer.abdulaziz.traffic_laws.Fragments.HomeFragments.TaqiqlovchiFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> OgohlantiruvchiFragment()
            1 -> ImtiyozliFragment()
            2 -> TaqiqlovchiFragment()
            else -> BuyuruvchiFragment()
        }
}