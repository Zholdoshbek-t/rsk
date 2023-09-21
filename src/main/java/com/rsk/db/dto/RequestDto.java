package com.rsk.db.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

    private Long employeeId;

    private Long equipmentId;

    private Long paymentId;

    private String fullName;

    private String equipmentName;

    private LocalDate paymentDate;

    private Long paymentSum;

    private String paymentComment;
}
