package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.GuideCardItem
import com.example.model.StudyHubDefaults
import com.example.ui.components.copyToClipboard
import com.example.ui.components.openExternalUrl
import com.example.ui.theme.AmberGoldContainer
import com.example.ui.theme.EmeraldGreen40
import com.example.ui.theme.EmeraldGreenContainer
import com.example.ui.theme.OnAmberGoldContainer
import com.example.ui.theme.OnEmeraldGreenContainer
import com.example.viewmodel.StudyHubUiState

@Composable
fun LaunchEarnGuideScreen(
    uiState: StudyHubUiState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showFullHtmlCode by remember { mutableStateOf(false) }

    val generatedHtml = remember(uiState.whatsappNumber, uiState.upiId, uiState.materials) {
        StudyHubDefaults.generateCompleteHtmlCode(
            whatsappNumber = uiState.whatsappNumber,
            upiId = uiState.upiId,
            materials = uiState.materials
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("launch_earn_guide_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Free Launch & Earning Guide",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Is app se students ki madad kaise karein, paise kaise kamayein, legal rules aur bina kharch ke internet par live kaise karein.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Section 1: Complete index.html Web App Exporter
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Ready-to-Host index.html Web App Code",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        text = "Yeh HTML code aapke Creator Studio ke WhatsApp (${uiState.whatsappNumber}), UPI ID (${uiState.upiId}), aur ${uiState.materials.size} PDFs ke sath auto-synced hai.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                copyToClipboard(context, "index.html Code", generatedHtml)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("copy_html_code_button"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EmeraldGreen40,
                                contentColor = Color.White
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Copy index.html",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        OutlinedButton(
                            onClick = {
                                val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                    type = "text/plain"
                                    putExtra(Intent.EXTRA_SUBJECT, "index.html")
                                    putExtra(Intent.EXTRA_TEXT, generatedHtml)
                                }
                                context.startActivity(
                                    Intent.createChooser(sendIntent, "Save or Share index.html")
                                )
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("share_html_code_button"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Share / Save File",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = { showFullHtmlCode = !showFullHtmlCode },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("toggle_html_preview_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = if (showFullHtmlCode) "Hide HTML Source Code" else "View Complete HTML Code (index.html)"
                        )
                    }

                    if (showFullHtmlCode) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF0F172A), RoundedCornerShape(10.dp))
                                .padding(12.dp)
                                .horizontalScroll(rememberScrollState()),
                            color = Color(0xFF0F172A),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = generatedHtml,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                color = Color(0xFFE2E8F0)
                            )
                        }
                    }
                }
            }
        }

        // Section 2: How to Launch for Free (4 Steps)
        item {
            SectionHeader(
                icon = Icons.Default.RocketLaunch,
                title = "How to Launch Web App for Free (4 Steps)"
            )
        }

        items(StudyHubDefaults.HOW_TO_LAUNCH_STEPS) { step ->
            GuideInfoCard(
                item = step,
                badgeContainerColor = MaterialTheme.colorScheme.primaryContainer,
                badgeContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        item {
            OutlinedButton(
                onClick = { openExternalUrl(context, "https://app.netlify.com/drop") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("open_netlify_drop_button"),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Open Netlify Drop (app.netlify.com/drop)")
            }
        }

        // Section 3: How to Earn / Benefit
        item {
            SectionHeader(
                icon = Icons.Default.MonetizationOn,
                title = "How to Earn & Benefit (4 Tarike)"
            )
        }

        items(StudyHubDefaults.HOW_TO_EARN) { earnItem ->
            GuideInfoCard(
                item = earnItem,
                badgeContainerColor = EmeraldGreenContainer,
                badgeContentColor = OnEmeraldGreenContainer
            )
        }

        // Section 4: Important Legal & Safety Rules
        item {
            SectionHeader(
                icon = Icons.Default.Gavel,
                title = "Important Legal & Safety Rules"
            )
        }

        items(StudyHubDefaults.LEGAL_AND_SAFETY_RULES) { ruleItem ->
            GuideInfoCard(
                item = ruleItem,
                badgeContainerColor = AmberGoldContainer,
                badgeContentColor = OnAmberGoldContainer
            )
        }
    }
}

@Composable
private fun SectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun GuideInfoCard(
    item: GuideCardItem,
    badgeContainerColor: Color,
    badgeContentColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = badgeContainerColor
                ) {
                    Text(
                        text = item.badge,
                        style = MaterialTheme.typography.labelMedium,
                        color = badgeContentColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
