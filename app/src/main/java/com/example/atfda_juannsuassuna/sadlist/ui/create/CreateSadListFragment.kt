package com.example.atfda_juannsuassuna.sadlist.ui.create

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
import com.example.atfda_juannsuassuna.sadlist.ui.factory.SadListViewModelFactory
import com.example.atfda_juannsuassuna.sadlist.ui.lista.SadListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.create_sad_list_fragment.*

class CreateSadListFragment : Fragment() {


    private lateinit var createSadListViewModel: CreateSadListViewModel
    private lateinit var mainViewModel: MainSadListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.create_sad_list_fragment, container, false)

        createSadListViewModel = ViewModelProvider(
            this,SadListViewModelFactory(
                SadListDatabase.getInstance())).get(CreateSadListViewModel::class.java)
        createSadListViewModel.let {
            it.status.observe(viewLifecycleOwner)
            {
                if (it){
                findNavController().popBackStack()
                }
            }
            it.msg.observe(viewLifecycleOwner){
                if (!it.isNullOrBlank())
                    Snackbar.make(
                        createSadListLayoutRoot, it, Snackbar.LENGTH_LONG
                    ).show()
            }
        }

        mainViewModel = ViewModelProvider(requireActivity()).get(MainSadListViewModel::class.java)

        mainViewModel.musica.observe(viewLifecycleOwner){
            if (it!=null) {
                editTextCreateNome.setText(it.nome)
                editTextCreateAutor.setText(it.autor)
                editTextCreateLink.setText(it.link)
                editTextDate.setText(it.ano.toString())
                editTextNota.setText(it.nota.toString())
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSadListSave.setOnClickListener{
            createSadListViewModel.store(
                editTextCreateNome.text.toString(),
                editTextCreateAutor.text.toString(),
                editTextCreateLink.text.toString(),
                editTextDate.text.toString(),
                editTextNota.text.toString(),
                mainViewModel.musica.value
            )

        }
    }

}