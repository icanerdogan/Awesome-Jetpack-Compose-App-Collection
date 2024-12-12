package com.ibrahimcanerdogan.cleannote.ui.view.list

import com.ibrahimcanerdogan.cleannote.data.model.Note
import com.ibrahimcanerdogan.cleannote.domain.util.NoteOrder
import com.ibrahimcanerdogan.cleannote.domain.util.OrderType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
