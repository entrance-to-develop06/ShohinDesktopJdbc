package main.java.desk.models.repositorys;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;

import main.java.desk.models.appservices.BusinessAppException;
import main.java.desk.models.domainobjects.VoDate;
import main.java.desk.models.domainobjects.VoTime;
import main.java.desk.models.domainobjects.entitys.ShohinEntity;
import main.java.desk.models.domainobjects.interfacerepositorys.ShohinRepository;
import main.java.desk.models.domainobjects.shohinvalueobjects.EditDateTime;
import main.java.desk.models.domainobjects.shohinvalueobjects.Remarks;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinCode;
import main.java.desk.models.domainobjects.shohinvalueobjects.ShohinName;
import main.java.desk.models.domainobjects.shohinvalueobjects.UniqueId;

/** Microsoft SQL Serverリポジトリ
 */
public class ShohinRepositoryImpl implements ShohinRepository {

    private final String SHOHIN_TABLE = "shohins"; //"SHOHIN_DATA_DESK";
    private final String UNIQUE_ID = "unique_id"; //"UNIQUE_ID";
    private final String SHOHIN_CODE = "shohin_code"; //"SHOHIN_CODE";
    private final String SHOHIN_NAME = "shohin_name"; //"SHOHIN_NAME";
    private final String EDIT_DATE = "updated_on"; //"EDIT_DATE";
    private final String EDIT_TIME = "updated_at"; //"EDIT_TIME";
    private final String REMARKS = "remarks"; //"REMARKS";

    @Override
    public ShohinEntity findByUniqueId(UniqueId uniqueId) {

        try(Connection con = DriverManager.getConnection(DbConfig.ConnectionString);) {
            con.getAutoCommit();
            var sql = "select * from " + SHOHIN_TABLE + " where " + UNIQUE_ID + " = ?";
            try (SQLServerPreparedStatement ps = (SQLServerPreparedStatement)con.prepareStatement(sql);) {
                ps.setString(1, uniqueId.getValue());
                try(ResultSet rs = ps.executeQuery();) {
                    if (rs.next()) {
                        var code = rs.getInt(SHOHIN_CODE);
                        var name = rs.getString(SHOHIN_NAME);
                        var date = rs.getBigDecimal(EDIT_DATE);
                        var time = rs.getBigDecimal(EDIT_TIME);
                        var note = rs.getString(REMARKS);
                        return new ShohinEntity(uniqueId, new ShohinCode(code), new ShohinName(name),
                                new EditDateTime(new VoDate(date), new VoTime(time)), new Remarks(note));
                    }
                    else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
        }
    }

    @Override
    public ShohinEntity findByShohinCode(ShohinCode shohinCode) {

        try(Connection con = DriverManager.getConnection(DbConfig.ConnectionString);) {
            con.getAutoCommit();
            var sql = "select * from " + SHOHIN_TABLE + " where " + SHOHIN_CODE + " = ?";
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                ps.setInt(1, shohinCode.getValue());
                try (ResultSet rs = ps.executeQuery();) {
                    if (rs.next()) {
                        var id = rs.getString(UNIQUE_ID);
                        var name = rs.getString(SHOHIN_NAME);
                        var date = rs.getBigDecimal(EDIT_DATE);
                        var time = rs.getBigDecimal(EDIT_TIME);
                        var note = rs.getString(REMARKS);
                        return new ShohinEntity(new UniqueId(id), shohinCode, new ShohinName(name),
                                new EditDateTime(new VoDate(date), new VoTime(time)), new Remarks(note));
                    }
                    else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
        }
    }

    @Override
    public List<ShohinEntity> findAll() {

        try(Connection con = DriverManager.getConnection(DbConfig.ConnectionString);) {
            con.getAutoCommit();
            var sql = "select * from " + SHOHIN_TABLE;
            try (Statement ps = con.createStatement();) {
                try (ResultSet rs = ps.executeQuery(sql);) {
                    var results = new ArrayList<ShohinEntity>();
                    while(rs.next()) {
                        var id = rs.getString(UNIQUE_ID);
                        var code = rs.getInt(SHOHIN_CODE);
                        var name = rs.getString(SHOHIN_NAME);
                        var date = rs.getBigDecimal(EDIT_DATE);
                        var time = rs.getBigDecimal(EDIT_TIME);
                        var note = rs.getString(REMARKS);
                        var shohin = new ShohinEntity(new UniqueId(id), new ShohinCode(code), new ShohinName(name),
                                new EditDateTime(new VoDate(date), new VoTime(time)), new Remarks(note));
                        results.add(shohin);
                    }
                    return results;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
        }
    }

    @Override
    public void save(ShohinEntity shohin) {

        try(Connection con = DriverManager.getConnection(DbConfig.ConnectionString);) {
            con.setAutoCommit(false);
            boolean isExist;
            var sql = "select * from " + SHOHIN_TABLE + " where " + UNIQUE_ID + " = ?";
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                ps.setString(1, shohin.getUniqueId().getValue());
                try (ResultSet rs = ps.executeQuery();) {
                    isExist = rs.next();
                }
            }

            sql = isExist
                    ? "update " + SHOHIN_TABLE + " set " + SHOHIN_CODE + " = ?, " + SHOHIN_NAME + " = ?, " + EDIT_DATE + " = ?, "
                    + EDIT_TIME + " = ?, " + REMARKS + " = ? where " + UNIQUE_ID + " = ?"
                    : "insert into " + SHOHIN_TABLE + " (" + SHOHIN_CODE + ", " + SHOHIN_NAME + ", " + EDIT_DATE + ", "
                    + EDIT_TIME + ", " + REMARKS + ", " + UNIQUE_ID + ") values (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                ps.setString(6, shohin.getUniqueId().getValue());
                ps.setInt(1, shohin.getShohinCode().getValue());
                ps.setString(2, shohin.getShohinName().getValue());
                ps.setBigDecimal(3, shohin.getEditDateTime().getEditDate().getValue());
                ps.setBigDecimal(4, shohin.getEditDateTime().getEditTime().getValue());
                ps.setString(5, shohin.getRemarks().getValue());

                ps.executeUpdate();
                con.commit();
            }
            catch (SQLException e) {
                con.rollback();
                throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
            }
        } catch (SQLException e) {
            throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
        }
    }

    @Override
    public void remove(ShohinEntity shohin) {

        try(Connection con = DriverManager.getConnection(DbConfig.ConnectionString);) {
            con.setAutoCommit(false);
            var sql = "delete from " + SHOHIN_TABLE + " where " + UNIQUE_ID + " = ?";
            try (PreparedStatement ps = con.prepareStatement(sql);) {
                ps.setString(1, shohin.getUniqueId().getValue());
                ps.executeUpdate();
                con.commit ();
            }
            catch (SQLException e) {
                con.rollback();
                e.getErrorCode();
                throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
            }
        } catch (SQLException e) {
            throw new BusinessAppException("アプリケーション内部でエラーが発生しました。");
        }
    }

}