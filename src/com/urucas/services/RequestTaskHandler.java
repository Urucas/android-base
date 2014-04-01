package com.urucas.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

public interface RequestTaskHandler {

	public void onSuccess(String result);
	public void onError(String message);
}
