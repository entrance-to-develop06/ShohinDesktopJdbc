package main.java.desk.models.domainobjects;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** 値オブジェクト：時刻
 *　　不変*/
public class VoTime extends ValueObject<VoTime> {

    private final BigDecimal value;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[]Hmmss");

    /** 完全コンストラクタ
     * */
    public VoTime(BigDecimal time) {
        isNull(time);
        try {
            formatter.format(LocalTime.parse(time.toString(), formatter));
        }
        catch (DateTimeParseException e) {
            throw new DomainObjectException(e.getMessage());
        }
        value = time;
    }

    /** ゲッター
     */
    public BigDecimal getValue() {
        return value;
    }

    /** コロン付きゲッター(00:00:00)
     * @return*/
    public String getValueAndColonFormat() {
        var time = new StringBuilder(value.toString());
        if (time.length() < 6)
            time.insert(0, "0");
        time = time.insert(4, ':').insert(2, ':');
        //var time = new StringBuilder(value.toString()).insert(4, ':').insert(2, ':');
        return time.toString();
    }

    /** 等値(同一オブジェクト)比較
     */
    @Override
    protected boolean runEquals(VoTime other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public VoTime recreate(BigDecimal rc) {
        return new VoTime(rc);
    }
}