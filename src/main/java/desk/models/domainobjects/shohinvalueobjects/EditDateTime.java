package main.java.desk.models.domainobjects.shohinvalueobjects;

import main.java.desk.models.domainobjects.VoDate;
import main.java.desk.models.domainobjects.VoTime;

/** 値オブジェクト：編集日付、編集時刻<br />
 *　  不変、継承不可*/
public final class EditDateTime {

    private final VoDate date;
    private final VoTime time;

    public EditDateTime(VoDate date, VoTime time) {
        this.date = date;
        this.time = time;
    }

    public VoDate getEditDate() {
        return date;
    }

    public VoTime getEditTime() {
        return time;
    }
}