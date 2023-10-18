package github.sachin2dehury.shoppingapp.category.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import github.sachin2dehury.shoppingapp.R
import github.sachin2dehury.shoppingapp.data.Category
import github.sachin2dehury.shoppingapp.databinding.FragmentCategoryDialogBinding

class CategoryDialogFragment : DialogFragment(R.layout.fragment_category_dialog) {

    private var binding: FragmentCategoryDialogBinding? = null

    private var listener: CategoryClickListener? = null

    private var categories: List<Category?>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryDialogBinding.bind(view)

        val adapter = CategoryDialogAdapter(listener)
        adapter.differ.submitList(categories)
        binding?.recyclerView?.adapter = adapter
        binding?.ivCross?.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    companion object {
        const val TAG = "CategoryDialogFragment"
        fun getInstance(data: List<Category?>?, clickListener: CategoryClickListener) =
            CategoryDialogFragment().apply {
                categories = data
                listener = clickListener
            }
    }
}