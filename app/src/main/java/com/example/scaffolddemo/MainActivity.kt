package com.example.scaffolddemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffolddemo.ui.theme.ScaffoldDemoTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldDemoTheme {
                ScaffoldApp()
            }
        }
    }
}

@Composable
fun Contentti(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ScaffoldAppPreview() {
    ScaffoldDemoTheme {
        ScaffoldApp()
    }
}

@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home")
    {
        composable("Home") { MainScreen(navController) }
        composable("Info") { InfoScreen(navController) }
        composable("Settings") { SettingsScreen(navController) }
    }
/*
    Scaffold(
        topBar = { MainTopBar() },
        //bottomBar = { MyBottomBar() },
        modifier = Modifier.fillMaxSize())
    { innerPadding ->
        Greeting(
            name = "Android",
            modifier = Modifier.padding(innerPadding)
        )
    }
*/
}

/*https://medium.com/@sedakundakitchen/creating-a-top-app-bar-with-material-design-3-jetpack-compose-1d7fe3c97d12*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String, navController: NavController
) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate("Info") },
                    text = { Text(text = "Info") })
                DropdownMenuItem(onClick = { navController.navigate("Settings") },
                    text = { Text(text = "Settings") })
            }
        }
    )
}

/*
@Composable
fun MyBottomBar() {
    BottomAppBar(
        content = {
            Text(text = "Bottom Bar")
        }
    )
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar("Main Screen", navController) },
        modifier = Modifier.fillMaxSize())
    { innerPadding ->
        Contentti(
            text = "Content for Home screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info Screen", navController) },
        modifier = Modifier.fillMaxSize())
    { innerPadding ->
        Contentti(
            text = "Content for Info screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings Screen", navController) },
        modifier=Modifier.fillMaxSize())
    { innerPadding ->
        Contentti(
            text = "Content for Settings screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}