@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.isPrime
import lesson3.task1.minDivisor
import lesson3.task1.maxDivisor


/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (element in v) {
        result += sqr(element)
    }
    return Math.sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    for(element in list) {
        result += element
    }
    return if(list.isEmpty()) result else ((result)/list.size)
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    for (i in 0 until a.size) {
        result += a[i]*b[i]
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var result = 0.0
    for (i in 0 until p.size) {
        result += p[i] * Math.pow(x, i.toDouble())
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.size > 1) {
        list[1] += list[0]
        for (i in 2 until list.size) {
            list[i] += list[i - 1]
        }
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun maxPrimeDiv(a: Int): Int {
    for (i in a/2 + 1 downTo 1) {
        if ((a%i == 0) && (isPrime(i))) return i
    }
    return a
}

fun factorize(n: Int): List<Int> {
    var k = n
    var result = mutableListOf<Int>()
    while (k>1) {
        var x = maxPrimeDiv(k)
        k/=x
        result.add(x)
    }
    return result.reversed()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    var result = factorize(n)
    return result.joinToString("*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var k = n
    var result = mutableListOf<Int>()
    while (k>=base) {
        result.add(0,k%base)
        k/=base
    }
    result.add(0,k)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun intToStr(a: Int): String {
    var m = 'a'
    return if (a in 10..35) {
        m += a-10
        m.toString()
    } else a.toString()
}

fun convertToString(n: Int, base: Int): String {
    var list = convert(n, base)
    var result = mutableListOf<String>()
    for (element in list) {
        result.add(intToStr(element))
    }
    return result.joinToString("")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for((j, i) in (digits.size-1 downTo 0).withIndex()) {
        result += digits[i]*Math.pow(base.toDouble(), j.toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun charToInt(a: Char): Int {
    var m = 9
    return if (a in 'a'..'z') {
        m += (a - 96).toInt()
        m
    } else a.toInt()-48
}
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    for ((j, i) in (0 until str.length).withIndex()) {
        val temp = charToInt(str[i])
        result += temp*Math.pow(base.toDouble(), str.length-1-j.toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var m = n
    val R = listOf("I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M")
    val A = listOf(1,4,5,9,10,40,50,90,100,400,500,900,1000)
    var result = ""
    var i = 12
    while (m > 0) {
        while (A[i] > m)
            i--
        result += R[i]
        m -= A[i]
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun addWithSpace(a: String, b:String): String = if (a.isNotEmpty()) (a + " " + b) else b

fun units(n: Int): String {
    var result = ""

    when {
        n/100==1 -> result = addWithSpace(result, "сто")
        n/100==2 -> result = addWithSpace(result, "двести")
        n/100==3 -> result = addWithSpace(result, "триста")
        n/100==4 -> result = addWithSpace(result, "четыреста")
        n/100==5 -> result = addWithSpace(result, "пятьсот")
        n/100==6 -> result = addWithSpace(result, "шестьсот")
        n/100==7 -> result = addWithSpace(result, "семьсот")
        n/100==8 -> result = addWithSpace(result, "восемьсот")
        n/100==9 -> result = addWithSpace(result, "девятьсот")
    }

    when {
        n/10%10==1 && n%10==0 -> result = addWithSpace(result,"десять")
        n/10%10==2 -> result = addWithSpace(result,"двадцать")
        n/10%10==3 -> result = addWithSpace(result, "тридцать")
        n/10%10==4 -> result = addWithSpace(result, "сорок")
        n/10%10==5 -> result = addWithSpace(result, "пятьдесят")
        n/10%10==6 -> result = addWithSpace(result, "шестьдесят")
        n/10%10==7 -> result = addWithSpace(result, "семьдесят")
        n/10%10==8 -> result = addWithSpace(result, "восемьдесят")
        n/10%10==9 -> result = addWithSpace(result, "девяносто")
    }

    when {
        n%100==11 -> result = addWithSpace(result, "одиннадцать")
        n%100==12 -> result = addWithSpace(result, "двенадцать")
        n%100==13 -> result = addWithSpace(result, "тринадцать")
        n%100==14 -> result = addWithSpace(result, "четырнадцать")
        n%100==15 -> result = addWithSpace(result, "пятнадцать")
        n%100==16 -> result = addWithSpace(result, "шестнадцать")
        n%100==17 -> result = addWithSpace(result, "семнадцать")
        n%100==18 -> result = addWithSpace(result, "восемнадцать")
        n%100==19 -> result = addWithSpace(result, "девятнадцать")

        n%10==1 -> result = addWithSpace(result, "один")
        n%10==2 -> result = addWithSpace(result, "два")
        n%10==3 -> result = addWithSpace(result, "три")
        n%10==4 -> result = addWithSpace(result, "четыре")
        n%10==5 -> result = addWithSpace(result, "пять")
        n%10==6 -> result = addWithSpace(result, "шесть")
        n%10==7 -> result = addWithSpace(result, "семь")
        n%10==8 -> result = addWithSpace(result, "восемь")
        n%10==9 -> result = addWithSpace(result, "девять")
    }
    return result
}

fun thousands(n: Int): String {
    var result = ""
    when {
        n/100==1 -> result = addWithSpace(result, "сто")
        n/100==2 -> result = addWithSpace(result, "двести")
        n/100==3 -> result = addWithSpace(result, "триста")
        n/100==4 -> result = addWithSpace(result, "четыреста")
        n/100==5 -> result = addWithSpace(result, "пятьсот")
        n/100==6 -> result = addWithSpace(result, "шестьсот")
        n/100==7 -> result = addWithSpace(result, "семьсот")
        n/100==8 -> result = addWithSpace(result, "восемьсот")
        n/100==9 -> result = addWithSpace(result, "девятьсот")
    }

    when {
        n/10%10==1 && n%10==0 -> result = addWithSpace(result,"десять")
        n/10%10==2 -> result = addWithSpace(result, "двадцать")
        n/10%10==3 -> result = addWithSpace(result, "тридцать")
        n/10%10==4 -> result = addWithSpace(result, "сорок")
        n/10%10==5 -> result = addWithSpace(result, "пятьдесят")
        n/10%10==6 -> result = addWithSpace(result, "шестьдесят")
        n/10%10==7 -> result = addWithSpace(result, "семьдесят")
        n/10%10==8 -> result = addWithSpace(result, "восемьдесят")
        n/10%10==9 -> result = addWithSpace(result, "девяносто")
    }

    when {
        n%100==11 -> result = addWithSpace(result, "одиннадцать")
        n%100==12 -> result = addWithSpace(result, "двенадцать")
        n%100==13 -> result = addWithSpace(result, "тринадцать")
        n%100==14 -> result = addWithSpace(result, "четырнадцать")
        n%100==15 -> result = addWithSpace(result, "пятнадцать")
        n%100==16 -> result = addWithSpace(result, "шестнадцать")
        n%100==17 -> result = addWithSpace(result, "семнадцать")
        n%100==18 -> result = addWithSpace(result, "восемнадцать")
        n%100==19 -> result = addWithSpace(result, "девятнадцать")

        n%10==1 -> result = addWithSpace(result, "одна")
        n%10==2 -> result = addWithSpace(result, "две")
        n%10==3 -> result = addWithSpace(result, "три")
        n%10==4 -> result = addWithSpace(result, "четыре")
        n%10==5 -> result = addWithSpace(result, "пять")
        n%10==6 -> result = addWithSpace(result, "шесть")
        n%10==7 -> result = addWithSpace(result, "семь")
        n%10==8 -> result = addWithSpace(result, "восемь")
        n%10==9 -> result = addWithSpace(result, "девять")
    }

    return if (n/10%10 != 1) {
        when {
            n%10 == 1 -> addWithSpace(result, "тысяча")
            n%10 in 2..4 -> addWithSpace(result, "тысячи")
            else -> addWithSpace(result, "тысяч")
        }
    } else {
        addWithSpace(result, "тысяч")
    }

}

fun russian(n: Int): String {
    var list = mutableListOf<Int>()
    if(n/1000>0) {
        list.add(n/1000%1000)
        list.add(n%1000)
    }
    else list.add(n%1000)

    return when {
        list.size == 1 -> units(list[0])
        list[1] == 0 -> thousands(list[0])
        else -> thousands(list[0]) + " " + units(list[1])
    }
}