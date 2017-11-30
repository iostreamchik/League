package com.petaurus.league.repository.db;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class DatabaseRealm {

  private Context context;
  private RealmConfiguration realmConfiguration;

  @Inject
  public DatabaseRealm(Context context) {
	this.context = context;
  }

  public void setup() {
	if (realmConfiguration == null) {
	  Realm.init(context);
	  realmConfiguration = new RealmConfiguration.Builder()
			  .deleteRealmIfMigrationNeeded()
			  .build();
	  Realm.setDefaultConfiguration(realmConfiguration);
	} else {
	  throw new IllegalStateException("database already configured");
	}
  }

  public Realm getRealmInstance() {
	return Realm.getDefaultInstance();
  }

  public <T extends RealmObject> T add(T model) {
	Realm realm = getRealmInstance();
	try {
	  realm.beginTransaction();
	  realm.copyToRealmOrUpdate(model);
	  realm.commitTransaction();
	} finally {
	  realm.close();
	}
	return model;
  }

  public <T extends RealmObject> void addAll(List<T> models) {
	Realm realm = getRealmInstance();
	try {
	  realm.beginTransaction();
	  realm.copyToRealmOrUpdate(models);
	  realm.commitTransaction();
	} finally {
	  realm.close();
	}
  }

  public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
	Realm realm = getRealmInstance();
	List<T> res = null;
	try {
	  res = realm.copyFromRealm(realm.where(clazz).findAll());
	} finally {
	  realm.close();
	}
	return res;
  }

  public <T extends RealmObject> void deleteAll(Class<T> clazz) {
	Realm realm = getRealmInstance();
	try {
	  realm.beginTransaction();
	  realm.delete(clazz);
	  realm.commitTransaction();
	} finally {
	  realm.close();
	}
  }

  public <T extends RealmObject> void deleteAll(Class<T>... clazz) {
	Realm realm = getRealmInstance();
	try {
	  realm.beginTransaction();
	  for (Class<T> tClass : clazz) {
		realm.delete(tClass);
	  }
	  realm.commitTransaction();
	} finally {
	  realm.close();
	}
  }

  public <T extends RealmObject> T findById(Class<T> clazz, long id) {
	Realm realm = getRealmInstance();
	T res = null;
	try {
	  T tmp = realm.where(clazz).equalTo("id", id).findFirst();
	  res = tmp != null ? realm.copyFromRealm(tmp) : null;
	} finally {
	  realm.close();
	}
	return res;
  }

  public <T extends RealmObject> T findByField(Class<T> clazz, String fieldName, String value) {
	Realm realm = getRealmInstance();
	T res = null;
	try {
	  T tmp = realm.where(clazz).equalTo(fieldName, value).findFirst();
	  res = tmp != null ? realm.copyFromRealm(tmp) : null;
	} finally {
	  realm.close();
	}
	return res;
  }

  public <T extends RealmObject> List<T> findAllByField(Class<T> clazz, String fieldName, long value) {
	Realm realm = getRealmInstance();
	List<T> res = null;
	try {
	  List<T> tmp = realm.where(clazz).equalTo(fieldName, value).findAll();
	  res = tmp != null ? realm.copyFromRealm(	tmp) : null;
	} finally {
	  realm.close();
	}
	return res;
  }
}
