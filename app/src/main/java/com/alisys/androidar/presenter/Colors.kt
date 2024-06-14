package com.alisys.androidar.presenter

import androidx.compose.ui.graphics.Color


class CustomizedColors() {
    companion object {
        val Purple200= Color(0xFFBB86FC)
        val Purple500= Color(0xFF6200EE)
        val Purple700= Color(0xFF3700B3)
        val Teal200= Color(0xFF03DAC5)
        val darkEsmeralda = Color(0xFF02839E)
        val darkerLetters = Color(0xFF3D4543)
        val lightGreen = Color(0xFFB7E1BB)
        val darkGreen = Color(0xFF005A00)
        val darkBackGround = Color.Gray.copy(alpha = 0.7f)
        val darkBlue = Color(0xFF03002E)
        val esmeralda = Color(0xFF007481)
        val transparent = Color(0x00000000)
    }
}



//Light Palette
class LightThemeColors(){
    companion object{
        val MAIN_BACKGROUND= Color(0xFFE8E9E9)
        val BLUE500= Color(0xFF2196F3) // Primary
        val BLUE800= Color(0xFF0277BD) // Primary
        val CYAN500= Color(0xFF00BCD4) // Primary
        val CYAN700= Color(0xFF008BA3) // Primary
        val SURFACE= Color(0xFF60269E)
        val EMERGENCY_CALL= Color.Black
        val darkEsmeralda = Color(0xFF02626E)
    }
}


// Dark Palette
class DarkThemeColors(){
    companion object{
        val BLUE900= Color(0xFF0D47A1) // Primary
        val BLUE950= Color(0xFF002171)
        val CYAN900= Color(0xFF006064)
        val CYAN800= Color(0xFF428E92)
        val SURFACE = Color.White
        val EMERGENCY_CALL= Color.White
    }
}