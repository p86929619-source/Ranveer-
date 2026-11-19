package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.StudyHubDefaults
import com.example.model.StudyMaterial
import com.example.ui.components.openExternalUrl
import com.example.ui.theme.AmberGoldContainer
import com.example.ui.theme.EmeraldGreen40
import com.example.ui.theme.EmeraldGreenContainer
import com.example.ui.theme.OnAmberGoldContainer
import com.example.ui.theme.OnEmeraldGreenContainer
import com.example.ui.theme.PaidOrange
import com.example.ui.theme.RoyalBlue30
import com.example.ui.theme.RoyalBlue40
import com.example.viewmodel.StudyHubUiState
import com.example.viewmodel.TypeFilter

@Composable
fun StudyHubScreen(
    uiState: StudyHubUiState,
    onSearchQueryChange: (String) -> Unit,
    onSelectSubject: (String) -> Unit,
    onSelectTypeFilter: (TypeFilter) -> Unit,
    onToggleBookmark: (Int) -> Unit,
    onOpenPaymentModal: (StudyMaterial) -> Unit,
    onOpenReader: (StudyMaterial) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val filteredList = uiState.filteredMaterials

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("study_hub_list"),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Hero Banner & Search Box
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                RoyalBlue40,
                                RoyalBlue30,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(148.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_study_hero_1795070910768),
                            contentDescription = "UP Board 12th Study Hub Banner",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xD90F172A),
                                            Color(0x800F172A),
                                            Color.Transparent
                                        )
                                    )
                                )
                                .padding(16.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Column {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = AmberGoldContainer
                                ) {
                                    Text(
                                        text = "UPMSP CLASS 12 • 2025-26",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = OnAmberGoldContainer,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = stringResource(id = R.string.header_title),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color.White
                                )
                                Text(
                                    text = stringResource(id = R.string.header_subtitle),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Search Bar matching #searchInput
                OutlinedTextField(
                    value = uiState.searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_input"),
                    placeholder = {
                        Text(text = stringResource(id = R.string.search_placeholder))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (uiState.searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = { onSearchQueryChange("") },
                                modifier = Modifier.testTag("clear_search_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }

        // Subject Filter Chips matching subjects = ['All', 'Hindi', 'English', 'Physics', 'Chemistry', 'Maths', 'Biology', 'Arts/Commerce']
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("subject_filter_row"),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(StudyHubDefaults.SUBJECTS) { subject ->
                        val isSelected = uiState.selectedSubject == subject
                        FilterChip(
                            selected = isSelected,
                            onClick = { onSelectSubject(subject) },
                            label = {
                                Text(
                                    text = subject,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                                )
                            },
                            modifier = Modifier.testTag("filter_chip_${subject.lowercase().replace("/", "_")}"),
                            shape = RoundedCornerShape(20.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = RoyalBlue40,
                                selectedLabelColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.surface,
                                labelColor = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }

                // Type filter row (All, Free PDFs, Paid Notes)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val freeCount = uiState.materials.count { !it.isPaid }
                    val paidCount = uiState.materials.count { it.isPaid }
                    TypeFilterChip(
                        label = "All (${uiState.materials.size})",
                        selected = uiState.selectedTypeFilter == TypeFilter.ALL,
                        onClick = { onSelectTypeFilter(TypeFilter.ALL) },
                        tag = "type_filter_all"
                    )
                    TypeFilterChip(
                        label = "Free PDF ($freeCount)",
                        selected = uiState.selectedTypeFilter == TypeFilter.FREE_ONLY,
                        onClick = { onSelectTypeFilter(TypeFilter.FREE_ONLY) },
                        tag = "type_filter_free"
                    )
                    TypeFilterChip(
                        label = "Paid Notes ($paidCount)",
                        selected = uiState.selectedTypeFilter == TypeFilter.PAID_ONLY,
                        onClick = { onSelectTypeFilter(TypeFilter.PAID_ONLY) },
                        tag = "type_filter_paid"
                    )
                }
            }
        }

        // Empty state or PDF Cards Grid
        if (filteredList.isEmpty()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .testTag("empty_pdf_state"),
                    shape = RoundedCornerShape(14.dp),
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
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(44.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = stringResource(id = R.string.empty_pdf_message),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(filteredList, key = { it.id }) { item ->
                val isBookmarked = uiState.bookmarkedIds.contains(item.id)
                val isUnlocked = !item.isPaid || uiState.unlockedIds.contains(item.id)

                StudyMaterialCard(
                    item = item,
                    isBookmarked = isBookmarked,
                    isUnlocked = isUnlocked,
                    onToggleBookmark = { onToggleBookmark(item.id) },
                    onFreeDownloadClick = {
                        openExternalUrl(context, item.link)
                    },
                    onReadInAppClick = {
                        if (isUnlocked) {
                            onOpenReader(item)
                        } else {
                            onOpenPaymentModal(item)
                        }
                    },
                    onUnlockPaidClick = {
                        onOpenPaymentModal(item)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun TypeFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    tag: String
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .testTag(tag),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun StudyMaterialCard(
    item: StudyMaterial,
    isBookmarked: Boolean,
    isUnlocked: Boolean,
    onToggleBookmark: () -> Unit,
    onFreeDownloadClick: () -> Unit,
    onReadInAppClick: () -> Unit,
    onUnlockPaidClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("pdf_card_${item.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top Row: Subject Badge + FREE / RS Price Badge + Bookmark Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Subject Badge (.badge)
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = Color(0xFFDBEAFE)
                    ) {
                        Text(
                            text = item.subject,
                            style = MaterialTheme.typography.labelMedium,
                            color = RoyalBlue30,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                        )
                    }

                    // Price / Free Badge (.tag-free / .tag-paid)
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (!item.isPaid) EmeraldGreenContainer else AmberGoldContainer
                    ) {
                        Text(
                            text = if (!item.isPaid) "FREE" else "RS ${item.price}",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (!item.isPaid) OnEmeraldGreenContainer else OnAmberGoldContainer,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                        )
                    }
                }

                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier
                        .size(48.dp)
                        .testTag("bookmark_btn_${item.id}")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = if (isBookmarked) "Remove bookmark" else "Save note",
                        tint = if (isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Description
            Text(
                text = item.desc,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Action Buttons
            if (!item.isPaid) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onReadInAppClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("read_notes_btn_${item.id}"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuBook,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Notes Padhein",
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = onFreeDownloadClick,
                        modifier = Modifier
                            .weight(1.25f)
                            .height(48.dp)
                            .testTag("free_download_btn_${item.id}"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EmeraldGreen40,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Free PDF Download",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (isUnlocked) {
                        OutlinedButton(
                            onClick = onReadInAppClick,
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("read_unlocked_btn_${item.id}"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LockOpen,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Unlocked Notes")
                        }
                    }

                    Button(
                        onClick = onUnlockPaidClick,
                        modifier = Modifier
                            .weight(1.25f)
                            .height(48.dp)
                            .testTag("unlock_paid_btn_${item.id}"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PaidOrange,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = if (isUnlocked) Icons.Default.WorkspacePremium else Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Unlock Notes (Rs ${item.price})",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
