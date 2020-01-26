package com.olabode.wilson.statussaver

class StatusClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(position: Int) = clickListener(position)
}