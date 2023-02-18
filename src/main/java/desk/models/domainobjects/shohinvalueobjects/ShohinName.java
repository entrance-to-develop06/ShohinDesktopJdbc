package main.java.desk.models.domainobjects.shohinvalueobjects;

import main.java.desk.models.domainobjects.ValueObject;

/** 値オブジェクト：商品名<br />
 *  不変、継承不可*/
public final class ShohinName extends ValueObject<ShohinName> {

    private final String value;
    private static final int MAX_BYTE_LENGTH = 50;

    /** 完全コンストラクタ
     * @param shohinName*/
    public ShohinName(String shohinName) {
        isNull(shohinName);
        isEmpty(shohinName);
        isByteOvered(shohinName, MAX_BYTE_LENGTH);
        value = shohinName;
    }

    /** ゲッター
     * @return*/
    public String getValue() {
        return value;
    }

    /** ゲッター、末尾の空白除去した値
     * @return*/
    public String getValueNotSpace() {
        return value.stripTrailing();
    }

    /** 等値(同一オブジェクト)比較
     * */
    @Override
    protected boolean runEquals(ShohinName other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public ShohinName recreate(String rc) {
        return new ShohinName(rc);
    }
}