package github.sachin2dehury.shoppingapp.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.shoppingapp.R
import github.sachin2dehury.shoppingapp.data.CartItem
import github.sachin2dehury.shoppingapp.databinding.FragmentSimpleRvBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_simple_rv), CartClickListener {

    private val viewModel: CartViewModel by viewModels()

    private var binding: FragmentSimpleRvBinding? = null

    private val adapter = CartAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSimpleRvBinding.bind(view)

        binding?.recyclerView?.adapter = adapter
        binding?.tvTitle?.text = "Cart"
        binding?.ivMenu?.setOnClickListener {
            requireActivity().onBackPressed()
        }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            viewModel.cartData.collectLatest {
                adapter.differ.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun plus(item: CartItem) {
        viewModel.incrementItem(item)
    }

    override fun minus(item: CartItem) {
        viewModel.decrementItem(item)
    }
}