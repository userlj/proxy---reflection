package genericCheckpointing.util;

import genericCheckpointing.server.StoreRestoreI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * proxy creator
 */
public class ProxyCreator
{

	/**
	 * method to create proxy
	 * @param interfaceArray
	 * @param handler
	 * @return storeRestoreI reference
	 */
	public StoreRestoreI createProxy(Class<?>[] interfaceArray, InvocationHandler handler){
		StoreRestoreI storeRestoreRef =
            (StoreRestoreI)
            Proxy.newProxyInstance(
                                   getClass().getClassLoader(),
                                   interfaceArray,
                                   handler
                                   );

		return storeRestoreRef;
	}
}