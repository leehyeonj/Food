package HealthSchedule.controller;

import java.util.ArrayList;

import HealthSchedule.model.Food;
import HealthSchedule.model.Foodlist;

public interface FoodTableListener {
	public void onClickListener(ArrayList<Foodlist> foodlistlist);
}
