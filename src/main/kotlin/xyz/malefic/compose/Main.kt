package xyz.malefic.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import xyz.malefic.compose.comps.box.BackgroundBox
import xyz.malefic.compose.comps.precompose.NavWindow
import xyz.malefic.compose.comps.text.Headline
import xyz.malefic.compose.nav.RouteManager
import xyz.malefic.compose.nav.RouteManager.RoutedNavHost
import xyz.malefic.compose.nav.RouteManager.navi
import xyz.malefic.compose.screens.App1
import xyz.malefic.compose.screens.Home
import xyz.malefic.compose.theming.MaleficTheme
import xyz.malefic.compose.theming.themeConfig
import xyz.malefic.ext.list.get
import xyz.malefic.ext.precompose.gate

/**
 * Entry point of the application that sets up the main navigation window.
 *
 * It determines the theme based on the system's current theme (dark or light), applies the selected theme, and initializes the route manager. The navigation menu is then displayed.
 */
fun main() =
    application {
        NavWindow(onCloseRequest = ::exitApplication, title = "Ice Cream's Calendar") {
            // Initialize the route manager
            RouteManager.initialize {
                startup("home") {
                    Home(navi)
                }

                dynamic("app1", hidden = true, "id", "name?") { params ->
                    App1(params[0]!!, params[1, null])
                }

                dynamic("hidden", hidden = true, "text?") { params ->
                    Headline(text = params[0, "Nope."])
                }
            }

            // Determine the theme file path based on the system's theme (dark or light)
            val themeConfig =
                if (isSystemInDarkTheme()) {
                    themeConfig {
                        primary = androidx.compose.ui.graphics.Color.Green
                        background = androidx.compose.ui.graphics.Color.Black
                    }
                } else {
                    themeConfig {
                        primary = androidx.compose.ui.graphics.Color.Red
                        background = androidx.compose.ui.graphics.Color.White
                    }
                }

            // Apply the selected theme and invoke the Navigation Menu
            MaleficTheme(themeConfig) {
                BackgroundBox {
                    NavigationMenu()
                }
            }
        }
    }

/**
 * Composable function that defines the navigation menu layout. It includes a sidebar and a
 * content area separated by a divider.
 */
@Composable
fun NavigationMenu() =
    Row(Modifier.fillMaxSize()) {
        Box (Modifier.fillMaxHeight().width(250.dp).background(MaterialTheme.colorScheme.onBackground), contentAlignment = Alignment.Center) {
            Button(onClick = { navi gate "home"}){ Text("home", color = MaterialTheme.colorScheme.onPrimary)}
        }
        RoutedNavHost()
    }
