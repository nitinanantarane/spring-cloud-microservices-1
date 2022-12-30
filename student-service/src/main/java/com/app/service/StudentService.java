package com.app.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.dto.StudentData;
import com.app.model.College;
import com.app.model.Student;
import com.app.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		
		return studentRepository.save(student);
	}


	@HystrixCommand(fallbackMethod = "fallback", groupKey = "Hello",
			commandKey = "hello",
			threadPoolKey = "helloThread"
	)
	public StudentData getStudentData(Long studentId) {
		College college=restTemplate.getForObject("http://COLLEGE-SERVICE/college/"+studentId, College.class);
		Student student= studentRepository.findByStudentId(studentId);
		StudentData studentData=new StudentData();
		BeanUtils.copyProperties(student, studentData);
		studentData.setCollege(college);
		 
		 return studentData;
	}

	public StudentData fallback(Long studentId) {
		System.out.println("Fallback called . Please wait ..");
		return new StudentData();
	}
	

}
