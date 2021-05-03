package com.example.fooood.model.models

import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["id"], unique = true)])
class Meal : BaseModel() {
    @SerializedName("idMeal")
    var id: String = ""
    @SerializedName("strMeal")
    var meal: String = ""
    @SerializedName("strDrinkAlternate")
    var drinkAlternate: String = ""
    @SerializedName("strCategory")
    var category: String = ""
    @SerializedName("strArea")
    var area: String = ""
    @SerializedName("strInstructions")
    var instructions: String = ""
    @SerializedName("strMealThumb")
    var mealThumb: String = ""
    @SerializedName("strTags")
    var tags: String = ""
    @SerializedName("strYoutube")
    var youTube: String = ""
    @SerializedName("strIngredient1")
    var ingredientOne: String = ""
    @SerializedName("strIngredient2")
    var ingredientTwo: String = ""
    @SerializedName("strIngredient3")
    var ingredientThree: String = ""
    @SerializedName("strIngredient4")
    var ingredientFour: String = ""
    @SerializedName("strIngredient5")
    var ingredientFive: String = ""
    @SerializedName("strIngredient6")
    var ingredientSix: String = ""
    @SerializedName("strIngredient7")
    var ingredientSeven: String = ""
    @SerializedName("strIngredient8")
    var ingredientEight: String = ""
    @SerializedName("strIngredient9")
    var ingredientNine: String = ""
    @SerializedName("strIngredient10")
    var ingredientTen: String = ""
    @SerializedName("strIngredient11")
    var ingredientEleven: String = ""
    @SerializedName("strIngredient12")
    var ingredientTwelve: String = ""
    @SerializedName("strIngredient13")
    var ingredientThirteen: String = ""
    @SerializedName("strIngredient14")
    var ingredientFourteen: String = ""
    @SerializedName("strIngredient15")
    var ingredientFifteen: String = ""
    @SerializedName("strIngredient16")
    var ingredientSixteen: String = ""
    @SerializedName("strIngredient17")
    var ingredientSeventeen: String = ""
    @SerializedName("strIngredient18")
    var ingredientEighteen: String = ""
    @SerializedName("strIngredient19")
    var ingredientNineteen: String = ""
    @SerializedName("strIngredient20")
    var ingredientTwenty: String = ""
    @SerializedName("strMeasure1")
    var measurementOne: String = ""
    @SerializedName("strMeasure2")
    var measurementTwo: String = ""
    @SerializedName("strMeasure3")
    var measurementThree: String = ""
    @SerializedName("strMeasure4")
    var measurementFour: String = ""
    @SerializedName("strMeasure5")
    var measurementFive: String = ""
    @SerializedName("strMeasure6")
    var measurementSix: String = ""
    @SerializedName("strMeasure7")
    var measurementSeven: String = ""
    @SerializedName("strMeasure8")
    var measurementEight: String = ""
    @SerializedName("strMeasure9")
    var measurementNine: String = ""
    @SerializedName("strMeasure10")
    var measurementTen: String = ""
    @SerializedName("strMeasure11")
    var measurementEleven: String = ""
    @SerializedName("strMeasure12")
    var measurementTwelve: String = ""
    @SerializedName("strMeasure13")
    var measurementThirteen: String = ""
    @SerializedName("strMeasure14")
    var measurementFourteen: String = ""
    @SerializedName("strMeasure15")
    var measurementFifteen: String = ""
    @SerializedName("strMeasure16")
    var measurementSixteen: String = ""
    @SerializedName("strMeasure17")
    var measurementSeventeen: String = ""
    @SerializedName("strMeasure18")
    var measurementEighteen: String = ""
    @SerializedName("strMeasure19")
    var measurementNineteen: String = ""
    @SerializedName("strMeasure20")
    var measurementTwenty: String = ""
    @SerializedName("strSource")
    var source: String = ""
    @SerializedName("strImageSource")
    var imageSource: String = ""
    @SerializedName("strCreativeCommonsConfirmed")
    var creativeCommonsConfirmed: String = ""
    var dateModified: String = ""
    var bookId: Int = 1
}