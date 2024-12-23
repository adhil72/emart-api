package lumina.emart.config

import lumina.emart.extras.Roles

val rolesList = mapOf(
    "/public/**" to listOf("permitAll"),
    "/user/**" to listOf("permitAll"),
    "/admin/**" to listOf(Roles.ADMIN),
    "/product/**" to listOf(Roles.ADMIN, Roles.MANAGER),
    "/category/**" to listOf(Roles.ADMIN, Roles.MANAGER),
    "/supplier/**" to listOf(Roles.ADMIN, Roles.MANAGER),
)