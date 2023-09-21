package com.rsk.db.controller;

import com.rsk.db.dto.RequestDto;
import com.rsk.db.dto.ResponseDto;
import com.rsk.db.entity.Employee;
import com.rsk.db.entity.Equipment;
import com.rsk.db.entity.Payment;
import com.rsk.db.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {

    private final EmployeeRepo employeeRepo;

    @GetMapping("/get-all")
    public List<ResponseDto> getEmployeeReport() {
        List<Employee> employees = employeeRepo.findAll();

        if(employees.isEmpty()) return null;

        List<ResponseDto> responseDtos = new ArrayList<>();

        for(Employee e: employees) {
            RequestDto requestDto = RequestDto.builder().employeeId(e.getId()).build();
            responseDtos.add(ResponseDto.builder()
                    .employeeId(e.getId())
                    .fullName(e.getFullName())
                    .employeeEquipments(getEmployeeEquipments(requestDto))
                    .employeePayments(getEmployeePayments(requestDto))
                    .build());
        }

        return responseDtos;
    }

    @GetMapping("/get-by-full-name")
    public ResponseDto getEmployeeByFullName(@RequestBody RequestDto request) {
        List<Employee> employees = employeeRepo.findByFullName(request.getFullName());

        if (!employees.isEmpty()) {
            return ResponseDto.builder()
                    .data(employees.stream()
                            .map(this::employeeDto)
                            .collect(Collectors.toList()))
                    .build();
        }

        return ResponseDto.builder()
                .data("No employee was found!")
                .build();
    }

    @GetMapping("/employee/equipments")
    public ResponseDto getEmployeeEquipments(@RequestBody RequestDto request) {
        if (request.getEmployeeId() == 0) {
            return ResponseDto.builder()
                    .data("Employee id was not provided!")
                    .build();
        }

        Optional<Employee> optional = employeeRepo.findById(request.getEmployeeId());

        if (optional.isEmpty()) {
            return ResponseDto.builder()
                    .data("Employee was not found!")
                    .build();
        }

        Employee employee = optional.get();

        return ResponseDto.builder()
                .employeeId(employee.getId())
                .fullName(employee.getFullName())
                .employeeEquipments(employee.getEquipments().stream()
                        .map(this::equipmentDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @GetMapping("/employee/payments")
    public ResponseDto getEmployeePayments(@RequestBody RequestDto request) {
        if (request.getEmployeeId() == 0) {
            return ResponseDto.builder()
                    .data("Employee id was not provided!")
                    .build();
        }

        Optional<Employee> optional = employeeRepo.findById(request.getEmployeeId());

        if (optional.isEmpty()) {
            return ResponseDto.builder()
                    .data("Employee was not found!")
                    .build();
        }

        Employee employee = optional.get();

        return ResponseDto.builder()
                .employeeId(employee.getId())
                .fullName(employee.getFullName())
                .employeeEquipments(employee.getPayments().stream()
                        .map(this::paymentDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public ResponseDto employeeDto(Employee employee) {
        return ResponseDto.builder()
                .employeeId(employee.getId())
                .fullName(employee.getFullName())
                .build();
    }

    public ResponseDto equipmentDto(Equipment equipment) {
        return ResponseDto.builder()
                .equipmentId(equipment.getId())
                .equipmentName(equipment.getName())
                .build();
    }

    public ResponseDto paymentDto(Payment payment) {
        return ResponseDto.builder()
                .paymentId(payment.getId())
                .paymentSum(payment.getSum())
                .paymentDate(payment.getDate())
                .paymentComment(payment.getComment())
                .build();
    }
}
