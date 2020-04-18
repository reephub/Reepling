package com.reepling.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.reepling.data.local.model.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    Completable insertAll(List<User> users);

    @Query("SELECT * FROM users")
    Single<List<User>> getAll();

    @Query("SELECT * FROM users WHERE login LIKE :login AND password LIKE :password")
    Single<User> getUser(String login, String password);

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    Single<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Single<User> findByName(String first, String last);

    @Delete
    void delete(User user);

    @Query("DELETE FROM users")
    void deleteAll();
}
