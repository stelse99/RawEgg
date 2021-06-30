package com.example.rawegg.views

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.rawegg.R
import com.example.rawegg.utils.DummyDataProvider
import java.util.*

// 2021.06.24 suchang Renamed VectorAsset to ImageVector
sealed class BottomNavigationMenu (
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Frankendroid : BottomNavigationMenu("Frankendroid", R.string.frankendroid_route, Icons.Filled.Terrain)
    object Pumpkin : BottomNavigationMenu("Pumpkin", R.string.pumpkin_screen_route, Icons.Filled.FoodBank)
    object Ghost : BottomNavigationMenu("Ghost", R.string.ghost_screen_route, Icons.Filled.Fireplace)
    object ScaryBag : BottomNavigationMenu("ScaryBag", R.string.scary_bag_screen_route, Icons.Filled.Cake)
}

sealed class ScaryAnimation (
    val animId: Int
) {
    //object Frankendroid: ScaryAnimation(R.raw.frankensteindroid)
    object Pumpkin: ScaryAnimation(R.raw.jackolantern)
    object Ghost: ScaryAnimation(R.raw.ghost)
    object ScaryBag: ScaryAnimation(R.raw.bag)
}


@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf (
        BottomNavigationMenu.Frankendroid,
        BottomNavigationMenu.Pumpkin,
        BottomNavigationMenu.Ghost,
        BottomNavigationMenu.ScaryBag
    )

    Scaffold (
        bottomBar = {
            RawEggBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        RawEggNavigationActions(navController)
    }
}

@Composable
private fun RawEggNavigationActions (
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = BottomNavigationMenu.Frankendroid.route
    ) {
        // BottomNavigations Start
        composable(BottomNavigationMenu.Frankendroid.route) {
            //ScaryScreen(ScaryAnimation.Frankendroid)
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
        composable(BottomNavigationMenu.Pumpkin.route) {
            //ScaryScreen(ScaryAnimation.Pumpkin)
            PokemonListScreen(navController = navController)
        }
        composable(BottomNavigationMenu.Ghost.route) {
            ScaryScreen(ScaryAnimation.Ghost)
        }
        composable(BottomNavigationMenu.ScaryBag.route) {
            //ScaryScreen(ScaryAnimation.ScaryBag)
            TestScreen()
        }
        // BottomNavigations End

        // Sub Navigations Start
        composable(
            "pokemon_detail_screen/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            )
        ) {
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }
            PokemonDetailScreen(
                dominantColor = dominantColor,
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController
            )
        }
        // Sub Navigations End
    }
}

@Composable
private fun RawEggBottomNavigation (
    navController: NavHostController,
    items: List<BottomNavigationMenu>
) {
    // 2021.06.24 suchang Change Icon parameters to specification value.
    BottomNavigation {
        val currentRoute = CurrentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem (
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                icon = {
                    Icon (
                        imageVector = screen.icon,
                        contentDescription= screen.route,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    )
                },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
private fun CurrentRoute (
    navController: NavHostController
): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    return navBackStackEntry?.destination?.route
}

@Composable
fun ScaryScreen (
    scaryAnimation: ScaryAnimation
) {
    // 2021.06.24 suchang Renamed ContextAmbient to LocalContext
    val context = LocalContext.current
    val customView = remember { LottieAnimationView(context) }
    // Adds view to Compose
    AndroidView(
        { customView },
        modifier = Modifier.background(Color.Black)
    ) { view ->
        // View's been inflated - add logic here if necessary
        with(view) {
            setAnimation(scaryAnimation.animId)
            playAnimation()
            repeatMode = LottieDrawable.REVERSE
        }
    }
}


