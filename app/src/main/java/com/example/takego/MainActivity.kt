package com.example.takego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
//import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.takego.ui.theme.TakeGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeGoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { TakeAppHomeScreen(navController) }
        composable("activity_screen") { Aktvitas(navController) }
        composable("akun_screen") { AkunScreen(navController) }
        composable("pesan_screen") { MessageScreen(navController) }
        composable("pembayaran_screen") { PembayaranScreen(navController) }
        composable("makanan_screen") { MakananScreen(navController) }
        composable("motor_screen") { MotorScreen(navController) }
        composable("mobil_screen") { CarScreen(navController) }
        composable("Belanja") { ShoppingScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
data class Discount(val title: String, val subtitle: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeAppHomeScreen(navController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        BasicTextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(40.dp)
                                .background(Color.White, CircleShape),
                            decorationBox = { innerTextField ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(start = 8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = "Search Icon"
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    innerTextField()
                                }
                            },
                            textStyle = TextStyle(fontSize = 14.sp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CAF50))
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedIndex = 0,
                onItemSelected = { }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Text(
                    text = "Selamat Datang di TakeGo",
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Layanan ojek online terbaik untuk kebutuhan Anda",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MenuItem(iconRes = R.drawable.cutlery, title = "Makanan") {
                        navController.navigate("makanan_screen")
                    }
                    MenuItem(iconRes = R.drawable.scooter, title = "Motor") {
                        navController.navigate("motor_screen")
                    }
                    MenuItem(iconRes = R.drawable.car_wash, title = "Mobil") {
                        navController.navigate("mobil_screen")
                    }
                    MenuItem(iconRes = R.drawable.store, title = "Belanja") {
                        navController.navigate("Belanja")
                    }
                }
            }

            item {
                Text(
                    text = "Promo Menarik",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                PromoCard(
                    title = "Diskon hingga 55%",
                    subtitle = "Santai pengeluaran dengan promo menarik",
                    onClick = { showDialog = true }
                )
            }

            item {
                Text(
                    text = "Promo Diskon Menarik",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(
                        listOf(
                            "Mixue" to R.drawable.mixue,
                            "Wizzmie" to R.drawable.wizzmie,
                            "Kopi Kenangan" to R.drawable.kopikenangan,
                            "Gacoan" to R.drawable.gacoan,
                            "Starbucks" to R.drawable.starbucks
                        )
                    ) { (service, iconRes) ->
                        ServiceCard(service = service, iconRes = iconRes)
                    }
                }

            }
        }
    }

    if (showDialog) {
        PromoDialog(
            discounts = discounts,
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun ServiceCard(service: String, iconRes: Int) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = service,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop // Membuat gambar memenuhi kotak
            )
            // Opsional: Tambahkan overlay teks di atas gambar
            Text(
                text = service,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.White),
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .padding(4.dp)
                    .align(Alignment.BottomCenter) // Posisikan teks di bagian bawah
            )
        }
    }
}



@Composable
fun PromoDialog(discounts: List<Discount>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Promo Saudara Ambatukam") },
        text = {
            Column {
                discounts.forEach { discount ->
                    Text(
                        text = "${discount.title}: ${discount.subtitle}",
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Tutup")
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.LightGray,
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val items = listOf(
            "Beranda" to R.drawable.home,
            "Aktivitas" to R.drawable.to_do_list,
            "Pembayaran" to R.drawable.credit_card,
            "Kotak Masuk" to R.drawable.messege,
            "Akun" to R.drawable.user
        )
        items.forEachIndexed { index, (label, iconRes) ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = if (selectedIndex == index) Color.Blue else Color.Black
                    )
                },
                selected = selectedIndex == index,
                onClick = {
                    onItemSelected(index)
                    when (index) {
                        0 -> navController.navigate("home")
                        1 -> navController.navigate("activity_screen")
                        2 -> navController.navigate("pembayaran_screen")
                        3 -> navController.navigate("pesan_screen")
                        4 -> navController.navigate("akun_screen")
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun MenuItem(iconRes: Int, title: String, function: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { function() }
    ) {
        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            modifier = Modifier.size(56.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(text = title, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun PromoCard(title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 14.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = subtitle, fontSize = 12.sp, color = Color.DarkGray)
        }
    }
}



