package github.sachin2dehury.shoppingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.sachin2dehury.shoppingapp.databinding.ItemCategoryBinding

class CategoryItemAdapter : RecyclerView.Adapter<CategoryItemViewHolder>() {

    val differ = AsyncListDiffer(this, LikedItemDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val likedItem = differ.currentList.getOrNull(position) ?: return
        val item = likedItem.item
        with(holder.binding) {
            ivIcon
            ivHeart.isSelected = likedItem.liked
            tvTitle.text = item.name
            tvSubtitle.text = "₹ ${item.price}"
        }
    }
}

class CategoryItemViewHolder(val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root)

class LikedItemDiffer : DiffUtil.ItemCallback<LikedItem>() {
    override fun areItemsTheSame(oldItem: LikedItem, newItem: LikedItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: LikedItem, newItem: LikedItem): Boolean {
        return oldItem == newItem
    }
}