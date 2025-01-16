package main.java.desk.models.appservices.dtos;

import java.math.BigDecimal;

import main.java.desk.models.domainobjects.entitys.ShohinEntity;

/** 商品Data Transfer Object<br />
 * データベースアクセス時に使用。不変、継承不可*/
public final class ShohinDto {

    private final String uniqueId;
    private final int shohinCode;
    private final String shohinName;
    private final BigDecimal editDate;
    private final BigDecimal editTime;
    private final String remarks;

    public ShohinDto(ShohinEntity source) {
        uniqueId = source.getUniqueId().getValue();
        shohinCode = source.getShohinCode().getValue();
        shohinName = source.getShohinName().getValueNotSpace();
        var datetime = new DateTimeDto(source.getEditDateTime());
        editDate = datetime.getEditDate();
        editTime = datetime.getEditTime();
        remarks = source.getRemarks().getValue();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public int getShohinCode() {
        return shohinCode;
    }

    public String getShohinName() {
        return shohinName;
    }

    public BigDecimal getEditDate() {
        return editDate;
    }

    public BigDecimal getEditTime() {
        return editTime;
    }

    public String getRemarks() {
        return remarks;
    }
}