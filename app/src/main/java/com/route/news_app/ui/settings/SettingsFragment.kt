package com.route.news_app.ui.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.route.news_app.R
import com.route.news_app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var viewBinding: FragmentSettingsBinding
    lateinit var viewModel: SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
        initViews()
    }

    private fun initViews() {
        viewModel.apply {
            checkTheme(requireContext().resources)
        }
        viewModel.enableDarkTheme.observe(viewLifecycleOwner) {
            viewBinding.modeSwitch.setOnCheckedChangeListener { btn, isChecked ->
                if (!isChecked) {
                    changeTheme()
                } else {
                    changeTheme()
                }
            }
        }

        changeLanguage()
    }

    private fun changeTheme() {
        viewModel.changeTheme(requireContext().resources)
    }

    private fun changeLanguage() {
        val languages: Array<String> = resources.getStringArray(R.array.languages)
        val adapter =
            ArrayAdapter<String>(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, languages
            )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        viewBinding.languageSpinner.adapter = adapter
        viewBinding.languageSpinner.setSelection(0)
        viewBinding.languageSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    itemView: View?,
                    position: Int,
                    id: Long
                ) {
                    val language = parent?.getItemAtPosition(position).toString()
                    if (language.equals("English")) {
                        viewModel.changeLanguage("en", requireContext().resources)
                        restartActivity()
                    } else if (language.equals("Arabic")) {
                        viewModel.changeLanguage("ar", requireContext().resources)
                        restartActivity()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    fun restartActivity() {
        activity?.finish()
        activity?.startActivity(activity?.intent)

    }
}