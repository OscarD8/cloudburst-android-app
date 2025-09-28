package com.example.cloudburst.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.cloudburst.R
import com.example.cloudburst.ui.theme.CloudburstTheme
import com.example.cloudburst.ui.theme.TopRoundedShape30
import com.example.cloudburst.ui.theme.shadowCustom

@Composable
fun CloudburstHomeScreen(
//    locationUiState: LocationUiState,
    windowSize: WindowWidthSizeClass,
//    contentType: CloudburstContentType,
    modifier: Modifier = Modifier
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact(modifier = modifier)
        }
        WindowWidthSizeClass.Medium -> {
            HomeScreenMedium()
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenExpanded()
        } else -> {
            HomeScreenCompact()
        }
    }
}

@Composable
internal fun HomeScreenCompact(
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    val verticalBias by animateFloatAsState(
        targetValue = if (isExpanded) 0.3f else 0.6f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )

    ConstraintLayout(
        modifier = modifier
    ) {
        val (textBox, image) = createRefs()

        Image(
            painter = painterResource(R.drawable.home_cover_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(TopRoundedShape30)
                .constrainAs(image) {
                    if (!isExpanded) {
                        bottom.linkTo(textBox.bottom, margin = 36.dp)
                    } else {
                        top.linkTo(parent.top, margin = 36.dp)
                    } // the logic is correct but the animation is all over the place now
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth(0.7f)
                .shadowCustom(
                    offsetY = 2.dp,
                    blurRadius = 10.dp,
                    shapeRadius = 30.dp,
                    color = Color.Gray
                )
        )
        Surface(
            shape = RoundedCornerShape(100.dp),
            color = MaterialTheme.colorScheme.inverseOnSurface,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                )
                .constrainAs(textBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top) // Align to the (animated) top of the container

                    this.verticalBias = verticalBias
                }
                .padding(10.dp)
                .fillMaxWidth(0.9f)
                .shadowCustom(
                    offsetY = 2.dp,
                    blurRadius = 10.dp,
                    shapeRadius = 100.dp,
                    color = Color.Gray
                )
        ) {
            Column (
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Cloudburst",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top= 10.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                AnimatedVisibility(visible = isExpanded) {
                    Text(
                        text = stringResource(R.string.home_city_description),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                IconButton(onClick = {isExpanded = !isExpanded}) {
                    Icon(
                        imageVector = if (!isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "Expand or collapse"
                    )
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.outline)
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.atr_icon),
                            contentDescription = "Click to Explore",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 10.dp),
                            tint = MaterialTheme.colorScheme.inverseOnSurface
                        )
                        Text(
                            text = "Explore",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.inverseOnSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun HomeScreenMedium() {

}

@Composable
internal fun HomeScreenExpanded() {

}

@Composable
@Preview (showBackground = true)
fun PreviewHomeScreenCompact() {
    CloudburstTheme {
        HomeScreenCompact(
            modifier = Modifier.fillMaxSize()
        )
    }
}