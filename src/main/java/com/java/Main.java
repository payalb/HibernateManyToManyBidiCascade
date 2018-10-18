package com.java;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.dto.Phone;
import com.java.dto.Student;
import com.java.dto.Type;

public class Main {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		Main obj = new Main();
		try {
			obj.insertRecords();
			obj.fetchData();
		} finally {
			sf.close();
		}

	}

	public void insertRecords() {
		Student st1 = new Student("payal", null);
		Student st2 = new Student("ritu", null);
		Phone p1 = new Phone(76_372l, Type.LANDLINE, Arrays.asList(st1, st2));
		Phone p2 = new Phone(7_38_47_476l, Type.MOBILE, Arrays.asList(st1));
		Session s = sf.openSession();
		s.beginTransaction();
		s.persist(p1);
		s.persist(p2);
		s.getTransaction().commit();
		s.close();
	}

	public void fetchData() {
		Session s = sf.openSession();
		Phone p1 = s.get(Phone.class, 76_372l);
		System.out.println(p1.getStudent());
		
		Student st=s.get(Student.class, 1);
		System.out.println(st.getPhone());
		s.close();
	}
}
