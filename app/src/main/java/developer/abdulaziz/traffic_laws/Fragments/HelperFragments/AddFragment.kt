package developer.abdulaziz.traffic_laws.Fragments.HelperFragments

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.traffic_laws.Classes.User
import developer.abdulaziz.traffic_laws.Database.MyDBHelper
import developer.abdulaziz.traffic_laws.Object.MyObject
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.FragmentAddBinding
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var myDBHelper: MyDBHelper
    private var imagePath: String? = null
    private val user = MyObject.user
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater)
        binding.apply {

            myDBHelper = MyDBHelper(root.context)

            spinner.adapter = ArrayAdapter(
                root.context,
                android.R.layout.simple_expandable_list_item_1,
                MyObject.list
            )

            image.setOnClickListener { getImageContent.launch("image/*") }

            when (MyObject.position) {
                1 -> {
                    binding.apply {
                        back.setOnClickListener { findNavController().popBackStack() }
                        save.setOnClickListener {
                            val image = imagePath
                            val name = name.text.toString()
                            val about = about.text.toString()
                            if (image != null && name.isNotEmpty() && about.isNotEmpty()) {
                                val user = User(
                                    image,
                                    name,
                                    about,
                                    spinner.selectedItemPosition,
                                    0
                                )
                                myDBHelper.createUser(user)
                                findNavController().popBackStack()
                            } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                2 -> myFun(my1 = false)
                else -> myFun(my1 = true)
            }

            return root
        }
    }

    private fun myFun(my1: Boolean) {
        binding.apply {
            image.setImageURI(Uri.parse(user.imagePath))
            imagePath = user.imagePath
            name.setText(user.name)
            about.setText(user.about)

            back.setOnClickListener {
                findNavController().popBackStack()
                if (my1) findNavController().navigate(R.id.likedFragment)
            }
            save.setOnClickListener {
                val image = imagePath
                val name = name.text.toString()
                val about = about.text.toString()
                if (image != null && name.isNotEmpty() && about.isNotEmpty()) {
                    user.imagePath = image
                    user.name = name
                    user.about = about
                    user.typeId = spinner.selectedItemPosition
                    myDBHelper.updateUser(user)

                    findNavController().popBackStack()
                    if (my1) findNavController().navigate(R.id.likedFragment)
                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SdCardPath")
    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.image.setImageURI(it)
            val imageName = SimpleDateFormat("ddMMyyyy_hh:mm:ss").format(Date())
            val file = File(requireActivity().filesDir, "$imageName.jpg")
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            imagePath = file.absolutePath
        }
}