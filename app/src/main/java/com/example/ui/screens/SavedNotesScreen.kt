package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.model.StudyMaterial
import com.example.ui.components.openExternalUrl
import com.example.viewmodel.StudyHubUiState

@Composable
fun SavedNotesScreen(
    uiState: StudyHubUiState,
    onToggleBookmark: (Int) -> Unit,
    onOpenPaymentModal: (StudyMaterial) -> Unit,
    onOpenReader: (StudyMaterial) -> Unit,
    onExploreAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val savedItems = uiState.materials.filter {
        uiState.bookmarkedIds.contains(it.id) || uiState.unlockedIds.contains(it.id)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("saved_notes_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Saved & Unlocked Notes",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Exam revision ke liye aapke bookmarked aur unlocked premium notes yahan save rehte hain.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (savedItems.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(28.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmarks,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Abhi tak koi note bookmark ya unlock nahi kiya gaya hai.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Study Hub me kisi bhi subject card par bookmark icon dabayein taaki exam se pehle jaldi revise kar sakein.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onExploreAllClick,
                            modifier = Modifier
                                .height(48.dp)
                                .testTag("explore_hub_button"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = "Study Hub Dekhein")
                        }
                    }
                }
            }
        } else {
            items(savedItems, key = { it.id }) { item ->
                val isBookmarked = uiState.bookmarkedIds.contains(item.id)
                val isUnlocked = !item.isPaid || uiState.unlockedIds.contains(item.id)

                StudyMaterialCard(
                    item = item,
                    isBookmarked = isBookmarked,
                    isUnlocked = isUnlocked,
                    onToggleBookmark = { onToggleBookmark(item.id) },
                    onFreeDownloadClick = { openExternalUrl(context, item.link) },
                    onReadInAppClick = {
                        if (isUnlocked) onOpenReader(item) else onOpenPaymentModal(item)
                    },
                    onUnlockPaidClick = { onOpenPaymentModal(item) }
                )
            }
        }
    }
}
