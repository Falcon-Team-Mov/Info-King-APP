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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.falconteam.infoking.ui.theme.InfoKingTheme
import com.falconteam.infoking.ui.theme.Typography
import com.falconteam.infoking.ui.theme.buttonCancelColor
import com.falconteam.infoking.ui.theme.buttonOKColor
import com.falconteam.infoking.ui.theme.primaryColorLight
import com.falconteam.infoking.ui.theme.secondaryAquaColor

@Composable
fun PopUpTwoButtons(
    onDismiss: () -> Unit,
    onBack: () -> Unit,
    titleText: String,
    descriptionText: String,
    firstButtonText: String,
    secondButtonText: String
) {
    InfoKingTheme {
        Dialog(
            onDismissRequest = {},
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(24.dp),
                color = primaryColorLight
            ) {
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
                        fontSize = TextResponsiveSize(size = 32.sp),
                        fontWeight = FontWeight.Bold,
                        color = buttonOKColor
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .padding(top = 4.dp),
                        text = descriptionText,
                        textAlign = TextAlign.Center,
                        style = Typography.displayMedium,
                        fontSize = TextResponsiveSize(size = 24.sp),
                        fontWeight = FontWeight.Bold,
                        color = secondaryAquaColor,
                        lineHeight = TextResponsiveSize(28.sp)
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
                                .heightIn(min = 36.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = resizePopUp(size = 8.dp)),
                            colors = ButtonDefaults.buttonColors(buttonCancelColor),
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Text(
                                modifier = Modifier,
                                text = firstButtonText,
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
                                onDismiss()
                            }
                        ) {
                            Text(
                                modifier = Modifier,
                                text = secondButtonText,
                                style = Typography.headlineSmall,
                                fontSize = TextResponsiveSize(size = 20.sp),
                                color = Color.White
                            )
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
    PopUpTwoButtons(
        {},
        {},
        titleText = "TÍTULO",
        descriptionText = "Esta es una descripción del pop-up. Esta es una descripción del pop-up",
        firstButtonText = "RECHAZAR",
        secondButtonText = "ACEPTAR"
    )
}