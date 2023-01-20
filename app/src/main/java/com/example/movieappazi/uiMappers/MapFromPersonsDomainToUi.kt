package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.movieappazi.uiModels.person.PersonUi
import com.example.movieappazi.uiModels.person.PersonsUi

class MapFromPersonsDomainToUi(
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