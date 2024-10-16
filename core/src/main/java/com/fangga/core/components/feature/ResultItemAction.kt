import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.data.model.enums.ResultItemSwipeType
import com.fangga.core.resource.bodyText12Medium
import com.fangga.core.resource.deleteActionBackground
import com.fangga.core.resource.redMainDanger
import com.fangga.core.resource.tosca10
import com.fangga.core.resource.tosca100

@Composable
fun ResultItemAction(
    actionType: ResultItemSwipeType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor =
        if (actionType == ResultItemSwipeType.DELETE)
            deleteActionBackground
        else
            tosca10

    val textColor =
        if (actionType == ResultItemSwipeType.DELETE)
            redMainDanger
        else
            tosca100

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = 70.dp, height = 88.dp)
            .background(color = backgroundColor)
            .clickable { onClick() }
    ) {
        AppText(
            text = if (actionType == ResultItemSwipeType.DELETE) "Hapus" else "Simpan",
            textStyle = bodyText12Medium,
            color = textColor
        )
    }
}