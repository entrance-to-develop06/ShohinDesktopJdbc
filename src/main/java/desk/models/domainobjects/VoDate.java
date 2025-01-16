package main.java.desk.models.domainobjects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** 値オブジェクト：日付
 *  不変*/
public class VoDate extends ValueObject<VoDate> {

    private final BigDecimal value;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** 完全コンストラクタ
     * @param date*/
    public VoDate(BigDecimal date) {
        isNull(date);
        try {
            formatter.format(LocalDate.parse(date.toString(), formatter));
        }
        catch (DateTimeParseException e) {
            throw new DomainObjectException(e.getMessage());
        }
        value = date;
    }

    /** ゲッター
     * @return*/
    public BigDecimal getValue() {
        return value;
    }

    /** 編集日付を0000/00/00形式で返します。
     * @return*/
    public String getValueAndSlashFormat() {
        var date = new StringBuilder(value.toString()).insert(6, '/').insert(4, '/');
        return date.toString();
    }

    /** 編集日付を0000-00-00形式で返します。
     * @return*/
    public String getValueAndHyphenFormat() {
        var date = new StringBuilder(value.toString()).insert(6, '-').insert(4, '-');
        return date.toString();
    }

    /** 等値(同一オブジェクト)比較
     */
    @Override
    protected boolean runEquals(VoDate other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public VoDate recreate(BigDecimal rc) {
        return new VoDate(rc);
    }
}