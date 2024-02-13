package com.chirag047.rapidrepair.Presentation.Components

import com.chirag047.rapidrepair.R

sealed class NavigationItem(val route: String, val label: String, val icon: Int) {
    object HomeNav : NavigationItem("HomeScreen", "Home", R.drawable.house)
    object VehicleNav : NavigationItem("VehicleScreen", "Vehicle", R.drawable.car)
    object TrackNav : NavigationItem("TrackScreen", "Track", R.drawable.track_nav_icon)
    object ProfileNav : NavigationItem("ProfileScreen", "Profile", R.drawable.account)

}
