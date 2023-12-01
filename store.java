//Author: Jayashree Adivarahan & Suvan Kumar
package teamproject;

public class store {
	private static String allstorypoints;
	private static String allAdditionalInfo;
	private static String allStoryNames;
	private static String allAmountTimes;
	private static String allEffortData;
	private static String allDefectData;
	private static String storyNumsPokerCard;
	
	public static void setStoryNumsPokerCard(String storyNumsPokerCardIn) {
		storyNumsPokerCard = storyNumsPokerCardIn;
	}
	
	public static String getStoryNumsPokerCard() {
		return storyNumsPokerCard;
	}
	
	public static void setAllStoryPoints(String storyPoints) {
//		this.storyPoints = this.storyPoints + "\n" + storyPoints;
		allstorypoints = storyPoints;
	}
	
	public static String getAllStoryPoints() {
//		this.storyPoints = this.storyPoints + "\n" + storyPoints;
		return allstorypoints;
	}
	
	public static void setAllAdditionalInfo(String additionalInfo) {
		allAdditionalInfo = additionalInfo;
	}
	public static String getAllAdditionalInfo() {
//		this.storyPoints = this.storyPoints + "\n" + storyPoints;
		return allAdditionalInfo;
	}
	
	
	public static void setAllStoryNames(String storyName) {
		allStoryNames = storyName;
	}
	public static String getAllStoryNames() {
//		this.storyPoints = this.storyPoints + "\n" + storyPoints;
		return allStoryNames;
	}
	
	public static void setAllAmountTimes(String amountTime) {
		allAmountTimes = amountTime;
	}
	public static String getAllAmountTimes() {
//		this.storyPoints = this.storyPoints + "\n" + storyPoints;
		return allAmountTimes;
	}
	
	public static void setAllEffortData(String effortData) {
		allEffortData = effortData;
	}
	public static String getAllEffortData() {
		return allEffortData;
	}
	
	public static void setAllDefectData(String defectData) {
		allDefectData = defectData;
	}
	public static String getAllDefectData() {
		return allDefectData;
	}
	
}