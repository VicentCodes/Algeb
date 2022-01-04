package com.example.algeb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlin.math.abs
import kotlin.math.roundToInt

// TODO: Rename parameter arguments, choose names that match

class MatrizLlenado : Fragment() {
    private lateinit var adaptador: ArrayAdapter<String>
    private val list: MutableList<String> = ArrayList()
    private var listPOS: MutableList<String> = ArrayList()
    private var listx: MutableList<String> = ArrayList()
    private var listInversaFrac: MutableList<String> = ArrayList()
    private var posList = 0
    private var col = 2
    var visi = false
    val dd: MatrizLlenadoArgs by navArgs<MatrizLlenadoArgs>()
    var seg = 1
    var prim = 1
    var textED: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matriz_llenado, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        col = dd.orden

        val gridInversa = view.findViewById<GridView>(R.id.grid)
        val gridMOriginal = view.findViewById<GridView>(R.id.gridOriginal)

        val edNum = view.findViewById<EditText>(R.id.edNum)
        val btnMoreOptions = view.findViewById<Button>(R.id.btnPop)
        val tvPos = view.findViewById<TextView>(R.id.tvPos)
        val tvPos2 = view.findViewById<TextView>(R.id.tvPos2)

        if (visi) {
            btnMoreOptions.isVisible = true
            edNum.isVisible = false
        } else {
            btnMoreOptions.isVisible = false
            edNum.isVisible = true

        }
        tvPos2.isVisible = !tvPos2.isVisible


        if (list.size == 0) {
            listPOS = fillGrid(col)
            adaptador = ArrayAdapter(
                requireContext(),
                R.layout.item, listPOS
            )

            gridMOriginal.adapter = adaptador
            gridMOriginal.numColumns = col
            tvPos.text = "Matriz en la posicion: [1], [1]"

        } else {
            adaptador = ArrayAdapter(
                requireContext(),
                R.layout.item, listPOS
            )
            gridMOriginal.adapter = adaptador
            gridMOriginal.numColumns = col
            if (prim == col && seg == col && list.size == col * col) {

                tvPos.text = "Matriz Completa"
            } else {
                tvPos.text = "Valor: [${prim}],[${seg}]"
            }
            if (listInversaFrac.size != 0) {
                adaptador = ArrayAdapter(
                    requireContext(),
                    R.layout.item, listInversaFrac
                )
                gridInversa.adapter = adaptador
                gridInversa.numColumns = col
            }
        }



        edNum.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                textED = edNum.text.toString()
                adaptador = ArrayAdapter(
                    requireContext(),
                    R.layout.item, list
                )
                gridMOriginal.adapter = adaptador

                if (edNum.text.toString().isNotEmpty()) {
                    if (list.size != col * col) {

                        list.add(edNum.text.toString())
                        try {

                            listx = gridRemplaze(listPOS, posList, textED)
                            listPOS.removeAt(posList)
                            listPOS.add(posList, textED)
                            adaptador = ArrayAdapter(
                                requireContext(),
                                R.layout.item, listx
                            )
                            gridMOriginal.adapter = adaptador
                        } catch (e: Exception) {
                            print("f")
                        }
                    } else {

                        btnMoreOptions.isVisible = true
                        visi = true
                    }




                    if (seg < col) {
                        seg++
                        if (seg == 1 && prim == 1) {

                            tvPos.text = "Valor: [${prim}],[${seg + 1}]"

                            posList++

                        } else {

                            tvPos.text = "Valor: [${prim}],[${seg}]"
                            posList++
                        }


                    } else {

                        if (prim == col) {
                            if (list.size == col * col) {
                                tvPos.text = "Matriz Completa"
                                tvPos2.text = "Matriz Inversa:"
                                tvPos2.isVisible = true


                                listInversaFrac = inversassFrac(col, list)
                                if (listInversaFrac.size == 0) {
                                    tvPos.text = "La matriz no tiene inversa"
                                } else {
                                    adaptador = ArrayAdapter(
                                        requireContext(),
                                        R.layout.item, listInversaFrac
                                    )
                                    gridInversa.adapter = adaptador
                                    gridInversa.numColumns = col

                                }
                                btnMoreOptions.isVisible = true
                                edNum.isVisible = false
                                visi = true


                            }
                        } else {
                            if (posList <= col * col) {


                                listx = gridRemplaze(listPOS, posList, textED)
                                listPOS.removeAt(posList)
                                listPOS.add(posList, textED)
                                adaptador = ArrayAdapter(
                                    requireContext(),
                                    R.layout.item, listx
                                )
                                gridMOriginal.adapter = adaptador
                            }

                            prim++
                            seg = 0
                            seg++
                            tvPos.text = "Valor: [${prim}],[${seg}]"
                            posList++
                            println(list.size)
                            println(" $list")
                            println("else ${list.size}")
                        }
                    }
                    edNum.text.clear()

                } else {


                    if (edNum.text.isEmpty() && list.size != col * col) {

                        try {

                            listx = gridRemplaze(listPOS, posList, textED)
                            listPOS.removeAt(posList)
                            listPOS.add(posList, textED)
                            adaptador = ArrayAdapter(
                                requireContext(),
                                R.layout.item, listx
                            )
                            gridMOriginal.adapter = adaptador
                        } catch (e: Exception) {
                            print("f")
                        }

                        Toast.makeText(
                            requireContext(),
                            getString(R.string.emptyStringNoValid),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        visi = true

                    }
                }



                return@OnKeyListener true
            }
            false
        })



        btnMoreOptions.setOnClickListener {
            val action =
                MatrizLlenadoDirections.actionMatrizLlenadoToMoreOptionsMatriz(
                    list.toTypedArray(), col
                )
            Navigation.findNavController(view).navigate(action)


        }


    }

}


fun gridRemplaze(mPos: MutableList<String>, pos: Int, newValue: String): MutableList<String> {
    val list: MutableList<String> = ArrayList()
    list.addAll(mPos)
    list.removeAt(pos)
    list.add(pos, newValue)
    return list
}

fun fillGrid(col: Int): MutableList<String> {
    val list: MutableList<String> = ArrayList()


    for (i in (0 until col)) {
        for (j in (0 until col)) {
            val x = "[${i + 1}], [${j + 1}]"
            list.add(x)
        }
    }
    return list

}

fun traspuesta(col: Int, listSTR: MutableList<String>): MutableList<String> {

    val listTraspuesta: MutableList<String> = ArrayList()

    //Matriz A
    val matriz = Array(col) { DoubleArray(col) }
    //Matriz Identidad
    var hf = 0
    // llenar la matriz
    for (i in (0 until col)) {
        for (j in (0 until col)) {
            print("Matriz en la posicion: [${i + 1}], [${j + 1}]")
            val x = listSTR[hf].toDouble()
            matriz[i][j] = x
            hf++
        }
    }

    //Crear Traspuesta
    for (i in (0 until col)) {
        for (j in (0 until col)) {
            val c = matriz[j][i]
            listTraspuesta.add(c.toString())
        }
    }
    return listTraspuesta
}


fun inversass(col: Int, listSTR: MutableList<String>): MutableList<String> {

    var aux: Double
    var piv: Double
// Se pide el numero de filas y columnas

    //Matriz A
    val matriz = Array(col) { DoubleArray(col) }
    //Matriz Identidad
    val matrizid = Array(col) { DoubleArray(col) }
    var hf = 0
    // llenar la matriz
    for (i in (0 until col)) {
        for (j in (0 until col)) {
            print("Matriz en la posicion: [${i + 1}], [${j + 1}]")
            val x = listSTR[hf].toDouble()
            matriz[i][j] = x
            hf++


            //Crear la matriz identidad
            matrizid[i][j] = 0.0
            if (i == j) {
                matrizid[i][j] = 1.0
            }
        }
    }
    val listNull: MutableList<String> = ArrayList()

    if (getDeterminante(matriz, col) == 0.0) {
        return listNull
    } else {

        var resID: Double
        val listResId: MutableList<String> = ArrayList()



        for (i in (0 until col)) {
            piv = matriz[i][i]
            //Coversir el pivote a 1 si no lo es
            for (k in (0 until col)) {
                matriz[i][k] = ((matriz[i][k] / piv))
                matrizid[i][k] = ((matrizid[i][k] / piv))
            }

            for (j in (0 until col)) {
                if (i != j) {
                    aux = matriz[j][i]
                    for (k in (0 until col)) {
                        matriz[j][k] = (matriz[j][k] - aux * matriz[i][k])
                        resID = (matrizid[j][k] - aux * matrizid[i][k])
                        matrizid[j][k] = resID


                    }
                }
            }
        }

        for (i in (0 until col)) {
            for (j in (0 until col)) {
                val c = matrizid[i][j]
                listResId.add(c.toString())
            }
        }
        return listResId

    }
}


fun toFraction(d: Double): String {
    val negligibleRatio = 0.00000001

    var i = 1
    while (true) {
        val tem = d / (1.0 / i)
        if (abs(tem - tem.roundToInt()) < negligibleRatio) {
            val x = tem.roundToInt() to i
            var a = x.toString().drop(1)
            a = a.dropLast(1)
            val asd = a.replace(",", "/")
            if (asd.replace("\\s".toRegex(), "").equals("0/1")) {
                return d.toString()
            } else {
                return asd.replace("\\s".toRegex(), "")
            }
        }
        i++
    }
}

fun decimalB(string: Double): Boolean {
    return string - string.toInt() != 0.0
}

fun decimalA(string: String): String {
    return try {
        val doubleAsString: String = java.lang.String.valueOf(string)
        val indexOfDecimal = doubleAsString.indexOf(".")
        doubleAsString.substring(0, indexOfDecimal)
    } catch (e: java.lang.Exception) {
        string
    }
}

fun toIntsDiv(operacion: String): String {
    var rA = operacion

    rA = if (!decimalB(rA.toDouble())) {
        decimalA(rA)
    } else {
        toFraction(rA.toDouble())
    }

    return rA
}

fun toDecimall(ratio: String): Double {
    return if (ratio.contains("/")) {
        val rat = ratio.split("/").toTypedArray()
        rat[0].toDouble() / rat[1].toDouble()
    } else {
        ratio.toDouble()
    }
}

fun inversassFrac(col: Int, listSTR: MutableList<String>): MutableList<String> {


    val newList: MutableList<String> = ArrayList()
    val listNull: MutableList<String> = ArrayList()

    newList.addAll(listSTR)
    var pos = 0
    for (i in newList) {
        val elem = toDecimall(i)
        listSTR.removeAt(pos)
        listSTR.add(pos, elem.toString())
        pos++

    }
    pos = 0

    var aux: Double
    var piv: Double
// Se pide el numero de filas y columnas

    //Matriz A
    val matriz = Array(col) { DoubleArray(col) }
    //Matriz Identidad
    val matrizid = Array(col) { DoubleArray(col) }
    var hf = 0
    // llenar la matriz
    for (i in (0 until col)) {
        for (j in (0 until col)) {
            print("Matriz en la posicion: [${i + 1}], [${j + 1}]")
            val x = listSTR[hf].toDouble()
            matriz[i][j] = x
            hf++


            //Crear la matriz identidad
            matrizid[i][j] = 0.0
            if (i == j) {
                matrizid[i][j] = 1.0
            }
        }
    }

    if (getDeterminante(matriz, col) == 0.0) {
        return listNull
    } else {


        var resID: Double
        val listResId: MutableList<String> = ArrayList()



        for (i in (0 until col)) {
            piv = matriz[i][i]
            //Coversir el pivote a 1 si no lo es
            for (k in (0 until col)) {
                matriz[i][k] = ((matriz[i][k] / piv))
                matrizid[i][k] = ((matrizid[i][k] / piv))
            }

            for (j in (0 until col)) {
                if (i != j) {
                    aux = matriz[j][i]
                    for (k in (0 until col)) {
                        matriz[j][k] = (matriz[j][k] - aux * matriz[i][k])
                        resID = (matrizid[j][k] - aux * matrizid[i][k])
                        matrizid[j][k] = resID


                    }
                }
            }
        }
        println()
        for (i in (0 until col)) {
            for (j in (0 until col)) {
                val c = matrizid[i][j]
                listResId.add(c.toString())
            }
        }


        for (i in listResId) {
            val elem = toIntsDiv(i)
            newList.removeAt(pos)
            newList.add(pos, elem)
            pos++
        }
        return newList

    }
}






