package main.java.desk;

import java.io.FileNotFoundException;

/// XML、JSONシリアライズ・インターフェース
public interface DataFormatSerialize<Class> {
    void Serialize(Class dto) throws FileNotFoundException;
    Class Deserialize() throws FileNotFoundException;
}