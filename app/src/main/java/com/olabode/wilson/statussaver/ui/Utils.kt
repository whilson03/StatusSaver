package com.olabode.wilson.statussaver.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Environment
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.FileProvider
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
    private const val BASE_SAVEPATH = "/WhatsAppStatusesWT/"
    private const val WHATSAPP_SAVE_DIR = "WhatsApp/"
    private const val GBWHATSAPP_SAVE_DIR = "GBWhatsApp/"
    private const val BIZWHATSAPP_SAVE_DIR = "BIZWhatsApp/"

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


    fun openWhatsApp(context: Context, path: String): Intent {
        val uri =
            FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider", File(path)
            )
        val intent = Intent(Intent.ACTION_SEND)
        intent.setPackage("com.whatsapp")
        intent.type = "image/* video/*"

        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        return intent
    }

    fun isIntentAvailable(ctx: Context, intent: Intent): Boolean {
        val packageManager = ctx.packageManager
        val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list.size > 0
    }


    fun shareToOtherApp(context: Context, path: String) {
        val uri =
            FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider", File(path)
            )
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/* video/*"

        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(Intent.createChooser(intent, "Share via"))
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