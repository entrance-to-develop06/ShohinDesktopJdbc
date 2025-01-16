package main.java.desk.models.domainobjects.shohinvalueobjects;

import main.java.desk.models.domainobjects.DomainObjectException;
import main.java.desk.models.domainobjects.ValueObject;

/** 値オブジェクト：商品番号<br />
 *  不変、継承不可*/
public final class ShohinCode extends ValueObject<ShohinCode> {

    private final int value;

    /** 完全コンストラクタ
     * @param shohinCode*/
    public ShohinCode(int shohinCode) {
        if (shohinCode < 1 | shohinCode > 99999) {
            throw new DomainObjectException("商品番号は1～99999で指定してください");
        }
        value = shohinCode;
    }

    /** 完全コンストラクタ
     * @param shohinCode*/
    public ShohinCode(String shohinCode) {
        isNull(shohinCode);
        //emptyCheck(shohinCode);
        if (shohinCode.matches("^[0-9]{1,5}$") == false) {
            throw new DomainObjectException("商品番号が入力されていないか、1～99999の間で指定してください。");
        }
        value = Integer.parseInt(shohinCode);
    }

    /** ゲッター
     * @return*/
    public int getValue() {
        return value;
    }

    /** 等値(同一オブジェクト)比較
     @param other*/
    @Override
    protected boolean runEquals(ShohinCode other) {
        return value == other.getValue();
    }

    /** 再作成
     * @param rc
     * @return*/
    public ShohinCode recreate(int rc) {
        return new ShohinCode(rc);
    }
}