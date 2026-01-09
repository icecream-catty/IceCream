package xyz.malefic.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                        // Primary colors
                        primary = Color(0xFF4CAF50) // Green
                        onPrimary = Color(0xFF003910)
                        primaryContainer = Color(0xFF005319)
                        onPrimaryContainer = Color(0xFFB3F0A1)

                        // Secondary colors
                        secondary = Color(0xFF81C784)
                        onSecondary = Color(0xFF003910)
                        secondaryContainer = Color(0xFF1B5E20)
                        onSecondaryContainer = Color(0xFFC8E6C9)

                        // Tertiary colors
                        tertiary = Color(0xFF66BB6A)
                        onTertiary = Color(0xFF002106)
                        tertiaryContainer = Color(0xFF00390A)
                        onTertiaryContainer = Color(0xFFA5D6A7)

                        // Error colors
                        error = Color(0xFFFFB4AB)
                        onError = Color(0xFF690005)
                        errorContainer = Color(0xFF93000A)
                        onErrorContainer = Color(0xFFFFDAD6)

                        // Background colors
                        background = Color(0xFF000000) // Black
                        onBackground = Color(0xFFE1E3DF)

                        // Surface colors
                        surface = Color(0xFF121212)
                        onSurface = Color(0xFFE1E3DF)
                        surfaceVariant = Color(0xFF424940)
                        onSurfaceVariant = Color(0xFFC1C9BE)

                        // Surface tints and containers
                        surfaceTint = Color(0xFF4CAF50)
                        surfaceContainer = Color(0xFF1A1C19)
                        surfaceContainerHigh = Color(0xFF252823)
                        surfaceContainerHighest = Color(0xFF30332E)
                        surfaceContainerLow = Color(0xFF191C18)
                        surfaceContainerLowest = Color(0xFF0D0F0C)

                        // Outline colors
                        outline = Color(0xFF8B9389)
                        outlineVariant = Color(0xFF424940)

                        // Inverse colors
                        inverseSurface = Color(0xFFE1E3DF)
                        inverseOnSurface = Color(0xFF2E312D)
                        inversePrimary = Color(0xFF006D2C)

                        // Other
                        scrim = Color(0xFF000000)
                    }
                } else {
                    themeConfig {
                        // Primary colors
                        primary = Color(0xFFD32F2F) // Red
                        onPrimary = Color(0xFFFFFFFF)
                        primaryContainer = Color(0xFFFFDAD6)
                        onPrimaryContainer = Color(0xFF410002)

                        // Secondary colors
                        secondary = Color(0xFFE57373)
                        onSecondary = Color(0xFFFFFFFF)
                        secondaryContainer = Color(0xFFFFCDD2)
                        onSecondaryContainer = Color(0xFF2C0000)

                        // Tertiary colors
                        tertiary = Color(0xFFEF5350)
                        onTertiary = Color(0xFFFFFFFF)
                        tertiaryContainer = Color(0xFFFFCDD2)
                        onTertiaryContainer = Color(0xFF3E0000)

                        // Error colors
                        error = Color(0xFFBA1A1A)
                        onError = Color(0xFFFFFFFF)
                        errorContainer = Color(0xFFFFDAD6)
                        onErrorContainer = Color(0xFF410002)

                        // Background colors
                        background = Color(0xFFFFFFFF) // White
                        onBackground = Color(0xFF191C1A)

                        // Surface colors
                        surface = Color(0xFFFAFAFA)
                        onSurface = Color(0xFF191C1A)
                        surfaceVariant = Color(0xFFE0E3E0)
                        onSurfaceVariant = Color(0xFF424940)

                        // Surface tints and containers
                        surfaceTint = Color(0xFFD32F2F)
                        surfaceContainer = Color(0xFFEEEEEE)
                        surfaceContainerHigh = Color(0xFFE8E8E8)
                        surfaceContainerHighest = Color(0xFFE0E0E0)
                        surfaceContainerLow = Color(0xFFF5F5F5)
                        surfaceContainerLowest = Color(0xFFFFFFFF)

                        // Outline colors
                        outline = Color(0xFF727970)
                        outlineVariant = Color(0xFFC1C9BE)

                        // Inverse colors
                        inverseSurface = Color(0xFF2E312E)
                        inverseOnSurface = Color(0xFFEFF1ED)
                        inversePrimary = Color(0xFFFFB4AB)

                        // Other
                        scrim = Color(0xFF000000)
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
        Box(
            Modifier.fillMaxHeight().width(250.dp).background(MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = { navi gate "home" }) { Text("home") }
        }
        RoutedNavHost()
    }
