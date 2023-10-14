package github.sachin2dehury.shoppingapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.shoppingapp.databinding.FragmentCategoryBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category), ItemClickListener,
    CategoryClickListener {

    private val viewModel: CategoryViewModel by viewModels()

    private var binding: FragmentCategoryBinding? = null

    private val adapter = CategoryAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)

        binding?.tvTitle?.text = "Category"
        binding?.recyclerView?.adapter = adapter
        binding?.ivCart?.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_cartFragment)
        }
        binding?.ivFav?.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_favFragment)
        }
        binding?.ivMenu?.setOnClickListener {
            CategoryDialogFragment.getInstance(viewModel.categoryData.value, this).also {
                it.show(childFragmentManager, CategoryDialogFragment.TAG)
            }
        }
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            viewModel.categoryData.collectLatest {
                adapter.differ.submitList(it)
            }
        }
        lifecycleScope.launch {
            viewModel.favData.collectLatest {
                adapter.likedSet = it?.map { it.id }?.toSet()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun toggleLike(item: Item) {
        viewModel.toggleFav(item)
    }

    override fun addToCart(item: Item) {
        viewModel.addToCart(item)
    }

    override fun onClick(item: Category) {
        val index = adapter.differ.currentList.indexOf(item)
        binding?.recyclerView?.scrollToPosition(index)
    }
}