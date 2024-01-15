package main.java.desk.models.domainobjects.shohinvalueobjects;

import main.java.desk.models.domainobjects.ValueObject;

/** 値オブジェクト：備考<br />
 *  不変、継承不可*/
public final class Remarks extends ValueObject<Remarks> {

    private final String value;
    private static final int MAX_BYTE_LENGTH = 255;

    /** 完全コンストラクタ
     * @param remarks*/
    public Remarks(String remarks) {
        isNull(remarks);
        isEmpty(remarks);
        isByteOvered(remarks, MAX_BYTE_LENGTH);
        value = remarks;
    }

    /** ゲッター
     * @return*/
    public String getValue() {
        return value;
    }

    /** 等値(同一オブジェクト)比較
     */
    @Override
    protected boolean runEquals(Remarks other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public Remarks recreate(String rc) {
        return new Remarks(rc);
    }
}