package com.example.atfda_juannsuassuna.sadlist.ui.lista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.atfda_juannsuassuna.MainSadListViewModel
import com.example.atfda_juannsuassuna.R
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.model.SadList
import com.example.atfda_juannsuassuna.sadlist.ui.factory.SadListViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.sad_list_fragment.*

class SadListFragment : Fragment() {

    private lateinit var sadListViewModell: SadListViewModel
    private lateinit var mainSadListViewModel: MainSadListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.sad_list_fragment, container, false)

        sadListViewModell =
            ViewModelProvider(this, SadListViewModelFactory(SadListDatabase.getInstance()))
                .get(SadListViewModel::class.java)

        sadListViewModell.sadlists.observe(viewLifecycleOwner) {
            if(!it.isNullOrEmpty())
                adaptarListView(it)
            else
                showSnackbar("Nenhuma musica foi encontrada.")
        }

        mainSadListViewModel =
            ViewModelProvider(requireActivity())
                .get(MainSadListViewModel:: class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sadListViewLayout.setOnItemClickListener{
            adaptarListView, view, i, l ->
            var sadlist = sadListViewModell.sadlists.value!!.get(i)
            mainSadListViewModel.selectMus(sadlist)
            findNavController().navigate(R.id.showSadListFragment)
        }
        buttonSadListAdd.setOnClickListener{
            mainSadListViewModel.selectMus(null)
            findNavController().navigate(R.id.createSadListFragment)
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
                sadlistLayoutRoot,
                msg,
                Snackbar.LENGTH_LONG
        ).show()
    }

    private fun adaptarListView(it: List<SadList>) {
        sadListViewLayout.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
        )
    }
}
