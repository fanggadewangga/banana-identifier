@file:Suppress("DEPRECATION")

package com.fangga.core.components.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.data.model.enums.ResultItemSwipeType
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.resource.bodyText14Medium
import com.fangga.core.resource.deleteActionBackground
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.redMainDanger
import com.fangga.core.resource.tosca10
import com.fangga.core.utils.noRippleClickable

/**
 * **Composable Function:** ResultItem
 *
 * **Purpose:**
 * A composable function that displays a result item with swipeable actions.
 * This function is used to display a card-like item that can be swiped to reveal
 * an action button (e.g., save or delete).
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the result item.
 * - `data`: The data object of type `ScanResultList` to display in the item.
 * - `swipeType`: An enum value of type `ResultItemSwipeType` that determines the
 *   action to be performed when swiped (e.g., save or delete).
 * - `onItemClicked`: A lambda function that is called when the item is clicked.
 *   It receives the `resultId` of the item as a parameter.
 * - `onActionClicked`: A lambda function that is called when the action button is clicked.
 *
 * **Functionality:**
 * - Creates a `Card` to act as the container for the result item.
 * - Uses a `swipeable` modifier to enable swiping on the item.
 * - Displays a revealable action button based on the `swipeType`.
 * - Displays the main content of the item, including an image, banana type, ripeness type,
 *   and timestamp.
 * - Handles clicks on the item and the action button.
 *
 * **Usage:**
 * Use this composable function to display result items with swipeable actions in your
 * Jetpack Compose UI.
 **/


@Deprecated("Migrate Swipeable to AnchorDraggable")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    data: ScanResultList,
    swipeType: ResultItemSwipeType,
    onItemClicked: (String) -> Unit,
    onActionClicked: () -> Unit,
) {

    val swipeState = rememberSwipeableState(initialValue = 0)
    val size = with(LocalDensity.current) { 32.dp.toPx() }
    val anchors = mapOf(0f to 0, -size to 1)

    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .swipeable(
                    state = swipeState,
                    thresholds = { _, _ -> FractionalThreshold(0.9f) },
                    orientation = Orientation.Horizontal,
                    anchors = anchors
                )
                .background(color = Color.Transparent)
        ) {
            // Reveal content
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .background(color = if (swipeType == ResultItemSwipeType.SAVE) tosca10 else deleteActionBackground)
                    .noRippleClickable { onActionClicked() }
            ) {
                AppText(
                    text = if (swipeType == ResultItemSwipeType.SAVE) "Simpan" else "Hapus",
                    textStyle = bodyText14Medium,
                    color = if (swipeType == ResultItemSwipeType.SAVE) greenPrimary else redMainDanger,
                    modifier = Modifier
                        .width(74.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            // Main content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(x = swipeState.offset.value.dp)
                    .background(Color.White)
                    .noRippleClickable { onItemClicked(data.resultId) }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(12.dp)
                ) {
                    AppImage(
                        imageUrl = data.image,
                        contentDescription = "${data.bananaType} image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(height = 88.dp, width = 80.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            AppText(
                                text = data.bananaType, textStyle = bodyText14Medium
                            )
                            AppText(
                                text = data.ripenessType,
                                textStyle = bodyText12Regular
                            )
                        }
                        AppText(
                            text = data.timestamp,
                            textStyle = bodyText12Regular,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}
