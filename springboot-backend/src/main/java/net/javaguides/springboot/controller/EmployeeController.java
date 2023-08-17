package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	//get all emp -->> http://localhost:8085/api/v1/employees
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/employees")
	public List<Employee> getAllEmp() {
		System.out.println("in controller");
		
		List<Employee> emp = employeeRepo.findAll();
		
		Iterator<Employee> i = emp.iterator();
		while(i.hasNext()) {
			System.out.println(i.next().getFirstname());
		}
		
		return employeeRepo.findAll();
	}
	
	
	
	

	
	
	//Save / add Employee
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/saveemp")
	public Employee saveEmp(@RequestBody Employee emp) {
		System.out.println("Saving the employee-->>"+emp.getFirstname());
		return employeeRepo.save(emp);
	}
	
	
	
	////Get Emp By Id
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getEmpById/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable Long id){
	
		Employee emp = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not exist with id "+id));
		return ResponseEntity.ok(emp);
	}
	
	
	
	//Update Emp based on Id
	@CrossOrigin(origins = "http://localhost:4200")
	//@CrossOrigin(origins = "http://localhost:8085/api/v1/UpdateEmpById")
	@PutMapping("/UpdateEmpById/{id}")
	public ResponseEntity<Employee> updateEmp(@RequestBody Employee emp, @PathVariable Long id) {
		
		Employee e = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not exist with id "+id));
		
		e.setFirstname(emp.getFirstname());
		e.setLastname(emp.getLastname());
		e.setEmailId(emp.getEmailId());
		
		Employee updatedEmployee = employeeRepo.save(e);
		
		return ResponseEntity.ok(updatedEmployee);
		
	}	
	
	
	
	
	
	//Delete Emp based on Id
	//@CrossOrigin(origins = "http://localhost:8085/api/v1/DeleteEmp")
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("DeleteEmp/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmp(@PathVariable Long id){
		
		Employee e = employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not exist with id "+id));
		
		employeeRepo.delete(e);
		
		Map<String,Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	
}











