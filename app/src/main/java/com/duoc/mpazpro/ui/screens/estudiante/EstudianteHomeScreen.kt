package com.duoc.mpazpro.ui.screens.estudiante

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duoc.mpazpro.domain.model.Rol
import com.duoc.mpazpro.domain.model.Usuario
import com.duoc.mpazpro.ui.theme.*

private data class AsignaturaUi(
    val id: Long,
    val nombre: String,
    val icono: ImageVector,
    val fondo: Color,
    val color: Color
)

private val asignaturas = listOf(
    AsignaturaUi(
        1,
        "Lenguaje",
        Icons.Default.MenuBook,
        LenguajeBackground,
        LenguajeColor
    ),
    AsignaturaUi(
        2,
        "Matemática",
        Icons.Default.Calculate,
        MatematicaBackground,
        MatematicaColor
    ),
    AsignaturaUi(
        3,
        "Ciencias Naturales",
        Icons.Default.Eco,
        CienciasBackground,
        CienciasColor
    ),
    AsignaturaUi(
        4,
        "Historia y Geografía",
        Icons.Default.Public,
        HistoriaBackground,
        HistoriaColor
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstudianteHomeScreen(
    usuario: Usuario,
    progreso: Float = 0f,
    onAsignaturaClick: (Long) -> Unit = {},
    onLogout: () -> Unit = {}
) {

    var seleccion by remember { mutableIntStateOf(0) }

    val progresoSeguro = progreso.coerceIn(0f, 1f)

    val opciones = listOf(
        "Inicio",
        "Mi avance",
        "Descargas",
        "Perfil"
    )

    val iconos = listOf(
        Icons.Default.Home,
        Icons.Default.BarChart,
        Icons.Default.Download,
        Icons.Default.Person
    )

    Scaffold(
        containerColor = AppBackground,

        topBar = {

            TopAppBar(
                title = {
                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(
                                    PrimaryBlue,
                                    RoundedCornerShape(10.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = "MpazPro",
                            style =
                                MaterialTheme.typography.titleLarge
                        )
                    }
                },

                actions = {

                    IconButton(
                        onClick = { seleccion = 3 }
                    ) {
                        Icon(
                            imageVector =
                                Icons.Default.AccountCircle,
                            contentDescription = "Mi perfil",
                            tint = PrimaryBlue,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppBackground
                )
            )
        },

        bottomBar = {

            NavigationBar(
                containerColor = SurfaceWhite
            ) {

                opciones.forEachIndexed { index, opcion ->

                    NavigationBarItem(
                        selected = seleccion == index,

                        onClick = {
                            seleccion = index
                        },

                        icon = {
                            Icon(
                                imageVector = iconos[index],
                                contentDescription = opcion
                            )
                        },

                        label = {
                            Text(opcion)
                        },

                        colors =
                            NavigationBarItemDefaults.colors(
                                selectedIconColor = PrimaryBlue,
                                selectedTextColor = PrimaryBlue,
                                indicatorColor = LenguajeBackground,
                                unselectedIconColor = TextSecondary,
                                unselectedTextColor = TextSecondary
                            )
                    )
                }
            }
        }
    ) { paddingValues ->

        when (seleccion) {

            0 -> {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),

                    contentPadding = PaddingValues(20.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(18.dp)
                ) {

                    item {

                        Text(
                            text = "¡Hola, ${usuario.nombre}!",
                            style =
                                MaterialTheme.typography.headlineLarge,
                            color = TextPrimary
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = "¿Qué aprenderemos hoy?",
                            style =
                                MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }

                    item {

                        TarjetaProgreso(
                            progreso = progresoSeguro
                        )
                    }

                    item {

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween,

                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Mis asignaturas",
                                style =
                                    MaterialTheme.typography.titleMedium,
                                color = TextPrimary
                            )

                            Text(
                                text = "1° básico",
                                style =
                                    MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }
                    }

                    items(asignaturas.chunked(2).size) { fila ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.spacedBy(12.dp)
                        ) {

                            asignaturas.chunked(2)[fila]
                                .forEach { asignatura ->

                                    TarjetaAsignatura(
                                        asignatura = asignatura,

                                        modifier = Modifier.weight(1f),

                                        onClick = {
                                            onAsignaturaClick(
                                                asignatura.id
                                            )
                                        }
                                    )
                                }
                        }
                    }

                    item {

                        Card(
                            onClick = { seleccion = 2 },

                            modifier = Modifier.fillMaxWidth(),

                            colors = CardDefaults.cardColors(
                                containerColor = SurfaceWhite
                            ),

                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Row(
                                modifier = Modifier.padding(16.dp),

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector =
                                        Icons.Default.Download,
                                    contentDescription = null,
                                    tint = PrimaryBlue,
                                    modifier = Modifier.size(30.dp)
                                )

                                Spacer(Modifier.width(14.dp))

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = "Aprende sin internet",
                                        style =
                                            MaterialTheme.typography.titleSmall
                                    )

                                    Spacer(Modifier.height(4.dp))

                                    Text(
                                        text = "Consulta tus recursos descargados.",
                                        style =
                                            MaterialTheme.typography.bodySmall,
                                        color = TextSecondary
                                    )
                                }

                                Icon(
                                    imageVector =
                                        Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = TextSecondary
                                )
                            }
                        }
                    }
                }
            }

            1 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(20.dp)
                ) {

                    Text(
                        text = "Mi avance",
                        style =
                            MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(20.dp))

                    TarjetaProgreso(progresoSeguro)

                    Spacer(Modifier.height(20.dp))

                    Text(
                        text = "Aquí podrás revisar tu progreso por asignatura.",
                        color = TextSecondary
                    )
                }
            }

            2 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(20.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(56.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Mis descargas",
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Aún no tienes recursos descargados.",
                        color = TextSecondary
                    )
                }
            }

            3 -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(24.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Spacer(Modifier.height(24.dp))

                    Icon(
                        imageVector =
                            Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "${usuario.nombre} ${usuario.apellido}",
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = usuario.correo,
                        color = TextSecondary
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "Estudiante · Primero básico",
                        color = PrimaryBlue
                    )

                    Spacer(Modifier.height(32.dp))

                    OutlinedButton(
                        onClick = onLogout,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),

                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = ErrorColor
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = null
                        )

                        Spacer(Modifier.width(8.dp))

                        Text("Cerrar sesión")
                    }
                }
            }
        }
    }
}

@Composable
private fun TarjetaProgreso(
    progreso: Float
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Mi progreso",
                    color = Color.White,
                    style =
                        MaterialTheme.typography.titleSmall
                )

                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "${(progreso * 100).toInt()}%",
                color = Color.White,
                style =
                    MaterialTheme.typography.headlineLarge
            )

            Spacer(Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { progreso },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),

                color = Color.White,

                trackColor = Color.White.copy(alpha = 0.25f)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = if (progreso == 0f) {
                    "Comienza tu primera actividad."
                } else {
                    "¡Sigue aprendiendo!"
                },

                color = Color.White,

                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun TarjetaAsignatura(
    asignatura: AsignaturaUi,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,

        modifier = modifier.height(155.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = asignatura.fondo
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Icon(
                imageVector = asignatura.icono,
                contentDescription = null,
                tint = asignatura.color,
                modifier = Modifier.size(32.dp)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = asignatura.nombre,
                style = MaterialTheme.typography.titleSmall,
                color = TextPrimary
            )

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = "Ver unidades",
                    color = asignatura.color,
                    style =
                        MaterialTheme.typography.bodySmall
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = asignatura.color,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EstudianteHomePreview() {

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