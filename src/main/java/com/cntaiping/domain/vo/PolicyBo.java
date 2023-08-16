package com.cntaiping.domain.vo;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PolicyBo {
    @Id
    private UUID id;
    private String policyNo;
    private String policyStatus;
    private String policyCurrency;
    private BigDecimal policyPremium;
    private BigDecimal policyAmount;
    private Boolean effective;
    private LocalDate applicationDate;
    private LocalDate policyEffectDate;
    private LocalDate policyAdoptionDate;

    @QueryProjection
    public PolicyBo(UUID id, String policyNo, String policyStatus, String policyCurrency, BigDecimal policyPremium, BigDecimal policyAmount, Boolean effective, LocalDate applicationDate, LocalDate policyEffectDate, LocalDate policyAdoptionDate) {
        this.id = id;
        this.policyNo = policyNo;
        this.policyStatus = policyStatus;
        this.policyCurrency = policyCurrency;
        this.policyPremium = policyPremium;
        this.policyAmount = policyAmount;
        this.effective = effective;
        this.applicationDate = applicationDate;
        this.policyEffectDate = policyEffectDate;
        this.policyAdoptionDate = policyAdoptionDate;
    }
}
