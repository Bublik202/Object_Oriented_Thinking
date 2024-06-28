package com.epam.rd.autocode.factory.plot;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new PlotFactory() {
			
			@Override
			public Plot plot() {
				String heroName = hero.name();
				String belovedName = beloved.name();
				String villainName = villain.name();
				
				String result = String.format(
						"%s is great. %s and %s love each other. %s interferes with their happiness but loses in the end.",
						heroName, heroName, belovedName, villainName);
				
				return new Plot() {
					@Override
                    public String toString() {
                        return result;
                    }
				};
			}
		};
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new PlotFactory() {
			
			@Override
			public Plot plot() {
				String heroName = hero.name();
				String epicName = epicCrisis.name();
				String funnyName = funnyFriend.name();
				
				String result = String.format(
						"%s feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - %s. "
						+ "%s stands up against it, but with no success at first.But putting self together and help of friends, "
						+ "including spectacular funny "
						+ "%s restore the spirit and %s overcomes the crisis and gains gratitude and respect", 
						heroName, epicName, heroName, funnyName, heroName);
				
				return new Plot() {
					@Override
                    public String toString() {
                        return result;
                    }
				};
			}
		};
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new PlotFactory() {
			
			@Override
			public Plot plot() {
				StringBuilder result = new StringBuilder();
				result.append(epicCrisis.name()).append(" threatens the world. But brave ");

	            for (int i = 0; i < heroes.length; i++) {
	            	result.append(heroes[i].name());
	                if (i < heroes.length - 1) {
	                	result.append(", brave ");
	                }
	            }	    
	            
	            result.append(" on guard. So, no way that intrigues of ")
	            .append(villain.name())
	            .append(" overcome the willpower of inflexible heroes");
				
				return new Plot() {
					@Override
					public String toString() {
						return result.toString();
					}
					
				};
			}
		};
    }  
}
