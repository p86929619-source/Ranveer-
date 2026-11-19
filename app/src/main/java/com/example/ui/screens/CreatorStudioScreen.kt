package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.model.StudyHubDefaults
import com.example.viewmodel.StudyHubUiState

@Composable
fun CreatorStudioScreen(
    uiState: StudyHubUiState,
    onSavePaymentConfig: (whatsapp: String, upi: String) -> Unit,
    onAddMaterial: (subject: String, title: String, desc: String, isPaid: Boolean, price: Int, link: String) -> Unit,
    onDeleteMaterial: (Int) -> Unit,
    onResetDefaults: () -> Unit,
    modifier: Modifier = Modifier
) {
    var whatsappInput by rememberSaveable(uiState.whatsappNumber) {
        mutableStateOf(uiState.whatsappNumber)
    }
    var upiInput by rememberSaveable(uiState.upiId) {
        mutableStateOf(uiState.upiId)
    }

    val availableSubjects = remember {
        StudyHubDefaults.SUBJECTS.filterNot { it == "All" }
    }
    var selectedSubject by rememberSaveable { mutableStateOf(availableSubjects.first()) }
    var noteTitle by rememberSaveable { mutableStateOf("") }
    var noteDesc by rememberSaveable { mutableStateOf("") }
    var isPaidNote by rememberSaveable { mutableStateOf(false) }
    var notePrice by rememberSaveable { mutableStateOf("29") }
    var noteLink by rememberSaveable { mutableStateOf("https://drive.google.com/") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("creator_studio_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Creator Studio (Admin Setup)",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Apna WhatsApp number, UPI ID aur Google Drive PDF links yahan update karein. Yeh बदलाव app aur HTML code dono me apply honge.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Card 1: UPI & WhatsApp Payment Configuration
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "1. UPI & WhatsApp Unlock Settings",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    OutlinedTextField(
                        value = whatsappInput,
                        onValueChange = { whatsappInput = it },
                        label = { Text("WhatsApp Number (with 91 country code)") },
                        placeholder = { Text("919999999999") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("creator_whatsapp_input")
                    )

                    OutlinedTextField(
                        value = upiInput,
                        onValueChange = { upiInput = it },
                        label = { Text("Your UPI ID (for receiving payments)") },
                        placeholder = { Text("aapka-upi@okaxis") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("creator_upi_input")
                    )

                    Button(
                        onClick = { onSavePaymentConfig(whatsappInput, upiInput) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("save_payment_config_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "WhatsApp & UPI ID Save Karein",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Card 2: Add New PDF / Study Note
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "2. Naya Subject PDF / Note Add Karein",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "Select Subject:",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(availableSubjects) { sub ->
                            FilterChip(
                                selected = selectedSubject == sub,
                                onClick = { selectedSubject = sub },
                                label = { Text(sub) },
                                modifier = Modifier.testTag("add_subject_chip_${sub.lowercase().replace("/", "_")}")
                            )
                        }
                    }

                    OutlinedTextField(
                        value = noteTitle,
                        onValueChange = { noteTitle = it },
                        label = { Text("PDF / Note Title") },
                        placeholder = { Text("e.g., 12th Physics Unsolved Paper 2025 Solved") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("new_note_title_input")
                    )

                    OutlinedTextField(
                        value = noteDesc,
                        onValueChange = { noteDesc = it },
                        label = { Text("Short Description") },
                        placeholder = { Text("Important derivations, formulas aur solved questions...") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("new_note_desc_input")
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isPaidNote) "Paid Notes (UPI + WhatsApp Unlock)" else "Free PDF (Direct Google Drive Link)",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Switch on for Paid Notes (e.g. Rs 29 / Rs 49)",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = isPaidNote,
                            onCheckedChange = { isPaidNote = it },
                            modifier = Modifier.testTag("new_note_paid_switch")
                        )
                    }

                    if (isPaidNote) {
                        OutlinedTextField(
                            value = notePrice,
                            onValueChange = { notePrice = it.filter { ch -> ch.isDigit() } },
                            label = { Text("Price in INR (Rs)") },
                            placeholder = { Text("29") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("new_note_price_input")
                        )
                    }

                    OutlinedTextField(
                        value = noteLink,
                        onValueChange = { noteLink = it },
                        label = { Text("Google Drive Shareable PDF Link") },
                        placeholder = { Text("https://drive.google.com/...") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("new_note_link_input")
                    )

                    Button(
                        onClick = {
                            if (noteTitle.isNotBlank() && noteDesc.isNotBlank()) {
                                onAddMaterial(
                                    selectedSubject,
                                    noteTitle,
                                    noteDesc,
                                    isPaidNote,
                                    notePrice.toIntOrNull() ?: 29,
                                    noteLink
                                )
                                noteTitle = ""
                                noteDesc = ""
                            }
                        },
                        enabled = noteTitle.isNotBlank() && noteDesc.isNotBlank(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("add_note_submit_button"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Study Hub Me PDF Add Karein",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Card 3: Manage Current PDFs
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Manage Study Hub PDFs (${uiState.materials.size})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedButton(
                    onClick = onResetDefaults,
                    modifier = Modifier.testTag("reset_defaults_button"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Reset Defaults")
                }
            }
        }

        items(uiState.materials, key = { it.id }) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${item.subject} • ${if (item.isPaid) "RS ${item.price}" else "FREE"}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = { onDeleteMaterial(item.id) },
                        modifier = Modifier.testTag("delete_material_btn_${item.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete material",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
