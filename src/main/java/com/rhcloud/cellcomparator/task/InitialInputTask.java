package com.rhcloud.cellcomparator.task;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.rhcloud.cellcomparator.cdi.qualifier.SmartphoneDAO;
import com.rhcloud.cellcomparator.dao.impl.SmartphoneDAOImpl;
import com.rhcloud.cellcomparator.entity.Characteristic;
import com.rhcloud.cellcomparator.entity.CharacteristicsType;
import com.rhcloud.cellcomparator.entity.Manufacturer;
import com.rhcloud.cellcomparator.entity.Smartphone;

/**
 * Task responsavel por inicializar o database.
 * 
 * @author Estanislau Araújo
 * @since 19/12/2015
 */
@Singleton
@ApplicationScoped
@Startup	
public class InitialInputTask {
	
	@Inject @SmartphoneDAO
	SmartphoneDAOImpl smartphoneDAOImpl;
	
	@PostConstruct
	public void init() throws IOException {
		
		System.out.println("Starting: " + this.getClass().getName() + ":init()");
		
		Smartphone iphone = new Smartphone("iPhone 6", "iphone6.jpg", Manufacturer.APPLE);
		iphone.setCharacteristics(Arrays.asList(
				new Characteristic("iOS", null, CharacteristicsType.OPERATIONAL_SYSTEM, iphone),
				new Characteristic("8.0", null, CharacteristicsType.OS_VERSION, iphone),
				new Characteristic("128", "GB", CharacteristicsType.STORAGE, iphone),
				new Characteristic("2", "GB", CharacteristicsType.RAM_MEMORY, iphone),
				new Characteristic("1.2", "Mpx", CharacteristicsType.FRONTAL_CAMERA, iphone),
				new Characteristic("8.0", "Mpx", CharacteristicsType.CAMERA, iphone),
				new Characteristic("IPS LCD", null, CharacteristicsType.DISPLAY_TYPE, iphone),
				new Characteristic("4.7", "polegadas", CharacteristicsType.DISPLAY_SIZE, iphone),
				new Characteristic("1810", "Mah", CharacteristicsType.BATTERY_CAPACITY, iphone),
				new Characteristic("250", "horas", CharacteristicsType.BATTERY_STAND_BY_TIME, iphone)
			));
		
		Smartphone samsung6s = new Smartphone("Samsung Galaxy S6", "samsungs6.jpg", Manufacturer.SAMSUNG);
		samsung6s.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, samsung6s),
				new Characteristic("5.0", null, CharacteristicsType.OS_VERSION, samsung6s),
				new Characteristic("128", "GB", CharacteristicsType.STORAGE, samsung6s),
				new Characteristic("3", "GB", CharacteristicsType.RAM_MEMORY, samsung6s),
				new Characteristic("5.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, samsung6s),
				new Characteristic("16.0", "Mpx", CharacteristicsType.CAMERA, samsung6s),
				new Characteristic("Super AMOLED", null, CharacteristicsType.DISPLAY_TYPE, samsung6s),
				new Characteristic("5.1", "polegadas", CharacteristicsType.DISPLAY_SIZE, samsung6s),
				new Characteristic("2550", "Mah", CharacteristicsType.BATTERY_CAPACITY, samsung6s)
			)); 
		
		Smartphone motoX2014 = new Smartphone("Motorola Moto X 2014", "motox2014.jpg", Manufacturer.MOTOROLA);
		motoX2014.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, motoX2014),
				new Characteristic("4.4.4", null, CharacteristicsType.OS_VERSION, motoX2014),
				new Characteristic("32", "GB", CharacteristicsType.STORAGE, motoX2014),
				new Characteristic("2", "GB", CharacteristicsType.RAM_MEMORY, motoX2014),
				new Characteristic("5.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, motoX2014),
				new Characteristic("13.0", "Mpx", CharacteristicsType.CAMERA, motoX2014),
				new Characteristic("AMOLED", null, CharacteristicsType.DISPLAY_TYPE, motoX2014),
				new Characteristic("5.2", "polegadas", CharacteristicsType.DISPLAY_SIZE, motoX2014),
				new Characteristic("2300", "Mah", CharacteristicsType.BATTERY_CAPACITY, motoX2014)
			));
		
		Smartphone huawaiNexus6p = new Smartphone("Huawei Nexus 6P", "huawainexus6p.jpg", Manufacturer.HUAWEI);
		huawaiNexus6p.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, huawaiNexus6p),
				new Characteristic("6.0", null, CharacteristicsType.OS_VERSION, huawaiNexus6p),
				new Characteristic("128", "GB", CharacteristicsType.STORAGE, huawaiNexus6p),
				new Characteristic("3", "GB", CharacteristicsType.RAM_MEMORY, huawaiNexus6p),
				new Characteristic("8.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, huawaiNexus6p),
				new Characteristic("12.0", "Mpx", CharacteristicsType.CAMERA, huawaiNexus6p),
				new Characteristic("AMOLED", null, CharacteristicsType.DISPLAY_TYPE, huawaiNexus6p),
				new Characteristic("5.7", "polegadas", CharacteristicsType.DISPLAY_SIZE, huawaiNexus6p),
				new Characteristic("3450", "Mah", CharacteristicsType.BATTERY_CAPACITY, huawaiNexus6p)
			));
		
		Smartphone sonyXperiaZ3 = new Smartphone("Sony Xperia Z3", "sonyz3.jpg", Manufacturer.SONY);
		sonyXperiaZ3.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, sonyXperiaZ3),
				new Characteristic("4.4.4", null, CharacteristicsType.OS_VERSION, sonyXperiaZ3),
				new Characteristic("16", "GB", CharacteristicsType.STORAGE, sonyXperiaZ3),
				new Characteristic("3", "GB", CharacteristicsType.RAM_MEMORY, sonyXperiaZ3),
				new Characteristic("2.2", "Mpx", CharacteristicsType.FRONTAL_CAMERA, sonyXperiaZ3),
				new Characteristic("20.7", "Mpx", CharacteristicsType.CAMERA, sonyXperiaZ3),
				new Characteristic("IPS LCD", null, CharacteristicsType.DISPLAY_TYPE, sonyXperiaZ3),
				new Characteristic("5.2", "polegadas", CharacteristicsType.DISPLAY_SIZE, sonyXperiaZ3),
				new Characteristic("3100", "Mah", CharacteristicsType.BATTERY_CAPACITY, sonyXperiaZ3),
				new Characteristic("740", "horas", CharacteristicsType.BATTERY_STAND_BY_TIME, sonyXperiaZ3)
			));
		
		Smartphone lgG4 = new Smartphone("LG G4", "lgg4.jpg", Manufacturer.LG);
		lgG4.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, lgG4),
				new Characteristic("5.1", null, CharacteristicsType.OS_VERSION, lgG4),
				new Characteristic("32", "GB", CharacteristicsType.STORAGE, lgG4),
				new Characteristic("3", "GB", CharacteristicsType.RAM_MEMORY, lgG4),
				new Characteristic("8.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, lgG4),
				new Characteristic("16.0", "Mpx", CharacteristicsType.CAMERA, lgG4),
				new Characteristic("IPS LCD", null, CharacteristicsType.DISPLAY_TYPE, lgG4),
				new Characteristic("5.5", "polegadas", CharacteristicsType.DISPLAY_SIZE, lgG4),
				new Characteristic("3000", "Mah", CharacteristicsType.BATTERY_CAPACITY, lgG4),
				new Characteristic("440", "horas", CharacteristicsType.BATTERY_STAND_BY_TIME, lgG4)
			));
		
		Smartphone quantumGo = new Smartphone("Quantum Go", "quantumgo.jpg", Manufacturer.POSITIVO);
		quantumGo.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, quantumGo),
				new Characteristic("5.1", null, CharacteristicsType.OS_VERSION, quantumGo),
				new Characteristic("32", "GB", CharacteristicsType.STORAGE, quantumGo),
				new Characteristic("2", "GB", CharacteristicsType.RAM_MEMORY, quantumGo),
				new Characteristic("5.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, quantumGo),
				new Characteristic("13.0", "Mpx", CharacteristicsType.CAMERA, quantumGo),
				new Characteristic("AMOLED", null, CharacteristicsType.DISPLAY_TYPE, quantumGo),
				new Characteristic("5.0", "polegadas", CharacteristicsType.DISPLAY_SIZE, quantumGo),
				new Characteristic("2300", "Mah", CharacteristicsType.BATTERY_CAPACITY, quantumGo)
			));
		
		Smartphone nokiaLumia930 = new Smartphone("Nokia Lumia 930", "lumia930.jpg", Manufacturer.NOKIA);
		nokiaLumia930.setCharacteristics(Arrays.asList(
				new Characteristic("Windows", null, CharacteristicsType.OPERATIONAL_SYSTEM, nokiaLumia930),
				new Characteristic("Phone 8.1", null, CharacteristicsType.OS_VERSION, nokiaLumia930),
				new Characteristic("32", "GB", CharacteristicsType.STORAGE, nokiaLumia930),
				new Characteristic("2", "GB", CharacteristicsType.RAM_MEMORY, nokiaLumia930),
				new Characteristic("1.2", "Mpx", CharacteristicsType.FRONTAL_CAMERA, nokiaLumia930),
				new Characteristic("20.0", "Mpx", CharacteristicsType.CAMERA, nokiaLumia930),
				new Characteristic("AMOLED", null, CharacteristicsType.DISPLAY_TYPE, nokiaLumia930),
				new Characteristic("5.0", "polegadas", CharacteristicsType.DISPLAY_SIZE, nokiaLumia930),
				new Characteristic("2420", "Mah", CharacteristicsType.BATTERY_CAPACITY, nokiaLumia930),
				new Characteristic("432", "horas", CharacteristicsType.BATTERY_STAND_BY_TIME, nokiaLumia930)
			));
		
		Smartphone htcOneM8 = new Smartphone("HTC One M8", "htconem8.jpg", Manufacturer.HTC);
		htcOneM8.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, htcOneM8),
				new Characteristic("4.4.2", null, CharacteristicsType.OS_VERSION, htcOneM8),
				new Characteristic("32", "GB", CharacteristicsType.STORAGE, htcOneM8),
				new Characteristic("2", "GB", CharacteristicsType.RAM_MEMORY, htcOneM8),
				new Characteristic("4.0", "Mpx", CharacteristicsType.FRONTAL_CAMERA, htcOneM8),
				new Characteristic("5.0", "Mpx", CharacteristicsType.CAMERA, htcOneM8),
				new Characteristic("Super LCD3", null, CharacteristicsType.DISPLAY_TYPE, htcOneM8),
				new Characteristic("5.0", "polegadas", CharacteristicsType.DISPLAY_SIZE, htcOneM8),
				new Characteristic("2600", "Mah", CharacteristicsType.BATTERY_CAPACITY, htcOneM8),
				new Characteristic("496", "horas", CharacteristicsType.BATTERY_STAND_BY_TIME, htcOneM8)
			));
		
		Smartphone lenovoS930 = new Smartphone("Lenovo S930", "lenovos930.jpeg", Manufacturer.LENOVO);
		lenovoS930.setCharacteristics(Arrays.asList(
				new Characteristic("Android", null, CharacteristicsType.OPERATIONAL_SYSTEM, lenovoS930),
				new Characteristic("4.2", null, CharacteristicsType.OS_VERSION, lenovoS930),
				new Characteristic("8", "GB", CharacteristicsType.STORAGE, lenovoS930),
				new Characteristic("1", "GB", CharacteristicsType.RAM_MEMORY, lenovoS930),
				new Characteristic("1.6", "Mpx", CharacteristicsType.FRONTAL_CAMERA, lenovoS930),
				new Characteristic("8.0", "Mpx", CharacteristicsType.CAMERA, lenovoS930),
				new Characteristic("IPS LCD", null, CharacteristicsType.DISPLAY_TYPE, lenovoS930),
				new Characteristic("6.0", "polegadas", CharacteristicsType.DISPLAY_SIZE, lenovoS930),
				new Characteristic("3000", "Mah", CharacteristicsType.BATTERY_CAPACITY, lenovoS930)
			));
		
		smartphoneDAOImpl.saveList(Arrays.asList(iphone, samsung6s, motoX2014, huawaiNexus6p, sonyXperiaZ3,
				lgG4, quantumGo, nokiaLumia930, htcOneM8, lenovoS930));
		
		System.out.println("Finalized: " + this.getClass().getName() + ":init()");
		
	}

}
