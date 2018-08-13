package com.fitzafful.gliveportal.db;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class BookHelper {

    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //realm.allObjects(Book.class).clear();
                realm.delete(Book.class);
            }
        });
    }

    public static void save(@NonNull final Book data) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(data);
            }
        });
    }

    public static void save(@NonNull final List<Book> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec");
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }

    /*public static void markAsNotModified(@NonNull final List<String> BookIdList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (String id : BookIdList) {
                    Book realmBook = realm.where(Book.class).equalTo("id", id).findFirst();
                    if (realmBook != null) {
                        realmBook.setModified(false);
                    }
                }
            }
        });
    }

    @NonNull
    public static List<Book> getAllModified(@NonNull Realm realm) {
        return realm.where(Book.class).equalTo("isModified", true).findAll();
    }*/

    @NonNull
    public static Book getBook(@NonNull Realm realm, String id) {
        return realm.where(Book.class).equalTo("id",id).findAll().first();
    }

    @NonNull
    public static void reserveBook(@NonNull Realm realm, final Book book) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec1");
                book.setReserved(true);
                book.setMeReserved(true);
                realm.copyToRealmOrUpdate(book);
            }
        });
    }

    @NonNull
    public static List<Book> getMeReserved(@NonNull Realm realm) {
        return realm.where(Book.class).equalTo("me_reserved",true).findAll();
    }

    @NonNull
    public static List<Book> getAllBooks(@NonNull Realm realm) {
        return realm.where(Book.class).findAll();
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

    public static void returnBook(Realm realm,final Book book) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("realm save","exec1");
                book.setReserved(false);
                book.setMeReserved(false);
                realm.copyToRealmOrUpdate(book);
            }
        });
    }
}
