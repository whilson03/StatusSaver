package com.olabode.wilson.statussaver.ui

import android.os.Environment
import androidx.appcompat.app.AppCompatDelegate
import java.io.File
import java.io.FileNotFoundException


/**
 *   Created by OLABODE WILSON on 2020-01-10.
 */
object Utils {
    // PATHS
    const val WHATSAPP_DIR = "/WhatsApp/Media/.Statuses"
    const val GBWHATSAPP_DIR = "/storage/emulated/0/GBWhatsApp/Media/.Statuses/"
    const val BIZWHATSAPP_DIR = "/storage/emulated/0/WhatsApp Business/Media/.Statuses/"


    // SAVE
    const val BASE_SAVEPATH = "/WhatsAppStatusesWT/"
    const val WHATSAPP_SAVE_DIR = "WhatsApp/"
    const val GBWHATSAPP_SAVE_DIR = "GBWhatsApp/"
    const val BIZWHATSAPP_SAVE_DIR = "BIZWhatsApp/"

    const val KEY_IMAGES = 0
    const val KEY_VIDEOS = 1


    fun saveFilestoDirectory(sourcePath: String, statusType: StatusType) {
        val sourceFile = File(sourcePath)
        val folderName = getFolderName(statusType)
        val dir = File(
            Environment.getExternalStorageDirectory()
                .toString() + "${BASE_SAVEPATH}Media/$folderName${sourcePath.substringAfterLast('/')}"
        )

        try {

            sourceFile.copyTo(dir)

        } catch (e: NoSuchFileException) {
            throw NoSuchFileException(sourceFile)

        } catch (e: FileAlreadyExistsException) {
            throw FileAlreadyExistsException(sourceFile)
        } catch (e: FileNotFoundException) {
            throw FileNotFoundException("File Not Found")
        }
    }

    fun getFolderName(statusType: StatusType): String {
        return when (statusType) {
            StatusType.BIZ_WHATSAPP -> BIZWHATSAPP_SAVE_DIR
            StatusType.GB_WHATSAPP -> GBWHATSAPP_SAVE_DIR
            else -> WHATSAPP_SAVE_DIR
        }
    }


}

enum class StatusType {
    WHATSAPP, BIZ_WHATSAPP, GB_WHATSAPP
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