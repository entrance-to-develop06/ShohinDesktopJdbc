package main.java.desk.models.domainobjects;

import java.io.UnsupportedEncodingException;
public abstract class ValueObject<VO> extends Object {

    protected abstract boolean runEquals(VO other);

    @Override
    public boolean equals(Object obj) {
        var vo = (VO)obj;
        if (vo.equals(null)) {
            return false;
        }

        return runEquals(vo);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    protected void isNull(Object value) {
        if(value.equals(null))
            throw new DomainObjectException("nullです。");
    }

    protected void isEmpty(String value) {
        if (value.equals("")) {
            throw new DomainObjectException("商品が選択されていませんのでIDを取得できませんでした。");
        }
    }

    protected void isByteOvered(String value, int maxByteLength) {

        int count = 0;
        try {
            count = value.getBytes("Shift_JIS").length; //Utf8 oracle, Sqlserver Shift_JIS
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (count > maxByteLength)
        {
            throw new DomainObjectException(maxByteLength + "バイトを超えた文字列が代入されました。");
        }
    }
}