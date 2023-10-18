package github.sachin2dehury.shoppingapp.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.sachin2dehury.shoppingapp.databinding.ItemFavBinding

class FavAdapter(private val listener: FavClickListener) : RecyclerView.Adapter<FavViewHolder>() {

    val differ = AsyncListDiffer(this, FavDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val item = differ.currentList.getOrNull(position) ?: return
        with(holder.binding) {
            ivIcon.load(item.icon)
            tvTitle.text = item.name
            tvSubtitle.text = "₹ ${item.price}"
            ivHeart.isSelected = true
            ivHeart.setOnClickListener { listener.unLike(item) }
            ivCart.setOnClickListener { listener.moveToCart(item) }
        }
    }
}

