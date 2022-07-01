package developer.abdulaziz.traffic_laws.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.traffic_laws.Adapters.MainPagerFragment
import developer.abdulaziz.traffic_laws.Object.MyObject
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.apply {

            viewPager2.adapter = MainPagerFragment(this@MainFragment)

            val list = arrayListOf(R.drawable.ic_home, R.drawable.ic_like, R.drawable.ic_about)
            TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
                tab.setIcon(list[pos])
            }.attach()

            tabListener()

            add.setOnClickListener {
                MyObject.position = 1
                findNavController().navigate(R.id.addFragment)
            }

            return root
        }
    }

    private fun tabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    1 -> {
                        myFun("Yoqtirganlar", View.INVISIBLE)
                    }
                    2 -> {
                        myFun("Biz haqimizda", View.INVISIBLE)
                    }
                    else -> {
                        myFun("Yo'l belgilari", View.VISIBLE)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun myFun(myTitle: String, myVisible: Int) {
        binding.apply {
            title.text = myTitle
            add.visibility = myVisible
            if (add.visibility == View.VISIBLE) {
                MyObject.position = 1
                add.setOnClickListener { findNavController().navigate(R.id.addFragment) }
            }
        }
    }
}