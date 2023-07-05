package codes.nathanbeam.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Light
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import codes.nathanbeam.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    companion object {
        val artworks = listOf(
            Artwork(
                workName = R.string.clare_hall_title,
                artistName = R.string.clare_hall_artist,
                year = R.string.clare_hall_year,
                work = R.drawable.clare_hall
            ),
            Artwork(
                workName = R.string.hare_title,
                artistName = R.string.hare_artist,
                year = R.string.hare_year,
                work = R.drawable.hare
            ),
            Artwork(
                workName = R.string.landscape_of_morocco_title,
                artistName = R.string.landscape_of_morocco_artist,
                year = R.string.landscape_of_morocco_year,
                work = R.drawable.landscape_of_morocco
            ),
            Artwork(
                workName = R.string.landscape_with_gypsies_and_wagon_title,
                artistName = R.string.landscape_with_gypsies_and_wagon_artist,
                year = R.string.landscape_with_gypsies_and_wagon_year,
                work = R.drawable.landscape_with_gypsies_and_wagon
            ), Artwork(
                workName = R.string.the_green_hill_title,
                artistName = R.string.the_green_hill_artist,
                year = R.string.the_green_hill_year,
                work = R.drawable.the_green_hill
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtScreen() {
    var artworkIndex by remember { mutableStateOf(0) }
    val artwork = MainActivity.artworks[artworkIndex]

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1f, false))
        Row{
            ImageRow(
                work = artwork.work,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
        Row{
            Column(
                modifier = Modifier.widthIn(0.dp, max = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InfoRow(
                    artwork = artwork,
                    modifier = Modifier
                        .padding(16.dp)
                )
                ButtonRow(
                    increment = { artworkIndex = increment(artworkIndex) },
                    decrement = { artworkIndex = decrement(artworkIndex) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .widthIn(0.dp, max = 500.dp)

                )
            }
        }
    }
}

@Composable
fun ImageRow(@DrawableRes work:Int, modifier: Modifier = Modifier ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(work) ,
            contentDescription = null,
            modifier = Modifier
                .padding(24.dp)
                .shadow(10.dp)

        )
    }
}

@Composable
fun InfoRow(artwork: Artwork, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(artwork.workName),
                fontSize = 40.sp,
                modifier = Modifier.padding(bottom = 10.dp),
                fontWeight = Light,
                lineHeight = 36.sp
            )
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = Bold)) {
                    append(stringResource(artwork.artistName))
                }
                append(" (")
                append(stringResource(artwork.year))
                append(")")
            }
            )
        }
    }
}

@Composable
fun ButtonRow(
    increment: () -> Unit,
    decrement: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = decrement, modifier = Modifier.width(152.dp)){
            Text(text = "Back")
        }
        Button(onClick = increment, modifier = Modifier.width(152.dp)){
            Text(text = "Next")
        }
    }
}

fun increment(input: Int): Int{
    if(input+1 < MainActivity.artworks.size )
        return input+1
    return 0
}
fun decrement(input: Int): Int{
    if(input-1 >= 0 )
        return input-1
    return MainActivity.artworks.size-1
}

data class Artwork (
        @StringRes val workName: Int,
        @StringRes val artistName: Int,
        @StringRes val year: Int,
        @DrawableRes val work: Int
        )
