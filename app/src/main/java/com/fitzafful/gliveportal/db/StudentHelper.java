package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.List;

import io.realm.Realm;

public class StudentHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.allObjects(Student.class).clear();
                realm.delete(Student.class);
            }
        });
    }


    /*
    public static void save(@NonNull final Student data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }*/


    public static void save(@NonNull final Student data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                realm.copyToRealmOrUpdate(data);
                Log.e("realm save","exec");
            }
        });
    }

    public static void shit(Realm realm, final String id, final String dob, final String doa, final String nat){

        final Student data = getStudent(realm, id);
        Log.e("data", data.toString());
        //data.setNationality(nat);
        //data.setDoa(doa);
        //data.setPicture(pic);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //Log.e("dob", dob);
                data.setDob(dob);
                data.setDoa(doa);
                data.setNationality(nat);
                realm.copyToRealmOrUpdate(data);

                final Student data1 = getStudent(realm, id);
                Log.e("data1", data1.toString());
            }
        });

        /*realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Student data = getStudent(bgRealm, id);
                data.setDob(dob);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Original queries and Realm objects are automatically updated.
                //puppies.size(); // => 0 because there are no more puppies younger than 2 years old
                //managedDog.getAge();   // => 3 the dogs age is updated
                Log.e("shai","za");

            }
        });*/
    }

    public static void save(@NonNull final List<Student> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                realm.copyToRealmOrUpdate(dataList);
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
    public static Student getStudent(@NonNull Realm realm, String id) {
        return realm.where(Student.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static List<Student> getAllStudents(@NonNull Realm realm) {
        return realm.where(Student.class).findAll();
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
