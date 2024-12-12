package com.ibrahimcanerdogan.jettodo.ui.component.priority

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.jettodo.R
import com.ibrahimcanerdogan.jettodo.data.model.TaskPriority
import com.ibrahimcanerdogan.jettodo.ui.theme.LARGE_PADDING
import com.ibrahimcanerdogan.jettodo.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.ibrahimcanerdogan.jettodo.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDownMenu(
    taskPriority: TaskPriority,
    onPrioritySelected: (TaskPriority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Angle Animation"
    )

    var parentSize by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                parentSize = it.size
            }
            .background(MaterialTheme.colorScheme.background)
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                ),
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(weight = 1f)
        ) {
            drawCircle(color = taskPriority.color)
        }
        Text(
            modifier = Modifier
                .weight(weight = 8f),
            text = taskPriority.name,
            style = MaterialTheme.typography.bodyMedium
        )
        IconButton(
            modifier = Modifier
                .alpha(0.5f)
                .rotate(degrees = angle)
                .weight(weight = 1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.drop_down_arrow)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { parentSize.width.toDp() }),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            TaskPriority.entries.toTypedArray().slice(0..2).forEach { priority ->
                DropdownMenuItem(
                    text = { PriorityDropDownItem(taskPriority = priority) },
                    onClick = {
                        expanded = false
                        onPrioritySelected(priority)
                    }
                )
            }
        }
    }
}


@Composable
@Preview
private fun PriorityDropDownMenuPreview() {
    PriorityDropDownMenu(
        taskPriority = TaskPriority.LOW,
        onPrioritySelected = {}
    )
}


@Composable
fun PriorityDropDownItem(taskPriority: TaskPriority) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
            drawCircle(color = taskPriority.color)
        }
        Text(
            modifier = Modifier
                .padding(start = LARGE_PADDING),
            text = taskPriority.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
fun PriorityDropDownItemPreview() {
    PriorityDropDownItem(taskPriority = TaskPriority.HIGH)
}


