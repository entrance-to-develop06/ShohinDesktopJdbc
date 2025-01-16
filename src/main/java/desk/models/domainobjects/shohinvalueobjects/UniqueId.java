package main.java.desk.models.domainobjects.shohinvalueobjects;

import main.java.desk.models.domainobjects.ValueObject;

/** 値オブジェクト：ユニークID<br />
 *  不変、継承不可*/
public final class UniqueId extends ValueObject<UniqueId> {

    private final String value;
    private static final int MAX_BYTE_LENGTH = 36;

    /** 完全コンストラクタ
     * @param uniqueId : */
    public UniqueId(String uniqueId) {
        isNull(uniqueId);
        isEmpty(uniqueId);
        isByteOvered(uniqueId, MAX_BYTE_LENGTH);
        value = uniqueId;
    }

    /** ゲッター
     * @return*/
    public String getValue() {
        return value;
    }

    /** 等値(同一オブジェクト)比較
     * @param other : UniqueIdオブジェクト */
    @Override
    protected boolean runEquals(UniqueId other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public UniqueId recreate(String rc) {
        return new UniqueId(rc);
    }
}