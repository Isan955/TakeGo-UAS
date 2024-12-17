package com.example.takego

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Motor",
                        color = Color.Black,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                },
                actions = {
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFE0F7E8))
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
        ) {
            item {
                SearchDestination()
                PopularDestinations()
                RidesGrid()
                PreBookSection()
            }
        }
    }
}

@Composable
fun SearchDestination() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Mau Kemana Hari ini?",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Handle click */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text(text = "Cari Motor", color = Color.White)
        }
    }
}

@Composable
fun PopularDestinations() {
    val destinations = listOf(
        "Madiun Station" to "Jl. Kompol Sunaryo, Madiun Lor",
        "Bus Terminal Purboyo" to "Jl. Jenderal Basuki Rahmat",
        "Sun City Mall Madiun" to "Jl. Letjend S Parman No. 8"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        destinations.forEach { (title, address) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { /* Handle click */ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(address, color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun RidesGrid() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Rides for your every need",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            RideOption("Advance Booking", R.drawable.calendar, Color(0xFF80D8FF))
            RideOption("Bike XL", R.drawable.delivery_bike, Color(0xFFB2FF59))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            RideOption("6 seater car", R.drawable.sedan, Color(0xFFFFF176))
            RideOption("Rent by the hour", R.drawable.rent, Color(0xFFFFAB91))
        }
    }
}

@Composable
fun RideOption(title: String, iconRes: Int, backgroundColor: Color) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Composable
fun PreBookSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pre-book your rides",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFD1C4E9))
        ) {
            Image(
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Advance Booking",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}