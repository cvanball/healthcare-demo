package com.redhat.summit.demo.datagrid.producer.cache;

import java.io.IOException;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.marshall.ProtoStreamMarshaller;
import org.infinispan.protostream.SerializationContext;
import org.infinispan.protostream.annotations.ProtoSchemaBuilder;
import org.infinispan.protostream.annotations.ProtoSchemaBuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.summit.demo.datagrid.domain.BloodPressureLevel;
import com.redhat.summit.demo.datagrid.domain.GlucoseLevel;

/**
 * Factory for {@link RemoteCacheManager RemoteCacheManagers}.
 * 
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 *
 */
public class RemoteCacheManagerFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCacheManagerFactory.class);
	
	public RemoteCacheManager getRemoteCacheFactory() {
		
		// HotRod ConfigurationBuilder.
		ConfigurationBuilder cb = new ConfigurationBuilder();
		// Make sure to register the ProtoStreamMarshaller.
		cb.addServer().host("127.0.0.1").port(11222).marshaller(new ProtoStreamMarshaller());

		RemoteCacheManager rcm = new RemoteCacheManager(cb.build());

		SerializationContext serCtx = ProtoStreamMarshaller.getSerializationContext(rcm);

		ProtoSchemaBuilder protoSchemaBuilder = new ProtoSchemaBuilder();
		try {
			String generatedSchema = protoSchemaBuilder.fileName("dv_demo.proto").packageName("com.redhat.summit.demo.datagrid.domain")
					.addClass(BloodPressureLevel.class).addClass(GlucoseLevel.class).build(serCtx);
		} catch (ProtoSchemaBuilderException psbe) {
			String message = "Error building ProtoBuf Schema.";
			LOGGER.error(message, psbe);
			throw new RuntimeException(message, psbe);
		} catch (IOException ioe) {
			String message = "I/O error while registering ProtoBufs.";
			LOGGER.error(message, ioe);
			throw new RuntimeException(message, ioe);
		}

		return rcm;
	}

}
