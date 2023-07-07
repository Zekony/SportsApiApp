package com.example.sportsapp.common

object LocalDataProvider {

    fun getCountriesList(): List<CountryItem> {
        return listOf(
            CountryItem(
                name = "England",
                id = "3",
                image = "https://www.megaflag.ru/sites/default/files/images/shop/products/flag_england1.jpg"
            ),
            CountryItem(
                name = "Germany",
                id = "4",
                image = "https://www.megaflag.ru/sites/default/files/images/directory_names/flag_germanija_enl.jpg"
            ),
            CountryItem(
                name = "Spain",
                id = "5",
                image = "https://www.megaflag.ru/sites/default/files/images/shop/products/flag_ispanija_new.jpg"
            ),
            CountryItem(
                name = "Italy",
                id = "6",
                image = "https://www.megaflag.ru/sites/default/files/images/directory_names/flag_italija_enl.jpg"
            ),
            CountryItem(
                name = "France",
                id = "7",
                image = "https://www.megaflag.ru/sites/default/files/styles/galleryformatter_slide/public/images/shop/products/flag_frantsija_new.jpg?itok=G24k-b_5"
            ),
            CountryItem(
                name = "Portugal",
                id = "96",
                image = "https://www.megaflag.ru/sites/default/files/images/shop/products/flag_portugalija_new.jpg"
            ),
            CountryItem(
                name = "Argentina",
                id = "12",
                image = "https://www.megaflag.ru/sites/default/files/images/directory_names/flag_argentina_enl.jpg"
            ),
            CountryItem(
                name = "Austria",
                id = "15",
                image = "https://www.megaflag.ru/sites/default/files/images/shop/products/flag_avstriya_enl.jpg"
            ),
            CountryItem(
                name = "Croatia",
                id = "34",
                image = "https://www.megaflag.ru/sites/default/files/images/shop/products/flag_horvatija_new.jpg"
            ),
            CountryItem(
                name = "Czech Republic",
                id = "36",
                image = "https://www.megaflag.ru/sites/default/files/images/directory_names/flag_chehija_enl.jpg"
            )
        )
    }
}

data class CountryItem(
    val name: String,
    val id: String,
    val image: String
)