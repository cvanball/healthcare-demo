package com.redhat.datagrid.producer.rdbms;

import java.util.Map;

import com.redhat.datagrid.domain.BPGLevel;
import com.redhat.datagrid.domain.BloodPressureLevel;
import com.redhat.datagrid.domain.GlucoseLevel;
import com.redhat.datagrid.producer.rdbms.cache.RemoteCacheManagerFactory;
import com.redhat.datagrid.producer.rdbms.repository.LevelRepository;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.datagrid.producer.rdbms.connection.PostgreSQLConnectionFactory;
import com.redhat.datagrid.producer.rdbms.repository.BloodPressureLevelRepository;
import com.redhat.datagrid.producer.rdbms.repository.GlucoseLevelRepository;

/**
 * Loads {@link BloodPressureLevel} and {@link GlucoseLevel} data from a <code>PostgreSQL</code> database and stores the data in
 * <code>ProtoBuf</code> format in <code>JBoss Data Grid</code>.
 *
 */
public class CacheDataProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheDataProducer.class);

	private static final String JDG_CACHE_NAME_BLOOD_PRESSURE_LEVEL;

	private static final String JDG_CACHE_NAME_GLUCOSE_LEVEL;

    private static final String JDG_CACHE_NAME_BPG_LEVEL;

	private static final PostgreSQLConnectionFactory dbConnectionFactory = new PostgreSQLConnectionFactory();

	private static RemoteCacheManager rcm = null;

	private static final RemoteCacheManagerFactory rcmFactory = new RemoteCacheManagerFactory();

	private static final BloodPressureLevelRepository bplRepo = new BloodPressureLevelRepository();

	private static final GlucoseLevelRepository glRepo = new GlucoseLevelRepository();

    private static final LevelRepository bpglRepo = new LevelRepository();

	// Read system properties.
	static {
        JDG_CACHE_NAME_BLOOD_PRESSURE_LEVEL=System.getProperty("jdg.cache.name.bloodpressurelevel");
        JDG_CACHE_NAME_GLUCOSE_LEVEL=System.getProperty("jdg.cache.name.glucoselevel");
        JDG_CACHE_NAME_BPG_LEVEL=System.getProperty("jdg.cache.name.bpglevel");
	}
	

	public static void main(String[] args) {
		LOGGER.info("Loading data from database and storing in JBoss Data Grid.");

		Map<String, BloodPressureLevel> bpls = bplRepo.getBloodPressureLevel();
		LOGGER.info("Found '" + bpls.size() + "' Blood Pressure Levels.");
		LOGGER.info("Pushing data to Infinspan.");
		putInIspn(JDG_CACHE_NAME_BLOOD_PRESSURE_LEVEL, bpls);

		Map<String, GlucoseLevel> gls = glRepo.getGlucoseLevel();
		LOGGER.info("Found '" + gls.size() + "' Glucose Levels.");
		LOGGER.info("Pushing data to Infinspan.");
		putInIspn(JDG_CACHE_NAME_GLUCOSE_LEVEL, gls);

        Map<String, BPGLevel> bpgls = bpglRepo.getBPGLevel();
        LOGGER.info("Found '" + bpgls.size() + "' BPG Levels.");
        LOGGER.info("Pushing data to Infinspan.");
        putInIspn(JDG_CACHE_NAME_BPG_LEVEL, bpgls);

		closeAndCleanUp();

		LOGGER.info("Data stored in JBoss Data Grid.");
	}

	/**
	 * Puts the data in the given {@link Map} in <code>JBoss Data Grid</code>
	 * 
	 * 
	 * @param cacheName
	 * 
	 * @param keyValues
	 */
	private static <T> void putInIspn(String cacheName, Map<String, T> keyValues) {
		RemoteCacheManager remoteCacheManager = getRemoteCacheManager();
		BasicCache<String, T> cache = remoteCacheManager.getCache(cacheName);

		cache.putAll(keyValues);
	}

	/**
	 * Get the {@link RemoteCacheManager}.
	 * 
	 * @return the {@link RemoteCacheManager}
	 */
	private static synchronized RemoteCacheManager getRemoteCacheManager() {
		if (CacheDataProducer.rcm == null) {
			CacheDataProducer.rcm = rcmFactory.getRemoteCacheFactory();
		}
		return CacheDataProducer.rcm;
	}

	/**
	 * Close and cleanup all resources.
	 */
	private static void closeAndCleanUp() {
		if (CacheDataProducer.rcm != null) {
			CacheDataProducer.rcm.stop();
		}
	}

}
