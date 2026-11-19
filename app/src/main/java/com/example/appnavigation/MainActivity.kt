package com.example.appnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                HomeContactApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContactApp() {
    var showContact by rememberSaveable { mutableStateOf(false) }

    BackHandler(enabled = showContact) {
        showContact = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (showContact) "Contact" else "Home") },
                navigationIcon = {
                    if (showContact) {
                        IconButton(
                            onClick = { showContact = false },
                            modifier = Modifier.semantics {
                                contentDescription = "Back to home"
                            }
                        ) {
                            Text("←", fontSize = 26.sp)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        val pageModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)

        if (showContact) {
            ContactPage(pageModifier)
        } else {
            HomePage(pageModifier, onContactClick = { showContact = true })
        }
    }
}

private val homeItems = listOf(
    "NYC",
    "LA",
    "DC",
    "LV",
    "MI",
    "SD",
    "NJ",
    "PA",
    "CA",
    "NM",
    "AB",
    "CD",
    "EF",
    "GH",
    "IJ",
    "KL",
    "MN",
    "OP",
    "QR",
    "ST",
    "UV",
    "WX",
    "YZ"
)

@Composable
fun HomePage(modifier: Modifier = Modifier, onContactClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(onClick = onContactClick) {
            Text("View Contact Info")
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(homeItems) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(item, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun ContactPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.nyc),
            contentDescription = "nyc",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Text("Samarpreet Dewal", style = MaterialTheme.typography.headlineSmall)
        Text("Computer Science Student")
        Text("Email: student123@gmail.com")
        Text("Phone: (123) 456-7891")
    }
}