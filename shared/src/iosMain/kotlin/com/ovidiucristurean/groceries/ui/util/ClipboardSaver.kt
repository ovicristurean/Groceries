package com.ovidiucristurean.groceries.ui.util

import platform.UIKit.UIPasteboard

actual class ClipboardSaver {
    actual fun saveToClipboard(text: String) {
        UIPasteboard.generalPasteboard().string = text
    }

}