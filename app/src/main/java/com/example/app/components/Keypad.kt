package com.example.app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeyPad(
    buttonSpacing: Dp = 8.dp,
    onPressNum: (num: Int) -> Unit,
    hasValues: Boolean,
    onClear: () -> Unit,
    onDelete: () -> Unit
) {
    Column {
        SymbolButtonRow(
            from = 1,
            to = 3
        ) { num -> onPressNum(num) }
        SymbolButtonRow(
            from = 4,
            to = 6
        ) { num -> onPressNum(num) }
        SymbolButtonRow(
            from = 7,
            to = 9,
        ) { num -> onPressNum(num) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            val buttonRowModifier = Modifier
                .aspectRatio(1.4f)
                .weight(1f)

            val clear = "C"
            SymbolButton(
                symbol = clear, isEnabled = hasValues, modifier = buttonRowModifier
            ) { onClear() }

            val zero = 0
            SymbolButton(
                num = zero, modifier = buttonRowModifier
            ) { onPressNum(zero) }

            val backspace = "backspace"
            IconButton(
                imageVector = Icons.Rounded.KeyboardBackspace,
                iconName = backspace,
                isEnabled = hasValues,
                modifier = buttonRowModifier
            ) { onDelete() }
        }
    }
}

@Composable
fun SymbolButtonRow(
    from: Int,
    to: Int,
    buttonSpacing: Dp = 8.dp,
    onClick: (num: Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
    ) {
        (from..to).toList().toTypedArray().forEach { num ->
            SymbolButton(
                num,
                modifier = Modifier
                    .aspectRatio(1.4f)
                    .weight(1f)
            ) { onClick(num) }
        }
    }
}

@Composable
fun SymbolButton(
    symbol: String,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    onClick: (symbol: String) -> Unit,
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(enabled = isEnabled) { onClick(symbol) }
            .then(modifier)) {
        Text(
            symbol,
            fontSize = fontSize,
            color = if (isEnabled) Color.Unspecified else Color.Transparent
        )
    }
}


@Composable
fun SymbolButton(
    num: Int,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 20.sp,
    onClick: (num: Int) -> Unit,
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable { onClick(num) }
            .then(modifier)) {
        Text(num.toString(), fontSize = fontSize)
    }
}

@Composable
fun IconButton(
    imageVector: ImageVector,
    iconName: String,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.clickable(enabled = isEnabled, onClick=onClick)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = iconName,
            modifier = Modifier
                .width(iconSize)
                .then(modifier),
            tint = if (isEnabled) LocalContentColor.current else Color.Transparent
        )
    }
}