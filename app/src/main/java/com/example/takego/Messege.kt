package com.example.takego

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kotak Masuk",
                        style = TextStyle(fontSize = 20.sp, color = Color.White)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleMessages) { message ->
                MessageCard(message)
            }
        }

    }
}

@Composable
fun MessageCard(message: Message) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = message.icon),
                contentDescription = message.title,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = message.title, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
                Text(text = message.subtitle, style = TextStyle(fontSize = 14.sp, color = Color.Gray))
                Text(text = message.time, style = TextStyle(fontSize = 12.sp, color = Color.LightGray))
            }
        }
    }
}


data class Message(val icon: Int, val title: String, val subtitle: String, val time: String)

val sampleMessages = listOf(
    Message(
        icon = R.drawable.bel,
        title = "Promo Baru!",
        subtitle = "Dapatkan diskon hingga 50% untuk layanan tertentu.",
        time = "10:30 AM"
    ),
    Message(
        icon = R.drawable.danger,
        title = "Pemberitahuan Penting",
        subtitle = "Akun Anda telah berhasil diperbarui.",
        time = "09:15 AM"
    ),
    Message(
        icon = R.drawable.letter,
        title = "Pesan Dari Admin",
        subtitle = "Terima kasih telah menggunakan aplikasi kami.",
        time = "Kemarin"
    )
)
