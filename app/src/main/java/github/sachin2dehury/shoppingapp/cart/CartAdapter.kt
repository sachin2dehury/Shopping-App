package github.sachin2dehury.shoppingapp.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import coil.load
import github.sachin2dehury.shoppingapp.databinding.ItemCartBinding

class CartAdapter(private val listener: CartClickListener) :
    RecyclerView.Adapter<CartViewHolder>() {

    val differ = AsyncListDiffer(this, CartDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

