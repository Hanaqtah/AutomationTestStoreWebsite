package SecondWebsite;

import java.util.Random;

public class Data {
	
	// to obtain random names from
		String [] FNames = {"Hala", "Ammar", "Sara", "Ali", "Esraa", "Mohammed", "Lama"}; 
		String [] LNames = {"Ahmad", "Omar", "Qais", "Ghaith", "Eyad", "Mousa", "Jamal"};
		String [] ZPCode = {"1196", "1654", "0098", "2176", "1739", "1776", "9898"};
		String [] phone = {"96279770023", "654787650023", "93245612349"};
		
		
	// email type
		String MailDomain = "@gmail.com";
	
	// rand num to add to name to avoid duplicate names for signup
		Random rand = new Random();
		int RandomNum = rand.nextInt(19870);
	
	// to obtain random info based on their index in random access
		int RFN = rand.nextInt(FNames.length);
		int RLN = rand.nextInt(LNames.length);
		int ZP = rand.nextInt(ZPCode.length);
		int ph = rand.nextInt(phone.length);
	// starts with 1 bc 0 is please select and 9 bc we dont have to test all the states impossible
		//int RandState = rand.nextInt(1,10);
		//int RandCountry = rand.nextInt(0,5);
		
		
	// actual first & last names
		String fname = FNames[RFN];
		String Lname = LNames[RLN];
	
	// Actual Full Login Name 
		String FullLoginName = fname + Lname + RandomNum;
		
	// full email
		String FullMail = FullLoginName + MailDomain;

	// password
		String pass = "P@$$WORD";
	// password confirmation
		String ConfPass = "P@$$WORD";
	
	// zip code
		String ZIPCode= ZPCode[ZP];
		
	// Address
		String  Addrs= "Amman - Jordan";
		
	// Phone
		String Phone = phone[ph];
		
	// Fax
		String fax = "0098786";
		
	// random index to select option for select mune
		int theSelectStateIndex = rand.nextInt(1,10);
		int theSelectCountryIndex = rand.nextInt(0,11);
		
	// expected texts for logout & signup
		String ExpectedSignUpMsg = "YOUR ACCOUNT HAS BEEN CREATED!";
		String ExpectedLogOutMsg = "You have been logged off your account. It is now safe to leave the computer.";
		String ExpectedLogOutMsg2 = "ACCOUNT LOGOUT";
		String WelcomeMessage =  "Welcome back "+fname;
	

}
