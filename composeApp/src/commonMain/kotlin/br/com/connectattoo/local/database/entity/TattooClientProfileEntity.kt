package br.com.connectattoo.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import br.com.connectattoo.domain.model.Gallery
import br.com.connectattoo.domain.model.TattooClientProfile
import br.com.connectattoo.domain.model.toGalleryList
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity(tableName = "profile")
data class TattooClientProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "display_name") val name: String? = "",
    @ColumnInfo(name = "user_name") val username: String? = "",
    @ColumnInfo(name = "birth_date") val birthDate: String? = "",
    @ColumnInfo(name = "image_profile") val imageProfile: String? = "",
 //   var tags: List<TagEntity> = emptyList(),
    val email: String? = "",
  //  var galleries: List<Gallery> = emptyList(),
) {

    fun toTattooClientProfile(): TattooClientProfile =
        TattooClientProfile(
            name = this.name ?: "",
            username = this.username ?: "",
            birthDate = this.birthDate ?: "",
            imageProfile = this.imageProfile ?: "",
           // tags = this.tags.toTag(),
            email = this.email,
           // galleries = this.galleries.toGalleryList()
        )

}
/*
class TattooClientProfileConverters {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @TypeConverter
    fun fromTagEntityList(tags: List<TagEntity>): String {
        // Converte a lista de TagEntity para uma string JSON
        return json.encodeToString(tags)
    }

    @TypeConverter
    fun toTagEntityList(tagsAsString: String): List<TagEntity> {
        // Converte a string JSON de volta para a lista de TagEntity
        return json.decodeFromString(tagsAsString)
    }

    @TypeConverter
    fun fromGalleryList(gallery: List<Gallery>): String {
        // Converte a lista de Gallery para uma string JSON
        return json.encodeToString(gallery)
    }

    @TypeConverter
    fun toGalleryList(galleryAsString: String): List<Gallery> {
        // Converte a string JSON de volta para a lista de Gallery
        return json.decodeFromString(galleryAsString)
    }
}*/

