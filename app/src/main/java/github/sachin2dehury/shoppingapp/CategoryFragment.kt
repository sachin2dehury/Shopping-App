package github.sachin2dehury.shoppingapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import github.sachin2dehury.shoppingapp.databinding.FragmentCategoryBinding

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val viewModel: CategoryViewModel by viewModels()

    private var binding: FragmentCategoryBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)


    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}