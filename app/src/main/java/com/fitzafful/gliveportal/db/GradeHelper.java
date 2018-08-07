package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class GradeHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.allObjects(Student.class).clear();
                realm.delete(Grade.class);
            }
        });
    }


    public static void save(@NonNull final Grade data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Grade> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                realm.copyToRealmOrUpdate(dataList);
                //realm.copyToRealm(dataList);
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
    public static Grade getGrade(@NonNull Realm realm, String id) {
        return realm.where(Grade.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static List<Grade> getFilteredGrades(@NonNull Realm realm, String class_, String term) {
        List<Grade> grades = realm.where(Grade.class).equalTo("year",class_).equalTo("term",term).findAll();
        Log.e("Grades",grades.toString());
        return grades;
    }

    @NonNull
    public static List<Grade> getAllGrades(@NonNull Realm realm) {
        return realm.where(Grade.class).findAll();
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
