package be.kdg.sa.velo.messaging.receivers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

import javax.el.MethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class Receiver<BaseType> {
	
	private final ObjectMapper objectMapper;
	private final Logger logger;
	protected Receiver (ObjectMapper objectMapper, Logger logger) {
		this.objectMapper = objectMapper;
		this.logger = logger;
	}
	
	public void receive (String message) {
		try {
			BaseType object = deserialize (message);
			process (object);
		} catch (JacksonException e) {
			logger.error ("Could not parse " + message, e);
		}
	}
	
	@SuppressWarnings ("unchecked")
	private BaseType deserialize (String message) throws JacksonException {
		var baseType = createInstanceOfBaseType ();
		return (BaseType) objectMapper.readValue (message, baseType.getClass ());
	}
	
	abstract void process (BaseType messageObject);
	
	@SuppressWarnings ("unchecked")
	private BaseType createInstanceOfBaseType () {
		// Create a new instance of the generic type BaseType
		Type type = ((ParameterizedType)this.getClass ().getGenericSuperclass ()).getActualTypeArguments ()[0];
		try {
			return ((Class<BaseType>) type).getConstructor ().newInstance ();
		} catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new MethodNotFoundException ("Could not create instance of type " + type.getTypeName () + ". Does it have a default constructor?", e);
		}
	}
}
