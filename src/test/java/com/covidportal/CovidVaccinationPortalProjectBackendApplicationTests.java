package com.covidportal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.covidportal.controller.UserController;
import com.covidportal.model.Admin;
import com.covidportal.model.Appointment;
import com.covidportal.model.AppointmentStatus;
import com.covidportal.model.Clinic;
import com.covidportal.model.Timeslot;
import com.covidportal.model.User;
import com.covidportal.model.VaccineType;
import com.covidportal.service.AdminService;
import com.covidportal.service.AppointmentService;
import com.covidportal.service.AppointmentStatusService;
import com.covidportal.service.ClinicService;
import com.covidportal.service.TimeslotService;
import com.covidportal.service.UserService;

@SpringBootTest
class CovidVaccinationPortalProjectBackendApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	

	@Mock
	private UserService userServ;
	@Mock
	private UserController userController;
	@Mock
	private AdminService adminServ;

	@Mock
	private AppointmentService apntServ;
	@Mock
	private ClinicService clinicServ;
	@Mock
	private AppointmentStatusService apntStatusServ;
	@Mock
	private TimeslotService timeslotServ;
	private User user, user1;
	private Appointment apnt;
	private Clinic clinic, clinic1;
	private Timeslot time1;
	private AppointmentStatus apntStatus;
	private VaccineType vcnType;
	private Admin admin;
	private Timeslot timeslot;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		clinic = new Clinic(1, "clinic1", "address1", 10, Time.valueOf("11:00:00"), Time.valueOf("18:00:00"));
		// clinic.setClinicId(1);
		time1 = new Timeslot(1234590L);
		apntStatus = new AppointmentStatus();
		apntStatus.setAppointmentStatus("pending");
		vcnType = new VaccineType();
		vcnType.setVaccineType("Moderna");
		apnt = new Appointment(1, clinic, user, time1, apntStatus, vcnType);
		List<Appointment> apntList = new ArrayList<>();
		apntList.add(apnt);
		user = new User();
		String date1 = "1994-10-17";
		Date dob = java.sql.Date.valueOf(date1);
		timeslot = new Timeslot(122323L);
		admin = new Admin(6, "DivyaSandeep", "Divya@17", "Divya", "Madgirl", "divyamadgirly@gmail.com",
				new BigInteger("1234567892"));
		user1 = new User(1, "Divya", "Reddy", dob, "3154502989", "divyareddy416@gmail.com",
				"42 Blackdome Cres,Kanata,On,K2T1A9", "heah144", "Confirm2s2", apntList);
		Appointment apnt = new Appointment(1, 1, clinic, user1, time1, apntStatus, vcnType);
		when(userServ.getUserByHealthCardNumber("heah144")).thenReturn(user1);
		when(userServ.getUserByConfirmationNumber("Confirm2s2")).thenReturn(user1);
		when(userServ.getUserByConfirmationNumberAndEmail("Confirm2s2", "divyareddy416@gmail.com")).thenReturn(user1);
		when(userController.sendingmail("divyareddy416@gmail.com", "Confirm2s2")).thenReturn(true);
		when(adminServ.getByUserName("DivyaSandeep")).thenReturn(admin);
		when(apntServ.findByAppointmentId(1)).thenReturn(apnt);
		when(apntServ.findAllAppointments()).thenReturn((List<Appointment>) new ArrayList<Appointment>());

		when(clinicServ.getByClinicId(1)).thenReturn((List<Clinic>) new ArrayList<Clinic>());
		when(clinicServ.getClinicByName("clinic1")).thenReturn(clinic);
		when(clinicServ.getAllClinic()).thenReturn((List<Clinic>) new ArrayList<Clinic>());
		when(timeslotServ.findByTimeslotId(1)).thenReturn(timeslot);
		when(timeslotServ.findbydateandtime(122323L)).thenReturn(timeslot);
	}

	// ************************UserService********************************

	@Test
	public void validateInsertUser() {
		apntStatus = new AppointmentStatus();
		vcnType = new VaccineType();
		apnt = new Appointment(1, clinic, user, time1, apntStatus, vcnType);
		List<Appointment> apntList = new ArrayList<>();
		apntList.add(apnt);
		userServ.insertUser(new User(1, "Divya", "Reddy", java.sql.Date.valueOf("1994-10-17"), "3154502989",
				"divyareddy416@gmail.com", "42 Blackdome Cres,Kanata,On,K2T1A9", "heah144", "Confirm2s2", apntList));
		assertEquals(user1, userServ.getUserByHealthCardNumber("heah144"));
	}

	@Test
	public void validateGetUserByHealthacardNumber() {
		assertEquals(user1, userServ.getUserByHealthCardNumber("heah144"));
	}

	@Test
	public void validateGetUserByConfrimationNumber() {
		assertEquals(user1, userServ.getUserByConfirmationNumber("Confirm2s2"));
	}

	@Test
	public void validateGetUserByConfirmationNumberAndEmail() {

		assertEquals(user1, userServ.getUserByConfirmationNumberAndEmail("Confirm2s2", "divyareddy416@gmail.com"));
	}
	@Test
	public void validateFailureGetUserByConfrimationNumber() {
		assertEquals(null, userServ.getUserByConfirmationNumber(""));
	}

	

	// ************************UserController********************************

	@Test
	public void validateSendEmail() {
		assertTrue(userController.sendingmail("divyareddy416@gmail.com", "Confirm2s2"));
	}

//	@Test
//	public void validateSuccessGenerateConfirmationNumber() {
//		assertNotNull(userController.generateConfirmationNumber());
//
//	}
	@Test
	public void validateFailureSendEmail() {
		assertFalse(userController.sendingmail("sadsad", "Confirm2s2"));
	}
	// **********************************AdminService********************************
	@Test
	public void validateGetAdmin() {
		assertEquals(admin, adminServ.getByUserName("DivyaSandeep"));
	}

	@Test
	public void validateInsertAdmin() {
		adminServ.insertAdmin(new Admin("DivyaSandeep", "Divya@17", "Divya", "Madgirl", "divyamadgirly@gmail.com",
				new BigInteger("1234567892")));
		assertEquals(admin, adminServ.getByUserName("DivyaSandeep"));

	}

	@Test
	public void validateFailureGetAdmin() {
		assertNull(adminServ.getByUserName("addadsad"));
	}

	// **************************Appointment Service******************************

	@Test
	public void validateBookAppointment() {
		
		Appointment apnt = new Appointment(1, 1, clinic, user1, time1, apntStatus, vcnType);
		
		apntServ.bookAppointment(apnt);

		assertNotNull(apntServ.findByAppointmentId(1));

	}

	@Test
	public void validategetAppointments() {
		assertEquals(new ArrayList<Appointment>(), apntServ.findAllAppointments());
		assertNotNull(apntServ.findAllAppointments());
	}

	// *********************************Clinic
	// Service*********************************************************

	@Test
	public void validateInsertClinic() {
		clinicServ.insertClinic(clinic);
		assertEquals((List<Clinic>) new ArrayList<Clinic>(), clinicServ.getByClinicId(1));

	}

	@Test
	public void validateGetClinicByName() {
		assertEquals(clinic, clinicServ.getClinicByName("clinic1"));
	}
	@Test
	public void validateFailureGetClinicByName() {
		assertEquals(null, clinicServ.getClinicByName("sasas"));
	}

	@Test
	public void validateGetAllClinics() {
		assertEquals((List<Clinic>) new ArrayList<Clinic>(), clinicServ.getAllClinic());
	}

	@Test
	public void validateFailureGetClinic() {
		assertNull(clinicServ.getClinicByName("saddadad"));
	}

	// *****************AppointmentStatusService***************
//	@Test
//	public void validateGetAppointmentById() {
//		assertEquals((List<Clinic>) new ArrayList<Clinic>(), apntStatusServ.getappointmentvyid(1));
//	}

	// ***********************Timeslot Service*****************

	@Test
	public void validateCreateTimeslot() {
		timeslot.setTimeslotId(1);
		timeslotServ.createTimeslot(timeslot);
		assertEquals(timeslot, timeslotServ.findByTimeslotId(1));

	}

	@Test
	public void validateFindbydateandtime() {
		assertEquals(timeslot, timeslotServ.findbydateandtime(122323L));
	}
	@Test
	public void validateFailureFindbydateandtime() {
		assertNull(timeslotServ.findbydateandtime(-1));
	}

	@Test
	public void validateFindAllTimeslots() {
		assertEquals((List<Timeslot>) new ArrayList<Timeslot>(), timeslotServ.findAll());
	}

	@Test
	public void validateFailureFindByTimeslotId() {
		assertThrows(NumberFormatException.class, () -> timeslotServ.findByTimeslotId(Integer.parseInt("abc")));
	}

	@Test
	void contextLoads() {
	}




}
