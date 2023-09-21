package com.rsk.db.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Object employeeEquipments;

    private Object employeePayments;

    private Object data;

    private Long employeeId;

    private Long equipmentId;

    private Long paymentId;

    private String fullName;

    private String equipmentName;

    private LocalDate paymentDate;

    private Long paymentSum;

    private String paymentComment;
}
