package com.example.algeb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.navigation.Navigation


class SelectOrden : Fragment(), RadioGroup.OnCheckedChangeListener {
    var x2: RadioButton? = null
    var x3: RadioButton? = null
    var x4: RadioButton? = null
    var x5: RadioButton? = null
    var radiogrup: RadioGroup? = null
    val x4b = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_orden, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        x2 = view.findViewById(R.id.x2)
        x3 = view.findViewById(R.id.x3)
        x4 = view.findViewById(R.id.x4)
        x5 = view.findViewById(R.id.x5)

        radiogrup = view.findViewById(R.id.rbOrden)

        radiogrup?.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            x2?.id -> {
                val action =
                    SelectOrdenDirections.actionSelectOrdenToMatrizLlenado(
                        2
                    )
                Navigation.findNavController(requireView()).navigate(action)
            }
            x3?.id -> {
                val actions =
                    SelectOrdenDirections.actionSelectOrdenToMatrizLlenado(
                        3
                    )
                Navigation.findNavController(requireView()).navigate(actions)
            }
            x4?.id -> {
                val action =
                    SelectOrdenDirections.actionSelectOrdenToMatrizLlenado(
                        4
                    )
                Navigation.findNavController(requireView()).navigate(action)
            }
            x5?.id -> {
                val action =
                    SelectOrdenDirections.actionSelectOrdenToMatrizLlenado(
                        5
                    )
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
    }
}

