package lumina.emart.dtos

data class JwtObject(val id: String, val role: String) {

    constructor(idRoleString: String) : this(
        idRoleString.split(":::ID_ROLE_STRING:::")[0],
        idRoleString.split(":::ID_ROLE_STRING:::")[1]
    )

    override fun toString(): String {
        return "$id:::ID_ROLE_STRING:::$role"
    }
}