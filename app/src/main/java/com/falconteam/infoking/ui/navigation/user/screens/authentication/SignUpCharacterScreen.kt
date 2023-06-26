package com.falconteam.infoking.ui.navigation.user.screens.authentication

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.infoking.ui.theme.InfoKingTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.falconteam.infoking.data.models.Personaje
import com.falconteam.infoking.data.network.dto.character.ImagesCharacter
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.primaryColor
import com.falconteam.infoking.ui.theme.primaryColorLight
import com.falconteam.infoking.ui.theme.secondaryBlueColor
import com.falconteam.infoking.ui.viewmodels.CharacterViewModel

@Composable
fun SignUpCharacterScreen(
    onSignUp: () -> Unit,
    onBack: () -> Unit,
    infoRegister: MutableList<String>
) {

    InfoKingTheme() {
        var selectedCharacter by rememberSaveable { mutableStateOf(-1) } // -1: no character selected
        var oldSelectedCharacter by rememberSaveable { mutableStateOf(-1) } // -1: no character selected
        Log.d("InfoScreenBack", "SignUpCharacterScreen: $infoRegister")
        val characterViewModel: CharacterViewModel = viewModel()
        GetCharactersView(characterViewModel)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = primaryColor),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Escoge tu personaje",
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                )

            }

            //Personajes
            Row (
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .clickable {
                            if (selectedCharacter == 0) {
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = -1
                            } else {
                                Log.d("Pruebas", "onTapPersonaje")
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = 0
                            }
                        }
                        .background(characterBoxState[0][0])
                ) {
                    Column(
                        modifier = Modifier
                            //.background(color = Color(0xFF0000FF))
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .width(150.dp)
                            .height(300.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            NamesCharacter(0)
                        }

                        AsyncImage(
                            model = Characters[0]?.images?._3d,
                            contentDescription = null,
                            modifier = Modifier
                                //.background(color = Color(0xFFFFFFFF))
                                .weight(1f)
                                .align(Alignment.CenterHorizontally),
                            alignment = Alignment.Center,
                        )
                        Text(
                            text = "+HP",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            Color(0xFFFFFFFF),
                            style = Typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            if (selectedCharacter == 1) {
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = -1
                            } else {
                                Log.d("Pruebas", "onTapPersonaje")
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = 1
                            }
                        }
                        .background(characterBoxState[1][0])
                ) {
                    Column(
                        modifier = Modifier
                            //.background(color = Color(0xFF0000FF))
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .width(150.dp)
                            .height(300.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            NamesCharacter( 1)
                        }
                        AsyncImage(
                            model = Characters[1]?.images?._3d,
                            contentDescription = null,
                            modifier = Modifier
                                //.background(color = Color(0xFFFFFFFF))
                                .padding(8.dp)
                                .weight(1f)
                        )
                        Text(
                            text = "+Energia",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            Color(0xFFFFFFFF),
                            style = Typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(modifier = Modifier
                    .clickable {
                        if (selectedCharacter == 2) {
                            oldSelectedCharacter = selectedCharacter
                            selectedCharacter = -1
                        } else {
                            Log.d("Pruebas", "onTapPersonaje")
                            oldSelectedCharacter = selectedCharacter
                            selectedCharacter = 2
                        }
                    }
                    .background(characterBoxState[2][0])
                ) {
                    Column(
                        modifier = Modifier
                            //.background(color = Color(0xFF0000FF))
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .width(150.dp)
                            .height(300.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            NamesCharacter( 2)
                        }

                        AsyncImage(
                            model = Characters[2]?.images?._3d,
                            contentDescription = null,
                            modifier = Modifier
                                //.background(color = Color(0xFFFFFFFF))
                                .padding(8.dp)
                                .weight(1f)
                        )
                        Text(
                            text = "+Defensa",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            Color(0xFFFFFFFF),
                            style = Typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .clickable {
                            if (selectedCharacter == 3) {
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = -1
                            } else {
                                Log.d("Pruebas", "onTapPersonaje")
                                oldSelectedCharacter = selectedCharacter
                                selectedCharacter = 3
                            }
                        }
                        .background(characterBoxState[3][0])
                ) {
                    Column(
                        modifier = Modifier
                            //.background(color = Color(0xFF0000FF))
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
                            .width(150.dp)
                            .height(300.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            NamesCharacter( 3)
                        }
                        AsyncImage(
                            model = Characters[3]?.images?._3d,
                            contentDescription = null,
                            modifier = Modifier
                                //.background(color = Color(0xFFFFFFFF))
                                .padding(8.dp)
                                .weight(1f)
                        )
                        Text(
                            text = "+Daño",
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterHorizontally),
                            Color(0xFFFFFFFF),
                            style = Typography.headlineSmall,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Column() {
                Button(
                    onClick = {
                        onSignUp()
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier
                        .fillMaxWidth()
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
        if(selectedCharacter != -1){
            Log.d("Pruebas", "SignUpCharacterScreen: " + characterBoxState[selectedCharacter][0])
            SelectedBox(id = selectedCharacter, clickedStates = true)

            Log.d("Pruebas", "Post - SignUpCharacterScreen: " + characterBoxState[selectedCharacter][0])
        }
        if(oldSelectedCharacter != -1){
            SelectedBox(id = oldSelectedCharacter, clickedStates = false)
        }
    }
}
@Composable
fun GetCharactersView(characterViewModel: CharacterViewModel) {
    LaunchedEffect(Unit) {
        characterViewModel.obtenerPersonajes()
    }

    val characterResponse = characterViewModel.datosPersonajes.value
    characterResponse?.let { response ->
        Log.d("Pruebas", "GetCharactersView: " + response)
        response.personajes?.let {
            for (i in 0 until it.size) {
                CharacterData(
                    posicion = i,
                    id = it[i].id,
                    nombre = it[i].nombre,
                    d2 = it[i].images._2d,
                    d3 = it[i].images._3d,
                    icon = it[i].images.icon,
                    buff = it[i].buff,
                    nerf = it[i].nerf
                )
            }
        }
    }
}

private var Characters = mutableStateMapOf<Int,Personaje>()
@Composable
fun CharacterData(posicion: Int, id: String, nombre:String, d2:String, d3: String, icon:String, buff: Double, nerf:Double){
    Characters[posicion] = Personaje(
        id = id,
        nombre = nombre,
        images = ImagesCharacter(
            _2d = d2,
            _3d = d3,
            icon = icon
        ),
        buff = buff,
        nerf = nerf
    )
    Log.d("Pruebas", "CharacterData: " + Characters[posicion])

}

private var characterBoxState = mutableStateListOf(
    mutableListOf(primaryColor, secondaryBlueColor),
    mutableListOf(primaryColor, secondaryBlueColor),
    mutableListOf(primaryColor, secondaryBlueColor),
    mutableListOf(primaryColor, secondaryBlueColor)
)

@Composable
fun SelectedBox(id: Int, clickedStates: Boolean) {
    if (id != -1) {
        if(clickedStates){
            characterBoxState[id][0] = secondaryBlueColor
            characterBoxState[id][1] = primaryColorLight
        }
        else{
            characterBoxState[id][0] = primaryColor
            characterBoxState[id][1] = secondaryBlueColor
        }
    }
}


@Composable
private fun NamesCharacter(id: Int){
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
                .background(characterBoxState[id][1])
        ){
            Characters[id]?.let {
                Text(
                    text = it.nombre,
                    modifier = Modifier
                        .align(Alignment.Center),
                    Color(0xFFFFFFFF),
                    style = Typography.headlineSmall,
                )
            }
        }

    }
}

@Preview
@Composable
fun SignUpCharacterScreenPreview() {
    InfoKingTheme(darkTheme = true) {
        SignUpCharacterScreen(
            onSignUp = {},
            onBack = {},
            infoRegister = MutableList(4) { "" }
        )
    }
}