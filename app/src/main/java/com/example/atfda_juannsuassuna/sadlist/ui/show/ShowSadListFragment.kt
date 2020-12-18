package com.example.atfda_juannsuassuna.sadlist.ui.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.atfda_juannsuassuna.MainSadListViewModel
import com.example.atfda_juannsuassuna.R
import com.example.atfda_juannsuassuna.sadlist.database.SadListDatabase
import com.example.atfda_juannsuassuna.sadlist.model.SadList
import com.example.atfda_juannsuassuna.sadlist.ui.factory.SadListViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.show_sad_list_fragment.*

class ShowSadListFragment : Fragment() {

    private lateinit var showSadListViewModel: ShowSadListViewModel
    private lateinit var mainSadListViewModel: MainSadListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.show_sad_list_fragment, container, false)
        showSadListViewModel =
            ViewModelProvider(this, SadListViewModelFactory(
                SadListDatabase.getInstance())).get(ShowSadListViewModel::class.java)
        mainSadListViewModel =
            ViewModelProvider(requireActivity()).get(MainSadListViewModel::class.java)
        mainSadListViewModel.musica.observe(viewLifecycleOwner){updateUI(it, showSadListViewModel.status.value!!) }

        showSadListViewModel.msg.observe(viewLifecycleOwner){
            if(!it.isNullOrBlank())
                showSnackbar(it)
        }
        showSadListViewModel.status.observe(viewLifecycleOwner){
            if(it)
                mainSadListViewModel.selectMus(null)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSadListDelete.setOnClickListener {
            var musica = mainSadListViewModel.musica.value
            showSadListViewModel.delete(musica!!)
        }
        showSadListEdit.setOnClickListener {
            findNavController().navigate(R.id.createSadListFragment)
        }
    }

    private fun updateUI(sadlist: SadList?, status: Boolean) {
        if (sadlist != null) {
            textViewShowSadlistNome.text = sadlist.nome
            textViewShowSadListAutor.text = sadlist.autor
            textViewShowSadListAno.text = sadlist.ano.toString()
            textViewShowSadListNota.text = sadlist.nota.toString()
            textViewShowSadListLink.text = sadlist.link
        } else if (!status) {

            showSnackbar("Nenhum dado foi encontrado")
        }
    }

    private fun showSnackbar(msg: String) {
        Snackbar.make(
            showSadListLayoutRoot, msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showSadListViewModel = ViewModelProvider(this).get(ShowSadListViewModel::class.java)
    }

}