package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.PaymentUnlockDialog
import com.example.ui.components.StudyReaderSheet
import com.example.ui.screens.CreatorStudioScreen
import com.example.ui.screens.LaunchEarnGuideScreen
import com.example.ui.screens.SavedNotesScreen
import com.example.ui.screens.StudyHubScreen
import com.example.ui.theme.EmeraldGreen40
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppTab
import com.example.viewmodel.StudyHubViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                UPBoardStudyHubApp()
            }
        }
    }
}

@Composable
fun UPBoardStudyHubApp(
    viewModel: StudyHubViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Handle system back from non-home tabs
    BackHandler(enabled = uiState.currentTab != AppTab.STUDY_HUB) {
        viewModel.selectTab(AppTab.STUDY_HUB)
    }

    // If a note is open in the full-screen reader, show StudyReaderSheet
    val activeReaderItem = uiState.selectedReaderItem
    if (activeReaderItem != null) {
        StudyReaderSheet(
            item = activeReaderItem,
            isBookmarked = uiState.bookmarkedIds.contains(activeReaderItem.id),
            onToggleBookmark = { viewModel.toggleBookmark(activeReaderItem.id) },
            onClose = { viewModel.closeReader() }
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.safeDrawing,
            bottomBar = {
                NavigationBar(
                    modifier = Modifier.testTag("bottom_nav_bar")
                ) {
                    NavigationBarItem(
                        selected = uiState.currentTab == AppTab.STUDY_HUB,
                        onClick = { viewModel.selectTab(AppTab.STUDY_HUB) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentTab == AppTab.STUDY_HUB) {
                                    Icons.AutoMirrored.Filled.MenuBook
                                } else {
                                    Icons.Outlined.MenuBook
                                },
                                contentDescription = stringResource(id = R.string.nav_study_hub)
                            )
                        },
                        label = { Text(stringResource(id = R.string.nav_study_hub)) },
                        modifier = Modifier.testTag("nav_tab_study_hub")
                    )

                    NavigationBarItem(
                        selected = uiState.currentTab == AppTab.SAVED_NOTES,
                        onClick = { viewModel.selectTab(AppTab.SAVED_NOTES) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentTab == AppTab.SAVED_NOTES) {
                                    Icons.Default.Bookmark
                                } else {
                                    Icons.Outlined.BookmarkBorder
                                },
                                contentDescription = stringResource(id = R.string.nav_saved)
                            )
                        },
                        label = { Text(stringResource(id = R.string.nav_saved)) },
                        modifier = Modifier.testTag("nav_tab_saved")
                    )

                    NavigationBarItem(
                        selected = uiState.currentTab == AppTab.CREATOR_STUDIO,
                        onClick = { viewModel.selectTab(AppTab.CREATOR_STUDIO) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentTab == AppTab.CREATOR_STUDIO) {
                                    Icons.Default.AdminPanelSettings
                                } else {
                                    Icons.Outlined.AdminPanelSettings
                                },
                                contentDescription = stringResource(id = R.string.nav_creator_studio)
                            )
                        },
                        label = { Text(stringResource(id = R.string.nav_creator_studio)) },
                        modifier = Modifier.testTag("nav_tab_creator_studio")
                    )

                    NavigationBarItem(
                        selected = uiState.currentTab == AppTab.LAUNCH_EARN_GUIDE,
                        onClick = { viewModel.selectTab(AppTab.LAUNCH_EARN_GUIDE) },
                        icon = {
                            Icon(
                                imageVector = if (uiState.currentTab == AppTab.LAUNCH_EARN_GUIDE) {
                                    Icons.Default.RocketLaunch
                                } else {
                                    Icons.Outlined.RocketLaunch
                                },
                                contentDescription = stringResource(id = R.string.nav_earn_guide)
                            )
                        },
                        label = { Text(stringResource(id = R.string.nav_earn_guide)) },
                        modifier = Modifier.testTag("nav_tab_launch_earn")
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AnimatedVisibility(visible = uiState.statusBannerMessage != null) {
                    uiState.statusBannerMessage?.let { msg ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = EmeraldGreen40
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = msg,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(onClick = { viewModel.clearStatusBanner() }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Dismiss notification",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    when (uiState.currentTab) {
                        AppTab.STUDY_HUB -> StudyHubScreen(
                            uiState = uiState,
                            onSearchQueryChange = viewModel::updateSearchQuery,
                            onSelectSubject = viewModel::selectSubject,
                            onSelectTypeFilter = viewModel::selectTypeFilter,
                            onToggleBookmark = viewModel::toggleBookmark,
                            onOpenPaymentModal = viewModel::openPaymentModal,
                            onOpenReader = viewModel::openReader
                        )

                        AppTab.SAVED_NOTES -> SavedNotesScreen(
                            uiState = uiState,
                            onToggleBookmark = viewModel::toggleBookmark,
                            onOpenPaymentModal = viewModel::openPaymentModal,
                            onOpenReader = viewModel::openReader,
                            onExploreAllClick = { viewModel.selectTab(AppTab.STUDY_HUB) }
                        )

                        AppTab.CREATOR_STUDIO -> CreatorStudioScreen(
                            uiState = uiState,
                            onSavePaymentConfig = viewModel::updateCreatorPaymentConfig,
                            onAddMaterial = viewModel::addStudyMaterial,
                            onDeleteMaterial = viewModel::deleteStudyMaterial,
                            onResetDefaults = viewModel::resetMaterialsToDefault
                        )

                        AppTab.LAUNCH_EARN_GUIDE -> LaunchEarnGuideScreen(
                            uiState = uiState
                        )
                    }
                }
            }
        }
    }

    // Paid Notes Unlock Modal (#payModal equivalent)
    uiState.selectedPayItem?.let { payItem ->
        PaymentUnlockDialog(
            item = payItem,
            upiId = uiState.upiId,
            whatsappNumber = uiState.whatsappNumber,
            onDismiss = viewModel::closePaymentModal,
            onInstantUnlockPreview = viewModel::unlockNoteInApp
        )
    }
}
