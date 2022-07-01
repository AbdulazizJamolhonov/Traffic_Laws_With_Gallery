package developer.abdulaziz.traffic_laws.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.traffic_laws.Classes.User
import developer.abdulaziz.traffic_laws.R
import developer.abdulaziz.traffic_laws.databinding.ItemUniversalBinding

class MyUnivrsalAdapter(
    private var list: ArrayList<User>,
    private val myClickListener: MyClickListener
) :
    RecyclerView.Adapter<MyUnivrsalAdapter.ViewHolderUser>() {

    inner class ViewHolderUser(private val binding: ItemUniversalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User, position: Int) {
            binding.apply {
                image.setImageURI(Uri.parse(user.imagePath))
                name.text = user.name
                if (user.liked == 1) like.setImageResource(R.drawable.like)
                else like.setImageResource(R.drawable.dislike)
                item.setOnClickListener { myClickListener.click(user, position) }
                like.setOnClickListener { myClickListener.liked(user, position) }
                edit.setOnClickListener { myClickListener.edit(user, position) }
                delete.setOnClickListener { myClickListener.delete(user, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser =
        ViewHolderUser(
            ItemUniversalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(hol: ViewHolderUser, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size

    interface MyClickListener {
        fun click(user: User, position: Int)
        fun liked(user: User, position: Int)
        fun edit(user: User, position: Int)
        fun delete(user: User, position: Int)
    }
}