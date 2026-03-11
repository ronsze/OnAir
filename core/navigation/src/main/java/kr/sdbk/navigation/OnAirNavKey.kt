package kr.sdbk.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface OnAirNavKey: NavKey

typealias OnAirNavBackStack = NavBackStack<OnAirNavKey>

typealias OnAirEntryProviderScope = EntryProviderScope<OnAirNavKey>