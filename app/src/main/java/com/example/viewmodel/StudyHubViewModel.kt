package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.StudyRepository
import com.example.model.StudyHubDefaults
import com.example.model.StudyMaterial
import com.example.model.StudyNoteSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppTab {
    STUDY_HUB,
    SAVED_NOTES,
    CREATOR_STUDIO,
    LAUNCH_EARN_GUIDE
}

enum class TypeFilter {
    ALL,
    FREE_ONLY,
    PAID_ONLY
}

data class StudyHubUiState(
    val currentTab: AppTab = AppTab.STUDY_HUB,
    val searchQuery: String = "",
    val selectedSubject: String = "All",
    val selectedTypeFilter: TypeFilter = TypeFilter.ALL,
    val materials: List<StudyMaterial> = StudyHubDefaults.INITIAL_MATERIALS,
    val bookmarkedIds: Set<Int> = emptySet(),
    val unlockedIds: Set<Int> = emptySet(),
    val whatsappNumber: String = StudyHubDefaults.DEFAULT_WHATSAPP,
    val upiId: String = StudyHubDefaults.DEFAULT_UPI,
    val selectedPayItem: StudyMaterial? = null,
    val selectedReaderItem: StudyMaterial? = null,
    val statusBannerMessage: String? = null
) {
    val filteredMaterials: List<StudyMaterial>
        get() {
            val q = searchQuery.trim().lowercase()
            return materials.filter { item ->
                val matchesSubject = selectedSubject == "All" || item.subject.equals(selectedSubject, ignoreCase = true)
                val matchesType = when (selectedTypeFilter) {
                    TypeFilter.ALL -> true
                    TypeFilter.FREE_ONLY -> !item.isPaid
                    TypeFilter.PAID_ONLY -> item.isPaid
                }
                val matchesQuery = q.isEmpty() ||
                    item.title.lowercase().contains(q) ||
                    item.desc.lowercase().contains(q) ||
                    item.subject.lowercase().contains(q)
                matchesSubject && matchesType && matchesQuery
            }
        }
}

class StudyHubViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = StudyRepository(application.applicationContext)

    private val _uiState = MutableStateFlow(
        StudyHubUiState(
            materials = repository.getStudyMaterials(),
            bookmarkedIds = repository.getBookmarkedIds(),
            unlockedIds = repository.getUnlockedIds(),
            whatsappNumber = repository.getWhatsappNumber(),
            upiId = repository.getUpiId()
        )
    )
    val uiState: StateFlow<StudyHubUiState> = _uiState.asStateFlow()

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab, statusBannerMessage = null) }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectSubject(subject: String) {
        _uiState.update { it.copy(selectedSubject = subject) }
    }

    fun selectTypeFilter(filter: TypeFilter) {
        _uiState.update { it.copy(selectedTypeFilter = filter) }
    }

    fun toggleBookmark(id: Int) {
        _uiState.update { state ->
            val updated = if (state.bookmarkedIds.contains(id)) {
                state.bookmarkedIds - id
            } else {
                state.bookmarkedIds + id
            }
            repository.saveBookmarkedIds(updated)
            state.copy(bookmarkedIds = updated)
        }
    }

    fun openPaymentModal(item: StudyMaterial) {
        _uiState.update { it.copy(selectedPayItem = item) }
    }

    fun closePaymentModal() {
        _uiState.update { it.copy(selectedPayItem = null) }
    }

    fun openReader(item: StudyMaterial) {
        _uiState.update { it.copy(selectedReaderItem = item) }
    }

    fun closeReader() {
        _uiState.update { it.copy(selectedReaderItem = null) }
    }

    fun unlockNoteInApp(item: StudyMaterial) {
        _uiState.update { state ->
            val updated = state.unlockedIds + item.id
            repository.saveUnlockedIds(updated)
            state.copy(
                unlockedIds = updated,
                selectedPayItem = null,
                selectedReaderItem = item,
                statusBannerMessage = "${item.title} unlocked for in-app reading!"
            )
        }
    }

    fun updateCreatorPaymentConfig(whatsapp: String, upi: String) {
        val cleanWa = whatsapp.trim().ifEmpty { StudyHubDefaults.DEFAULT_WHATSAPP }
        val cleanUpi = upi.trim().ifEmpty { StudyHubDefaults.DEFAULT_UPI }
        repository.setWhatsappNumber(cleanWa)
        repository.setUpiId(cleanUpi)
        _uiState.update {
            it.copy(
                whatsappNumber = cleanWa,
                upiId = cleanUpi,
                statusBannerMessage = "WhatsApp & UPI ID updated successfully!"
            )
        }
    }

    fun addStudyMaterial(
        subject: String,
        title: String,
        desc: String,
        isPaid: Boolean,
        price: Int,
        link: String
    ) {
        if (title.isBlank() || desc.isBlank()) return
        _uiState.update { state ->
            val nextId = (state.materials.maxOfOrNull { it.id } ?: 7) + 1
            val newItem = StudyMaterial(
                id = nextId,
                subject = subject,
                title = title.trim(),
                desc = desc.trim(),
                isPaid = isPaid,
                price = if (isPaid) price.coerceAtLeast(1) else 0,
                link = link.trim().ifEmpty { "https://drive.google.com/" },
                badgeTag = if (isPaid) "Premium PDF" else "Free Study PDF",
                sections = listOf(
                    StudyNoteSection(
                        heading = "Chapter Summary & Exam Points",
                        points = listOf(
                            desc.trim(),
                            "UP Board Class 12 exam ke liye is PDF ke sabhi important questions aur formulas revise karein."
                        )
                    )
                )
            )
            val updated = listOf(newItem) + state.materials
            repository.saveStudyMaterials(updated)
            state.copy(
                materials = updated,
                statusBannerMessage = "Added '${newItem.title}' to Study Hub!"
            )
        }
    }

    fun deleteStudyMaterial(id: Int) {
        _uiState.update { state ->
            val updated = state.materials.filterNot { it.id == id }
            repository.saveStudyMaterials(updated)
            state.copy(
                materials = updated,
                statusBannerMessage = "Study material removed."
            )
        }
    }

    fun resetMaterialsToDefault() {
        val defaults = repository.resetToDefaultMaterials()
        _uiState.update {
            it.copy(
                materials = defaults,
                statusBannerMessage = "Restored default UP Board 12th subjects & PDFs."
            )
        }
    }

    fun clearStatusBanner() {
        _uiState.update { it.copy(statusBannerMessage = null) }
    }
}
