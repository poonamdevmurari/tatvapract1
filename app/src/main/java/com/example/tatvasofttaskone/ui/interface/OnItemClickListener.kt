package com.example.tatvasofttaskone.ui.`interface`

import com.example.tatvasofttaskone.data.model.CustomButton

interface OnItemClickListener {
   fun  onItemClick(t: CustomButton, position:Int)
}