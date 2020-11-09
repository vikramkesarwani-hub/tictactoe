package com.game.java.tictactoe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.game.java.tictactoe.constants.Constants;


@SpringBootTest
@AutoConfigureMockMvc
class BloxGameApplicationTests {
	
    @Autowired
    private MockMvc mockMvc;

	@Test
	 public void testGame() throws Exception {
	      this.mockMvc.perform(MockMvcRequestBuilders.get("/tictactoe"))
	                  .andExpect(MockMvcResultMatchers.status().isOk())
	                  .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.GAME_STATE))
	                  .andExpect(MockMvcResultMatchers.view().name(Constants.VIEW_GAME))
	                  .andDo(MockMvcResultHandlers.print());
	  }
	
	@Test
	 public void testreset() throws Exception {
	      this.mockMvc.perform(MockMvcRequestBuilders.get("/tictactoe/reset"))
	                  .andExpect(MockMvcResultMatchers.status().isOk())
	                  .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.GAME_STATE))
	                  .andExpect(MockMvcResultMatchers.view().name(Constants.VIEW_GAME))
	                  .andDo(MockMvcResultHandlers.print());
	  }
	
	
	@Test
	 public void testgameNew() throws Exception {
	      this.mockMvc.perform(MockMvcRequestBuilders.get("/tictactoe/new"))
	                  .andExpect(MockMvcResultMatchers.status().isOk())
	                  .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.GAME_STATE))
	                  .andExpect(MockMvcResultMatchers.view().name(Constants.VIEW_GAME))
	                  .andDo(MockMvcResultHandlers.print());
	  }
	

	/*
	@Test
	 public void testPlayerMove() throws Exception {
	      this.mockMvc.perform(MockMvcRequestBuilders.get("/tictactoe/move").param("row","0"))
	                  .andExpect(MockMvcResultMatchers.status().isOk())
	                  .andExpect(MockMvcResultMatchers.model().attributeExists(Constants.GAME_STATE))
	                  .andExpect(MockMvcResultMatchers.view().name(Constants.VIEW_GAME))
	                  .andDo(MockMvcResultHandlers.print());
	  }*/
}
