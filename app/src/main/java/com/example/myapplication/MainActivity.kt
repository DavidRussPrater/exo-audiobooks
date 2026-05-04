package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.ui.theme.SkeletonAndroidProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkeletonAndroidProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    val uiState by viewModel.data.collectAsStateWithLifecycle()

                    NavigationSuiteScaffold(
                        modifier = Modifier.fillMaxSize(),
                        navigationSuiteItems = {
                            AppDestinations.entries.forEach { destination ->
                                item(
                                    icon = {
                                        Icon(
                                            painter = painterResource(destination.icon),
                                            contentDescription = stringResource(destination.contentDescription)
                                        )
                                    },
                                    label = { Text(stringResource(destination.label)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true,
                                    onClick = {
                                        navController.navigate(destination.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppDestinations.ONE.route,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable(AppDestinations.ONE.route) {
                                AppTabScreen(
                                    titleRes = AppDestinations.ONE.label,
                                    contentText = uiState
                                )
                            }
                            composable(AppDestinations.TWO.route) {
                                AppTabScreen(
                                    titleRes = AppDestinations.TWO.label,
                                    contentText = stringResource(AppDestinations.TWO.label)
                                )
                            }
                            composable(AppDestinations.THREE.route) {
                                AppTabScreen(
                                    titleRes = AppDestinations.THREE.label,
                                    contentText = stringResource(AppDestinations.THREE.label)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AppTabScreen(
    @StringRes titleRes: Int,
    contentText: String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(titleRes)) })
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.screen, contentText),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabOnePreview() {
    SkeletonAndroidProjectTheme {
        AppTabScreen(
            titleRes = R.string.one,
            contentText = "Preview Data"
        )
    }
}

enum class AppDestinations(
    @param:StringRes val label: Int,
    @param:DrawableRes val icon: Int,
    @param:StringRes val contentDescription: Int,
    val route: String
) {
    ONE(R.string.one, R.drawable.water_loss_24, R.string.one, "one"),
    TWO(R.string.two, R.drawable.water_medium_24, R.string.two, "two"),
    THREE(R.string.three, R.drawable.water_full_24, R.string.three, "three")
}
