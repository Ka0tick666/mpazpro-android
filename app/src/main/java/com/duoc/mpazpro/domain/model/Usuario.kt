package com.duoc.mpazpro.domain.model

data class Usuario(
    val id: Long,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val rol: Rol,
    val activo: Boolean = true
)