package com.levnovikov.postbus.deeplinks

import com.levnovikov.postbus.root.home.HomeRouter
import com.levnovikov.postbus.root.home.prebooking.PrebookingNodeHolder
import com.levnovikov.postbus.root.home.prebooking.PrebookingRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraNodeHolder
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.BookingExtraRouter
import com.levnovikov.postbus.root.home.prebooking.booking_extra_widget.options.OptionsNodeHolder
import com.levnovikov.postbus.root.home.prebooking.car_type_selector.CarTypeSelectorNodeHolder
import com.levnovikov.system_base.node_state.ActivityState
import com.levnovikov.system_base.node_state.NodeState
import java.net.URI
import javax.inject.Inject

/**
 * Created by lev.novikov
 * Date: 1/3/18.
 */

interface DeeplinkParser {
    fun parseDeeplink(deeplink: URI): ActivityState?
}

class DeeplinkParserImpl @Inject constructor() : DeeplinkParser {

    private val deeplinkCases = setOf(PromoDeeplink())

    override fun parseDeeplink(deeplink: URI): ActivityState? =
            deeplinkCases.firstOrNull { it.validateLink(deeplink) }?.parseState()
}

interface DeeplinkCase {
    fun validateLink(deeplink: URI): Boolean
    fun parseState(): ActivityState
}

class PromoDeeplink : DeeplinkCase {
    override fun validateLink(deeplink: URI) =
            deeplink.path == "/prebooking/extra/options"

    override fun parseState(): ActivityState =
        ActivityState(stateMap = mapOf(
            className<HomeRouter>() to NodeState(null, setOf(className<PrebookingNodeHolder>())),
            className<PrebookingRouter>() to NodeState(null, setOf(
                    className<BookingExtraNodeHolder>(), className<CarTypeSelectorNodeHolder>())),
            className<BookingExtraRouter>() to NodeState(null, setOf(
                    className<OptionsNodeHolder>()))))


    inline fun <reified T> className() = T::class.java.simpleName
}