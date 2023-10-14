package github.sachin2dehury.shoppingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import github.sachin2dehury.shoppingapp.databinding.ItemRecyclerViewBinding

class CategoryAdapter(private val listener: ItemClickListener) : RecyclerView.Adapter<CategoryViewHolder>() {

    val differ = AsyncListDiffer(this, CategoryDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
        val adapter = CategoryItemAdapter(listener)
        return CategoryViewHolder(binding, adapter)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = differ.currentList.getOrNull(position) ?: return
        val items = category.items ?: return
        val likedItems = items.filterNotNull().map { LikedItem(it, false) }
        with(holder.binding) {
            recyclerView.adapter = holder.adapter
            holder.adapter.differ.submitList(likedItems)
            tvTitle.text = category.name
        }
    }
}

class CategoryViewHolder(
    val binding: ItemRecyclerViewBinding,
    val adapter: CategoryItemAdapter
) : RecyclerView.ViewHolder(binding.root)

class CategoryDiffer : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}