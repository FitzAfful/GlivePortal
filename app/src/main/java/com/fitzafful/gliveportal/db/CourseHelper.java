package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class CourseHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Course.class);
            }
        });
    }

    public static void save(@NonNull final Course data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Course> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }

    @NonNull
    public static Course getCourse(@NonNull Realm realm, String id) {
        return realm.where(Course.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static void registerCourse(@NonNull Realm realm, final Course course) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec1");
                course.setRegistered(true);
                realm.copyToRealmOrUpdate(course);
            }
        });
    }

    @NonNull
    public static List<Course> getCores(@NonNull Realm realm, boolean id) {
        return realm.where(Course.class).equalTo("core",id).findAll();
    }

    @NonNull
    public static List<Course> getAllCourses(@NonNull Realm realm) {
        return realm.where(Course.class).findAll();
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

    public static List<Course> getRegistered(Realm realm) {
        return realm.where(Course.class).equalTo("registered",true).findAll();
    }
}
