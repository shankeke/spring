package com.jusfoun.test;

import org.junit.Test;

import com.jusfoun.common.message.exception.ControllerException;
import com.jusfoun.common.message.exception.CoreException;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.message.result.ErrType;

public class ExceptionTest {

	@Test
	public void isAssignableFrom() {
		System.out.println(RuntimeException.class.isAssignableFrom(CoreException.class));
		System.out.println(CoreException.class.isAssignableFrom(Exception.class));
		System.out.println(ControllerException.class.isAssignableFrom(CoreException.class));
		System.out.println(ServiceException.class.isAssignableFrom(CoreException.class));
		System.out.println((new ControllerException(ErrType.INTERNAL_SERVER_ERROR)).getClass().isAssignableFrom(CoreException.class));
		System.out.println((new ControllerException(ErrType.INTERNAL_SERVER_ERROR)).getClass().isAssignableFrom(Exception.class));
		System.out.println((new ControllerException(ErrType.INTERNAL_SERVER_ERROR)).getClass().isAssignableFrom(Throwable.class));
		System.out.println((new ControllerException(ErrType.INTERNAL_SERVER_ERROR)).getClass().isAssignableFrom(ControllerException.class));
		System.out.println((new CoreException(ErrType.INTERNAL_SERVER_ERROR)).getClass().isAssignableFrom(ControllerException.class));
	}

}
