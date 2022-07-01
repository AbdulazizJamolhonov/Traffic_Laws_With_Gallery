package developer.abdulaziz.traffic_laws.Fragments.HomeFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.traffic_laws.Adapters.MyUnivrsalAdapter
import developer.abdulaziz.traffic_laws.Classes.User
import developer.abdulaziz.traffic_laws.Database.MyDBHelper
import developer.abdulaziz.traffic_laws.Object.MyObject
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.FragmentBuyuruvchiBinding

class BuyuruvchiFragment : Fragment() {
    private lateinit var binding: FragmentBuyuruvchiBinding
    private lateinit var myUnivrsalAdapter: MyUnivrsalAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuyuruvchiBinding.inflate(layoutInflater)
        binding.apply {

            MyObject.myHome(root.context, 3)
            val myDBHelper = MyDBHelper(root.context)

            myUnivrsalAdapter =
                MyUnivrsalAdapter(MyObject.homeList, object : MyUnivrsalAdapter.MyClickListener {
                    override fun click(user: User, position: Int) {
                        MyObject.user = user
                        findNavController().navigate(R.id.itemAboutFragment)
                    }

                    override fun liked(user: User, position: Int) {
                        if (user.liked == 0) {
                            user.liked = 1
                            myDBHelper.updateUser(user)
                            myUnivrsalAdapter.notifyItemChanged(position)
                        } else {
                            user.liked = 0
                            myDBHelper.updateUser(user)
                            myUnivrsalAdapter.notifyItemChanged(position)
                        }
                    }

                    override fun edit(user: User, position: Int) {
                        MyObject.position = 2
                        MyObject.user = user
                        findNavController().navigate(R.id.addFragment)
                    }

                    override fun delete(user: User, position: Int) {
                        myDBHelper.deleteUser(user)
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.mainFragment)
                    }
                })
            rvBuyuruvchi.adapter = myUnivrsalAdapter

            return root
        }
    }
}