package com.duoc.mpazpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.mpazpro.domain.model.Rol
import com.duoc.mpazpro.domain.model.Usuario
import com.duoc.mpazpro.ui.screens.estudiante.EstudianteHomeScreen
import com.duoc.mpazpro.ui.theme.MpazProTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MpazProTheme {

                EstudianteHomeScreen(
                    usuario = Usuario(
                        id = 1,
                        nombre = "Lucas",
                        apellido = "Gonzalez",
                        correo = "estudiante@mpazpro.cl",
                        rol = Rol.ESTUDIANTE
                    )
                )
            }
        }
    }
}