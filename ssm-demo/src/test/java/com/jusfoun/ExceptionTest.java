package com.jusfoun;

import org.junit.Test;

import com.jusfoun.common.exception.ControllerException;
import com.jusfoun.common.exception.CoreException;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.message.result.ErrType;

public class ExceptionTest {

	@Test
	public void isAssignableFrom() {
		System.out.println(RuntimeException.class.isAssignableFrom(CoreException.class));
		System.out.println(CoreException.class.isAssignableFrom(Exception.class));
		System.out.println(ControllerException.class.isAssignableFrom(CoreException.class));
		System.out.println(ServiceException.class.isAssignableFrom(CoreException.class));
		System.out.println((new ControllerException(ErrType.ERROR)).getClass().isAssignableFrom(CoreException.class));
		System.out.println((new ControllerException(ErrType.ERROR)).getClass().isAssignableFrom(Exception.class));
		System.out.println((new ControllerException(ErrType.ERROR)).getClass().isAssignableFrom(Throwable.class));
		System.out.println(
				(new ControllerException(ErrType.ERROR)).getClass().isAssignableFrom(ControllerException.class));
		System.out.println((new CoreException(ErrType.ERROR)).getClass().isAssignableFrom(ControllerException.class));
	}

}
