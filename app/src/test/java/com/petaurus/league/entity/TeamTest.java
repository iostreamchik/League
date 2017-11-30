package com.petaurus.league.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamTest {

  @Test
  public void testTeamIdParsing() throws Exception {
	WinInfo.Links.Team team = new WinInfo.Links.Team();
	team.setHref("http://api.football-data.org/v1/teams/431");

	assertEquals(431, team.getTeamId());
	team.setHref("dfdsf");
	assertEquals(1, team.getTeamId());
  }
}