package com.cntaiping.domain.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PolicyAllView{
    private UUID id;

    private String policyNo;

    private String policyStatus;

    private String policyCurrency;

    private BigDecimal policyPremium;

    private BigDecimal policyAmount;

    private Boolean effective;

    private Boolean deleted;

    private LocalDate applicationDate;

    private LocalDate policyEffectDate;

    private LocalDate ensureEffectDate;

    private LocalDate ensureExpireDate;

    private LocalDate policyAdoptionDate;

    private String month;

    @QueryProjection
    public PolicyAllView(UUID id, String policyNo, String policyStatus, String policyCurrency, BigDecimal policyPremium, BigDecimal policyAmount, Boolean effective, Boolean deleted, LocalDate applicationDate, LocalDate policyEffectDate, LocalDate ensureEffectDate, LocalDate ensureExpireDate, LocalDate policyAdoptionDate) {
        this.id = id;
        this.policyNo = policyNo;
        this.policyStatus = policyStatus;
        this.policyCurrency = policyCurrency;
        this.policyPremium = policyPremium;
        this.policyAmount = policyAmount;
        this.effective = effective;
        this.deleted = deleted;
        this.applicationDate = applicationDate;
        this.policyEffectDate = policyEffectDate;
        this.ensureEffectDate = ensureEffectDate;
        this.ensureExpireDate = ensureExpireDate;
        this.policyAdoptionDate = policyAdoptionDate;
    }
}
