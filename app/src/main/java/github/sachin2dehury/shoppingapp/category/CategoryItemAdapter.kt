package github.sachin2dehury.shoppingapp.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.sachin2dehury.shoppingapp.databinding.ItemCategoryBinding

class CategoryItemAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<CategoryItemViewHolder>() {

    val differ = AsyncListDiffer(this, LikedItemDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val likedItem = differ.currentList.getOrNull(position) ?: return
        val item = likedItem.item
        with(holder.binding) {
            ivIcon.load(item.icon)
            ivHeart.isSelected = likedItem.liked
            tvTitle.text = item.name
            tvSubtitle.text = "₹ ${item.price}"
            ivHeart.setOnClickListener {
                ivHeart.isSelected = !ivHeart.isSelected
                listener.toggleLike(item)
            }
            ivCart.setOnClickListener { listener.addToCart(item) }
        }
    }
}

