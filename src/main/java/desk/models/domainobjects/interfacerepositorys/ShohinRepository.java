package main.java.desk.models.domainobjects.interfacerepositorys;

import java.util.List;

import main.java.desk.models.appservices.BusinessAppException;
import main.java.desk.models.domainobjects.entitys.ShohinEntity;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinCode;
import main.java.desk.models.domainobjects.shohinvalueobjects.UniqueId;

/** 商品リポジトリ・インターフェース
 */
public interface ShohinRepository {

    ShohinEntity findByUniqueId(UniqueId uniqueId);

    ShohinEntity findByShohinCode(ShohinCode shohinCode);

    List<ShohinEntity> findAll();

    void save(ShohinEntity shohin);

    void remove(ShohinEntity shohin) throws BusinessAppException;
}