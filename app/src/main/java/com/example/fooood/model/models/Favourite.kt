package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.fooood.utils.DateTimeUtils
import java.io.Serializable

@Entity(indices = [Index(value = ["id"], unique = true)])
class Favourite : BaseModel(), Serializable {
    @PrimaryKey(autoGenerate = false)
    var id: String = ""
    var meal: String = ""
    var drinkAlternate: String? = ""
    var category: String? = ""
    var area: String? = ""
    var instructions: String? = ""
    var mealThumb: String? = ""
    var tags: String? = ""
    var youTube: String? = ""
    var ingredientOne: String? = ""
    var ingredientTwo: String? = ""
    var ingredientThree: String? = ""
    var ingredientFour: String? = ""
    var ingredientFive: String? = ""
    var ingredientSix: String? = ""
    var ingredientSeven: String? = ""
    var ingredientEight: String? = ""
    var ingredientNine: String? = ""
    var ingredientTen: String? = ""
    var ingredientEleven: String? = ""
    var ingredientTwelve: String? = ""
    var ingredientThirteen: String? = ""
    var ingredientFourteen: String? = ""
    var ingredientFifteen: String? = ""
    var ingredientSixteen: String? = ""
    var ingredientSeventeen: String? = ""
    var ingredientEighteen: String? = ""
    var ingredientNineteen: String? = ""
    var ingredientTwenty: String? = ""
    var measurementOne: String? = ""
    var measurementTwo: String? = ""
    var measurementThree: String? = ""
    var measurementFour: String? = ""
    var measurementFive: String? = ""
    var measurementSix: String? = ""
    var measurementSeven: String? = ""
    var measurementEight: String? = ""
    var measurementNine: String? = ""
    var measurementTen: String? = ""
    var measurementEleven: String? = ""
    var measurementTwelve: String? = ""
    var measurementThirteen: String? = ""
    var measurementFourteen: String? = ""
    var measurementFifteen: String? = ""
    var measurementSixteen: String? = ""
    var measurementSeventeen: String? = ""
    var measurementEighteen: String? = ""
    var measurementNineteen: String? = ""
    var measurementTwenty: String? = ""
    var source: String? = ""
    var imageSource: String? = ""
    var creativeCommonsConfirmed: String? = ""
    var dateModified: String? = ""
    var bookId: Int = 1
    var timestamp: String = DateTimeUtils.getCurrentDateTime()
}