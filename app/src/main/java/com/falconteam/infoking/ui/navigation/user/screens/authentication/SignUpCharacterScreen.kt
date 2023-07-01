package com.falconteam.infoking.ui.navigation.user.screens.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.falconteam.infoking.data.models.Personaje
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.ui.navigation.user.screens.tools.LoadingScreen
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.primaryColorLight
import com.falconteam.infoking.ui.theme.secondaryAquaColor
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.viewmodels.SignUpViewModel

@Composable
fun SignUpCharacterScreen(
    onSignUp: () -> Unit,
    onBack: (msg: String) -> String,
    infoRegister: SignUpFormOne
) {
    val signUpViewModel: SignUpViewModel = viewModel()
    LaunchedEffect(Unit) {
        signUpViewModel.obtenerPersonajes()
    }
    InfoKingTheme(darkTheme = true) {
        val id = mutableMapOf<Int, String>()
        val selectedCardIndex = remember { mutableStateOf(-1) }
        if ((signUpViewModel._datosPersonajes.value.size != 0)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(color = primaryColor),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    Text(
                        text = "ESCOGE TU PERSONAJE",
                        color = secondaryAquaColor,
                        modifier = Modifier
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.8f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .height(700.dp),
                        columns = GridCells.Fixed(2),
                        userScrollEnabled = false
                    ) {

                        itemsIndexed(signUpViewModel._datosPersonajes.value) { index, personaje ->
                            id[index] = personaje.id
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight(0.8f)
                                    .fillMaxWidth(1f)
                            ) {
                                CardCharacter(
                                    personaje,
                                    index == selectedCardIndex.value
                                ) { selected ->
                                    if (selected) {
                                        selectedCardIndex.value = index
                                    } else {
                                        selectedCardIndex.value = -1
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 32.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                onSignUp()
                                Log.d(
                                    "Pruebas",
                                    "SignUpCharacterScreen: $infoRegister + id: ${id[selectedCardIndex.value]}"
                                )
                                if (id[selectedCardIndex.value] != null) {
                                    signUpViewModel.AddAccount(
                                        SignUpFormOne(
                                            infoRegister.username,
                                            infoRegister.email,
                                            infoRegister.password,
                                            id[selectedCardIndex.value]!!
                                        )
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(secondaryAquaColor),
                            modifier = Modifier
                                .padding(vertical = 24.dp, horizontal = 54.dp)
                        ) {
                            Text(
                                text = "Registrarse",
                                style = Typography.headlineSmall,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            if (signUpViewModel.msg.value != "") {
                Message(msg = onBack(signUpViewModel.msg.value))
                signUpViewModel.msg.value = ""
            }
        } else {
            LoadingScreen()
        }
    }
}

@Composable
private fun NamesCharacter(color: Color, nombre: String) {
    return Box(
        modifier = Modifier
            .width(116.dp)
            .height(25.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color)
        ) {
            Text(
                text = nombre,
                modifier = Modifier
                    .align(Alignment.Center),
                Color(0xFFFFFFFF),
                style = Typography.headlineSmall,
            )
        }

    }
}

@Composable
private fun ImageCharacter(url: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                //.background(color = Color(0xFFFFFFFF))
                .padding(8.dp)
                .fillMaxSize()
        )
    }
}

@Composable
private fun PowerCharacter(poder: String) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = poder,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            Color(0xFFFFFFFF),
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CardCharacter(
    personaje: Personaje,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit
) {
    var CharacterColor = mutableListOf(
        primaryColor,
        secondaryBlueColor,
        primaryColorLight
    )
    Box(
        modifier = Modifier
            .clickable {
                onSelectedChange(!selected)
            }
            .background(if (selected) CharacterColor[1] else CharacterColor[0])
    ) {
        Column(
            modifier = Modifier
                //.background(color = Color(0xFF0000FF))
                .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                NamesCharacter(
                    if (selected) CharacterColor[2] else CharacterColor[1],
                    personaje.nombre
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageCharacter(url = personaje.images._3d!!)
            }
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PowerCharacter(poder = "+ Prueba")
            }

        }
    }

}

@Composable
private fun Message(msg: String) {
    Log.d("Pruebas", "SignUpCharacterScreen: $msg")
    Toast.makeText(
        LocalContext.current,
        msg,
        Toast.LENGTH_LONG
    ).show()
}

@Preview
@Composable
fun SignUpCharacterScreenPreview() {
    SignUpCharacterScreen(
        onSignUp = {},
        onBack = { "" },
        infoRegister = SignUpFormOne(
            "",
            "",
            "",
            "",
        )
    )
}