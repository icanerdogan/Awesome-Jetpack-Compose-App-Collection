package com.ibrahimcanerdogan.jettodo.data.model

import androidx.compose.ui.graphics.Color
import com.ibrahimcanerdogan.jettodo.ui.theme.HighPriorityColor
import com.ibrahimcanerdogan.jettodo.ui.theme.LowPriorityColor
import com.ibrahimcanerdogan.jettodo.ui.theme.MediumPriorityColor

enum class TaskPriority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(Color.Transparent)
}