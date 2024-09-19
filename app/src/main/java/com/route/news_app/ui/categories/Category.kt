package com.route.news_app.ui.categories

import com.route.news_app.R

data class Category(
    val id: String,
    val name: Int,
    val imageId: Int,
    val backgroundColorId: Int
) {
    companion object {
        fun getCategoriesList(): List<Category> {
            return listOf(

                Category(
                    id = "sports",
                    name = R.string.sports,
                    imageId = R.drawable.sport,
                    backgroundColorId = R.color.red
                ),
                Category(
                    id = "entertainment",
                    name = R.string.entertainment,
                    imageId = R.drawable.politics,
                    backgroundColorId = R.color.blue
                ),
                Category(
                    id = "health",
                    name = R.string.health,
                    imageId = R.drawable.health,
                    backgroundColorId = R.color.pink
                ),
                Category(
                    id = "business",
                    name = R.string.business,
                    imageId = R.drawable.business,
                    backgroundColorId = R.color.orange
                ),
                Category(
                    id = "technology",
                    name = R.string.tech,
                    imageId = R.drawable.enviroment,
                    backgroundColorId = R.color.babyBlue
                ),
                Category(
                    id = "science",
                    name = R.string.science,
                    imageId = R.drawable.science,
                    backgroundColorId = R.color.yellow
                ),
            )
        }
    }
}

