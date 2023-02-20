package com.example.data.storage.movie.movie

class PersonDetailsState(
    val known_for_department: String,
    val also_known_as: String,
    val biography: String,
    val birthday: String,
    val deathDay: String,
    val gender: String,
    val name: String,
    val rating: String,
    val place_of_birth: String,
    val known_for: String,
) {
    companion object {
        fun getRuDetails(): PersonDetailsState {
            return PersonDetailsState(
                known_for_department = "Деятельность:",
                also_known_as = "Также знают как:",
                biography = "Биография:",
                birthday = "Дата рождения:",
                deathDay = "Дата смерти",
                gender = "Пол:",
                name = "Имя",
                rating = "Рейтинг",
                place_of_birth = "Место рождения",
                known_for = "Фильмы и сериалы:"
            )
        }

        fun getUsDetails(): PersonDetailsState {
            return PersonDetailsState(
                known_for_department = "Known for department:",
                also_known_as = "Also known as:",
                biography = "Biography:",
                birthday = "Birthday:",
                deathDay = "Death day",
                gender = "Sex:",
                name = "Name",
                rating = "Rating",
                place_of_birth = "Place of birth",
                known_for = "Known for:"
            )
        }
    }
}