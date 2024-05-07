package com.example.yemekuygulamasi

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.example.yemekuygulamasi.entity.Yemekler
import com.example.yemekuygulamasi.ui.theme.YemekUygulamasiTheme
import com.example.yemekuygulamasi.viewmodel.AnaSayfaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YemekUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigator(screen = AnaSayfa()) {
                        FadeTransition(navigator = it)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
class AnaSayfa: Screen {
    @SuppressLint("DiscouragedApi")
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = remember { AnaSayfaViewModel() } //anasayfaviewmodelden bir nesne oluşturduk. anasayfaviewmodeldeki init metodu çalışacak fun yemekleriyukle sayaesinde yemkler gelecek !
        val yemekListesi = viewModel.yemeklerListesi.observeAsState(listOf()) //anasayfa viewmodeldeki livedataya ulaştık state özelliğini livedata sayesinde devam ettirecek


    Scaffold (
        topBar = {
                  TopAppBar(
                      title = { Text(text = "Yemekler" )},
                      colors = TopAppBarDefaults.topAppBarColors(
                          containerColor = colorResource(id = R.color.anaRenk),
                          titleContentColor = colorResource(id = R.color.white),
                      )
                  )
        },
        content = {
            LazyColumn {
                items(
                    count = yemekListesi.value!!.count(), //nullable yapı olabilir !! ünlem koyduk
                    itemContent = {
                        val yemek = yemekListesi.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 5.dp)
                            .fillMaxWidth()
                        ) {
                            Row (modifier =  Modifier.clickable {
                                Log.e("Yemek", "$yemek tıklandı")
                                navigator?.push(DetaySayfa(yemek = yemek))

                            }) {
                                Row (verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    val activity = (LocalContext.current as Activity)
                                    Image(bitmap = ImageBitmap.imageResource(id = activity.resources.getIdentifier(
                                        yemek.yemek_resim_adi, "drawable", activity.packageName
                                    )), contentDescription = "" , modifier = Modifier.size(70.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(all = 10.dp)

                                        ) {
                                        Column (verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxHeight()){
                                            Text(text = yemek.yemek_adi, fontSize = 20.sp)
                                            Spacer(modifier = Modifier.size(10.dp))
                                            Text(text = "${yemek.yemek_fiyat} ₺ ", color = Color.Blue, fontSize = 20.sp)
                                        }
                                        Icon(painter = painterResource(id = R.drawable.arrow_resim),
                                            contentDescription = "")
                                    }

                                }

                            }

                        }
                    }
                )
            }
            
        }
    )
   }
}

@Preview(showBackground = true)
@Composable
 fun GreetingPreview() {
    YemekUygulamasiTheme {
        AnaSayfa().Content()
    }
 }
}

