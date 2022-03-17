package xyz.madki.dilmiltest.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.madki.dilmiltest.domain.GetMemesUseCase
import xyz.madki.dilmiltest.ui.memes.MemesScreen
import xyz.madki.dilmiltest.ui.memes.MemesViewModel

@Composable
fun Navigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.MEMES) {
        composable(route = NavigationKeys.Route.MEMES) {
            MemesDestination(navController)
        }
    }
}

@Composable
private fun MemesDestination(navController: NavHostController) {
    val viewModel: MemesViewModel = hiltViewModel()
    MemesScreen(viewModel)
}

object NavigationKeys {
    object Route {
        const val MEMES = "memes"
    }
}