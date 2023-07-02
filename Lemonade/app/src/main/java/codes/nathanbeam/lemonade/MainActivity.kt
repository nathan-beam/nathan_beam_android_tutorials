package codes.nathanbeam.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import codes.nathanbeam.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeScreen(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeScreen(modifier: Modifier = Modifier){
    var stage by remember { mutableStateOf(1) }
    var maxSqueezes by remember { mutableStateOf(2) }
    var numberOfSqeezes by remember { mutableStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if(stage == 2){
                    numberOfSqeezes++
                    if (numberOfSqeezes >= maxSqueezes) {
                        maxSqueezes = (2..4).random()
                        numberOfSqeezes = 0
                        stage++
                    }
                }
                else if (stage == 4){
                    stage = 1
                }
                else{
                    stage++
                }
                      },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCCFFCC))
        ) {
            LemonadeImage(stage = stage)
        }
        Spacer(modifier = Modifier.height(16.dp))
        LemonadeText(stage = stage)
    }
}

@Composable
fun LemonadeImage(stage: Int) {
    val imageResource = when (stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else-> R.drawable.lemon_restart
    }
    val descriptionResource = when (stage) {
        1 -> R.string.lemon_tree_description
        2 -> R.string.lemon_squeeze_description
        3 -> R.string.lemon_drink_description
        else-> R.string.lemon_restart_description
    }
    Spacer(modifier = Modifier.height(16.dp))
    Image(
        painter = painterResource(imageResource),
        contentDescription = stringResource(descriptionResource),
    )
}

@Composable
fun LemonadeText(stage: Int) {
    val textResource = when (stage) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon_squeeze
        3 -> R.string.lemon_drink
        else-> R.string.lemon_restart
    }
    Text(text = stringResource(textResource))
}


