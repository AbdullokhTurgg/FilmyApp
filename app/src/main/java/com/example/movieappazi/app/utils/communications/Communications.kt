package com.example.movieappazi.app.utils.communications

import com.example.movieappazi.app.utils.event.Event
import com.example.movieappazi.app.utils.navigation.NavigationCommand

interface NavigationCommunication : Communication<Event<NavigationCommand>> {
    class Base : Communication.Base<Event<NavigationCommand>>(), NavigationCommunication
}