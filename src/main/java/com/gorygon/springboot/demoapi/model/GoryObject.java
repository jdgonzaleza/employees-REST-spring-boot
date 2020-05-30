package com.gorygon.springboot.demoapi.model;

public interface GoryObject<T> {
	public T mergeForUpdate( T dbObject );
}
