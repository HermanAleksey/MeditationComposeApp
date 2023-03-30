package com.example.shuffle_puzzle.internal.presentation.composables.puzzle_board

import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Build.VERSION_CODES
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.example.feature.shuffle_puzzle.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun SelectPuzzleImageFromGalleryCard(
    modifier: Modifier,
    onImageSelected: (Bitmap) -> Unit,
) {
    val context = LocalContext.current

    val cropImage = rememberLauncherForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            try {
                result.uriContent?.let {
                    val bitmap: Bitmap = if (Build.VERSION.SDK_INT < VERSION_CODES.P) {
                        @Suppress("DEPRECATION")
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
        } else {
            // an error occurred
//            val exception = result.error
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

    Column(
        modifier = modifier
            .clickable {
                externalStoragePermission.launchPermissionRequest()
                if (externalStoragePermission.status == PermissionStatus.Granted) {
                    startCrop()
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_selection),
            contentDescription = "Select image from gallery"
        )

        val selectPuzzleImageFromGalleryLabel =
            stringResource(id = R.string.select_puzzle_image_from_gallery_text)
        Text(
            text = selectPuzzleImageFromGalleryLabel,
            modifier = Modifier.wrapContentWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}