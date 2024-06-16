package com.ezlotest.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ezlotest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    onResetClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = { onResetClick() }) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.baseline_arrow_circle_down_24),
                    contentDescription = stringResource(R.string.appBarResetButtonDescription)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = Color.White
        )
    )
}