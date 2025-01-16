package main.java.desk.models.appservices;

import java.util.ArrayList;
import java.util.List;

import main.java.desk.models.appservices.dtos.ShohinDto;
import main.java.desk.models.domainobjects.domainservices.ShohinDomainService;
import main.java.desk.models.domainobjects.entitys.ShohinEntity;
import main.java.desk.models.domainobjects.interfacerepositorys.ShohinRepository;
import main.java.desk.models.domainobjects.shohinvalueobjects.Remarks;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinCode;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinName;
import main.java.desk.models.domainobjects.shohinvalueobjects.UniqueId;

public class ShohinAppService {

    private final ShohinRepository repository;
    private final ShohinDomainService domainService;

    public ShohinAppService(ShohinRepository shohinRepository)
    {
        repository = shohinRepository;
        domainService = new ShohinDomainService(repository);
    }

    public void registerShohin(String shohinCode, String shohinName, String remarks)
    {//同じ商品名を登録したらExceptionでなくそれに到達するまでに別の例外が起きる
        var shohin = new ShohinEntity(
                new ShohinCode(shohinCode),
                new ShohinName(shohinName),
                new Remarks(remarks)
        );
        if (domainService.isRegistered(shohin))
        {
            throw new BusinessAppException("商品番号：" + shohinCode + "はすでに登録されております。");
        }
        else
        {
            repository.save(shohin);
        }
    }

    public void editShohin(String uniqueId, String shohinCode, String shohinName, String remarks)
    {
        var id = new UniqueId(uniqueId);
        var shohin = repository.findByUniqueId(id);
        if (shohin == null)
        {
            throw new BusinessAppException("商品に対するIDを見つけれませんでした。ID:" + uniqueId);
        }
        var code = new ShohinCode(shohinCode);
        shohin.setShohinCode(code);
        var name = new ShohinName(shohinName);
        shohin.setShohinName(name);
        shohin.setDateTime();
        var note = new Remarks(remarks);
        shohin.setRemarks(note);
        repository.save(shohin);
    }

    public void removeShohin(String uniqueId)
    {
        var id = new UniqueId(uniqueId);
        var shohin = repository.findByUniqueId(id);
        if (shohin == null)
        {
            throw new BusinessAppException("商品に対するIDを見つけれませんでした。ID:" + uniqueId);
        }
        repository.remove(shohin);
    }

    //ユーザー情報取得
    public ShohinDto getShohinInfo(String uniqueId)
    {
        var id = new UniqueId(uniqueId);
        var shohin = repository.findByUniqueId(id);
        if (shohin == null)
        {
            throw new BusinessAppException("商品に対するIDを見つけれませんでした。ID:" + uniqueId);
        }

        return new ShohinDto(shohin);
    }

    public List<ShohinDto> getAllShohinList() {
        var shohins = repository.findAll();
        List<ShohinDto> dtos = new ArrayList<>();
        for(var shohin : shohins) {
            var dto = new ShohinDto(shohin);
            dtos.add(dto);
        }

        return dtos;
    }
}