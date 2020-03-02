package com.olabode.wilson.statussaver.ui.adapters.listeners

class StatusClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(position: Int) = clickListener(position)
}