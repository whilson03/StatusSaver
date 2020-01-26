package com.olabode.wilson.statussaver.ui

import android.os.Environment
import androidx.appcompat.app.AppCompatDelegate
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException

/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
object Utils {
    // PATHS
    const val WHATSAPP_DIR = "/WhatsApp/Media/.Statuses"
    const val GBWHATSAPP_DIR = "/storage/emulated/0/GBWhatsApp/Media/.Statuses/"
    const val BIZWHATSAPP_DIR = "/storage/emulated/0/WhatsApp Business/Media/.Statuses/"


    // SAVE DIR
    const val WHATSAPP_SAVE_DIR = "/WhatsAppStatusesWT/Media/WhatsApp/"
    const val GBWHATSAPP_SAVE_DIR = "/WhatsAppStatusesWT/Media/GBWhatsApp/"
    const val BIZWHATSAPP_SAVE_DIR = "/WhatsAppStatusesWT/Media/BIZWhatsApp/"

    const val KEY_IMAGES = 0
    const val KEY_VIDEOS = 1


    fun saveFilestoDirectory(sourcePath: String, folderName: String, fileName: String) {
        val sourceFile = File(sourcePath)
        val dir = File(
            Environment.getExternalStorageDirectory()
                .toString() + folderName + "Media"
        )
        try {
            if (!dir.exists()) {
                dir.mkdir()
            }
            val destination = File(dir.absolutePath + fileName)
            FileUtils.copyFile(sourceFile, destination)
        } catch (e: IOException) {
            throw IOException("Error Saving")
        }
    }
}


object ThemeHelper {
    private const val lightMode = "light"
    private const val darkMode = "dark"
    private const val batterySaverMode = "battery"
    const val default = "default"

    fun applyTheme(theme: String) {
        when (theme) {
            lightMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            batterySaverMode -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            default -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }
    }
}