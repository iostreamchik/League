package com.petaurus.league.entity;

import org.junit.Test;

public class WinInfoTest {
  @Test
  public void testUrlFormatting() throws Exception {
	WinInfo winInfo = new WinInfo();
	winInfo.setCrestURI("http://upload.wikimedia.org/wikipedia/de/6/6a/FC_Twente_Enschede_(ab_2006).svg");
	String res = winInfo.getCrestURIFormatted();
	System.out.println(res);
  }
}