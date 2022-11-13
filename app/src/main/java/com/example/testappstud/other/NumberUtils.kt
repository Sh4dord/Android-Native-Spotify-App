package com.example.testappstud.other

abstract class NumberUtils {
    companion object {
        fun numberFormatter(num: Double): String {
            if(num.div(1000.0) < 1)
                return num.toInt().toString()
            if(num.div(1000000.0) < 1) {
                val toStringNum = num.div(1000.0).toString().split('.')
                val firstPart = toStringNum[0]
                val secondPart = toStringNum[1].substring(0, 2)
                return "$firstPart, ${secondPart}k"
            }
            val toStringNum = num.div(1000000.0).toString().split('.')
            val firstPart = toStringNum[0]
            val secondPart = toStringNum[1].substring(0, 2)
            return "$firstPart, ${secondPart}M"

        }

    }


}