package com.example.algeb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MoreOptionsMatriz.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreOptionsMatriz : Fragment() , RadioGroup.OnCheckedChangeListener {
    val args: MoreOptionsMatrizArgs by navArgs<MoreOptionsMatrizArgs>()

    private lateinit var adaptador: ArrayAdapter<String>

    var rbdeter: RadioButton? = null
    var rbTraspuesta: RadioButton? = null
    var rbInversaDecimal: RadioButton? = null
    var rbMatrizOrgin: RadioButton? = null
    var radiogrup: RadioGroup? = null
    var hf = 0
    var tvTitulo: TextView? = null
    var tvDeter: TextView? = null
    var determinante = 0.0
    var inversaNula:String = ""
    var gridMOriginal: GridView? = null
    var Lista: MutableList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_options_matriz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val matriz = Array(args.orden) { DoubleArray(args.orden) }
        println("Holaaaa ${args.orden}")

        Lista = args.lista.toMutableList()

        gridMOriginal = view.findViewById<GridView>(R.id.gridOriginals)



        tvDeter = view.findViewById<TextView>(R.id.determinateResul)

        rbdeter = view.findViewById(R.id.rbDeterminante)
        rbTraspuesta = view.findViewById(R.id.rbTraspuesta)
        rbInversaDecimal = view.findViewById(R.id.rbInversaDecil)
        radiogrup = view.findViewById(R.id.radiogrup)
        tvTitulo = view.findViewById<TextView>(R.id.tvTituloOps)
        radiogrup?.setOnCheckedChangeListener(this)


        for (i in (0 until args.orden)) {
            for (j in (0 until args.orden)) {
                val x = Lista[hf].toDouble()
                matriz[i][j] = x
                hf++
            }
        }
        determinante = getDeterminante(matriz, args.orden)

        adaptador = ArrayAdapter(
            requireContext(),
            R.layout.item, Lista
        )
        println(Lista)
        gridMOriginal!!.adapter = adaptador
        gridMOriginal!!.numColumns = args.orden





        tvTitulo?.let {
            gridMOriginal?.let { it1 -> rbAction(it, getString(R.string.Determinante) ,tvDeter!!, it1, toIntsDiv(determinante.toString()),true) }
        }


    }

    @SuppressLint("SetTextI18n")
    fun rbAction(text: TextView, valorTitulo:String?, textDeter: TextView?, grid: GridView, valor: String?, tvVisible:Boolean) {
        text.text = valorTitulo
        if (tvVisible) {
            textDeter?.isVisible  = true

            textDeter?.text = "| x | = $valor"
        }else{
            textDeter!!.isVisible = false
        }

    }

    override fun onCheckedChanged(p0: RadioGroup?, idRadio: Int) {
        when (idRadio) {
            rbdeter?.id -> tvTitulo?.let {
                adaptador = ArrayAdapter(
                    requireContext(),
                    R.layout.item, Lista
                )
                println(Lista)
                gridMOriginal!!.adapter = adaptador
                gridMOriginal!!.numColumns = args.orden

                tvTitulo!!.text = getString(R.string.Determinante)
                tvDeter!!.text = "|x| = ${toIntsDiv(determinante.toString())}"

            }
            rbTraspuesta?.id -> tvTitulo?.let {
                adaptador = ArrayAdapter(
                    requireContext(),
                    R.layout.item, traspuesta(args.orden, Lista)
                )
                println(Lista)
                gridMOriginal!!.adapter = adaptador
                gridMOriginal!!.numColumns = args.orden
                tvDeter?.text = ""
                tvTitulo!!.text = getString(R.string.MatrizTraspuesta)
            }
            rbInversaDecimal?.id -> tvTitulo?.let {

                if (inversass(args.orden, Lista).size == 0) {
                    tvDeter?.text  = "La matriz no tiene inversa"
                }else {
                    adaptador = ArrayAdapter(
                        requireContext(),
                        R.layout.item, inversass(args.orden, Lista)
                    )
                    println(Lista)
                    gridMOriginal!!.adapter = adaptador
                    gridMOriginal!!.numColumns = args.orden
                }
                tvDeter?.text = ""

                tvTitulo!!.text = getString(R.string.matrizInversaDecimal)
            }


        }
    }
}

fun getCofactor(
    mat: Array<DoubleArray>, temp: Array<DoubleArray>,
    p: Int, q: Int, n: Int
) {
    var i = 0
    var j = 0

    // Bucle para cada elemento de
    // la matriz
    for (row in 0 until n) {
        for (col in 0 until n) {
            if (row != p && col != q) {
                temp[i][j++] = mat[row][col]
                if (j == n - 1) {
                    j = 0
                    i++
                }
            }
        }
    }
}

fun getDeterminante(mat: Array<DoubleArray>, n: Int): Double {
    var D = 0.0
    if (n == 1) return mat[0][0]
    val temp = Array(n) { DoubleArray(n) }

    var sign = 1

    for (f in 0 until n) {
        // Gobtener cofactor
        getCofactor(mat, temp, 0, f, n)
        D += (sign * mat[0][f]
                * getDeterminante(temp, n - 1))

        // alternar signo
        sign = -sign
    }
    return D
}
