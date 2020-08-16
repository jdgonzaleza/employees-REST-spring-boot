package com.gorygon.springboot.demoapi.ui.controller;

public abstract class AbstractRestController<REST, ENT> {
	protected abstract ENT toEntity( REST dto);
	protected  abstract REST toRest(ENT entity);
}
