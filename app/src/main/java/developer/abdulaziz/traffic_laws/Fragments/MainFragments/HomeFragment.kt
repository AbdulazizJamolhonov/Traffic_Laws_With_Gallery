package developer.abdulaziz.traffic_laws.Fragments.MainFragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.traffic_laws.Adapters.HomePagerAdapter
import developer.abdulaziz.traffic_laws.Database.MyDBHelper
import developer.abdulaziz.traffic_laws.Object.MyObject
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.FragmentHomeBinding
import developer.abdulaziz.traffic_laws.databinding.ItemTabBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myDBHelper: MyDBHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)

            viewPager2.adapter = HomePagerAdapter(this@HomeFragment)
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                val itemTab = ItemTabBinding.inflate(layoutInflater)
                tab.customView = itemTab.root
                itemTab.itemText.text = MyObject.list[position]
                if (position == 0) {
                    itemTab.itemText.setTextColor(Color.parseColor("#005CA1"))
                    itemTab.itemText.setBackgroundColor(Color.WHITE)
                }
            }.attach()

            tabListener()
            return root
        }
    }

    private fun tabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                cv?.findViewById<TextView>(R.id.item_text)
                    ?.setTextColor(Color.parseColor("#005CA1"))
                cv?.findViewById<TextView>(R.id.item_text)?.setBackgroundColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val cv = tab?.customView
                cv?.findViewById<TextView>(R.id.item_text)?.setTextColor(Color.WHITE)
                cv?.findViewById<TextView>(R.id.item_text)
                    ?.setBackgroundColor(Color.parseColor("#005CA1"))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setTab() {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tabView = ItemTabBinding.inflate(layoutInflater)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabView.root
            tabView.itemText.text = MyObject.list[i]

            if (i == 0) {
                tabView.itemText.setTextColor(Color.parseColor("#005CA1"))
                tabView.itemText.setBackgroundColor(Color.WHITE)
            }
        }
    }
}