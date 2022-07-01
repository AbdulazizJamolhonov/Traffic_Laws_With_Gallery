package developer.abdulaziz.traffic_laws.Fragments.HelperFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        binding.apply {

            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.my_splash_anim)
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    findNavController().navigate(R.id.mainFragment)
                }

                override fun onAnimationRepeat(p0: Animation?) {

                }
            })
            binding.textSplash.startAnimation(animation)

            return root
        }
    }
}