package developer.abdulaziz.traffic_laws.Fragments.HelperFragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.traffic_laws.Object.MyObject
import developer.abdulaziz.traffic_laws.databinding.FragmentItemAboutBinding

class ItemAboutFragment : Fragment() {
    private lateinit var binding: FragmentItemAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemAboutBinding.inflate(layoutInflater)
        binding.apply {
            val user = MyObject.user

            title.text = user.name
            image.setImageURI(Uri.parse(user.imagePath))
            name.text = user.name
            about.text = user.about
            back.setOnClickListener { findNavController().popBackStack() }

            return root
        }
    }
}