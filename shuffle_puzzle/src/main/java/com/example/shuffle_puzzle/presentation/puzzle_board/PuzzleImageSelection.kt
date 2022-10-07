package com.example.shuffle_puzzle.presentation.puzzle_board

import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SelectPuzzleImageFromGalleryCard(
    modifier: Modifier,
    onImageSelected: (Bitmap) -> Unit,
) {
    val context = LocalContext.current

    val cropImage = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            try {
                result.uriContent?.let {
                    val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(
                            context.contentResolver,
                            it
                        )
                    } else {
                        val source = ImageDecoder.createSource(
                            context.contentResolver,
                            it
                        )
                        ImageDecoder.decodeBitmap(source)
                    }

                    onImageSelected(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Log.e("TAG", "SelectPuzzleImageFromGalleryCard: ")

        } else {
            // an error occurred
            val exception = result.error
        }
    }

    fun startCrop() {
        // start picker to get image for cropping and then use the image in cropping activity
        cropImage.launch(options {
            setAspectRatio(1, 1)
            setImageSource(includeGallery = true, includeCamera = false)
            setGuidelines(CropImageView.Guidelines.ON)
        })
    }


    val externalStoragePermission =
        rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)


    Box(contentAlignment = Alignment.Center, modifier = modifier
        .clickable {
            externalStoragePermission.launchPermissionRequest()
            if (externalStoragePermission.status == PermissionStatus.Granted) {
                startCrop()
            }
        }) {
        Text(text = "Click here to select photo from your gallery")
    }
}