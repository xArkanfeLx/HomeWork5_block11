package com.example.gridlayout_tablelayout

class Calculator {
    fun getResult(str: String):Double {
        var valuesStr:List<String> = str.replace("[+,-]".toRegex()," ").split(" ") // отделяем умножение и деление от второстепенных операций
        val doList:List<String> = getDoList(str,false) // получаем знаки второстепенных операций
        if(valuesStr.size==1) return calcStrValues(valuesStr[0])
        else return calcResult(valuesStr,doList)
    }

    fun calcStrValues (str:String):Double{
        var values:List<String> = str.replace("[*,/]".toRegex()," ").split(" ") // разбиваем выражения на значения
        if(values.size==1) return values[0].toDouble() 
        val doList:List<String> = getDoList(str,true) // получаем знаки первостепенных операций
        return calcResult(values,doList)
    }

    fun calcResult(values:List<String>,doList:List<String>):Double {
        var result =calcStrValues(values[0])
        for(i in doList.indices) {
            val value = calcStrValues(values[i+1])
            when (doList[i]){
                "*" -> result*=value
                "/" -> result/=value
                "+" -> result+=value
                "-" -> result-=value
            }
        }
        return result
    }

    fun getDoList(str:String,isFirstDo:Boolean):List<String> {
        var doList:String = str.replace("[1234567890]".toRegex(),"") // убираем все числа, оставляем только знаки
        if(!isFirstDo) doList = doList.replace("[/,*]".toRegex(),"") // если не первостепенные задачи, убираем соответствующие знаки
        return doList.split("").slice(1..doList.length)
    }
}