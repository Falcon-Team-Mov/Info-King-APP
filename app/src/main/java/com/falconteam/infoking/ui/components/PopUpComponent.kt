package com.falconteam.infoking.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColorLight
import com.falconteam.infoking.ui.theme.secondaryAquaColor

@Composable
fun Screen() {
    var showDialog by remember { mutableStateOf(false) }

    Column() {
        Button(onClick = { showDialog = true }) {
            Text(text = "Botoncito cochinito")
        }
    }

    if (showDialog) {
        MessagePopUp(onDismiss = { showDialog = false }, {}, true, false, "", "")
    }
}


@Composable
fun MessagePopUp(
    onDismiss: () -> Unit,
    onBack: () -> Unit,
    closeAcceptPopUp: Boolean,
    closePopUp: Boolean,
    titleText: String,
    descriptionText: String
) {
    val okButtonText = "ACEPTAR"
    val cancelButtonText = "RECHAZAR"
    val closeButtonText = "CERRAR"

    InfoKingTheme {
        Dialog(
            onDismissRequest = {},
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                shape = RoundedCornerShape(24.dp),
                color = primaryColorLight
            ) {
                if (closeAcceptPopUp) {
                    Column(
                        modifier = Modifier
                            .background(primaryColorLight),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, bottom = 4.dp),
                            text = titleText,
                            textAlign = TextAlign.Center,
                            style = Typography.displayMedium,
                            fontSize = TextResponsiveSize(size = 16.sp),
                            fontWeight = FontWeight.Bold,
                            color = secondaryAquaColor
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            text = descriptionText,
                            textAlign = TextAlign.Center,
                            style = Typography.displayMedium,
                            fontSize = TextResponsiveSize(size = 16.sp),
                            fontWeight = FontWeight.Bold,
                            color = secondaryAquaColor,
                            lineHeight = TextResponsiveSize(20.sp)
                        )

                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 8.dp)
                                .padding(horizontal = 8.dp, vertical = 16.dp)
                        ) {
                            Button(
                                modifier = Modifier
                                    .heightIn(min = 36.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(bottom = resize(size = 8.dp)),
                                colors = ButtonDefaults.buttonColors(buttonCancelColor),
                                onClick = {
                                    onDismiss()
                                }
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = cancelButtonText,
                                    style = Typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }

                            Button(
                                modifier = Modifier
                                    .heightIn(min = 36.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                colors = ButtonDefaults.buttonColors(buttonOKColor),
                                onClick = {
                                    onBack()
                                }
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = okButtonText,
                                    style = Typography.headlineSmall,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }
                    }
                } else if (closePopUp) {
                    Column(
                        modifier = Modifier
                            .background(primaryColorLight),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            text = titleText,
                            textAlign = TextAlign.Center,
                            style = Typography.displayMedium,
                            fontSize = TextResponsiveSize(size = 18.sp),
                            fontWeight = FontWeight.Bold,
                            color = secondaryAquaColor
                        )

                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .padding(horizontal = 8.dp, vertical = 16.dp)
                        ) {
                            Button(
                                modifier = Modifier
                                    .heightIn(min = 32.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                colors = ButtonDefaults.buttonColors(buttonCancelColor),
                                onClick = {
                                    onDismiss()
                                    onBack()
                                }
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = closeButtonText,
                                    style = Typography.headlineSmall,
                                    fontSize = TextResponsiveSize(size = 20.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MessagePopUpPreview() {
    //Screen()
    MessagePopUp(onDismiss = {}, {}, false, true, "¡HAS GANADO!", "Esta es una descripción del pop-up. Es un poco larga para ver qué pasa. Esta es una descripción del pop-up. Es un poco larga para ver qué pasa")
}

@Composable
fun resize(size: Dp): Dp {
    val configuration = LocalConfiguration.current
    val scaleFactor = if (configuration.smallestScreenWidthDp >= 600) 1.5f else 0.1f
    return (size * scaleFactor)
}