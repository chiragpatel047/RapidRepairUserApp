package com.chirag047.rapidrepair.Presentation.Components

import com.chirag047.rapidrepair.R

sealed class NavigationItem(val route: String, val label: String, val icon: Int) {
    object HomeNav : NavigationItem("homeNav", "Home", R.drawable.house)
    object VehicleNav : NavigationItem("vehicleNav", "Vehicle", R.drawable.car)
    object ServiceNav : NavigationItem("serviceNav", "Service", R.drawable.store)
    object ProfileNav : NavigationItem("profileNav", "Profile", R.drawable.account)

}
