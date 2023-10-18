package github.sachin2dehury.shoppingapp.fav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.shoppingapp.R
import github.sachin2dehury.shoppingapp.data.Item
import github.sachin2dehury.shoppingapp.databinding.FragmentSimpleRvBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavFragment : Fragment(R.layout.fragment_simple_rv), FavClickListener {

    private val viewModel: FavViewModel by viewModels()

    private var binding: FragmentSimpleRvBinding? = null

    private val adapter = FavAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSimpleRvBinding.bind(view)

        binding?.recyclerView?.adapter = adapter
        binding?.tvTitle?.text = "Fav"
        binding?.ivMenu?.setOnClickListener {
            requireActivity().onBackPressed()
        }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            viewModel.favData.collectLatest {
                adapter.differ.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun unLike(item: Item) {
        viewModel.removeItem(item)
    }

    override fun moveToCart(item: Item) {
        viewModel.moveToCart(item)
    }
}