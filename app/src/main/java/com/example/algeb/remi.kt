package com.example.algeb
import java.lang.String.valueOf
import kotlin.math.roundToInt
import kotlin.math.abs

class logd {


    fun decimalB(string: Double): Boolean {
        return string - string.toInt() != 0.0
    }

    fun decimalA(string: String): String {
        val doubleAsString: String = valueOf(string)
        val indexOfDecimal = doubleAsString.indexOf(".")
        return doubleAsString.substring(0, indexOfDecimal)
    }

    fun getA(com: String): String {
        val posParA = com.indexOf(')') + 1
        val A = com.substring(0, posParA)
        return A
    }

    fun getB(com: String): String {
        val posParA = com.lastIndexOf('(')
        val posParA1 = com.lastIndexOf(')') + 1
        val A = com.substring(posParA, posParA1)
        return A
    }

    fun getOperacion(com: String): String {
        val posParA = com.indexOf(')') + 1
        val posParA1 = com.lastIndexOf('(')
        val A = com.substring(posParA, posParA1)
        return A
    }

    fun getOperacionA(com: String): Boolean {
        return com.contains("+")

    }

    fun getRealDiv(r: String): String {
        var a = r.drop(1)
        a = a.dropLast(1)

        val postion_plus: Int = a.lastIndexOf('+')
        val posMul: Int = a.lastIndexOf('*')
        val posDiv: Int = a.lastIndexOf('/')
        val postion_neg: Int = a.lastIndexOf('-')



        if (postion_plus == -1) {
            if (posMul == -1) {
                if (posDiv != -1) {
                    a = a.substring(0, posDiv)
                } else {
                    a = a.substring(0, postion_neg)
                }
            } else {
                a = a.substring(0, posMul)
            }

        } else {
            a = a.substring(0, postion_plus)
        }

        return a

    }

    fun getReal(r: String): String {
        var a = r.drop(1)
        a = a.dropLast(1)

        val postion_plus: Int = a.lastIndexOf('+')
        val posMul: Int = a.lastIndexOf('*')
        val posDiv: Int = a.lastIndexOf('&')
        val postion_neg: Int = a.lastIndexOf('-')



        if (postion_plus == -1) {
            if (posMul == -1) {
                if (posDiv != -1) {
                    a = a.substring(0, posDiv)
                } else {
                    a = a.substring(0, postion_neg)
                }
            } else {
                a = a.substring(0, posMul)
            }

        } else {
            a = a.substring(0, postion_plus)
        }

        return a

    }

    fun getImaDiv(r: String): String {
        var a = r.drop(1)
        a = a.dropLast(1)

        val pos_Plus: Int = a.lastIndexOf('+')
        val posNeg: Int = a.lastIndexOf('-')
        val posMul: Int = a.lastIndexOf('*')
        val posDiv: Int = a.lastIndexOf('/')
        val posI: Int = a.indexOf('i')

        if (pos_Plus == -1) {
            if (posMul == -1) {
                if (posDiv != -1) {
                    a = a.substring(posDiv + 1, posI)
                } else {
                    a = a.substring(posNeg, posI)
                }
            } else {
                a = a.substring(posMul + 1, posI)
            }

        } else {
            a = a.substring(pos_Plus, posI)
        }

        return a

    }

    fun getIma(r: String): String {
        var a = r.drop(1)
        a = a.dropLast(1)

        val pos_Plus: Int = a.lastIndexOf('+')
        val posNeg: Int = a.lastIndexOf('-')
        val posMul: Int = a.lastIndexOf('*')
        val posDiv: Int = a.lastIndexOf('&')
        val posI: Int = a.indexOf('i')

        if (pos_Plus == -1) {
            if (posMul == -1) {
                if (posDiv != -1) {
                    a = a.substring(posDiv + 1, posI)
                } else {
                    a = a.substring(posNeg, posI)
                }
            } else {
                a = a.substring(posMul + 1, posI)
            }

        } else {
            a = a.substring(pos_Plus, posI)
        }

        return a

    }

    fun sumaComplejos(
        realA: String,
        realB: String,
        imaA: String,
        imaB: String,
        opera: String
    ): String {

        // (32+55i) + (65+9i)
        val rA: Double
        val rB: Double
        val iA: Double
        val iB: Double
        if (realA.contains("/")) {
            rA = toDecimal(realA)
        } else {
            rA = realA.toDouble()
        }
        if (realB.contains("/")) {
            rB = toDecimal(realB)
        } else {
            rB = realB.toDouble()
        }
        if (imaA.contains("/")) {
            iA = toDecimal(imaA)
        } else {
            iA = imaA.toDouble()
        }
        if (imaB.contains("/")) {
            iB = toDecimal(imaB)
        } else {
            iB = imaB.toDouble()
        }

        val sumaReal: Double
        val sumaIma: Double
        if (opera == "+") {
            sumaReal = rA + rB
            sumaIma = iA + iB
        } else {
            sumaReal = rA - rB
            sumaIma = iA - iB
        }
        if (sumaIma < 0) {
            return "(${sumaReal}${sumaIma}i)"
        } else {
            return "(${sumaReal}+${sumaIma}i)"
        }
    }

    fun multiComplejos(realA: String, realB: String, imaA: String, imaB: String): String {

        val rA: Double
        val rB: Double
        val iA: Double
        val iB: Double

        if (realA.contains("/")) {
            rA = toDecimal(realA)
        } else {
            rA = realA.toDouble()
        }
        if (realB.contains("/")) {
            rB = toDecimal(realB)
        } else {
            rB = realB.toDouble()
        }
        if (imaA.contains("/")) {
            iA = toDecimal(imaA)
        } else {
            iA = imaA.toDouble()
        }
        if (imaB.contains("/")) {
            iB = toDecimal(imaB)
        } else {
            iB = imaB.toDouble()
        }
        val A = rA * rB - iA * iB
        val b = rA * iB + iA * rB

        if (b < 0) {
            return "(${A}${b}i)"
        } else {
            return "(${A}+${b}i)"
        }
    }

    fun doubleToFractionDiv(operacionComp: String): String {
        val A = getA(operacionComp)
        var rA = getRealDiv(A)
        var iA = getImaDiv(A)

        if (!decimalB(rA.toDouble())) {
            rA = decimalA(rA)
        } else {
            rA = toFraction(rA.toDouble())
        }
        if (!decimalB(iA.toDouble())) {
            iA = decimalA(iA)
        } else {
            iA = toFraction(iA.toDouble())
        }


        if (getOperacionA(operacionComp)) {
            if (getOperacionA("(${rA}${iA}i)")) {
                return "(${rA}${iA}i)"
            } else {
                return "(${rA}+${iA}i)"
            }
        } else {
            return "(${rA} ${iA}i)"

        }

    }


    fun doubleToFraction(operacionComp: String): String {
        val A = getA(operacionComp)
        var rA = getReal(A)
        var iA = getIma(A)

        if (!decimalB(rA.toDouble())) {
            rA = decimalA(rA)
        } else {
            rA = toFraction(rA.toDouble())
        }
        if (!decimalB(iA.toDouble())) {
            iA = decimalA(iA)
        } else {
            iA = toFraction(iA.toDouble())
        }


        if (getOperacionA(operacionComp)) {
            if (getOperacionA("(${rA}${iA}i)")) {
                return "(${rA}${iA}i)"
            } else {
                return "(${rA}+${iA}i)"
            }
        } else {
            return "(${rA} ${iA}i)"

        }

    }

    fun toFraction(d: Double): String {
        val negligibleRatio = 0.00001

        var i = 1
        while (true) {
            val tem = d / (1.0 / i)
            if (abs(tem - tem.roundToInt()) < negligibleRatio) {
                val x = tem.roundToInt() to i
                var a = x.toString().drop(1)
                a = a.dropLast(1)
                val asd = a.replace(",", "/")
                return asd.replace("\\s".toRegex(), "")
            }
            i++
        }
    }

    fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    fun division(realA: String, realB: String, imaA: String, imaB: String): String {

        val rA: Double
        val rB: Double
        val iA: Double
        val iB: Double
        if (realA.contains("/")) {
            rA = toDecimal(realA)
        } else {
            rA = realA.toDouble()
        }
        if (realB.contains("/")) {
            rB = toDecimal(realB)
        } else {
            rB = realB.toDouble()
        }
        if (imaA.contains("/")) {
            iA = toDecimal(imaA)
        } else {
            iA = imaA.toDouble()
        }
        if (imaB.contains("/")) {
            iB = toDecimal(imaB)
        } else {
            iB = imaB.toDouble()
        }
        val resR: Double = (rA * rB + iA * iB) / (rB * rB + iB * iB)
        val resI: Double = (iA * rB - rA * iB) / (rB * rB + iB * iB)

        if (resI.toString().contains("-")) {
            return "(${resR}${resI}i)"
        } else if (resR == 0.0) {
            return "(${resI}i)"
        } else if (resR != 0.0) {
            return "(${resR}/${resI}i)"
        } else {
            return "(${resR})i"

        }
    }

    fun toDecimal(ratio: String): Double {
        return if (ratio.contains("/")) {
            val rat = ratio.split("/").toTypedArray()
            rat[0].toDouble() / rat[1].toDouble()
        } else {
            ratio.toDouble()
        }
    }
}



