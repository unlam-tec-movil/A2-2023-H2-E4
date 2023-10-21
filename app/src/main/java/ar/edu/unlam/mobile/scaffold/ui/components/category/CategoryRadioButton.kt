package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//  Funcion de Radio buttons
@Composable
fun CategoryRadioButton(
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    val radioOptions = listOf("Ingresos", "Gastos")

    Row(
        Modifier.fillMaxWidth(),
        Arrangement.Center,
    ) {
        radioOptions.forEach { option ->
            val selected = option == selectedOption
            LabelledRadioButton(
                label = option,
                selected = selected,
                onClick = {
                    if (!selected) {
                        onOptionSelected(option)
                    }
                },
            )
        }
    }
}

@Composable
fun LabelledRadioButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors,
        )
        Text(
            text = label,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 10.dp),
        )
    }
}
