package github.sachin2dehury.shoppingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import github.sachin2dehury.shoppingapp.databinding.ItemCategoryDialogBinding

class CategoryDialogAdapter(private val listener: CategoryClickListener?) :
    RecyclerView.Adapter<CategoryDialogViewHolder>() {

    val differ = AsyncListDiffer(this, CategoryDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDialogViewHolder {
        val binding = ItemCategoryDialogBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryDialogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryDialogViewHolder, position: Int) {
        val category = differ.currentList.getOrNull(position) ?: return
        with(holder.binding) {
            tvTitle.text = category.name
            root.setOnClickListener {
                listener?.onClick(category)
            }
        }
    }
}

interface CategoryClickListener {
    fun onClick(item: Category)
}

class CategoryDialogViewHolder(val binding: ItemCategoryDialogBinding) :
    RecyclerView.ViewHolder(binding.root)