package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
    	return  (deck, players) -> {
    		Map<String, List<Card>> map = new HashMap<>();   		
    		
//    		Map<String, List<Card>> map = IntStream.rangeClosed(1, players)
//    				.boxed()//Stream<Integer>
//    				.collect(Collectors.toMap(
//    						i -> "Player " + i,          раздаю карты игрокам (Вариант 2);   
//    						t -> new ArrayList<>()          
//    						));  	
//    		
//    		IntStream.rangeClosed(1, 2).forEach(cardOne -> 
//    			IntStream.rangeClosed(1, players).forEach(numberPlayer -> {
//    						map.get("Player " + numberPlayer).add(deck.dealCard());
//    					}));
    		
    		dealCardsMethod(deck, players, map, 2); // раздаю карты игрокам    
    		
    		List<Card> restCards = deck.restCards();
    		List<Card> communityCards = restCards.stream()    				
    				.limit(5)
    	    		.collect(Collectors.toList());
    		map.put("Community", communityCards);  // Доп. Стопка карт		  	
	
    		map.put("Remaining", restCards.stream()
    				.skip(communityCards.size())
    				.collect(Collectors.toList())); // Оставшиеся карты
    		return map;
    	};
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
    	return  (deck, players) -> {
    		Map<String, List<Card>> map = new HashMap<>();   		
    		
    		dealCardsMethod(deck, players, map, 5); // раздаю карты игрокам  
    		List<Card> restCards = deck.restCards();
    		map.put("Remaining", restCards); // Оставшиеся карты
    		
    		return map;
    	};
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
    	return  (deck, players) -> {
    		Map<String, List<Card>> map = new HashMap<>();    		
    		dealCardsMethod(deck, players, map, 13); // раздаю карты игрокам      		
    		return map;
    	};
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
    	return  (deck, players) -> {
    		Map<String, List<Card>> map = new HashMap<>();   		  		
    		dealCardsMethod(deck, players, map, 6); // раздаю карты игрокам    
    		
    		List<Card> restCards = deck.restCards();    		
    		map.put("Trump card", restCards.stream().limit(1).collect(Collectors.toList()));   		   		
    		map.put("Remaining", restCards.stream().skip(1).collect(Collectors.toList())); // Оставшиеся карты
    		
    		return map;
    	};
    }

	private static void dealCardsMethod(Deck deck, int players, Map<String, List<Card>> map, int val) {
		for (int j = 0; j < val; j++) {
			for (int i = 1; i < players+1; i++) {  
				List<Card> card = map.computeIfAbsent("Player " + i, t -> new ArrayList<>());
				card.add(deck.dealCard());
			}
		}
	}
	

}
