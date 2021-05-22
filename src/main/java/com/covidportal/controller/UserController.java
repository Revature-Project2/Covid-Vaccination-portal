package com.covidportal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covidportal.model.Appointment;
import com.covidportal.model.AppointmentStatus;
import com.covidportal.model.Clinic;
import com.covidportal.model.Timeslot;
import com.covidportal.model.User;
import com.covidportal.model.VaccineType;
import com.covidportal.service.AppointmentService;
import com.covidportal.service.AppointmentStatusService;
import com.covidportal.service.ClinicService;
import com.covidportal.service.TimeslotService;
import com.covidportal.service.UserService;
import com.covidportal.service.VaccineTypeService;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

	

	public UserController() {

	}
        UserService userServ;
	TimeslotService tserv;
	ClinicService cServ;
	AppointmentStatusService asServ;
	AppointmentService appServ;
	VaccineTypeService vtServ;
	

	@Autowired
	public UserController(UserService userServ, TimeslotService timeServ,ClinicService cServ,AppointmentService apServ,	AppointmentStatusService asServ
			,VaccineTypeService vsServ) {
		super();
		this.userServ = userServ;
		this.tserv = timeServ;
		this.cServ= cServ;
		this.appServ= apServ;
		this.asServ = asServ;
		this.vtServ = vsServ;

	}
	

	// This is tested with postmap and working


//	@PostMapping("/saveuserandappointment")
//	public ResponseEntity<Object> insertUserandappointment(@RequestBody LinkedHashMap<String, String> formdata)
//			throws ParseException, java.text.ParseException {
//
//		System.out.println("request comes inside this");
//		Set set = formdata.entrySet();
//		User user = new User();
//		Appointment app = new Appointment();
//		Timeslot td = new Timeslot();
//		VaccineType vt= new VaccineType();
//		// Displaying elements of LinkedHashMap
//		Iterator iterator = set.iterator();
//		while (iterator.hasNext()) {
//			Map.Entry<String, String> me = (Map.Entry) iterator.next();
//			if (me.getKey() == "firstName")
//				user.setFirstName(me.getValue());
//			if (me.getKey() == "lastName")
//				user.setLastName(me.getValue());
//			if (me.getKey() == "email")
//				user.setEmail(me.getValue());
//			if (me.getKey() == "phoneNumber")
//				user.setPhoneNumber(me.getValue());
//			if (me.getKey() == "address")
//				user.setAddress(me.getValue());
//			if (me.getKey() == "healthCardNumber")
//				user.setHealthCardNumber(me.getValue());
//			if (me.getKey() == "dateOfBirth") {
//
////            	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
////            		LocalDateTime localDateTime = LocalDateTime.parse(me.getValue().toString(), formatter);
////            		Date date = (Date) Date.from(LocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
////            		System.out.println(date);
////            		 LocalDate localDate = localDateTime.toLocalDate();
////            	     user.setDateOfBirth(localDate);
////            	     System.out.println(localDate);
//			}
//
//			// getting data for appointment table
//			if (me.getKey() == "clinicId") {
//				System.out.println(me.getValue()+"88888888888888888888888888888888888888888888");
//                  List<Clinic> clinicList =   cServ.getByClinicId(Integer.parseInt(me.getValue()));
//                  app.setClinic((clinicList.size()!=0)?clinicList.get(0):null);
//			}		
//			
//			
//			if (me.getKey() == "vaccineId") {
//                 
//				System.out.println("=================="+me.getValue());
//			List<VaccineType>	vList =vtServ.findvaccinebyid(Integer.parseInt(me.getValue())+1);
//			System.out.println("=================="+vList.get(0).getVaccineType());
//			app.setVaccineType(vList.get(0));
//			
//					
//			}
//			
//			if (me.getKey() == "bookdatectrl") {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//				Date date =  sdf.parse(me.getValue());
//				System.out.println(date);
//				System.out.println(me.getValue());
//				java.sql.Date sd = new java.sql.Date(date.getTime());
//				    System.out.println("fdgf"+sd.getTime());
//				    td = new Timeslot(sd.getTime(), null, null);
//				    
//				app.setTimeslot(td);
//
//		}
//	
//	}
//		
//		//adding short to appointment
//		app.setShot_number(1);
//		//setting appointment status 
//		List<AppointmentStatus> appointmentstatus = asServ.getappointmentvyid(1);
//		app.setAppointmentStatus(appointmentstatus.get(0));
//		System.out.println(user);
//		userServ.insertUser(user);
//		//User user1 = userServ.findByFirstname(user.getFirstName());
//		app.setUser(user);
//		appServ.bookAppointment(app);
//		System.out.println(app);
//		return new ResponseEntity<Object>(formdata, HttpStatus.CREATED);
//}
//
//
//
//	@PostMapping("/{id}")
//    public ResponseEntity<String> deleteBookings(@PathVariable("id") int id) {
//
//        List<Appointment> aList = new ArrayList<>();
//
//        aList = appServ.findByUserId(id);
//
//        if(aList.size() > 0) {
//            for(Appointment list : aList) {
//                appServ.deleteByAppointmentId(list.getAppointmentId());
//            }
//        }
//
//        userServ.deleteByUserId(id);
//
//        return new ResponseEntity<String>("Both appointments cancelled successfully!", HttpStatus.GONE);
//
//    }







	@GetMapping("/initial")
	public ResponseEntity<String> inserInitialValues()

	{

		User user1 = new User();
		String date1 = "1994-10-17";
		user1.setFirstName("Divya");
		user1.setLastName("Reddy");
		user1.setEmail("Divyareddy@gmail.com");
		user1.setDateOfBirth(java.sql.Date.valueOf(date1));
		user1.setConfirmationNumber("Confirm2s2");
		user1.setHealthCardNumber("heah144");
		user1.setAddress("42 Blackdome Cres,Kanata,On,K2T1A9");
		user1.setPhoneNumber("3154502989");

		String phone2 = "6474502989";
		// input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3")
		User user2 = new User();
		String date2 = "1992-01-17";
		user2.setFirstName("Mona");
		user2.setLastName("Borisagar");
		user2.setEmail("Mona@gmail.com");
		user2.setDateOfBirth(java.sql.Date.valueOf(date2));
		user2.setConfirmationNumber("Conf56s2s2");
		user2.setHealthCardNumber("health147");
		user2.setAddress("42 Blackdome Cres,Toronto,On,K2T1A9");
		user2.setPhoneNumber(phone2);

		// List<User> uList=null;
		/*
		 * List<User> uList=new ArrayList<User>(Arrays.asList(new User("Divya", "Reddy",
		 * new Calendar("1993-01-17"),3154502989,"divya@gmail.com",
		 * "balckdome kanata k2t1A9", "HealthCard00", "confirmation46s"), new
		 * User("Divya", "Reddy", "1993-01-17",3154502989,"divya@gmail.com",
		 * "balckdome kanata k2t1A9", "HealthCard00", "confirmation46s")));
		 */

		ArrayList<User> uList = new ArrayList<User>();

		uList.add(user1);
		uList.add(user2);
		for (User u : uList) {
			userServ.insertUser(u);
		}

		return new ResponseEntity<String>("User inserted", HttpStatus.CREATED);
	}

//This is working.Tested with postmap	
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userServ.findAll(), HttpStatus.CREATED);
	}

	// This is working.Tested with postmap

	@PostMapping()
	public ResponseEntity<Object> insertUser(@RequestBody User user) {
		System.out.println(user);
		userServ.insertUser(user);
		return new ResponseEntity<Object>(userServ.getUserByHealthCardNumber(user.getHealthCardNumber()),
				HttpStatus.CREATED);

	}

	// This is working.Tested with postmap

	@GetMapping("/findUserByHealtCardNumber/{healthCardNumber}") //chnage to post
	public ResponseEntity<User> getUser(@PathVariable("healthCardNumber") String cardNumber) {

		User user = userServ.getUserByHealthCardNumber(cardNumber);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	// This is working.Tested with postmap
//post method
	@PostMapping("/findUserByConfimationNumber/{confirmationNumber}")
	public ResponseEntity<User> getUserByConfirmationNumber(@PathVariable("confirmationNumber") String confirmationNumber) {

		User user = userServ.getUserByConfirmationNumber(confirmationNumber);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}

	}

	//
	// This is working.Tested with postmap

	@DeleteMapping("/{healthCardNumber}")
	public ResponseEntity<String> deleteUser(@PathVariable("healthCardNumber") String cardNumber) {

		User user = userServ.getUserByHealthCardNumber(cardNumber);
		System.out.println("In dlete method" + user);
		userServ.deleteUser(user);
		return new ResponseEntity<String>("User Deleted", HttpStatus.GONE);

	}
	
	
	
	@GetMapping("/email/{email}") 
	public ResponseEntity<String> sendEmail(@PathVariable("email") String email) {

		
		boolean isSend = sendingmail(email,generateConfirmationNumber());
		if (isSend) {
			return new ResponseEntity<String>("email sent", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Email Not sent", HttpStatus.I_AM_A_TEAPOT);
		}

	}
	
	
	@PostMapping("/managebooking")
	public ResponseEntity<Object> manageBooking(@RequestBody LinkedHashMap<String, String> userInput) {
		
		Set set = userInput.entrySet();
		String confirmationNumber="";
		String email ="";

		 // Displaying elements of LinkedHashMap
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry me = (Map.Entry)iterator.next();
           
           if(me.getKey() == "confirmationNumberCtrl")
        	   confirmationNumber = (String) me.getValue();
           if(me.getKey() == "emailCtrl")
        	   email = (String) me.getValue();
           
        	
        }
        
       System.out.println(confirmationNumber+" " + email);
        
        User user = userServ.getUserByConfirmationNumberAndEmail(confirmationNumber, email);
       System.out.println(user);
        
		
    	if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		}
       
      // return new ResponseEntity<String>("Testing", HttpStatus.OK);
       
		 

		
       //return new ResponseEntity<Object>("Email Not sent", HttpStatus.I_AM_A_TEAPOT);
	}
	
	
	
	
	
	
	


	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Method to Generate Confirmation number
	
   public  String generateConfirmationNumber() {

		
		int total = 1;
		String randomConfNumber = "";

		
		int length = 10;

	

		
		for (int i = 0; i < total; i++) {
		
			// Randomly generate a character for the Confirmation number length number of times
			for (int j = 0; j < length; j++) {
				// Add a random lowercase or UPPERCASE character to our Confirmation number String
				randomConfNumber += randomCharacter();
			}
		
		}

		
		return randomConfNumber;

	}

	// Create a function that randomly generates a letter (lowercase or uppercase)
	// or number (0-9) using ASCII
	// '0' - '9' => 48-57 in ASCII
	// 'A' - 'Z' => 65-90 in ASCII
	// 'a' - 'z' => 97-122 in ASCII
	public static char randomCharacter() {
		// We multiply Math.random() by 62 because there are 26 lowercase letters, 26
		// uppercase letters, and 10 numbers, and 26 + 26 + 10 = 62
		// This random number has values between 0 (inclusive) and 62 (exclusive)
		int rand = (int) (Math.random() * 62);

		// 0-61 inclusive = all possible values of rand
		// 0-9 inclusive = 10 possible numbers/digits
		// 10-35 inclusive = 26 possible uppercase letters
		// 36-61 inclusive = 26 possible lowercase letters
		// If rand is between 0 (inclusive) and 9 (inclusive), we can say it's a
		// number/digit
		// If rand is between 10 (inclusive) and 35 (inclusive), we can say it's an
		// uppercase letter
		// If rand is between 36 (inclusive) and 61 (inclusive), we can say it's a
		// lowercase letter
		if (rand <= 9) {
			// Number (48-57 in ASCII)
			// To convert from 0-9 to 48-57, we can add 48 to rand because 48-0 = 48
			int number = rand + 48;
			// This way, rand = 0 => number = 48 => '0'
			// Remember to cast our int value to a char!
			return (char) (number);
		} else if (rand <= 35) {
			// Uppercase letter (65-90 in ASCII)
			// To convert from 10-35 to 65-90, we can add 55 to rand because 65-10 = 55
			int uppercase = rand + 55;
			// This way, rand = 10 => uppercase = 65 => 'A'
			// Remember to cast our int value to a char!
			return (char) (uppercase);
		} else {
			// Lowercase letter (97-122 in ASCII)
			// To convert from 36-61 to 97-122, we can add 61 to rand because 97-36 = 61
			int lowercase = rand + 61;
			// This way, rand = 36 => lowercase = 97 => 'a'
			// Remember to cast our int value to a char!
			return (char) (lowercase);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Send Email Method
	
	
	public boolean sendingmail(String toemail,String confirmationNumber)
	{
		//in gmail account setting please change allow less secure app to true 
		  String host="smtp.gmail.com";  
		  final String user="ers.register@gmail.com";
		  final String password="";//change accordingly  
		    
		 
		  
		   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.ssl.trust", host);

		   props.put("mail.smtp.auth", "true");  
		   props.put("mail.debug", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.port", "587");
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setContent(message, "text/html");
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(toemail));  
		     message.setSubject("Thank you for your booking - Confirmation Number "+confirmationNumber);  
		   //  message.setText("Hello " +username+",\n\nUsername: "+username+"\n Password:"+temppassword+"\n Website link: http://localhost:8081/ReimursementSystem/loginpage.jsp \n\n\n Regards, \n Mona Borisagar \n Financial Manager \n Reimbursement System");  
		     String messa= "<i>Greetings!</i><br>";
		     messa += "<b>Wish you a nice day!</b><br>";
		     messa += "<font color=red>Duke</font>";
		     
		     String goodmessage = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
		     		+ "<head>\r\n"
		     		+ "	<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\r\n"
		     		+ "  	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0;\">\r\n"
		     		+ " 	<meta name=\"format-detection\" content=\"telephone=no\"/>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- Responsive Mobile-First Email Template by Konstantin Savchenko, 2015.\r\n"
		     		+ "	https://github.com/konsav/email-templates/  -->\r\n"
		     		+ "\r\n"
		     		+ "	<style>\r\n"
		     		+ "/* Reset styles */ \r\n"
		     		+ "body { margin: 0; padding: 0; min-width: 100%; width: 100% !important; height: 100% !important;}\r\n"
		     		+ "body, table, td, div, p, a { -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%; }\r\n"
		     		+ "table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-collapse: collapse !important; border-spacing: 0; }\r\n"
		     		+ "img { border: 0; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; }\r\n"
		     		+ "#outlook a { padding: 0; }\r\n"
		     		+ ".ReadMsgBody { width: 100%; } .ExternalClass { width: 100%; }\r\n"
		     		+ ".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; }\r\n"
		     		+ "\r\n"
		     		+ "/* Rounded corners for advanced mail clients only */ \r\n"
		     		+ "@media all and (min-width: 560px) {\r\n"
		     		+ "	.container { border-radius: 8px; -webkit-border-radius: 8px; -moz-border-radius: 8px; -khtml-border-radius: 8px; }\r\n"
		     		+ "}\r\n"
		     		+ "\r\n"
		     		+ "/* Set color for auto links (addresses, dates, etc.) */ \r\n"
		     		+ "a, a:hover {\r\n"
		     		+ "	color: #FFFFFF;\r\n"
		     		+ "}\r\n"
		     		+ ".footer a, .footer a:hover {\r\n"
		     		+ "	color: #828999;\r\n"
		     		+ "}\r\n"
		     		+ "\r\n"
		     		+ " 	</style>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- MESSAGE SUBJECT -->\r\n"
		     		+ "	<title>Responsive HTML email templates</title>\r\n"
		     		+ "\r\n"
		     		+ "</head>\r\n"
		     		+ "\r\n"
		     		+ "<!-- BODY -->\r\n"
		     		+ "<!-- Set message background color (twice) and text color (twice) -->\r\n"
		     		+ "<body topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\" leftmargin=\"0\" marginwidth=\"0\" marginheight=\"0\" width=\"100%\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%; height: 100%; -webkit-font-smoothing: antialiased; text-size-adjust: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; line-height: 100%;\r\n"
		     		+ "	background-color: #2D3445;\r\n"
		     		+ "	color: #FFFFFF;\"\r\n"
		     		+ "	bgcolor=\"#2D3445\"\r\n"
		     		+ "	text=\"#FFFFFF\">\r\n"
		     		+ "\r\n"
		     		+ "<!-- SECTION / BACKGROUND -->\r\n"
		     		+ "<!-- Set message background color one again -->\r\n"
		     		+ "<table width=\"100%\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; width: 100%;\" class=\"background\"><tr><td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\"\r\n"
		     		+ "	bgcolor=\"#2D3445\">\r\n"
		     		+ "\r\n"
		     		+ "<!-- WRAPPER -->\r\n"
		     		+ "<!-- Set wrapper width (twice) -->\r\n"
		     		+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\"\r\n"
		     		+ "	width=\"500\" style=\"border-collapse: collapse; border-spacing: 0; padding: 0; width: inherit;\r\n"
		     		+ "	max-width: 500px;\" class=\"wrapper\">\r\n"
		     		+ "\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n"
		     		+ "			padding-top: 20px;\r\n"
		     		+ "			padding-bottom: 20px;\">\r\n"
		     		+ "\r\n"
		     		+ "			<!-- PREHEADER -->\r\n"
		     		+ "			<!-- Set text color to background color -->\r\n"
		     		+ "			<div style=\"display: none; visibility: hidden; overflow: hidden; opacity: 0; font-size: 1px; line-height: 1px; height: 0; max-height: 0; max-width: 0;\r\n"
		     		+ "				color: #2D3445;\" class=\"preheader\">\r\n"
		     		+ "				Available on&nbsp;GitHub and&nbsp;CodePen. Highly compatible. Designer friendly. More than 50%&nbsp;of&nbsp;total email opens occurred on&nbsp;a&nbsp;mobile device&nbsp;— a&nbsp;mobile-friendly design is&nbsp;a&nbsp;must for&nbsp;email campaigns.</div>\r\n"
		     		+ "\r\n"
		     		+ "			<!-- LOGO -->\r\n"
		     		+ "			<!-- Image text color should be opposite to background color. Set your url, image src, alt and title. Alt text should fit the image size. Real image size should be x2. URL format: http://domain.com/?utm_source={{Campaign-Source}}&utm_medium=email&utm_content=logo&utm_campaign={{Campaign-Name}} -->\r\n"
		     		+ "			<a target=\"_blank\" style=\"text-decoration: none;\"\r\n"
		     		+ "				href=\"https://github.com/konsav/email-templates/\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n"
		     		+ "				src=\"https://raw.githubusercontent.com/konsav/email-templates/master/images/logo-white.png\"\r\n"
		     		+ "				width=\"100\" height=\"30\"\r\n"
		     		+ "				alt=\"Logo\" title=\"Logo\" style=\"\r\n"
		     		+ "				color: #FFFFFF;\r\n"
		     		+ "				font-size: 10px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\" /></a>\r\n"
		     		+ "\r\n"
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
//		     		+ "\r\n"
//		     		+ "	<!-- HERO IMAGE -->\r\n"
//		     		+ "	<!-- Image text color should be opposite to background color. Set your url, image src, alt and title. Alt text should fit the image size. Real image size should be x2 (wrapper x2). Do not set height for flexible images (including \"auto\"). URL format: http://domain.com/?utm_source={{Campaign-Source}}&utm_medium=email&utm_content={{Ìmage-Name}}&utm_campaign={{Campaign-Name}} -->\r\n"
//		     		+ "	<tr>\r\n"
//		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;\r\n"
//		     		+ "			padding-top: 0px;\" class=\"hero\"><a target=\"_blank\" style=\"text-decoration: none;\"\r\n"
//		     		+ "			href=\"https://github.com/konsav/email-templates/\"><img border=\"0\" vspace=\"0\" hspace=\"0\"\r\n"
//		     		+ "			src=\"https://raw.githubusercontent.com/konsav/email-templates/master/images/hero-block.png\"\r\n"
//		     		+ "			alt=\"Please enable images to view this content\" title=\"Hero Image\"\r\n"
//		     		+ "			width=\"340\" style=\"\r\n"
//		     		+ "			width: 87.5%;\r\n"
//		     		+ "			max-width: 340px;\r\n"
//		     		+ "			color: #FFFFFF; font-size: 13px; margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\"/></a></td>\r\n"
//		     		+ "	</tr>\r\n"
//		     		+ "\r\n"
//		     		+ "	<!-- SUPHEADER -->\r\n"
//		     		+ "	<!-- Set text color and font family (\"sans-serif\" or \"Georgia, serif\") -->\r\n"
//		     		+ "	<tr>\r\n"
//		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 14px; font-weight: 400; line-height: 150%; letter-spacing: 2px;\r\n"
//		     		+ "			padding-top: 27px;\r\n"
//		     		+ "			padding-bottom: 0;\r\n"
//		     		+ "			color: #FFFFFF;\r\n"
//		     		+ "			font-family: sans-serif;\" class=\"supheader\">\r\n"
//		     		+ "				INTRODUCING\r\n"
//		     		+ "		</td>\r\n"
//		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- HEADER -->\r\n"
		     		+ "	<!-- Set text color and font family (\"sans-serif\" or \"Georgia, serif\") -->\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0;  padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 24px; font-weight: bold; line-height: 130%;\r\n"
		     		+ "			padding-top: 5px;\r\n"
		     		+ "			color: #FFFFFF;\r\n"
		     		+ "			font-family: sans-serif;\" class=\"header\">\r\n"
		     		+ "				COVID-19 Vaccine Appointments Booking Confirmation\r\n"
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- PARAGRAPH -->\r\n"
		     		+ "	<!-- Set text color and font family (\"sans-serif\" or \"Georgia, serif\"). Duplicate all text styles in links, including line-height -->\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 17px; font-weight: 400; line-height: 160%;\r\n"
		     		+ "			padding-top: 15px; \r\n"
		     		+ "			color: #FFFFFF;\r\n"
		     		+ "			font-family: sans-serif;\" class=\"paragraph\">\r\n"
		     		+ "				Booking Confirmation Code : "+confirmationNumber
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- BUTTON -->\r\n"
		     		+ "	<!-- Set button background color at TD, link/text color at A and TD, font family (\"sans-serif\" or \"Georgia, serif\") at TD. For verification codes add \"letter-spacing: 5px;\". Link format: http://domain.com/?utm_source={{Campaign-Source}}&utm_medium=email&utm_content={{Button-Name}}&utm_campaign={{Campaign-Name}} -->\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n"
		     		+ "			padding-top: 25px;\r\n"
		     		+ "			padding-bottom: 5px;\" class=\"button\"><a\r\n"
		     		+ "			href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline;\">\r\n"
		     		+ "				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"max-width: 240px; min-width: 120px; border-collapse: collapse; border-spacing: 0; padding: 0;\"><tr><td align=\"center\" valign=\"middle\" style=\"padding: 12px 24px; margin: 0; text-decoration: underline; border-collapse: collapse; border-spacing: 0; border-radius: 4px; -webkit-border-radius: 4px; -moz-border-radius: 4px; -khtml-border-radius: 4px;\"\r\n"
		     		+ "					bgcolor=\"#E9703E\"><a target=\"_blank\" style=\"text-decoration: underline;\r\n"
		     		+ "					color: #FFFFFF; font-family: sans-serif; font-size: 17px; font-weight: 400; line-height: 120%;\"\r\n"
		     		+ "					href=\"http://localhost:8081/ReimursementSystem/loginpage.jsp\">\r\n"
		     		+ "						Covid Vaccine Portal\r\n"
		     		+ "					</a>\r\n"
		     		+ "			</td></tr></table></a>\r\n"
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- LINE -->\r\n"
		     		+ "	<!-- Set line color -->\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%;\r\n"
		     		+ "			padding-top: 30px;\" class=\"line\"><hr\r\n"
		     		+ "			color=\"#565F73\" align=\"center\" width=\"100%\" size=\"1\" noshade style=\"margin: 0; padding: 0;\" />\r\n"
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "	<!-- FOOTER -->\r\n"
		     		+ "	<!-- Set text color and font family (\"sans-serif\" or \"Georgia, serif\"). Duplicate all text styles in links, including line-height -->\r\n"
		     		+ "	<tr>\r\n"
		     		+ "		<td align=\"center\" valign=\"top\" style=\"border-collapse: collapse; border-spacing: 0; margin: 0; padding: 0; padding-left: 6.25%; padding-right: 6.25%; width: 87.5%; font-size: 13px; font-weight: 400; line-height: 150%;\r\n"
		     		+ "			padding-top: 10px;\r\n"
		     		+ "			padding-bottom: 20px;\r\n"
		     		+ "			color: #828999;\r\n"
		     		+ "			font-family: sans-serif;\" class=\"footer\">\r\n"
		     		+ "\r\n"
		     		+ "				This email template was sent to&nbsp;you becouse we&nbsp;want to&nbsp;make the&nbsp;world a&nbsp;better place. You&nbsp;could change your <a href=\"https://github.com/konsav/email-templates/\" target=\"_blank\" style=\"text-decoration: underline; color: #828999; font-family: sans-serif; font-size: 13px; font-weight: 400; line-height: 150%;\">subscription settings</a> anytime.\r\n"
		     		+ "\r\n"
		     		+ "				<!-- ANALYTICS -->\r\n"
		     		+ "				<!-- https://www.google-analytics.com/collect?v=1&tid={{UA-Tracking-ID}}&cid={{Client-ID}}&t=event&ec=email&ea=open&cs={{Campaign-Source}}&cm=email&cn={{Campaign-Name}} -->\r\n"
		     		+ "				<img width=\"1\" height=\"1\" border=\"0\" vspace=\"0\" hspace=\"0\" style=\"margin: 0; padding: 0; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; border: none; display: block;\"\r\n"
		     		+ "				src=\"https://www.repricerexpress.com/wp-content/uploads/2016/03/amazon-fba-reimbursements.jpg\" />\r\n"
		     		+ "\r\n"
		     		+ "		</td>\r\n"
		     		+ "	</tr>\r\n"
		     		+ "\r\n"
		     		+ "<!-- End of WRAPPER -->\r\n"
		     		+ "</table>\r\n"
		     		+ "\r\n"
		     		+ "<!-- End of SECTION / BACKGROUND -->\r\n"
		     		+ "</td></tr></table>\r\n"
		     		+ "\r\n"
		     		+ "</body>\r\n"
		     		+ "</html>";
		     
		     message.setContent(goodmessage, "text/html");
		    // message.setSentDate(LocalDate.now());
		    //send the message  
		     Transport.send(message);  
		     
		     System.out.println("message sent successfully...");  
		     return true;
		     } catch (MessagingException e) {
		    	 
		    	 e.printStackTrace();
		    	 return false;}
			
	}
	
	
	
}
