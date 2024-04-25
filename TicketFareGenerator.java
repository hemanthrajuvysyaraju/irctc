package com.pennant.irctc.mvc;

public class TicketFareGenerator {
	private static Double fare=0.0;
	public static Double getfare(PassengerListModel psngrs,TicketModel ticket)
	{
		Double basefare=FareGeneratorDAL.getbasefare(ticket.getClass_Index(), ticket.getFrom(), ticket.getTo());
		for(PassengerModel psngr:psngrs)
		{
			if((psngr.getAge()>=60&&psngr.getGender().equals("male"))||((psngr.getAge()>=58&&psngr.getGender().equals("female"))))
			{
				fare+=basefare*0.75;
			}
			else
			{
				fare+=basefare;
			}
		}
		return fare;
	}
}
