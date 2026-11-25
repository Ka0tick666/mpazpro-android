package com.duoc.mpazpro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.mpazpro.domain.model.Rol
import com.duoc.mpazpro.domain.model.Usuario
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val usuario: Usuario? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    // Usuarios ficticios para pruebas locales
    private val usuariosDemo = listOf(
        Usuario(
            id = 1,
            nombre = "Lucas",
            apellido = "Gonzalez",
            correo = "estudiante@mpazpro.cl",
            rol = Rol.ESTUDIANTE
        ),
        Usuario(
            id = 2,
            nombre = "Camila",
            apellido = "Soto",
            correo = "docente@mpazpro.cl",
            rol = Rol.DOCENTE
        ),
        Usuario(
            id = 3,
            nombre = "Andrea",
            apellido = "Muñoz",
            correo = "utp@mpazpro.cl",
            rol = Rol.UTP
        )
    )

    fun login(correo: String, password: String) {

        if (_uiState.value.isLoading) return

        viewModelScope.launch {

            _uiState.value = AuthUiState(
                isLoading = true
            )

            delay(600)

            val usuario = usuariosDemo.find {
                it.correo.equals(
                    correo.trim(),
                    ignoreCase = true
                )
            }

            if (
                usuario != null &&
                usuario.activo &&
                password == "Mpaz123"
            ) {

                _uiState.value = AuthUiState(
                    usuario = usuario
                )

            } else {

                _uiState.value = AuthUiState(
                    errorMessage = "Correo o contraseña incorrectos."
                )
            }
        }
    }

    fun logout() {
        _uiState.value = AuthUiState()
    }

    fun limpiarError() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }
}