package main.java.desk.models.appservices.dtos;

import java.math.BigDecimal;

import main.java.desk.models.domainobjects.shohinvalueobjects.EditDateTime;

/** 編集日付時刻Data Transfer Object<br />
 *  データベースアクセス時に使用。不変*/
public class DateTimeDto {

    private final BigDecimal date;
    private final BigDecimal time;

    public DateTimeDto(EditDateTime source) {
        date = source.getEditDate().getValue();
        time = source.getEditTime().getValue();
    }

    public BigDecimal getEditDate() {
        return date;
    }

    public BigDecimal getEditTime() {
        return time;
    }
}