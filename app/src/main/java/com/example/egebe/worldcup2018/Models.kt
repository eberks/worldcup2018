package com.example.egebe.worldcup2018

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


data class WorldCupResponse(
        // var stadiums: List<Stadium>,
        // var tvchannels: List<TvChannel>,
        var teams: List<Team>,
        var groups: GroupResponse,
        var knockout: KnockoutResponse
) : Serializable


data class GroupResponse(var a: Group,
                         var b: Group,
                         var c: Group,
                         var d: Group,
                         var e: Group,
                         var f: Group,
                         var g: Group,
                         var h: Group
) : Serializable

data class Group(var name: String,
                 var winner: Int,
                 var runnerup: Int,
                 var matches: List<Match>
) : Serializable


data class Match(var name: Int,
                 var type: String,
                 var home_team: Int,
                 var away_team: Int,
                 var home_result: Int,
                 var away_result: Int,
                 var date: Date,
                 var stadium: Int,
        //   var channels: Channel
                 var finished: Boolean,
                 var matchday: Int
) : Serializable

data class Team(var id: Int,
                var name: String,
                var fifaCode: String,
                var iso2: String,
                var flag: String,
                var emoji: String,
                var emojiString: String

) : Serializable

data class Knockout(var name: String,
                    var matches: List<Match>

) : Serializable

data class KnockoutResponse(var round_16: Knockout,
                            var round_8: Knockout,
                            var round_4: Knockout,
                            var round_2_loser: Knockout,
                            var round_2: Knockout) : Serializable {
    fun getAsList(): List<Knockout> {
        val listKnockout = ArrayList<Knockout>()
        listKnockout.addAll(Arrays.asList(round_16, round_8, round_4, round_2_loser, round_2))
        return listKnockout
    }
}



