package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class NotificationHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.allObjects(Student.class).clear();
                realm.delete(Notification.class);
            }
        });
    }

    public static void save(@NonNull final Notification data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Notification> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                //realm.copyToRealmOrUpdate(dataList);
                realm.copyToRealm(dataList);
            }
        });
    }

    public static void markAsRead(@NonNull final List<String> noteIdList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (String id : noteIdList) {
                    Notification realmNote = realm.where(Notification.class).equalTo("read", id).findFirst();
                    if (realmNote != null) {
                        realmNote.setRead(true);
                    }
                }
            }
        });
    }

    public static void markAsRead(@NonNull final Notification not) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                not.setRead(true);
            }
        });
    }

    /*public static void markAsNotModified(@NonNull final List<String> StudentIdList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (String id : StudentIdList) {
                    Student realmStudent = realm.where(Student.class).equalTo("id", id).findFirst();
                    if (realmStudent != null) {
                        realmStudent.setModified(false);
                    }
                }
            }
        });
    }

    @NonNull
    public static List<Student> getAllModified(@NonNull Realm realm) {
        return realm.where(Student.class).equalTo("isModified", true).findAll();
    }*/

    @NonNull
    public static Notification getNotification(@NonNull Realm realm, int id) {
        return realm.where(Notification.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static List<Notification> getUnreadNotifications(@NonNull Realm realm) {

        return realm.where(Notification.class).equalTo("read", false).findAll();
    }

    @NonNull
    public static int getBadgeCount(@NonNull Realm realm) {

        return realm.where(Notification.class).equalTo("read",false).findAll().size();
    }

    @NonNull
    public static List<Notification> getAllNotifications(@NonNull Realm realm) {
        return realm.where(Notification.class).findAll();
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
