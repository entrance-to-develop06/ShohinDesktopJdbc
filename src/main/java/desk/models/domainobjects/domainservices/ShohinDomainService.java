package main.java.desk.models.domainobjects.domainservices;

import main.java.desk.models.domainobjects.entitys.ShohinEntity;
import main.java.desk.models.domainobjects.interfacerepositorys.ShohinRepository;

/** ドメインサービス
 */
public class ShohinDomainService {

    private final ShohinRepository repository;

    public ShohinDomainService(ShohinRepository shohinRepository) {
        repository = shohinRepository;
    }

    /** 商品番号の登録チェック
     * @param shohin
     * @return*/
    public boolean isRegistered(ShohinEntity shohin) {
        var code = shohin.getShohinCode();

        //データベースに問い合わせてすでに同じ商品番号が登録されているかチェックする
        var serched = repository.findByShohinCode(code);

        return serched != null;
    }
}