package main.java.desk.models.domainobjects.entitys;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import main.java.desk.models.domainobjects.Entity;
import main.java.desk.models.domainobjects.shohinvalueobjects.EditDateTime;
import main.java.desk.models.domainobjects.shohinvalueobjects.Remarks;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinCode;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinName;
import main.java.desk.models.domainobjects.shohinvalueobjects.UniqueId;
import main.java.desk.models.domainobjects.VoDate;
import main.java.desk.models.domainobjects.VoTime;

/** エンティティ：商品
 */
public class ShohinEntity extends Entity<ShohinEntity> {

    private final UniqueId uniqueId; //識別子
    private ShohinCode shohinCode;
    private ShohinName shohinName;
    private EditDateTime editDateTime;
    private Remarks remarks;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /** データベースから読み取った値格納コンストラクタ。よって、日付、時刻も取ってきたデータそのままを使用する。
     * @param uniqueId
     * @param shohinCode
     * @param shohinName
     * @param dateTime
     * @param remarks*/
    public ShohinEntity(UniqueId uniqueId, ShohinCode shohinCode, ShohinName shohinName, EditDateTime dateTime, Remarks remarks) {
        this.uniqueId = uniqueId;
        this.shohinCode = shohinCode;
        this.shohinName = shohinName;
        this.editDateTime = dateTime;
        this.remarks = remarks;
    }

    /** 生成するときのコンストラクタ(Uuid も作成される)
     */
    public ShohinEntity(ShohinCode shohinCode, ShohinName shohinName, Remarks remarks) {
        this.uniqueId = new UniqueId(UUID.randomUUID().toString());
        this.shohinCode = shohinCode;
        this.shohinName = shohinName;
        String daytime = formatter.format(LocalDateTime.now(ZoneId.of("Asia/Tokyo")));
        var nowDate = new VoDate(BigDecimal.valueOf(Integer.valueOf(daytime.substring(0,8))));
        var nowTime = new VoTime(BigDecimal.valueOf(Integer.valueOf(daytime.substring(8))));
        this.editDateTime = new EditDateTime(nowDate, nowTime);
        this.remarks = remarks;
    }

    public UniqueId getUniqueId() {
        return uniqueId;
    }

    public ShohinCode getShohinCode() {
        return shohinCode;
    }

    public ShohinName getShohinName() {
        return shohinName;
    }

    public EditDateTime getEditDateTime() {
        return editDateTime;
    }

    public Remarks getRemarks() {
        return remarks;
    }

    public void setShohinCode(ShohinCode shohinCode) {
        isNull(shohinCode);
        this.shohinCode = shohinCode;
    }

    public void setShohinName(ShohinName shohinName) {
        isNull(shohinName);
        this.shohinName = shohinName;
    }

    public void setDateTime() {
        String daytime = formatter.format(LocalDateTime.now(ZoneId.of("Asia/Tokyo")));
        var nowDate = new VoDate(BigDecimal.valueOf(Integer.valueOf(daytime.substring(0,8))));
        var nowTime = new VoTime(BigDecimal.valueOf(Integer.valueOf(daytime.substring(8))));
        this.editDateTime = new EditDateTime(nowDate, nowTime);
    }

    public void setRemarks(Remarks remarks) {
        isNull(remarks);
        this.remarks = remarks;
    }

    public void setValue(ShohinCode shohinCode, ShohinName shohinName, Remarks remarks) {
        setShohinCode(shohinCode);
        setShohinName(shohinName);
        setDateTime();
        setRemarks(remarks);
    }

    /** 等値(同一オブジェクト)比較
     */
    @Override
    protected boolean runEquals(ShohinEntity other) {
        if(uniqueId.equals(other.getUniqueId()))
            if(shohinCode.equals(other.getShohinCode()))
                if(shohinName.equals(other.getShohinName()))
                    if(editDateTime.equals(other.getEditDateTime()))
                        if(remarks.equals(other.getRemarks()))
                            return true;
        return false;
    }

    /** UniqueIdのhashCodeを返します。nullなら0が返ります。
     */
    @Override
    public int hashCode() {
        //nullでないならハッシュコード、nullなら0を返す
        return (uniqueId != null ? uniqueId.hashCode() : 0);
    }

    /** 再作成
     * @param shohinCode
     * @param shohinName
     * @param remarks
     * @return*/
    public ShohinEntity recreate(ShohinCode shohinCode, ShohinName shohinName, Remarks remarks) {
        return new ShohinEntity(shohinCode, shohinName, remarks);
    }
}