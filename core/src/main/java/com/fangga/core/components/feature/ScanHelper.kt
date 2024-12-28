package com.fangga.core.components.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.data.model.enums.ScanExampleType
import com.fangga.core.resource.bodyText12Regular
import com.fangga.core.resource.scanExampleBlur
import com.fangga.core.resource.scanExampleCorrect
import com.fangga.core.resource.scanExampleTooClose
import com.fangga.core.resource.scanExampleTooFar
import com.fangga.core.resource.scanExampleTooManyType
import com.fangga.core.resource.yellow10

/**
 * **Composable Function:** ScanHelper
 *
 * **Purpose:**
 * A composable function that displays a helper section for scanning, providing
 * examples of correct and incorrect scanning techniques.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the scan helper section.
 * - `backgroundColor`: The background color of the scan helper section.
 * - `helperHeader`: A composable lambda that defines the header content for the
 *   scan helper section.
 *
 * **Functionality:**
 * - Creates a `Box` to act as the container for the scan helper section.
 * - Sets the background color of the scan helper section.
 * - Displays a header using the `helperHeader` composable lambda.
 * - Displays a text description of the correct scanning technique.
 * - Displays an example of a correct scan using the `ScanImageExample` composable.
 * - Displays a divider to separate the correct and incorrect examples.
 * - Displays a text description of the incorrect scanning techniques.
 * - Displays examples of incorrect scans using the `ScanImageExample` composable,
 *   including examples of "too close," "too far," "blur," and "too many types."
 *
 * **Usage:**
 * Use this composable function to display a scan helper section in your Jetpack Compose UI.
 **/


@Composable
fun ScanHelper(
    modifier: Modifier = Modifier,
    backgroundColor: Color = yellow10,
    helperHeader: @Composable () -> Unit,
) {
    Box(modifier = modifier.background(color = backgroundColor)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            helperHeader()

            AppText(
                text = "Contoh mengambil foto dan menata pisang dengan benar",
                textStyle = bodyText12Regular,
                modifier = Modifier.align(Alignment.Start)
            )
            ScanImageExample(
                imageSource = scanExampleCorrect,
                contentDescription = "Scan Correct Example",
                type = ScanExampleType.CORRECT,
                imageHeight = 230.dp,
                imageWidth = 160.dp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Divider(
                thickness = (1).dp,
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth()
            )
            AppText(
                text = "Berikut kemungkinan besar akan menghasilkan identifikasi yang kurang baik",
                textStyle = bodyText12Regular
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ScanImageExample(
                    imageSource = scanExampleTooClose,
                    contentDescription = "Scan Too Close Example",
                    type = ScanExampleType.INCORRECT,
                    description = "Terlalu dekat",
                    imageHeight = 120.dp,
                    imageWidth = 144.dp,
                )
                ScanImageExample(
                    imageSource = scanExampleTooFar,
                    contentDescription = "Scan Too Far Example",
                    type = ScanExampleType.INCORRECT,
                    description = "Terlalu jauh",
                    imageHeight = 120.dp,
                    imageWidth = 144.dp,
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                ScanImageExample(
                    imageSource = scanExampleBlur,
                    contentDescription = "Scan Blur Example",
                    type = ScanExampleType.INCORRECT,
                    description = "Buram",
                    imageHeight = 120.dp,
                    imageWidth = 144.dp,
                )
                ScanImageExample(
                    imageSource = scanExampleTooManyType,
                    contentDescription = "Scan Too Many Tipe Example",
                    type = ScanExampleType.INCORRECT,
                    description = "Banyak Jenis",
                    imageHeight = 120.dp,
                    imageWidth = 144.dp,
                )
            }
        }
    }
}