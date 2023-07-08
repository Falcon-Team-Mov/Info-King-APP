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
import com.falconteam.infoking.ui.theme.primaryColorLight
import com.falconteam.infoking.ui.theme.secondaryAquaColor

@Composable
fun PopUpOneButton(
    onDismiss: () -> Unit,
    onBack: () -> Unit,
    titleText: String,
    buttonText: String,
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
                        .background(primaryColorLight)
                        .padding(ElementResponsiveSize(size = 12.dp)),
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
                        fontSize = TextResponsiveSize(size = 32.sp),
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
                                .heightIn(min = 36.dp)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(top = resizePopUp(size = 16.dp), bottom = resizePopUp(size = 8.dp)),
                            colors = ButtonDefaults.buttonColors(buttonCancelColor),
                            onClick = {
                                onDismiss()
                                onBack()
                            }
                        ) {
                            Text(
                                modifier = Modifier,
                                text = buttonText,
                                style = Typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = TextResponsiveSize(size = 20.sp)
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
fun PopUpOneBtnPreview() {
    PopUpOneButton(
        {},
        {},
        titleText = "TÍTULO",
        buttonText = "CERRAR",
    )
}