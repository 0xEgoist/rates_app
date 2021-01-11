package com.example.domain.cases.rates

typealias Emoji = String
typealias Name = String

fun getCurrencyEmojiAndName(isoCode: String): Pair<Emoji, Name>? {
    return when (isoCode) {
        "EUR" -> Pair("\uD83C\uDDEA\uD83C\uDDFA", "Euro")
        "AUD" -> Pair("\uD83C\uDDE6\uD83C\uDDFA", "Australian dollar")
        "BGN" -> Pair("\uD83C\uDDE7\uD83C\uDDEC", "Bulgarian lev")
        "BRL" -> Pair("\uD83C\uDDE7\uD83C\uDDF7", "Brazilian real")
        "CAD" -> Pair("\uD83C\uDDE8\uD83C\uDDE6", "Canadian dollar")
        "CHF" -> Pair("\uD83C\uDDE8\uD83C\uDDED", "Swiss franc")
        "CNY" -> Pair("\uD83C\uDDE8\uD83C\uDDF3", "Chinese yuan")
        "CZK" -> Pair("\uD83C\uDDE8\uD83C\uDDFF", "Czech koruna")
        "DKK" -> Pair("\uD83C\uDDE9\uD83C\uDDF0", "Danish krone")
        "GBP" -> Pair("\uD83C\uDDEC\uD83C\uDDE7", "Pound sterling")
        "HKD" -> Pair("\uD83C\uDDED\uD83C\uDDF0", "Hong Kong dollar")
        "HRK" -> Pair("\uD83C\uDDED\uD83C\uDDF7", "Croatian kuna")
        "HUF" -> Pair("\uD83C\uDDED\uD83C\uDDFA", "Hungarian forint")
        "IDR" -> Pair("\uD83C\uDDEE\uD83C\uDDE9", "Indonesian rupiah")
        "ILS" -> Pair("\uD83C\uDDEE\uD83C\uDDF1", "Israeli new shekel")
        "INR" -> Pair("\uD83C\uDDEE\uD83C\uDDF3", "Indian rupee")
        "ISK" -> Pair("\uD83C\uDDEE\uD83C\uDDF8", "Icelandic króna")
        "JPY" -> Pair("\uD83C\uDDEF\uD83C\uDDF5", "Japanese yen")
        "KRW" -> Pair("\uD83C\uDDF0\uD83C\uDDF7", "South Korean won")
        "MXN" -> Pair("\uD83C\uDDF2\uD83C\uDDFD", "Mexican peso")
        "MYR" -> Pair("\uD83C\uDDF2\uD83C\uDDFE", "Malaysian ringgit")
        "NOK" -> Pair("\uD83C\uDDF3\uD83C\uDDF4", "Norwegian krone")
        "NZD" -> Pair("\uD83C\uDDF3\uD83C\uDDFF", "New Zealand dollar")
        "PHP" -> Pair("\uD83C\uDDF5\uD83C\uDDED", "Philippine peso")
        "PLN" -> Pair("\uD83C\uDDF5\uD83C\uDDF1", "Polish złoty")
        "RON" -> Pair("\uD83C\uDDF7\uD83C\uDDF4", "Romanian leu")
        "RUB" -> Pair("\uD83C\uDDF7\uD83C\uDDFA", "Russian ruble")
        "SEK" -> Pair("\uD83C\uDDF8\uD83C\uDDEA", "Swedish krona")
        "SGD" -> Pair("\uD83C\uDDF8\uD83C\uDDEC", "Singapore dollar")
        "THB" -> Pair("\uD83C\uDDF9\uD83C\uDDED", "Thai baht")
        "USD" -> Pair("\uD83C\uDDFA\uD83C\uDDF8", "United States dollar")
        "ZAR" -> Pair("\uD83C\uDDFF\uD83C\uDDE6", "South African rand")
        else -> null
    }
}