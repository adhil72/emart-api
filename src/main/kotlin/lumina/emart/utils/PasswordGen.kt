package lumina.emart.utils

import java.util.UUID

fun generatePassword():String{
    val pwd = UUID.randomUUID().toString().split("-")[0]
    return pwd
}