package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.models.person.PersonsUi
import javax.inject.Inject

class MapFromPersonsDomainToUi @Inject constructor(
    private val mapFromListPersonDomainToUi: BaseMapper<List<PersonDomain>, List<PersonUi>>,
) : BaseMapper<PersonsDomain, PersonsUi> {
    override fun map(from: PersonsDomain) = from.run {
        PersonsUi(
            page = page,
            persons = mapFromListPersonDomainToUi.map(persons),
            total_results = total_results,
            total_pages = total_pages,
        )
    }
}