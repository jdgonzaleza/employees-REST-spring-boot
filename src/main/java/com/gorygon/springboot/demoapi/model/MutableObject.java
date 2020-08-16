package com.gorygon.springboot.demoapi.model;

public interface MutableObject<T> {
	public T mergeForUpdate( T dbObject );
}
