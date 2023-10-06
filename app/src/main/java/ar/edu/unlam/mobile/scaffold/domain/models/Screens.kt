package ar.edu.unlam.mobile.scaffold.domain.models

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Secondary : Screens("secondary") {
        fun withId(id: Int) = "secondary/$id"
    }

}