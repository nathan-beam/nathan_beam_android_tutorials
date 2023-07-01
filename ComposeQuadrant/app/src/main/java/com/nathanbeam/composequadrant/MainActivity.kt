package com.nathanbeam.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nathanbeam.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    QuadScreen(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun QuadScreen(modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxWidth()) {
            Row(modifier = modifier.weight(1f)) {
                Quadrant(
                    title = stringResource(R.string.text_composable_title),
                    content = stringResource(R.string.text_composable_content),
                    bgColor = Color(0xFFEADDFF),
                    modifier = Modifier.weight(1f)

                )
                Quadrant(
                    title = stringResource(R.string.image_composable_title),
                    content = stringResource(R.string.image_composable_content),
                    bgColor = Color(0xFFD0BCFF),
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = modifier.weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                Quadrant(
                    title = stringResource(R.string.row_composable_title),
                    content = stringResource(R.string.row_composable_content),
                    bgColor = Color(0xFFB69DF8),
                    modifier = Modifier.weight(1f)
                )
                Quadrant(
                    title = stringResource(R.string.column_composable_title),
                    content = stringResource(R.string.column_composable_content),
                    bgColor = Color(0xFFF6EDFF),
                    modifier = Modifier.weight(1f)
                )
            }
        }
}

@Composable
fun Quadrant(title: String, content: String, bgColor: Color, modifier: Modifier = Modifier) {
    Column(modifier =
            modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        QuadScreen()
    }
}