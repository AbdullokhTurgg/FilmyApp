package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.movieappazi.uiModels.person.PersonDetailsUi

class MapFromLisOfPersonDetDomainToUi(
    private val mapFromPersonDetDomainToUi: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
) : BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsUi>> {
    override fun map(from: List<PersonDetailsDomain>): List<PersonDetailsUi> = from.map {
        mapFromPersonDetDomainToUi.map(it)
    }
}