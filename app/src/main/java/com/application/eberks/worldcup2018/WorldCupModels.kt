package com.application.eberks.worldcup2018

import com.application.eberks.worldcup2018.models.TeamPosition
import com.application.eberks.worldcup2018.models.WorldCupDataSingleton
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


data class WorldCupResponse(
        var stadiums: List<Stadium>,
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
) : Serializable {

    fun getTeamsInGroup(): List<Team> {
        val worldCupResponse = WorldCupDataSingleton.getInstance().worldCupResponse
        val teamIds = java.util.ArrayList<Int>()
        val teams = java.util.ArrayList<Team>()

        run {
            var i = 0
            while (i < this.matches.size) {

                if (teamIds.contains(this.matches.get(i).away_team)) {
                    i++
                } else {
                    teamIds.add(this.matches.get(i).away_team)
                }
                i++
            }
        }
        run {
            var i = 0
            while (i < this.matches.size) {

                if (teamIds.contains(this.matches.get(i).home_team)) {
                    i++
                } else {
                    teamIds.add(this.matches.get(i).home_team)
                }
                i++
            }
        }

        for (i in teamIds.indices) {

            for (j in 0 until worldCupResponse.teams.size) {

                if (worldCupResponse.teams.get(j).id == teamIds[i]) {
                    teams.add(worldCupResponse.teams.get(j))
                }

            }

        }
        return teams;
    }

    fun fillTeamPositionObject(currentTeam: Team): TeamPosition {
        var numOfMatch = 0
        var numOfWin = 0
        var numOfDraw = 0
        var numOfLose = 0



        for (i in 0 until this.matches.size) {
            if (this.matches[i].away_team == currentTeam.id) numOfMatch++
        }
        for (i in 0 until this.matches.size) {
            if (this.matches[i].home_team == currentTeam.id) numOfMatch++
        }
        for (i in 0 until this.matches.size) {
            if (this.matches[i].away_team == currentTeam.id && this.matches[i].away_result!! > this.matches.get(i).home_result!!) {
                numOfWin++
            } else if (this.matches.get(i).away_team == currentTeam.id && this.matches[i].away_result!! < this.matches.get(i).home_result!!) {
                numOfLose++
            } else if (this.matches.get(i).away_team == currentTeam.id && this.matches.get(i).away_result == this.matches.get(i).home_result) {
                numOfDraw++
            }
        }
        for (i in 0 until this.matches.size) {
            if (this.matches.get(i).home_team == currentTeam.id && this.matches.get(i).home_result!! > this.matches.get(i).away_result!!) {
                numOfWin++
            } else if (this.matches.get(i).home_team == currentTeam.id && this.matches.get(i).home_result!! < this.matches.get(i).away_result!!) {
                numOfLose++
            } else if (this.matches.get(i).home_team == currentTeam.id && this.matches.get(i).home_result == this.matches.get(i).away_result) {
                numOfDraw++
            }
        }
        val totalPoint = numOfWin * 3 + numOfDraw
        var totalGS = 0
        var totalGC = 0

        for (i in 0 until this.matches.size) {
            if (this.matches.get(i).away_team == currentTeam.id) {
                totalGS = totalGS + this.matches.get(i).away_result!!
                totalGC = totalGC + this.matches.get(i).home_result!!
            }

        }
        for (i in 0 until this.matches.size) {
            if (this.matches.get(i).home_team == currentTeam.id) {
                totalGS = totalGS + this.matches.get(i).home_result!!
                totalGC = totalGC + this.matches.get(i).away_result!!
            }

        }

        val totalAverage = totalGS - totalGC

        return TeamPosition(currentTeam, numOfMatch, numOfWin, numOfDraw, numOfLose, totalGS, totalGC, totalAverage, totalPoint)

    }

}


data class Match(var name: Int,
                 var type: String,
                 var home_team: Int,
                 var away_team: Int,
                 var home_result: Int?,
                 var away_result: Int?,
                 var home_penalty: Int,
                 var away_penalty: Int,
                 var date: Date,
                 var stadium: Int,
        //   var channels: Channel
                 var finished: Boolean,
                 var matchday: Int
) : Serializable {
    fun getMatchInformations(listMatch: List<Match>): FullMatchObject {
        val teamIds = java.util.ArrayList<Int>()
        val teams = java.util.ArrayList<Team>()
        val wcrData = WorldCupDataSingleton.getInstance().worldCupResponse
        var homeTeamName = ""
        var awayTeamName = ""
        var homeTeamScore = 0
        var awayTeamScore = 0
        var matchNumber = 0


        run {
            var i = 0
            while (i < listMatch.size) {

                if (teamIds.contains(listMatch.get(i).away_team)) {
                    i++
                } else {
                    teamIds.add(listMatch.get(i).away_team)
                }
                i++
            }
        }
        run {
            var i = 0
            while (i < listMatch.size) {

                if (teamIds.contains(listMatch.get(i).home_team)) {
                    i++
                } else {
                    teamIds.add(listMatch.get(i).home_team)
                }
                i++
            }
        }

        for (i in teamIds.indices) {

            for (j in 0 until wcrData.teams.size) {

                if (wcrData.teams.get(j).id == teamIds[i]) {
                    teams.add(wcrData.teams.get(j))
                }

            }

        }


        //------------------------- setOperation ----------------------------------------------------

        for (i in teams.indices) {
            if (teams[i].id == this.home_team)

                homeTeamName = teams[i].name
        }
        for (i in teams.indices) {
            if (teams[i].id == this.away_team)
                awayTeamName = teams[i].name
        }
        for (i in teams.indices) {
            if (teams[i].id == this.home_team)
                if (this.home_result == null) {
                    homeTeamScore = 123456789
                } else {
                    homeTeamScore = this.home_result!!
                }

        }
        for (i in teams.indices) {
            if (teams[i].id == this.away_team)
                if (this.away_result == null) {
                    awayTeamScore = 123456789
                } else {
                    awayTeamScore = this.away_result!!
                }

        }

        matchNumber = this.matchday;

        return FullMatchObject(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore, matchNumber)
    }
}

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

data class Stadium(var id: Int,
                   var name: String,
                   var city: String,
                   var lat: Double,
                   var lng: Double,
                   var image: String

) : Serializable

data class FullMatchObject(var homeTeamName: String,
                           var awayTeamName: String,
                           var homeTeamScore: Int?,
                           var awayTeamScore: Int?,
                           var matchNumber: Int

) : Serializable


