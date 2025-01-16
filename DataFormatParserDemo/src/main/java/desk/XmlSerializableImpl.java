package main.java.desk;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * XmlSerializerクラスでXMLファイルをシリアライズ、デシリアライズするクラス
 * @param <XmlClass>
 */
public class XmlSerializableImpl<XmlClass> implements DataFormatSerialize<XmlClass> {
    private XmlClass xmlIns; //= new;

    /**
     * XMLファイルへシリアル化し標準的な内容で書き込みます
     * @param dto XMLファイルとマップするData Transfer Objectクラス
     * @throws FileNotFoundException 例外を呼び出し側でキャッチできるように再スローしています
     */
    @Override
    public void Serialize(XmlClass dto) throws FileNotFoundException {
        xmlIns = dto;
        try (var encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("")))){
            encoder.writeObject(xmlIns);
        } catch (FileNotFoundException ex) {
            //ログ
            throw ex;
        }
    }

    /**
     * XMLファイルからXML形式で逆シリアル化しData Transfer Objectクラスへセットします
     * @return XMLファイルとマップするData Transfer Objectクラス
     * @throws FileNotFoundException 例外を呼び出し側でキャッチできるように再スローしています
     */
    @Override
    public XmlClass Deserialize() throws FileNotFoundException {
        try (var decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("ファイルパス")));) {
            xmlIns = (XmlClass) decoder.readObject();
        } catch (FileNotFoundException ex) {
            //ログ
            throw ex;
        }

        return xmlIns;
    }
}