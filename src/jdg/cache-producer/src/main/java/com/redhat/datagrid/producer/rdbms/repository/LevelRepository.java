package com.redhat.datagrid.producer.rdbms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.redhat.datagrid.domain.BPGLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.datagrid.producer.rdbms.connection.PostgreSQLConnectionFactory;

/**
 * Repository for {@link BPGLevel Levels}.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class LevelRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BPGLevel.class);

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private static final PostgreSQLConnectionFactory dbConnectionFactory = new PostgreSQLConnectionFactory();

	/**
	 * Retrieves the {@link BPGLevel} from the database.
	 *
	 * @return a {@link Map} of {@link BPGLevel Levels}.
	 */
	public Map<String, BPGLevel> getBPGLevel() {

		Map<String, BPGLevel> bpgls = new HashMap<>();

		String selectBpl = "SELECT * FROM \"LEVELS\"";

		try (Connection connection = dbConnectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(selectBpl);
			try (ResultSet rs = statement.executeQuery()) {

				while (rs.next()) {
					BPGLevel bpgl = new BPGLevel();

					int id = rs.getInt("id");
					bpgl.setId(id);

					bpgl.setPatientId(rs.getInt("patientId"));
					Date dateRead = rs.getDate("dateRead");
					bpgl.setDateRead(df.format(dateRead));
					bpgl.setMam(rs.getString("mam"));
					bpgl.setSys(rs.getInt("sys"));
					bpgl.setDia(rs.getInt("dia"));
					bpgl.setPulse(rs.getInt("pulse"));
					bpgl.setMap(rs.getInt("map"));

					bpgl.setGlucoseType(rs.getString("glucoseType"));
					bpgl.setGlucoseValue(rs.getInt("glucoseValue"));
					bpgl.setGlucoseLabel(rs.getString("glucoseLabel"));
					bpgl.setNote(rs.getString("note"));

					bpgls.put(Integer.toString(id), bpgl);
				}
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			String message = "Error while fetching Level data.";
			LOGGER.error(message, sqle);
			throw new RuntimeException(message, sqle);
		}

		return bpgls;
	}
}
