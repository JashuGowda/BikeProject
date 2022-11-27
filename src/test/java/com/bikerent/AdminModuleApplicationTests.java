package com.bikerent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bikerent.dao.BikeRepo;
import com.bikerent.entities.Bike;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminModuleApplicationTests {
	

	@Autowired
	BikeRepo br;
	
	
	@Test
	@Order(1)
	public void testCreate() {
		Bike bike=new Bike();
		bike.setId(14);
		bike.setBikename("Hero");
		bike.setBikenum("KA-13 LU 0922");
		bike.setBikevar("Splender");
		bike.setModelyear(2020);
		bike.setPrice(600);
		bike.setStatus("Available");
		br.save(bike);
		assertNotNull(br.findById(14).get());
	}
	@Test
	@Order(2)
	public void testReadAll() {	
		List<Bike> list=br.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	@Test
	@Order(3)
	public void getSinglebike() {
		Bike bike=br.findById(7).get();
		assertEquals(800,bike.getPrice());
	}
	@Test
	@Order(4)
	public void testUpdate() {
	Bike bike=br.findById(8).get();
	bike.setPrice(600);
	br.save(bike);
	assertNotEquals(800, br.findById(8).get().getPrice());
	}
	@Test
	@Order(5)
	public void testDelete() {
		br.deleteById(13);
		assertThat(br.existsById(13)).isFalse();
	}
	
	
	

}
