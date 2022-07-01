package developer.abdulaziz.traffic_laws.Fragments.MainFragments

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
import developer.abdulaziz.traffic_laws.databinding.FragmentLikedBinding

class LikedFragment : Fragment() {
    private lateinit var binding: FragmentLikedBinding
    private lateinit var myUnivrsalAdapter: MyUnivrsalAdapter
    private lateinit var myDBHelper: MyDBHelper
    private lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLikedBinding.inflate(layoutInflater)
        binding.apply {

            myDBHelper = MyDBHelper(root.context)

            list = ArrayList()
            for (i in myDBHelper.readUser()) {
                if (i.liked == 1) list.add(i)
            }

            myUnivrsalAdapter = MyUnivrsalAdapter(list, object : MyUnivrsalAdapter.MyClickListener {
                override fun click(user: User, position: Int) {
                    MyObject.user = user
                    findNavController().navigate(R.id.itemAboutFragment)
                }

                override fun liked(user: User, position: Int) {
                    user.liked = 0
                    myDBHelper.updateUser(user)

                    list = ArrayList()
                    for (i in myDBHelper.readUser()) {
                        if (i.liked == 1) list.add(i)
                    }

                    myUnivrsalAdapter.notifyItemChanged(position)
                }

                override fun edit(user: User, position: Int) {
                    MyObject.position = 3
                    MyObject.user = user
                    findNavController().navigate(R.id.addFragment)
                }

                override fun delete(user: User, position: Int) {
                    myDBHelper.deleteUser(user)
                    list.remove(user)
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.mainFragment)
                }
            })
            rvLiked.adapter = myUnivrsalAdapter

            return root
        }
    }
}