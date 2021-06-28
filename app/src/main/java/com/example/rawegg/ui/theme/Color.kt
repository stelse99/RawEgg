package com.example.rawegg.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val LightBlue = Color(0xFFBAC7FF)
val LightGrey = Color(0xFFAAAAAA)

val HPColor = Color(0xFFF5FF00)
val AtkColor = Color(1f, 0f, 0f, 0.66f)
val DefColor = Color(0f, 0f, 1f, 0.44f)
val SpAtkColor = Color(0.671f, 0f, 1f, 0.57f)
val SpDefColor = Color(1f, 0f, 0.8f, 0.7f)
val SpdColor = Color(0f, 1f, 0.063f, 0.55f)

val TypeNormal = Color(0xFFA8A77A)
val TypeFire = Color(0xFFEE8130)
val TypeWater = Color(0xFF6390F0)
val TypeElectric = Color(0xFFF7D02C)
val TypeGrass = Color(0xFF7AC74C)
val TypeIce = Color(0xFF96D9D6)
val TypeFighting = Color(0xFFC22E28)
val TypePoison = Color(0xFFA33EA1)
val TypeGround = Color(0xFFE2BF65)
val TypeFlying = Color(0xFFA98FF3)
val TypePsychic = Color(0xFFF95587)
val TypeBug = Color(0xFFA6B91A)
val TypeRock = Color(0xFFB6A136)
val TypeGhost = Color(0xFF735797)
val TypeDragon = Color(0xFF6F35FC)
val TypeDark = Color(0xFF705746)
val TypeSteel = Color(0xFFB7B7CE)
val TypeFairy = Color(0xFFD685AD)

val dark200 = Color(0xFFB4A5A5)
val dark500 = Color(0xFF3C415C)
val dark700 = Color(0xFF301B3F)
val dark900 = Color(0xFF151515)

val light200 = Color(0xFFCAF7E3)
val light500 = Color(0xFFF8EDED)
val light700 = Color(0xFFF6DFEB)
val light900 = Color(0xFFE4BAD4)


val purple200 = Color(0xFF000000)
val purple500 = Color(0xFF52057B)
val purple700 = Color(0xFF892CDC)
val purple900 = Color(0xFFBC6FF1)

val teal200 = Color(0xFFEBFFFA)
val teal500 = Color(0xFFC6FCE5)
val teal700 = Color(0xFF6EF3D6)
val teal900 = Color(0xFF03DAC5)

val yellow200 = Color(0xffffeb46)
val yellow400 = Color(0xffffc000)
val yellow500 = Color(0xffffde03)
val yellowDarkPrimary = Color(0xff242316)

val blue200 = Color(0xff91a4fc)
val blue700 = Color(0xff0336ff)
val blue800 = Color(0xff0035c9)
val blueDarkPrimary = Color(0xff1c1d24)

val pink200 = Color(0xffff7597)
val pink500 = Color(0xffff0266)
val pink600 = Color(0xffd8004d)
val pinkDarkPrimary = Color(0xff24191c)

/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}
