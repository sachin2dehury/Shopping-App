package github.sachin2dehury.shoppingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.sachin2dehury.shoppingapp.databinding.ItemCartBinding

class CartAdapter(private val listener: CartClickListener) :
    RecyclerView.Adapter<CartViewHolder>() {

    val differ = AsyncListDiffer(this, CartDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context))
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = differ.currentList.getOrNull(position) ?: return
        val price = item.quantity * (item.price ?: 0.0)
        with(holder.binding) {
            tvTitle.text = item.name
            tvSubtitle.text = "₹ $price"
            tvQuantity.text = "${item.quantity}"
            ivIcon.load(item.icon)
            ivPlus.setOnClickListener { listener.plus(item) }
            ivMinus.setOnClickListener { listener.minus(item) }
        }
    }
}

interface CartClickListener {
    fun plus(item: CartItem)
    fun minus(item: CartItem)
}

class CartViewHolder(val binding: ItemCartBinding) :
    RecyclerView.ViewHolder(binding.root)

class CartDiffer : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
        return oldItem == newItem
    }
}