package com.batdemir.stackexchange.ui.main

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.batdemir.stackexchange.R
import com.batdemir.stackexchange.data.entities.db.UserModel
import com.batdemir.stackexchange.databinding.FragmentMainBinding
import com.batdemir.stackexchange.ui.MainActivity
import com.batdemir.stackexchange.ui.adapter.UserAdapter
import com.batdemir.stackexchange.ui.base.BaseFragment
import com.batdemir.stackexchange.utils.hideKeyboard
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    @Inject
    lateinit var viewModel: MainViewModel
    private val adapter: UserAdapter by lazy {
        UserAdapter(object : UserAdapter.ItemListener {
            override fun onClick(model: UserModel) {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(
                        model
                    )
                )
            }
        })
    }
    private var searchJob: Job? = null

    //Logical

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
            viewModel.search(query).collect {
                adapter.submitData(it)
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding!!.viewEmpty.visibility = View.VISIBLE
            binding!!.recyclerView.visibility = View.GONE
        } else {
            binding!!.viewEmpty.visibility = View.GONE
            binding!!.recyclerView.visibility = View.VISIBLE
        }
    }

    //Override

    override fun inject() {
        (requireActivity() as MainActivity).mainComponent?.inject(this)
    }

    override fun setupDefinition(savedInstanceState: Bundle?) {
        binding!!.adapter = adapter
    }

    override fun setupData() {
        //("Not yet implemented")
    }

    override fun setupListener() {
        binding!!.inputLayoutSearch.editText!!.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                binding!!.inputLayoutSearch.editText!!.text!!.trim().let {
                    if (it.isNotEmpty()) {
                        binding!!.recyclerView.scrollToPosition(0)
                        search(it.toString())
                    }
                }
                true
            } else {
                false
            }
        }

        adapter.addLoadStateListener { loadState ->
            when {
                loadState.append is LoadState.Loading ||
                        loadState.refresh is LoadState.Loading ||
                        loadState.prepend is LoadState.Loading
                -> binding!!.progressBar.visibility = View.VISIBLE
                else -> {
                    binding!!.progressBar.visibility = View.GONE
                    showEmptyList(adapter.itemCount == 0)
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    error?.let {
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}