package com.redhat.summit.demo.datagrid.producer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.summit.demo.datagrid.domain.BPGLevel;
import com.redhat.summit.demo.datagrid.producer.db.DbConnectionFactory;

/**
 * Repository for {@link Level Levels}.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public class LevelRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BPGLevel.class);

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	private static final DbConnectionFactory dbConnectionFactory = new DbConnectionFactory();

	/**
	 * Retrieves the {@link Level} from the database.
	 * 
	 * @param connection
	 *            the {@link Connection} to use.
	 * @return a {@link Map} of {@link Level Levels}.
	 */
	public Map<String, BPGLevel> getBPGLevel() {

		Map<String, BPGLevel> gls = new HashMap<>();

		String selectBpl = "SELECT * FROM \"LEVELS\"";

		try (Connection connection = dbConnectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(selectBpl);
			try (ResultSet rs = statement.executeQuery()) {

				while (rs.next()) {
					BPGLevel gl = new BPGLevel();

					int id = rs.getInt("id");
					gl.setId(id);

					gl.setPatientId(rs.getInt("patientId"));
					Date dateRead = rs.getDate("dateRead");
					gl.setDateRead(df.format(dateRead));
					gl.setMam(rs.getString("mam"));
					gl.setSys(rs.getInt("sys"));
					gl.setDia(rs.getInt("dia"));
					gl.setPulse(rs.getInt("pulse"));
					gl.setMap(rs.getInt("map"));
					
					gl.setGlucoseType(rs.getString("glucoseType"));
					gl.setGlucoseValue(rs.getInt("glucoseValue"));
					gl.setGlucoseLabel(rs.getString("glucoseLabel"));
					gl.setNote(rs.getString("note"));

					gls.put(Integer.toString(id), gl);
				}
			}

		} catch (SQLException sqle) {
			// TODO Auto-generated catch block
			String message = "Error while fetching Level data.";
			LOGGER.error(message, sqle);
			throw new RuntimeException(message, sqle);
		}

		return gls;
	}
}
