package com.nathanbeam.businesscard

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nathanbeam.businesscard.ui.theme.NathanBeamBusinessCardTheme
import com.nathanbeam.businesscard.ui.theme.PrimaryText
import com.nathanbeam.businesscard.ui.theme.SecondaryText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NathanBeamBusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        IdentityBlock()
        ContactInfoBlock()
    }
}

@Composable
fun IdentityBlock() {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Headshot()
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            NameAndTitle()
        }
    }
}

@Composable
fun Headshot() {
    val headshot = painterResource(R.drawable.headshot)
    Image(
        painter = headshot,
        contentDescription = null,
        modifier = Modifier.clip(RoundedCornerShape(10.dp))
    )
}

@Composable
fun NameAndTitle() {
    Text(
        text = stringResource(R.string.full_name),
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 40.sp,
        color = PrimaryText
    )
    Text(
        text = stringResource(R.string.job_title),
        fontSize = 20.sp,
        color = SecondaryText
    )}


@Composable
fun ContactInfoBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Phone()
        Email()
        Website()
    }
}

@Composable
fun Phone() {
    ContactInfoRow(
        icon = Icons.Rounded.Phone,
        contentDescription = "Phone number",
        text = stringResource(R.string.phone_number)
    )
}

@Composable
fun Email() {
    ContactInfoRow(
        icon = Icons.Rounded.Email,
        contentDescription = "Email address",
        text = stringResource(R.string.email_address)
    )
}

@Composable
fun Website() {
    ContactInfoRow(
        icon = Icons.Rounded.Home,
        contentDescription = "Website",
        text = stringResource(R.string.website)
    )
}

@Composable
fun ContactInfoRow(icon: ImageVector, contentDescription: String, text: String){
    Row {
        Column(Modifier.weight(1f)) {
        }
        Column(Modifier.weight(1f)) {
            Icon(icon, contentDescription = contentDescription, tint = PrimaryText)
        }
        Column(Modifier.weight(5f)) {
            Text(text = text, color = PrimaryText)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CardPreview() {
    NathanBeamBusinessCardTheme {
        BusinessCard()
    }
}