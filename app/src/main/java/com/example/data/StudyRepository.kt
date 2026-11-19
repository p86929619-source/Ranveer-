package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.StudyHubDefaults
import com.example.model.StudyMaterial
import com.example.model.StudyNoteSection
import org.json.JSONArray
import org.json.JSONObject

class StudyRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("up_board_study_hub_prefs", Context.MODE_PRIVATE)

    fun getWhatsappNumber(): String =
        prefs.getString(KEY_WHATSAPP, StudyHubDefaults.DEFAULT_WHATSAPP)
            ?: StudyHubDefaults.DEFAULT_WHATSAPP

    fun setWhatsappNumber(number: String) {
        prefs.edit().putString(KEY_WHATSAPP, number.trim()).apply()
    }

    fun getUpiId(): String =
        prefs.getString(KEY_UPI, StudyHubDefaults.DEFAULT_UPI)
            ?: StudyHubDefaults.DEFAULT_UPI

    fun setUpiId(upiId: String) {
        prefs.edit().putString(KEY_UPI, upiId.trim()).apply()
    }

    fun getBookmarkedIds(): Set<Int> {
        val raw = prefs.getStringSet(KEY_BOOKMARKS, emptySet()) ?: emptySet()
        return raw.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun saveBookmarkedIds(ids: Set<Int>) {
        prefs.edit().putStringSet(KEY_BOOKMARKS, ids.map { it.toString() }.toSet()).apply()
    }

    fun getUnlockedIds(): Set<Int> {
        val raw = prefs.getStringSet(KEY_UNLOCKED, emptySet()) ?: emptySet()
        return raw.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun saveUnlockedIds(ids: Set<Int>) {
        prefs.edit().putStringSet(KEY_UNLOCKED, ids.map { it.toString() }.toSet()).apply()
    }

    fun getStudyMaterials(): List<StudyMaterial> {
        val savedJson = prefs.getString(KEY_CUSTOM_MATERIALS, null) ?: return StudyHubDefaults.INITIAL_MATERIALS
        return try {
            val array = JSONArray(savedJson)
            val result = mutableListOf<StudyMaterial>()
            val defaultById = StudyHubDefaults.INITIAL_MATERIALS.associateBy { it.id }
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val id = obj.getInt("id")
                val fallbackSections = defaultById[id]?.sections ?: listOf(
                    StudyNoteSection(
                        heading = "Key Revision Points",
                        points = listOf(
                            obj.optString("desc", "Important UP Board Class 12 revision material."),
                            "Board Exam ke liye sabhi formulas, definitions aur previous year questions zaroor revise karein."
                        )
                    )
                )
                result.add(
                    StudyMaterial(
                        id = id,
                        subject = obj.getString("subject"),
                        title = obj.getString("title"),
                        desc = obj.getString("desc"),
                        isPaid = obj.getBoolean("isPaid"),
                        price = obj.getInt("price"),
                        link = obj.optString("link", "https://drive.google.com/"),
                        badgeTag = obj.optString("badgeTag", "2025-26 Syllabus"),
                        sections = fallbackSections
                    )
                )
            }
            if (result.isEmpty()) StudyHubDefaults.INITIAL_MATERIALS else result
        } catch (e: Exception) {
            StudyHubDefaults.INITIAL_MATERIALS
        }
    }

    fun saveStudyMaterials(materials: List<StudyMaterial>) {
        val array = JSONArray()
        materials.forEach { item ->
            val obj = JSONObject().apply {
                put("id", item.id)
                put("subject", item.subject)
                put("title", item.title)
                put("desc", item.desc)
                put("isPaid", item.isPaid)
                put("price", item.price)
                put("link", item.link)
                put("badgeTag", item.badgeTag)
            }
            array.put(obj)
        }
        prefs.edit().putString(KEY_CUSTOM_MATERIALS, array.toString()).apply()
    }

    fun resetToDefaultMaterials(): List<StudyMaterial> {
        prefs.edit().remove(KEY_CUSTOM_MATERIALS).apply()
        return StudyHubDefaults.INITIAL_MATERIALS
    }

    companion object {
        private const val KEY_WHATSAPP = "my_whatsapp"
        private const val KEY_UPI = "my_upi"
        private const val KEY_BOOKMARKS = "bookmarked_ids"
        private const val KEY_UNLOCKED = "unlocked_ids"
        private const val KEY_CUSTOM_MATERIALS = "custom_materials_json"
    }
}
