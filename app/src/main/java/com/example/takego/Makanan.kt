package com.example.takego

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavHostController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakananScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Menu Makanan",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = { },
                placeholder = { Text(text = "Kamu pesan apa nih?", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search",
                        tint = Color.Gray
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Rekomendasi",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item { RecommendationCard(title = "Resto Sekitar", color = Color(0xFF4CAF50)) }
                item { RecommendationCard(title = "Megahedon s.d 55%", color = Color(0xFF4CAF50)) }
                item { RecommendationCard(title = "Promo Spesial", color = Color(0xFF4CAF50)) }
                item { RecommendationCard(title = "#FYP", color = Color(0xFF4CAF50)) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Jalan Yuk",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Super untung ke mana aja",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        )
                        Text(
                            text = "Jalan cuma 1rb",
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.scooter),
                        contentDescription = "Bike",
                        modifier = Modifier.size(40.dp),
                        tint = Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Pesan Ulang",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item { PesanUlangCard(title = "Mie Gacoan - Madiun", description = "Tutup · Buka 11.30") }
                item { PesanUlangCard(title = "Cinta Abadi - Grobogan", description = "Rp8.000 · 20 mnt") }
                item { PesanUlangCard(title = "Mie Gacoan - Madiun", description = "Tutup · Bukak 11:00") }
                item { PesanUlangCard(title = "Cinta Abadi - Grobogan", description = "Rp8.000 · 20 mnt") }
            }
        }
    }
}

@Composable
fun RecommendationCard(title: String, color: Color) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 100.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = title, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black))
        }
    }
}

@Composable
fun PesanUlangCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 100.dp)
            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = description, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

