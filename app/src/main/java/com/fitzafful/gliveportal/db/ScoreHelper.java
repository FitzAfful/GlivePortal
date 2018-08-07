package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class ScoreHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.allObjects(Score.class).clear();
                realm.delete(Score.class);
            }
        });
    }

    public static void save(@NonNull final Score data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Score> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }

    /*public static void markAsNotModified(@NonNull final List<String> ScoreIdList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (String id : ScoreIdList) {
                    Score realmScore = realm.where(Score.class).equalTo("id", id).findFirst();
                    if (realmScore != null) {
                        realmScore.setModified(false);
                    }
                }
            }
        });
    }

    @NonNull
    public static List<Score> getAllModified(@NonNull Realm realm) {
        return realm.where(Score.class).equalTo("isModified", true).findAll();
    }*/

    @NonNull
    public static Score getScore(@NonNull Realm realm, String id) {
        return realm.where(Score.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static List<Score> getAllScores(@NonNull Realm realm) {
        return realm.where(Score.class).findAll();
    }

    private static void executeTransaction(@NonNull Realm.Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } catch (Throwable e) {
            Log.e("executeTransaction", e.getLocalizedMessage());
        } finally {
            close(realm);
        }
    }

    private static void close(@Nullable Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }
}
