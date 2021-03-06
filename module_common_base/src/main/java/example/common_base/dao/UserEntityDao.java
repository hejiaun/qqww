package example.common_base.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import example.common_base.entity.UserEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_ENTITY".
*/
public class UserEntityDao extends AbstractDao<UserEntity, Long> {

    public static final String TABLENAME = "USER_ENTITY";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property HeadURL = new Property(0, String.class, "headURL", false, "HEAD_URL");
        public final static Property UserID = new Property(1, Long.class, "userID", true, "_id");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
    }


    public UserEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UserEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_ENTITY\" (" + //
                "\"HEAD_URL\" TEXT," + // 0: headURL
                "\"_id\" INTEGER PRIMARY KEY UNIQUE ," + // 1: userID
                "\"NAME\" TEXT);"); // 2: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserEntity entity) {
        stmt.clearBindings();
 
        String headURL = entity.getHeadURL();
        if (headURL != null) {
            stmt.bindString(1, headURL);
        }
 
        Long userID = entity.getUserID();
        if (userID != null) {
            stmt.bindLong(2, userID);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserEntity entity) {
        stmt.clearBindings();
 
        String headURL = entity.getHeadURL();
        if (headURL != null) {
            stmt.bindString(1, headURL);
        }
 
        Long userID = entity.getUserID();
        if (userID != null) {
            stmt.bindLong(2, userID);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1);
    }    

    @Override
    public UserEntity readEntity(Cursor cursor, int offset) {
        UserEntity entity = new UserEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // headURL
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // userID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserEntity entity, int offset) {
        entity.setHeadURL(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserEntity entity, long rowId) {
        entity.setUserID(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserEntity entity) {
        if(entity != null) {
            return entity.getUserID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserEntity entity) {
        return entity.getUserID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
