package com.reepling.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.reepling.data.local.ReeplingDatabase;
import com.reepling.data.local.dao.UserDao;
import com.reepling.data.local.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ReeplingRepository {

    private final String TAG = ReeplingRepository.class.getSimpleName();

    private ReeplingDatabase db;

    private UserDao userDao;

    public ReeplingRepository(Context context) {
        db = ReeplingDatabase.getInstance(context);
    }

    public ReeplingRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void loadUsersInDatabase() {
        Log.e(TAG, "loadUsersInDatabase()");

        db.userDao()
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(@NonNull List<User> users) {
                        if (users.isEmpty()) {
                            Log.e(TAG, "table is empty");

                            // Create users
                            List<User> preparedList = prepareUsers();

                            db.userDao()
                                    .insertAll(preparedList)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new DisposableCompletableObserver() {
                                        @Override
                                        public void onComplete() {
                                            Log.e(TAG, "Operation complete");
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                        }
                                    });
                        } else {
                            Log.e(TAG, "Users table is not empty");

                            for (User user : users) {
                                Log.e(TAG, user.toString());
                            }

                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }


    private List<User> prepareUsers() {

        List<User> preparedUserList = new ArrayList<>();
        // Mike
        preparedUserList.add(new User(
                "Mike07",
                "Michael",
                "Lawrence",
                "0612121212",
                "mike-test@test.fr",
                "test",
                "France",
                true,
                true));

        // Sylvie
        preparedUserList.add(new User(
                "Sysy",
                "Sylvie",
                "Air France",
                "0630625145",
                "sysy-test@test.fr",
                "sysytest",
                "France",
                true,
                true));

        //User 1
        preparedUserList.add(new User(
                "KWorth",
                "Ken",
                "Worth",
                "0678451296",
                "ken-worth@test.fr",
                "kworth",
                "Angleterre",
                false,
                false));

        // User 2
        preparedUserList.add(new User(
                "Max20",
                "Max",
                "Alexander",
                "0698989898",
                "max-test@test.fr",
                "maxalexander",
                "Suisse",
                true,
                false));

        return preparedUserList;
    }

    public Single<User> getUser(String login, String password) {
        String encodedPassword = User.encodePassword(password);

        return db.userDao()
                .getUser(login, encodedPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
