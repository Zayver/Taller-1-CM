package entity

import java.io.Serializable

data class Country(val name: String, val intName: String, val initials: String,
                   val capital: String): Serializable{
    override fun toString(): String {
        return "$name ($intName)"
    }
}
