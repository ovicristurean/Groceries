package com.ovidiucristurean.groceries.ui.util

import platform.UIKit.UIPasteboard

actual fun copyToClipboard(text: String) {
    UIPasteboard.generalPasteboard().string = text
}
