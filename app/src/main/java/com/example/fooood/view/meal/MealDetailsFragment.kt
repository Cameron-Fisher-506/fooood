package com.example.fooood.view.meal

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.fooood.R
import com.example.fooood.databinding.MealDetailsFragmentBinding
import com.example.fooood.enum.Status
import com.example.fooood.model.models.Favourite
import com.example.fooood.model.models.Meal
import com.example.fooood.utils.Resource
import com.example.fooood.utils.TextUtils
import com.example.fooood.view.menu.favourites.FavouriteViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class MealDetailsFragment : MealBaseFragment(R.layout.meal_details_fragment) {
    private lateinit var binding: MealDetailsFragmentBinding
    private lateinit var mealViewModel: MealViewModel
    private lateinit var favouriteViewModel: FavouriteViewModel

    private var isFavourite: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.binding = MealDetailsFragmentBinding.bind(view)

        this.mealViewModel = ViewModelProviders.of(this).get(MealViewModel::class.java)
        this.favouriteViewModel = ViewModelProviders.of(this).get(FavouriteViewModel::class.java)

        arguments?.let {
            val meal = MealDetailsFragmentArgs.fromBundle(it).meal
            favouriteViewModel.findById(meal.id)
            mealViewModel.fetchMealById(meal.id)
        }
        attachObservers()
    }

    private fun attachObservers() {
        mealViewModel.mealLiveData.observe(viewLifecycleOwner, {
            mealActivity.dismissProgressBar()
            when (it.status) {
                Status.SUCCESS -> {
                    displayMealDetails()
                    if (it.data != null && it.data.isNotEmpty()) {
                        wireUI(it.data.first())
                    } else {
                        displayMealErrorTextView()
                    }
                }
                Status.ERROR -> { displayMealErrorTextView() }
                Status.LOADING -> {  }
            }
        })

        favouriteViewModel.findByIdLiveData.observe(viewLifecycleOwner, {
            mealActivity.dismissProgressBar()
            when (it.status) {
                Status.SUCCESS -> {
                    isFavourite = true
                    binding.favouriteImageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                    displayMealDetails()
                }
                Status.LOADING -> {}
                Status.ERROR -> {
                    isFavourite = false
                    binding.favouriteImageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    displayMealDetails()
                }
            }
        })
    }

    private fun displayMealDetails() {
        with(this.binding) {
            mealImageView.visibility = View.VISIBLE
            mealTextView.visibility = View.VISIBLE
            ingredientsOneTextView.visibility = View.VISIBLE
            ingredientsTwoTextView.visibility = View.VISIBLE
            instructionsTextView.visibility = View.VISIBLE
            favouriteImageButton.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
            mealErrorTextView.visibility = View.GONE
        }
    }

    private fun hideMealDetails() {
        with(this.binding) {
            mealTextView.visibility = View.GONE
            mealImageView.visibility = View.GONE
            ingredientsOneTextView.visibility = View.GONE
            ingredientsTwoTextView.visibility = View.GONE
            instructionsTextView.visibility = View.GONE
            favouriteImageButton.visibility = View.GONE
        }
    }

    private fun displayMealErrorTextView() {
        hideMealDetails()
        with(this.binding) {
            mealErrorTextView.visibility = View.VISIBLE
            mealProgressBar.visibility = View.GONE
        }
    }

    private fun displayMealProgressBar() {
        hideMealDetails()
        with(this.binding) {
            mealErrorTextView.visibility = View.GONE
            mealProgressBar.visibility = View.VISIBLE
        }
    }

    private fun buildIngredientsListItem(ingredient: String?, measurement: String?): String {
        return if (ingredient != null && ingredient.isNotEmpty() && measurement != null && measurement.isNotEmpty()) {
            "${TextUtils.BULLET_POINT} $ingredient ($measurement)<br/>"
        } else {
            ""
        }
    }

    private fun wireUI(meal: Meal) {
        with(this.binding) {
            Glide.with(this.root)
                .asBitmap()
                .load(meal.mealThumb)
                .into(mealImageView)

            Glide.with(this.root)
                .asBitmap()
                .load(meal.mealThumb)
                .into(object: CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Palette.from(resource)
                            .generate() { palette ->
                                val color = palette?.lightMutedSwatch?.rgb ?: 0
                                mealMaterialCardView.setBackgroundColor(color)
                            }
                    }
                })

            mealTextView.text = meal.meal

            val ingredientsListOne = StringBuilder()
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientOne, meal.measurementOne))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientTwo, meal.measurementTwo))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientThree, meal.measurementThree))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientFour, meal.measurementFour))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientFive, meal.measurementFive))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientSix, meal.measurementSix))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientSeven, meal.measurementSeven))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientEight, meal.measurementEight))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientNine, meal.measurementNine))
            ingredientsListOne.append(buildIngredientsListItem(meal.ingredientTen, meal.measurementTen))
            ingredientsOneTextView.text = Html.fromHtml(ingredientsListOne.toString())

            val ingredientsListTwo = StringBuilder()
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientEleven, meal.measurementEleven))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientTwelve, meal.measurementTwelve))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientThirteen, meal.measurementThirteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientFourteen, meal.measurementFourteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientFifteen, meal.measurementFifteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientSixteen, meal.measurementSixteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientSeventeen, meal.measurementSeventeen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientEighteen, meal.measurementEighteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientNineteen, meal.measurementNineteen))
            ingredientsListTwo.append(buildIngredientsListItem(meal.ingredientTwenty, meal.measurementTwenty))
            ingredientsTwoTextView.text = Html.fromHtml(ingredientsListTwo.toString())

            instructionsTextView.text = meal.instructions

            favouriteImageButton.setOnClickListener {
                val favourite = Favourite().apply {
                    id = meal.id
                    this.meal = meal.meal
                    drinkAlternate = meal.drinkAlternate
                    category = meal.category
                    area = meal.area
                    instructions = meal.instructions
                    mealThumb = meal.mealThumb
                    tags = meal.tags
                    youTube = meal.youTube
                    ingredientOne = meal.ingredientOne
                    ingredientTwo = meal.ingredientTwo
                    ingredientThree = meal.ingredientThree
                    ingredientFour = meal.ingredientFour
                    ingredientFive = meal.ingredientFive
                    ingredientSix = meal.ingredientSix
                    ingredientSeven = meal.ingredientSeven
                    ingredientEight = meal.ingredientEight
                    ingredientNine = meal.ingredientNine
                    ingredientTen = meal.ingredientTen
                    ingredientEleven = meal.ingredientEleven
                    ingredientTwelve = meal.ingredientTwelve
                    ingredientThirteen = meal.ingredientThirteen
                    ingredientFourteen = meal.ingredientFourteen
                    ingredientFifteen = meal.ingredientFifteen
                    ingredientSixteen = meal.ingredientSixteen
                    ingredientSeventeen = meal.ingredientSeventeen
                    ingredientEighteen = meal.ingredientEighteen
                    ingredientNineteen = meal.ingredientNineteen
                    ingredientTwenty = meal.ingredientTwenty
                    measurementOne = meal.measurementOne
                    measurementTwo = meal.measurementTwo
                    measurementThree = meal.measurementThree
                    measurementFour = meal.measurementFour
                    measurementFive = meal.measurementFive
                    measurementSix = meal.measurementSix
                    measurementSeven = meal.measurementSeven
                    measurementEight = meal.measurementEight
                    measurementNine = meal.measurementNine
                    measurementTen = meal.measurementTen
                    measurementEleven = meal.measurementEleven
                    measurementTwelve = meal.measurementTwelve
                    measurementThirteen = meal.measurementThirteen
                    measurementFourteen = meal.measurementFourteen
                    measurementFifteen = meal.measurementFifteen
                    measurementSixteen = meal.measurementSixteen
                    measurementSeventeen = meal.measurementSeventeen
                    measurementEighteen = meal.measurementEighteen
                    measurementNineteen = meal.measurementNineteen
                    measurementTwenty = meal.measurementTwenty
                    source = meal.source
                    imageSource = meal.imageSource
                    creativeCommonsConfirmed = meal.creativeCommonsConfirmed
                    dateModified = meal.dateModified
                    bookId = meal.bookId
                    timestamp = meal.timestamp
                }

                if (isFavourite) {
                    favouriteViewModel.delete(favourite)
                    favouriteViewModel.findById(favourite.id)
                } else {
                    favouriteViewModel.insert(favourite)
                    favouriteViewModel.findById(favourite.id)
                }
            }

            lifecycle.addObserver(youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val youtubeLink = meal.youTube
                    var videoId = ""
                    if (youtubeLink != null && youtubeLink.isNotEmpty() && youtubeLink.contains("v=")) {
                        videoId = meal.youTube?.split("v=")?.get(1) ?: ""
                    }

                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }
}